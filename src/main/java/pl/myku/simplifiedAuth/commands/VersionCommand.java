package pl.myku.simplifiedAuth.commands;
import net.minecraft.core.net.command.*;

public class VersionCommand extends Command {
    public VersionCommand() {super("version");}

    public boolean execute(CommandHandler handler, CommandSender sender, String[] args) {
        if(sender.getPlayer() != null) {
            sender.sendMessage("Version 0.0.1.2 ");
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
