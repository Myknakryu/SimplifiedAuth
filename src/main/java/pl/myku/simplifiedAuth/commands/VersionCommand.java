package pl.myku.simplifiedAuth.commands;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.net.command.*;
import pl.myku.simplifiedAuth.SimplifiedAuth;

public class VersionCommand extends Command {
    public VersionCommand() {super("version", new String[0]);}

    public boolean execute(CommandHandler handler, CommandSender sender, String[] args) {
        EntityPlayer player;
        if(sender.getPlayer() != null) {
            sender.sendMessage("Version 0.0.1 ");
            return true;
        }
        else{
            return false;
        }
    }

    public boolean opRequired(String[] args) {
        return false;
    }

    public void sendCommandSyntax(CommandHandler handler, CommandSender sender) {
        sender.sendMessage("/version");
    }
}
