package me.hozukizdev.CraftoriaRealmsVOAR.comandos;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class ComandoVoar implements CommandExecutor {

    private final JavaPlugin plugin;
    public static Set<UUID> semDano = new HashSet<>();

    public ComandoVoar(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

    Player player = ((Player) commandSender);

    if(!(commandSender instanceof Player)) {
        String mensagem =  plugin.getConfig().getString("mensagem_somente_jofador");
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', mensagem));
        return true;
    }
    if (!commandSender.hasPermission("craftoriavoar.use")) {
        String mensagem =  plugin.getConfig().getString("mensagem_sem_permissao");
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', mensagem));
        return true;
        }

        if (strings.length > 0) {
            if (strings[0].equalsIgnoreCase("reload")) {
                // Permissão de recarregar
                if (!player.hasPermission("craftoriavoar.reload")) {
                    player.sendMessage(ChatColor.RED + "Você não tem permissão para recarregar!");
                    return true;
                }

                plugin.reloadConfig();
                player.sendMessage(ChatColor.GREEN + "Config recarregada com sucesso!");
                return true;
            } else {
                // Qualquer outro argumento é inválido
                String mensagem =  plugin.getConfig().getString("mensagem_uso_errado");
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', mensagem));
                return true;
            }
        }

    if (player.getAllowFlight()){
        player.setAllowFlight(false);
        semDano.add(player.getUniqueId());
        Bukkit.getScheduler().runTaskLater(plugin, () -> semDano.remove(player.getUniqueId()), 60);
    String mensagem =  plugin.getConfig().getString("mensagem_voo_desativado");
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', mensagem));
    } else{
        player.setAllowFlight(true);
        player.setFlying(true);
        player.setFlySpeed(0.03F);
        String mensagem =  plugin.getConfig().getString("mensagem_voo_ativado");
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', mensagem));

    }
        return true;
    }
}
