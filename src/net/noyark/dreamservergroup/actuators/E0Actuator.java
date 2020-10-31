package net.noyark.dreamservergroup.actuators;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.entity.Entity;
import cn.nukkit.entity.EntityCreature;
import cn.nukkit.entity.item.EntityItem;
import net.noyark.dreamservergroup.AshManPro;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author zzz1999 @ AshManPro Project
 */
public class E0Actuator implements Actuator {
    private final List<String> levelExcept;

    public E0Actuator(List<String> levelExcept) {
        this.levelExcept = levelExcept;
    }

    public void perform(Collection<Entity> stream) {
        final int[] ic = {0};
        final int[] ec = {0};
        stream.forEach(e -> {
            if (!levelExcept.contains(e.getLevel().getFolderName())) {
                if (e instanceof EntityCreature && !(e instanceof Player) && AshManPro.ELIMINATE_ENTITY) {
                    if (AshManPro.ELIMINATE_ENTITY_FORCE || !AshManPro.EXEMPTED_ENTITIES.containsKey(e.getId())) {
                        e.close();
                        ec[0]++;
                    }
                } else if (e instanceof EntityItem && AshManPro.ELIMINATE_ITEMS) {
                    if (!((EntityItem) e).getItem().hasCompoundTag() || !AshManPro.ELIMINATE_ITEMS_EXCEPT_NBTCONTAINS) {
                        e.close();
                        ic[0]++;
                    }
                }
            }
        });
        String s = AshManPro.terminate_message;
        AshManPro.broadcastMessage(s, ic[0], ec[0]);
    }

    @Override
    public void perform() {
        Stream.Builder<Entity> build = Stream.builder();
        Server.getInstance().getLevels().values().forEach(l -> Stream.of(l.getEntities()).forEach(build::add));
        Stream<Entity> stream = build.build();
        final int[] ic = {0};
        final int[] ec = {0};
        stream.forEach(e -> {
            if (!levelExcept.contains(e.getLevel().getFolderName())) {
                if (e instanceof EntityCreature && !(e instanceof Player) && AshManPro.ELIMINATE_ENTITY) {
                    if (AshManPro.ELIMINATE_ENTITY_FORCE || !AshManPro.EXEMPTED_ENTITIES.containsKey(e.getId())) {
                        e.close();
                        ec[0]++;
                    }
                } else if (e instanceof EntityItem && AshManPro.ELIMINATE_ITEMS) {
                    if (!((EntityItem) e).getItem().hasCompoundTag() || !AshManPro.ELIMINATE_ITEMS_EXCEPT_NBTCONTAINS) {
                        e.close();
                        ic[0]++;
                    }
                }
            }
        });
        String s = AshManPro.terminate_message;
        AshManPro.broadcastMessage(s, ic[0], ec[0]);
    }
}
