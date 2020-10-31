package net.noyark.dreamservergroup.actuators;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.entity.Entity;
import cn.nukkit.entity.EntityCreature;
import cn.nukkit.entity.item.EntityItem;
import cn.nukkit.level.Level;
import net.noyark.dreamservergroup.AshManPro;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author zzz1999 @ AshManPro Project
 */
public class E1Actuator implements Actuator {
    private List<String> levelExcept;
    private int threshold;

    public E1Actuator(List<String> levelExcept, int threshold) {
        this.levelExcept = levelExcept;
        this.threshold = threshold;
    }

    @Override
    public void perform(Collection<Entity> stream) {
        final int[] ic = {0};
        final int[] ec = {0};
        stream.forEach(e -> {
            if (!levelExcept.contains(e.getLevel().getFolderName()) && e.getLevel().getPlayers().size() > threshold) {
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
        final int[] ic = {0};
        final int[] ec = {0};
        Server.getInstance().getLevels().values().stream()
                .filter(((Predicate<Level>) l -> l.getPlayers().size() > threshold)
                        .and(l -> !levelExcept.contains(l.getFolderName()))
                )
                .map(Level::getEntities)
                .flatMap(Arrays::stream)
                .forEach(e -> {
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
                );
        String s = AshManPro.terminate_message;
        AshManPro.broadcastMessage(s, ic[0], ec[0]);
    }
}
