package com.github.sakakiaruka.talkwithdiscord.listener;

import com.github.sakakiaruka.talkwithdiscord.TalkWithDiscord;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;

import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;

import java.util.List;

public class OnMessage implements Listener {

    private Plugin plugin = new TalkWithDiscord().getPlugin();
    private FileConfiguration config = plugin.getConfig();

    @EventHandler
    public void onMessage(AsyncPlayerChatEvent event){
        String name = event.getPlayer().getName();
        String message = event.getMessage();

        List<String> urls = config.getStringList("url");

        String json = "{\"content\" : \""+message+"\" , \"username\" : \""+name+"\"}";

        for(String in : urls){
            try{
                URL url = new URL(in);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                conn.setDoOutput(true);
                conn.setRequestProperty("Content-Type","application/json; charset=utf-8");
                conn.connect();
                PrintStream ps = new PrintStream(conn.getOutputStream());
                ps.print(json);
                ps.close();

            }catch (Exception e){
                System.out.println("url exception occurred.");
                continue;
            }
        }
    }
}
