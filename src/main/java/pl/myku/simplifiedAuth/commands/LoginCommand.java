package pl.myku.simplifiedAuth.commands;

import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.net.command.Command;
import net.minecraft.core.net.command.CommandHandler;
import net.minecraft.core.net.command.CommandSender;
import pl.myku.simplifiedAuth.SimplifiedAuth;

public class LoginCommand extends Command{
    public LoginCommand() {super("login");}
    public boolean execute(CommandHandler handler, CommandSender sender, String[] args) {
        EntityPlayer player;
        if(sender.getPlayer() != null) {
            player = sender.getPlayer();
            if(!SimplifiedAuth.dbManager.isPlayerRegistered(player.username)){
                sender.sendMessage("Player is not registered.");
                sender.sendMessage("Use /register <password>.");
                return true;
            }
            else if(args.length == 1){
                if(SimplifiedAuth.playerManager.get(player).isAuthorized()){
                    sender.sendMessage("You're already logged in.");
                    return true;
                }
                else if(SimplifiedAuth.dbManager.loginPlayer(player.username, args[0])){
                    SimplifiedAuth.playerManager.get(player).authorize();
                    sender.sendMessage("Authenticated.");
                }
                else{
                    sender.sendMessage("Wrong password.");
                }
                return true;
            }
        }
        return false;
    }

    public boolean opRequired(String[] args) {
        return false;
    }

    public void sendCommandSyntax(CommandHandler handler, CommandSender sender) {
        sender.sendMessage("/login <password>");
    }
}
