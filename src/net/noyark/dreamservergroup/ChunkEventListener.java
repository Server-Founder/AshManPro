package net.noyark.dreamservergroup;

import cn.nukkit.Player;
import cn.nukkit.entity.Entity;
import cn.nukkit.entity.EntityCreature;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.level.ChunkUnloadEvent;

import java.util.function.Predicate;

/**
 * @author zzz1999 @ AshManPro Project
 */
public class ChunkEventListener implements Listener {

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onChunkUnload(ChunkUnloadEvent event) {
        if (!event.getChunk().getEntities().isEmpty()) {
            event.getChunk().getEntities().values().stream()
                    .filter(((Predicate<Entity>) e -> e instanceof EntityCreature && !(e instanceof Player) && AshManPro.ELIMINATE_ENTITY)
                            .and(e -> AshManPro.ELIMINATE_ENTITY_FORCE || !AshManPro.EXEMPTED_ENTITIES.containsKey(e.getId()))
                    )
                    .forEach(Entity::close);
        }
    }
}
