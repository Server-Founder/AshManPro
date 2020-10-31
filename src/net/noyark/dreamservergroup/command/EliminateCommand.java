package net.noyark.dreamservergroup.command;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;
import net.noyark.dreamservergroup.AshManPro;

/**
 * @author zzz1999 @ AshManPro Project
 */
public class EliminateCommand extends Command {
    public EliminateCommand() {
        super("ashman", "AshManPro命令", "/ashman help", new String[]{"am", "清道夫"});
        this.setPermission("AshManPro.command.eliminate");
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (args.length == 0) {
            AshManPro.getActuator().perform();
        } else if (args.length == 1) {
            switch (args[0]) {
                case "force":
                    AshManPro.forceEliminate();
                    break;
                case "help":
                    sender.sendMessage(TextFormat.YELLOW + "===AshManPro命令帮助===");
                    sender.sendMessage(TextFormat.YELLOW + "/am force | 强制清理所有实体掉落物");
                    sender.sendMessage(TextFormat.YELLOW + "/am help | 显示本插件帮助");
                    break;
            }
        }
        return false;
    }
}
