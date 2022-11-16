package com.github.sakakiaruka.talkwithdiscord;

import com.github.sakakiaruka.talkwithdiscord.listener.OnMessage;
import com.github.sakakiaruka.talkwithdiscord.runnable.RunNable;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class TalkWithDiscord extends JavaPlugin {

    private Plugin plugin = this;
    public static List<String> command;
    @Override
    public void onEnable() {
        // Plugin startup logic
        long delay = this.getConfig().getLong("delay");
        long interval = this.getConfig().getLong("interval");
        new RunNable().runTaskTimer(new TalkWithDiscord().getPlugin(),delay,interval);

        command = this.getConfig().getStringList("command");

        getServer().getPluginManager().registerEvents(new OnMessage(),this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        new RunNable().run();
    }

    public Plugin getPlugin(){
        return this.plugin;
    }
}
