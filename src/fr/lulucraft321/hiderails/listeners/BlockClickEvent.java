/**
 * Copyright Java Code
 * All right reserved.
 *
 * @author lulucraft321
 */

package fr.lulucraft321.hiderails.listeners;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;

import fr.lulucraft321.hiderails.HideRails;
import fr.lulucraft321.hiderails.enums.Messages;
import fr.lulucraft321.hiderails.managers.HideRailsManager;
import fr.lulucraft321.hiderails.managers.MessagesManager;
import fr.lulucraft321.hiderails.managers.SpamPlayerDataManager;
import fr.lulucraft321.hiderails.reflection.BukkitNMS;
import fr.lulucraft321.hiderails.utils.abstractclass.AbstractEvent;
import fr.lulucraft321.hiderails.utils.checkers.BlocksChecker;
import fr.lulucraft321.hiderails.utils.data.railsdata.HiddenRail;

public class BlockClickEvent extends AbstractEvent
{
	/*
	 * Refresh hidden signs after clicking
	 */
	@EventHandler
	public void onInteract(PlayerInteractEvent e)
	{
		final Block block = e.getClickedBlock();
		if (block == null) return;
		final Player p = e.getPlayer();
		if (HideRailsManager.isInPlayerWhoDisplayedBlocks(p)) return;

		if (p.isOp() || p.hasPermission("hiderails.admin")) {
			// If player click in hiddenSign
			if (BlocksChecker.isSign(block)) {
				final Sign s = (Sign) Bukkit.getServer().getWorld(block.getWorld().getName()).getBlockAt(block.getLocation()).getState();
				if (HideRailsManager.getHiddenRail(s.getLocation()) != null) {
					s.setLine(0, s.getLine(0));
					s.setLine(1, s.getLine(1));
					s.setLine(2, s.getLine(2));
					s.setLine(3, s.getLine(3));
					s.update(true);
				}
			} else {
				// If player click in block around hiddenSign
				Block checkedBlock = BlocksChecker.getBlockFaceHiddenSign(block);
				if (checkedBlock != null) {
					if (BlocksChecker.isSign(checkedBlock)) {
						if (HideRailsManager.getHiddenRail(checkedBlock.getLocation()) != null) {
							final Sign s = (Sign) Bukkit.getServer().getWorld(checkedBlock.getWorld().getName()).getBlockAt(checkedBlock.getLocation()).getState();
							s.setLine(0, s.getLine(0));
							s.setLine(1, s.getLine(1));
							s.setLine(2, s.getLine(2));
							s.setLine(3, s.getLine(3));
							s.update(true);
						}
					}
				}
			}
		}


		// Renvoie des packets si le joueur n'est pas OP et qu'il clique sur un block masque
		List<Block> checked = BlocksChecker.getBlockFaceHiddenBlocks(block);
		if (checked != null) {
			if (!checked.isEmpty()) {
				for (Block checkedB : checked) {
					if (BlocksChecker.checkBlockIfActive(checkedB)) {
						HiddenRail hRail = HideRailsManager.getHiddenRail(checkedB.getLocation());

						if (hRail != null) {
							Location loc = hRail.getLocation();

							if (!p.isOp() && !p.hasPermission("hiderails.admin")) {
								if (SpamPlayerDataManager.getSpamNumber(p) >= HideRailsManager.max_spam_nbr) {
									SpamPlayerDataManager.delPlayer(p);
									if (HideRailsManager.spam_kick) {
										p.kickPlayer(MessagesManager.getColoredMessage(Messages.KICK_SPAM_BLOCK));
										return;
									}
								}
								e.setUseInteractedBlock(PlayerInteractEvent.Result.DENY);
								e.setUseItemInHand(PlayerInteractEvent.Result.DENY);

								// Si le joueur n'a pas de task de suppresion en cours
								if (SpamPlayerDataManager.getPendingTask(p) == null)
									SpamPlayerDataManager.setPendingTask(p, Bukkit.getServer().getScheduler().runTaskLater(HideRails.getInstance(), () -> SpamPlayerDataManager.delPlayer(p), 50L));

								// Re-send block change packet
								SpamPlayerDataManager.addPlayerSpamTask(p, Bukkit.getServer().getScheduler().runTaskLater(HideRails.getInstance(), () -> {
									BukkitNMS.changeBlock(p, hRail.getMaterial(), hRail.getData(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
								}, 1L));
							} else {
								if (!BreakBlockEvent.trashList.contains(checkedB)) {
									Bukkit.getServer().getScheduler().runTaskLater(HideRails.getInstance(), () -> {
										if (!BreakBlockEvent.breakBlocks.contains(p)) {
											BukkitNMS.changeBlock(p, hRail.getMaterial(), hRail.getData(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
										} else {
											Bukkit.getServer().getScheduler().runTaskLater(HideRails.getInstance(), () -> BreakBlockEvent.breakBlocks.remove(p), 20L);
										}
									}, 20L);
								}
							}
						}
					}
				}
			}
		}
	}
}
