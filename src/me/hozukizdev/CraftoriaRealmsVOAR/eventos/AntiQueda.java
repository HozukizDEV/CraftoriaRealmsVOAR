package me.hozukizdev.CraftoriaRealmsVOAR.eventos;

import me.hozukizdev.CraftoriaRealmsVOAR.comandos.ComandoVoar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class AntiQueda implements Listener {

    @EventHandler
    public void onFallDamage(EntityDamageEvent e) {
        if (!(e.getEntity() instanceof Player)) return;

        Player player = (Player) e.getEntity();

        // Cancela dano se o jogador estiver na lista de proteção
        if (e.getCause() == EntityDamageEvent.DamageCause.FALL
                && ComandoVoar.semDano.contains(player.getUniqueId())) {
            e.setCancelled(true);
        }
    }
}
