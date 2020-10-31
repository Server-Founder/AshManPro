package net.noyark.dreamservergroup.placeholder;

import cn.nukkit.Player;

/**
 * @author zzz1999 @ AshManPro Project
 */
public interface PlaceHolder {
    String translate(String str, Player player, Object... os);
}
