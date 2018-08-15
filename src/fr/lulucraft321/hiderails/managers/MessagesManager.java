/**
 * Copyright Java Code
 * All right reserved.
 *
 */

package fr.lulucraft321.hiderails.managers;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import fr.lulucraft321.hiderails.HideRails;
import fr.lulucraft321.hiderails.enums.Messages;
import fr.lulucraft321.hiderails.enums.Version;

public class MessagesManager
{
	public static final String PREFIX = "�8[�6Hide�7Rails�8] ";
	private static final String MSG_PATH;

	static {
		MSG_PATH = FileConfigurationManager.msgPath;
	}

	public static void loadAllMessages()
	{
		Messages.SENDER_TYPE_ERROR.setMessage(FileConfigurationManager.getLangConfig().getString(MSG_PATH + "sender_type_error"));
		Messages.PLAYER_NO_ENOUGH_PERMISSION.setMessage(FileConfigurationManager.getLangConfig().getString(MSG_PATH + "player_no_enough_permission"));
		Messages.MATERIAL_TYPE_ERROR.setMessage(FileConfigurationManager.getLangConfig().getString(MSG_PATH + "material_type_error"));
		Messages.SUCCESS_CHANGE_RAIL.setMessage(FileConfigurationManager.getLangConfig().getString(MSG_PATH + "rail_success_change"));
		Messages.SUCCESS_BREAK_RAIL.setMessage(FileConfigurationManager.getLangConfig().getString(MSG_PATH + "rail_success_break"));
		Messages.SUCCESS_UNHIDE_RAIL.setMessage(FileConfigurationManager.getLangConfig().getString(MSG_PATH + "rail_success_unhide"));
		Messages.RAIL_ERROR.setMessage(FileConfigurationManager.getLangConfig().getString(MSG_PATH + "rail_error"));
		Messages.INVALID_WORLDNAME.setMessage(FileConfigurationManager.getLangConfig().getString(MSG_PATH + "invalid_worldname"));
		Messages.WATER_PROTECTION_STATUS_ALREADY.setMessage(FileConfigurationManager.getLangConfig().getString(MSG_PATH + "water_protection_status_already"));
		Messages.SUCCESS_CHANGE_WATER_PROTECTION_STATUS.setMessage(FileConfigurationManager.getLangConfig().getString(MSG_PATH + "water_protection_status_success_change"));
		Messages.SUCCESS_RELOAD.setMessage(FileConfigurationManager.getLangConfig().getString(MSG_PATH + "plugin_success_reloaded"));
		Messages.NO_BACKUP.setMessage(FileConfigurationManager.getLangConfig().getString(MSG_PATH + "no_backup"));
		Messages.RETURN_BACKUP_SUCCESS.setMessage(FileConfigurationManager.getLangConfig().getString(MSG_PATH + "return_backup_success"));
		Messages.WORLDEDIT_NOT_INSTALLED.setMessage(FileConfigurationManager.getLangConfig().getString(MSG_PATH + "worldedit_not_installed"));
		Messages.WORLDEDIT_NO_SELECTION.setMessage(FileConfigurationManager.getLangConfig().getString(MSG_PATH + "worldedit_no_selection"));
		Messages.DISPLAY_HIDDEN_BLOCKS.setMessage(FileConfigurationManager.getLangConfig().getString(MSG_PATH + "display_hidden_blocks"));
		Messages.INVALID_PLAYER.setMessage(FileConfigurationManager.getLangConfig().getString(MSG_PATH + "invalid_player"));
		Messages.UPDATE_FOUND.setMessage(FileConfigurationManager.getLangConfig().getString(MSG_PATH + "update_found"));
		Messages.KICK_SPAM_BLOCK.setMessage(FileConfigurationManager.getLangConfig().getString(MSG_PATH + "kick_spam_hidden_block"));
	}

	public static void sendHelpPluginMessage(CommandSender sender)
	{
		String required = "�c�o > Need Worldedit";
		String weColor1 = "�c";
		String weColor2 = "�6�m";
		if(HideRails.getInstance().getWorldEdit() != null) {
			if (HideRails.version == Version.v1_12) {
				required = "";
				weColor1 = "�f";
				weColor2 = "�6";
			} else {
				required = "�c�o > Incompatible with 1.13";
			}
		}
		sender.sendMessage("\n" + "�8�l�m---------------�8�l[�6Hide�7Rails�8�l]�8�l�m---------------");
		sender.sendMessage("\n" + "�f�l � �6/hiderails reload");
		sender.sendMessage("�f�l � �6/hiderails help");
		sender.sendMessage("\n" + "�f�l � �6/hiderails hideone \"�oblock:data�6\"");
		sender.sendMessage("�f�l � �6/hiderails hide \"�oblock:data�6\"");
		sender.sendMessage("�f�l � �6/hiderails unhideone");
		sender.sendMessage("�f�l � �6/hiderails unhide");
		sender.sendMessage(weColor1 + "�l � " + weColor2 + "/hiderails hideselection \"block:data\"" + required);
		sender.sendMessage(weColor1 + "�l � " + weColor2 + "/hiderails unhideselection" + required);
		sender.sendMessage("\n" + "�f�l � �6/hiderails return");
		sender.sendMessage("�f�l � �6/hiderails display");
		sender.sendMessage("�f�l � �6/hiderails waterprotection \"�oworld�6\" \"�ovalue�6\"");
		sender.sendMessage("�7�l � �6�o/hiderails display [\"player\"] �8(Command Block only)");
		sender.sendMessage("\n" + "�8�l�m-------------------------------------\n�r�o Plugin by lulucraft321");
	}

	public static void sendPluginMessage(CommandSender sender, Messages messageType)
	{
		sender.sendMessage(getColoredMessage(messageType));
	}

	public static void sendRailChangeMessage(CommandSender sender, Messages messageType, String block)
	{
		sender.sendMessage(getColoredMessage(messageType, block));
	}

	public static void sendAlreadyStatusMessage(CommandSender sender, Messages messageType, String worldName, boolean b)
	{
		sendChangeStatusMessage(sender, messageType, worldName, b);
	}

	public static void sendChangeStatusMessage(CommandSender sender, Messages messageType, String worldName, boolean b)
	{
		sender.sendMessage(getColoredMessage(messageType, worldName, b));
	}

	public static void sendDisplayChangeMessage(CommandSender sender, Messages messageType, boolean b)
	{
		sender.sendMessage(getColoredMessage(messageType, b));
	}


	public static String getColoredMessage(Messages messageType) {
		return PREFIX + ChatColor.translateAlternateColorCodes('&', messageType.getMessage())
		.replace("\\n", "\n")
		.replace("%link%", "https://www.spigotmc.org/resources/55158/")
		.replace("%pluginlink%", "https://www.spigotmc.org/resources/55158/")
		.replace("%plugin_link%", "https://www.spigotmc.org/resources/55158/");
	}

	private static String getColoredMessage(Messages messageType, String block) {
		return getColoredMessage(messageType).replace("%blocktype%", block);
	}

	private static String getColoredMessage(Messages messageType, String worldName, boolean b) {
		return getColoredMessage(messageType).replace("%world%", worldName).replace("%status%", String.valueOf(b));
	}

	private static String getColoredMessage(Messages messageType, boolean b) {
		return getColoredMessage(messageType).replace("%hide%", String.valueOf(b));
	}
}
