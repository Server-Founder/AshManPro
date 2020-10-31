package net.noyark.dreamservergroup.triggers;

import cn.nukkit.Server;
import cn.nukkit.entity.Entity;
import cn.nukkit.scheduler.PluginTask;
import net.noyark.dreamservergroup.AshManPro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author zzz1999 @ AshManPro Project
 */
public class T2Trigger extends PluginTask<AshManPro> implements BaseTrigger {
    private int entityThreshold;
    private int AvgTPSThreshold;

    public T2Trigger(AshManPro owner, int entityThreshold, int AvgTPSThreshold) {
        super(owner);
        this.entityThreshold = entityThreshold;
        this.AvgTPSThreshold = AvgTPSThreshold;
    }

    @Override
    public void onRun(int i) {
        if (AshManPro.ELIMINATE_ENTITY || AshManPro.ELIMINATE_ITEMS) {
            List<Entity> entityList = new ArrayList<>();
            owner.getServer().getLevels().values().forEach(l -> {
                entityList.addAll(Arrays.asList(l.getEntities()));
            });
            if (entityList.size() > entityThreshold && Server.getInstance().getTicksPerSecondAverage() < AvgTPSThreshold) {
                if (AshManPro.BEFOREHAND_MESSAGE.isEmpty()) {
                    AshManPro.getActuator().perform(entityList);
                    return;
                }
                owner.getServer().getScheduler().scheduleRepeatingTask(new PluginTask<AshManPro>(AshManPro.getInstance()) {
                    Map<Integer, String> bm = AshManPro.BEFOREHAND_MESSAGE;
                    private int cel = bm.keySet().iterator().next();

                    @Override
                    public void onRun(int i) {
                        if (cel < 0) {
                            AshManPro.getActuator().perform(entityList);
                            cancel();
                        }
                        String s;
                        if ((s = bm.get(cel)) != null) {
                            AshManPro.broadcastMessage(s);
                        }
                        cel--;

                    }
                }, 20);
            }
        }
    }
}
