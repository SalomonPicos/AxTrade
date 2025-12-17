package com.artillexstudios.axtrade.commands.subcommands;

import com.artillexstudios.axapi.utils.StringUtils;
import com.artillexstudios.axtrade.hooks.HookManager;
import com.artillexstudios.axtrade.lang.LanguageManager;
import com.artillexstudios.axtrade.utils.NumberUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.util.Map;

import static com.artillexstudios.axtrade.AxTrade.CONFIG;
import static com.artillexstudios.axtrade.AxTrade.GUIS;
import static com.artillexstudios.axtrade.AxTrade.HOOKS;
import static com.artillexstudios.axtrade.AxTrade.LANG;
import static com.artillexstudios.axtrade.AxTrade.MESSAGEUTILS;

public enum Reload {
    INSTANCE;

    public void execute(CommandSender sender) {
        Bukkit.getConsoleSender().sendMessage(StringUtils.formatToString("<#00FFDD>[AxTrade] <#AAFFDD>Reloading configuration..."));
        if (!CONFIG.reload()) {
            MESSAGEUTILS.sendLang(sender, "reload.failed", Map.of("%file%", "config.yml"));
            return;
        }
        Bukkit.getConsoleSender().sendMessage(StringUtils.formatToString("<#00FFDD>╠ <#AAFFDD>Reloaded <white>config.yml</white><#AAFFDD>!"));

        if (!LANG.reload()) {
            MESSAGEUTILS.sendLang(sender, "reload.failed", Map.of("%file%", "lang.yml"));
            return;
        }
        Bukkit.getConsoleSender().sendMessage(StringUtils.formatToString("<#00FFDD>╠ <#AAFFDD>Reloaded <white>lang.yml</white><#AAFFDD>!"));

        if (!GUIS.reload()) {
            MESSAGEUTILS.sendLang(sender, "reload.failed", Map.of("%file%", "guis.yml"));
            return;
        }
        Bukkit.getConsoleSender().sendMessage(StringUtils.formatToString("<#00FFDD>╠ <#AAFFDD>Reloaded <white>guis.yml</white><#AAFFDD>!"));

        if (!HOOKS.reload()) {
            MESSAGEUTILS.sendLang(sender, "reload.failed", Map.of("%file%", "currencies.yml"));
            return;
        }
        Bukkit.getConsoleSender().sendMessage(StringUtils.formatToString("<#00FFDD>╠ <#AAFFDD>Reloaded <white>currencies.yml</white><#AAFFDD>!"));

        LanguageManager.reload();

        HookManager.updateHooks();
        NumberUtils.reload();

        Bukkit.getConsoleSender().sendMessage(StringUtils.formatToString("<#00FFDD>╚ <#AAFFDD>Successful reload!"));
        MESSAGEUTILS.sendLang(sender, "reload.success");
    }
}
