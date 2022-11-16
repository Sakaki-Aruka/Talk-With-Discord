package com.github.sakakiaruka.talkwithdiscord.runnable;

import com.github.sakakiaruka.talkwithdiscord.TalkWithDiscord;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.*;

import static com.github.sakakiaruka.talkwithdiscord.TalkWithDiscord.command;

public class RunNable extends BukkitRunnable {

    private Plugin plugin = new TalkWithDiscord().getPlugin();
    private int times = 0;
    private Process process;
    //private List<String> messages = new ArrayList<>();

    @Override
    public void run(){
        if(times == 0){
            this.processBuild();
            times++;
        }else{
            plugin.getLogger().info("The Discord-bot will soon stop.");
            process.destroy();
        }
    }

    private void processBuild(){
        if(times == 0){
            ProcessBuilder pb = new ProcessBuilder(command);
            try{
                process = pb.start();
                BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
                while(true) {
                    String message = br.readLine();
                    Bukkit.broadcastMessage(message);
                }
            }catch (IOException ioe){
                plugin.getLogger().info("Error occurred.\n");
                ioe.printStackTrace();
            }
        }
    }
}
