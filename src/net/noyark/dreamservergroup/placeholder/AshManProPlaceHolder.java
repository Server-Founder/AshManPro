package net.noyark.dreamservergroup.placeholder;

import cn.nukkit.Player;
import cn.nukkit.Server;
import com.creeperface.nukkit.placeholderapi.api.PlaceholderAPI;
import net.noyark.dreamservergroup.enums.MessageEnum;

import java.util.EnumMap;

/**
 * @author zzz1999 @ AshManPro Project
 */
public class AshManProPlaceHolder implements PlaceHolder {

    private static boolean enablePlaceholder;
    private static final EnumMap<MessageEnum, Integer> enumMap = new EnumMap<>(MessageEnum.class);

    static {
        enumMap.put(MessageEnum.ITEM_COUNT, 0);
        enumMap.put(MessageEnum.ENTITY_COUNT, 1);
    }

    public AshManProPlaceHolder() {
        enablePlaceholder = Server.getInstance().getPluginManager().getPlugin("PlaceholderAPI") != null;
    }

    public static boolean isEnablePlaceholder() {
        return enablePlaceholder;
    }

    public static void setEnablePlaceholder(boolean enablePlaceholder) {
        AshManProPlaceHolder.enablePlaceholder = enablePlaceholder;
    }

    public String translate(String str, Player player, Object... os) {
        for (int i = 0; i < os.length; i++) {
            str = str.replaceAll("\\{" + i + "}", String.valueOf(os[i]));
        }
        return translatePlaceholder(str, player);
    }

    public static String translatePlaceholder(String str, Player player) {
        if (enablePlaceholder) {
            return PlaceholderAPI.getInstance().translateString(str, player);
        }
        return str;
    }


}
