/**
 * Copyright Java Code
 * All right reserved.
 *
 * @author lulucraft321
 */

package fr.lulucraft321.hiderails;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;

import fr.lulucraft321.hiderails.commands.CommandsHandle;
import fr.lulucraft321.hiderails.commands.TabComplete;
import fr.lulucraft321.hiderails.enums.Version;
import fr.lulucraft321.hiderails.external.metrics.Metrics;
import fr.lulucraft321.hiderails.external.updater.SpigotUpdater;
import fr.lulucraft321.hiderails.listeners.BlockClickEvent;
import fr.lulucraft321.hiderails.listeners.BlockPhysicEvent;
import fr.lulucraft321.hiderails.listeners.BreakBlockEvent;
import fr.lulucraft321.hiderails.listeners.JoinEvent;
import fr.lulucraft321.hiderails.listeners.RedstoneInWaterEvents;
import fr.lulucraft321.hiderails.managers.FileConfigurationManager;
import fr.lulucraft321.hiderails.managers.HideRailsManager;
import fr.lulucraft321.hiderails.reflection.BukkitNMS;

public class HideRails extends JavaPlugin
{
	private static HideRails instance;
	public static HideRails getInstance() { return instance; }

	public static Version version;

	@Override
	public void onEnable()
	{
		instance = this;

		// Init this.version and all class
		new BukkitNMS();

		// Init and setup all custom configs
		FileConfigurationManager.setupConfigs();
		FileConfigurationManager.saveConfigs();

		registerListeners();
		registerCommands();

		// Chargement de tous les rails masques
		HideRailsManager.loadHideRails();

		// Verification MAJ
		try {
			new SpigotUpdater(this, 55158, true);
		} catch (IOException e) {
			Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "[Updater] Resource not found ! Ressource non trouvee !");
		}

		// Metrics stats
		new Metrics(this);
	}

	private void registerListeners()
	{
		new BreakBlockEvent();
		new RedstoneInWaterEvents();
		new JoinEvent();
		new BlockClickEvent();
		new BlockPhysicEvent();
	}

	private void registerCommands()
	{
		getCommand("hiderails").setExecutor(new CommandsHandle());
		getCommand("hiderails").setTabCompleter(new TabComplete());
	}


	/*
	 * GetWordedit if plugin is installed
	 */
	public WorldEditPlugin getWorldEdit() {
		Plugin we = Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
		if(we instanceof WorldEditPlugin) return (WorldEditPlugin) we;
		else return null;
	}
}
