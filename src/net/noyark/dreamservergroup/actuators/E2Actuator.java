package net.noyark.dreamservergroup.actuators;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.entity.Entity;
import cn.nukkit.entity.EntityCreature;
import cn.nukkit.entity.item.EntityItem;
import cn.nukkit.math.AxisAlignedBB;
import cn.nukkit.math.SimpleAxisAlignedBB;
import net.noyark.dreamservergroup.AshManPro;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author zzz1999 @ AshManPro Project
 */
public class E2Actuator implements Actuator {
    private double offsetX;
    private double offsetY;
    private double offsetZ;
    private List<String> playerExcept;

    public E2Actuator(double offsetX, double offsetY, double offsetZ, List<String> playerExcept) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.offsetZ = offsetZ;
        this.playerExcept = playerExcept;
    }

    @Override
    public void perform(Collection<Entity> stream) {
        perform();
    }

    @Override
    public void perform() {
        final int[] ic = {0};
        final int[] ec = {0};
        Server.getInstance().getLevels().values()
                .stream()
                .filter(l -> l.getPlayers().size() > 0)
                .map(l -> l.getPlayers().values())
                .flatMap(Collection::stream)
                .filter(p -> !playerExcept.contains(p.getName()))
                .forEach(p -> {
                    if (!playerExcept.contains(p.getName())) {
                        AxisAlignedBB aabb = new SimpleAxisAlignedBB(
                                p.getX() - offsetX,
                                p.getY(),
                                p.getZ() - offsetZ,
                                p.getX() + offsetX,
                                p.getY() + offsetY,
                                p.getZ() + offsetZ
                        );
                        Stream.of(p.getLevel().getCollidingEntities(aabb))
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
                                });
                            /*
                                .filter(((Predicate<Entity>)def->AshManPro.eliminate_entity_force)
                                .or(ety->!AshManPro.exemptedEntities.containsKey(ety.getId())))
                                .forEach(Entity::close);
                            */
                    }
                });
        String s = AshManPro.terminate_message;
        AshManPro.broadcastMessage(s, ic[0], ec[0]);
    }
}
