package pl.myku.simplifiedAuth.commands;

import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.net.command.Command;
import net.minecraft.core.net.command.CommandHandler;
import net.minecraft.core.net.command.CommandSender;
import pl.myku.simplifiedAuth.SimplifiedAuth;


public class RegisterCommand extends Command {
    public RegisterCommand() {
        super("register");
    }

    public boolean execute(CommandHandler handler, CommandSender sender, String[] args) {
        EntityPlayer player;
        if(sender.getPlayer() != null) {
            player = sender.getPlayer();
            if(SimplifiedAuth.dbManager.isPlayerRegistered(player.username)){
                sender.sendMessage("Player is already registered.");
                return true;
            }
            else if(args.length == 2){
                if(args[0].equals(args[1])){
                    SimplifiedAuth.dbManager.addPlayerToDatabase(player.username, args[0]);
                    if(SimplifiedAuth.dbManager.isPlayerRegistered(player.username)){
                        SimplifiedAuth.playerManager.get(player).authorize();
                        sender.sendMessage("Registered successfully.");
                        return true;
                    }
                    sender.sendMessage("Unknown error has occurred!.");
                }
                sender.sendMessage("Passwords need to match.");
            }
        }
        return false;
    }

    public boolean opRequired(String[] args) {
        return false;
    }

    public void sendCommandSyntax(CommandHandler handler, CommandSender sender) {
        sender.sendMessage("/register <password> <password>");
    }
}
