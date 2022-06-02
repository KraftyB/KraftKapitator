package com.krafty.kraftkapitator;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin{
	public void onEnable() {
	    Bukkit.getPluginManager().registerEvents((Listener) new TreeHandler(), (Plugin)this);
	   
	  }
}
