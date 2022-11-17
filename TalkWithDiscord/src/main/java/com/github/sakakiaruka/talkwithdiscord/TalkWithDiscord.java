package com.github.sakakiaruka.talkwithdiscord;

import com.github.sakakiaruka.talkwithdiscord.listener.OnMessage;
import com.github.sakakiaruka.talkwithdiscord.runnable.RunNable;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

import static com.github.sakakiaruka.talkwithdiscord.runnable.RunNable.process;

public final class TalkWithDiscord extends JavaPlugin {

    private Plugin plugin = this;
    public static List<String> command;
    public static long delay;
    public static List<String> urls;
    @Override
    public void onEnable() {
        // Plugin startup logic
        delay = this.getConfig().getLong("delay");
        command = this.getConfig().getStringList("command");
        urls = this.getConfig().getStringList("url");
        new RunNable().runTaskLaterAsynchronously(new TalkWithDiscord().getPlugin(),delay);

        getServer().getPluginManager().registerEvents(new OnMessage(),this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        process.destroy();
    }

    public Plugin getPlugin(){
        return this.plugin;
    }
}
