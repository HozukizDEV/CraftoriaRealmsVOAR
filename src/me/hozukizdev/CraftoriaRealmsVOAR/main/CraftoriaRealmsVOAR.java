package me.hozukizdev.CraftoriaRealmsVOAR.main;

import me.hozukizdev.CraftoriaRealmsVOAR.comandos.ComandoVoar;
import me.hozukizdev.CraftoriaRealmsVOAR.eventos.AntiQueda;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class CraftoriaRealmsVOAR extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new AntiQueda(), this);
        this.saveDefaultConfig();
    getServer().getConsoleSender().sendMessage(ChatColor.BLUE +"Plugin inicializado com sucesso!");
        getCommand("voar").setExecutor(new ComandoVoar(this));
    }
}
