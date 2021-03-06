/**
 * Copyright Java Code
 * All right reserved.
 *
 * @author lulucraft321
 */

package fr.lulucraft321.hiderails.configurations.configs;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

import fr.lulucraft321.hiderails.managers.FileConfigurationManager;
import fr.lulucraft321.hiderails.utils.abstractclass.AbstractLangConfig;

public class French extends FileConfigurationManager implements AbstractLangConfig
{
	@Override
	public void setupConfig()
	{
		// FR.yml
		FileConfigurationManager.frLangFile = new File(FileConfigurationManager.path, "FR.yml");
		if(!FileConfigurationManager.frLangFile.exists())
		{
			try {
				FileConfigurationManager.frLangFile.createNewFile();
			} catch (IOException e) {
				System.out.println("Erreur lors de la creation du fichier de configuration \"" + FileConfigurationManager.frLangConfig.getName().toString() + "\" !");
				return;
			}

			FileConfigurationManager.frLangConfig = YamlConfiguration.loadConfiguration(FileConfigurationManager.frLangFile);
			FileConfigurationManager.frLangConfig.options().header("Language by lulucraft321").copyDefaults(true);
			FileConfigurationManager.frLangConfig.set(FileConfigurationManager.msgPath + "sender_type_error", "&cSeul un joueur peut executer cette commande !");
			FileConfigurationManager.frLangConfig.set(FileConfigurationManager.msgPath + "player_no_enough_permission", "&cVous n'avez pas la permission d'�x�cuter cette commande !");
			FileConfigurationManager.frLangConfig.set(FileConfigurationManager.msgPath + "rail_success_change", "&2Vous avez remplac� les rails par %blocktype%");
			FileConfigurationManager.frLangConfig.set(FileConfigurationManager.msgPath + "material_type_error", "&cCe bloc n'existe pas !");
			FileConfigurationManager.frLangConfig.set(FileConfigurationManager.msgPath + "rail_error", "&cLe bloc que vous visez n'est pas un rail !");
			FileConfigurationManager.frLangConfig.set(FileConfigurationManager.msgPath + "rail_success_break", "&2Vous avez cass� un rail masqu� !");
			FileConfigurationManager.frLangConfig.set(FileConfigurationManager.msgPath + "rail_success_unhide", "&2Vous avez fait re apparaitre les rails !");
			FileConfigurationManager.frLangConfig.set(FileConfigurationManager.msgPath + "water_protection_status_success_change", "&2Vous avez %status% la protection de la redstone sous l'eau pour le monde %world%");
			FileConfigurationManager.frLangConfig.set(FileConfigurationManager.msgPath + "invalid_worldname", "&cMonde invalide !");
			FileConfigurationManager.frLangConfig.set(FileConfigurationManager.msgPath + "plugin_success_reloaded", "&2Plugin recharg� avec succ�s !");
			FileConfigurationManager.frLangConfig.set(FileConfigurationManager.msgPath + "water_protection_status_already", "&cLa protection de la redstone et des rails sous l'eau dans le monde %world% est d�ja d�finit sur %status%");
			FileConfigurationManager.frLangConfig.set(FileConfigurationManager.msgPath + "no_backup", "&cAucune sauvegarde disponible !");
			FileConfigurationManager.frLangConfig.set(FileConfigurationManager.msgPath + "return_backup_success", "&2Sauvegarde restor�e avec succ�s !");
			FileConfigurationManager.frLangConfig.set(FileConfigurationManager.msgPath + "worldedit_not_installed", "&cLe plugin worldedit n'est pas install� sur le serveur !");
			FileConfigurationManager.frLangConfig.set(FileConfigurationManager.msgPath + "worldedit_no_selection", "&cVous devez d'abord s�lectionner une r�gion avec worldedit !");
			FileConfigurationManager.frLangConfig.set(FileConfigurationManager.msgPath + "display_hidden_blocks", "&2Vous avez %hide% le masquage des blocs pour vous !");
			FileConfigurationManager.frLangConfig.set(FileConfigurationManager.msgPath + "invalid_player", "&cLe joueur n'est pas connect�/n'existe pas !");
			FileConfigurationManager.frLangConfig.set(FileConfigurationManager.msgPath + "update_found", "&bUne nouvelle mise � jour est disponible !\n&o%link%");
			FileConfigurationManager.frLangConfig.set(FileConfigurationManager.msgPath + "kick_spam_hidden_block", "�cTu veux bien arr�ter de spam sur les blocs svp ??!!");

			// Sauveguarde des modifs
			try {
				FileConfigurationManager.frLangConfig.save(FileConfigurationManager.frLangFile);
			} catch (IOException e) { System.err.println("Erreur lors de la sauveguarde du fichier de configuration \"" + FileConfigurationManager.frLangConfig.getName().toString() + "\" !"); }
		} else {
			FileConfigurationManager.frLangConfig = YamlConfiguration.loadConfiguration(FileConfigurationManager.frLangFile);
			FileConfigurationManager.frLangConfig.options().header("Language by lulucraft321").copyDefaults(true);
			checkConfContains(FileConfigurationManager.frLangConfig, "sender_type_error", "&cSeul un joueur peut executer cette commande !");
			checkConfContains(FileConfigurationManager.frLangConfig, "player_no_enough_permission", "&cVous n'avez pas la permission d'�x�cuter cette commande !");
			checkConfContains(FileConfigurationManager.frLangConfig, "rail_success_change", "&2Vous avez remplac� les rails par %blocktype%");
			checkConfContains(FileConfigurationManager.frLangConfig, "material_type_error", "&cCe bloc n'existe pas !");
			checkConfContains(FileConfigurationManager.frLangConfig, "rail_error", "&cLe bloc que vous visez n'est pas un rail !");
			checkConfContains(FileConfigurationManager.frLangConfig, "rail_success_break", "&2Vous avez cass� un rail masqu� !");
			checkConfContains(FileConfigurationManager.frLangConfig, "rail_success_unhide", "&2Vous avez fait re apparaitre les rails !");
			checkConfContains(FileConfigurationManager.frLangConfig, "water_protection_status_success_change", "&2Vous avez %status% la protection de la redstone sous l'eau pour le monde %world%");
			checkConfContains(FileConfigurationManager.frLangConfig, "invalid_worldname", "&cMonde invalide !");
			checkConfContains(FileConfigurationManager.frLangConfig, "plugin_success_reloaded", "&2Plugin recharg� avec succ�s !");
			checkConfContains(FileConfigurationManager.frLangConfig, "water_protection_status_already", "&cLa protection de la redstone et des rails sous l'eau dans le monde %world% est d�ja d�finit sur %status%");
			checkConfContains(FileConfigurationManager.frLangConfig, "no_backup", "&cAucune sauvegarde disponible !");
			checkConfContains(FileConfigurationManager.frLangConfig, "return_backup_success", "&cSauvegarde restor�e avec succ�s !");
			checkConfContains(FileConfigurationManager.frLangConfig, "worldedit_not_installed", "&cLe plugin worldedit n'est pas install� sur le serveur !");
			checkConfContains(FileConfigurationManager.frLangConfig, "worldedit_no_selection", "&cVous devez d'abord s�lectionner une r�gion avec worldedit !");
			checkConfContains(FileConfigurationManager.frLangConfig, "display_hidden_blocks", "&2Vous avez %hide% le masquage des blocs pour vous !");
			checkConfContains(FileConfigurationManager.frLangConfig, "invalid_player", "&cLe joueur n'est pas connect�/n'existe pas !");
			checkConfContains(FileConfigurationManager.frLangConfig, "update_found", "&bUne nouvelle mise � jour est disponible !\n&o%link%");
			checkConfContains(FileConfigurationManager.frLangConfig, "kick_spam_hidden_block", "�cTu veux bien arr�ter de spam sur les blocs svp ??!!");

			// Sauveguarde des modifs
			try {
				FileConfigurationManager.frLangConfig.save(FileConfigurationManager.frLangFile);
			} catch (IOException e) { System.err.println("Erreur lors de la sauveguarde du fichier de configuration \"" + FileConfigurationManager.frLangConfig.getName().toString() + "\" !"); }
		}
		FileConfigurationManager.frLangConfig = YamlConfiguration.loadConfiguration(FileConfigurationManager.frLangFile);
		//
	}
}
