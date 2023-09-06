package pl.myku.simplifiedAuth.commands;

import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.net.command.Command;
import net.minecraft.core.net.command.CommandHandler;
import net.minecraft.core.net.command.CommandSender;
import pl.myku.simplifiedAuth.SimplifiedAuth;

public class ChangePasswordCommand extends Command {
    public ChangePasswordCommand() {
        super("changepassword", new String[0]);
    }

    public boolean execute(CommandHandler handler, CommandSender sender, String[] args) {
        EntityPlayer player;
        if(sender.getPlayer() != null) {
            player = sender.getPlayer();
            if(!SimplifiedAuth.playerManager.get(player).isAuthorized()){
                sender.sendMessage("User is not authorized.");
                return true;
            }
            else if(args.length == 3){
                if(SimplifiedAuth.dbManager.loginPlayer(player.username, args[0])){
                    if(args[1].equals(args[2])){
                        SimplifiedAuth.dbManager.changePassword(player.username, args[1]);
                        sender.sendMessage("Password changed.");
                        return true;
                    }
                    else{
                        sender.sendMessage("New passwords are not matching");
                    }
                }
                else{
                    sender.sendMessage("Wrong password.");
                }
            }
        }
        return false;
    }

    public boolean opRequired(String[] args) {
        return false;
    }

    public void sendCommandSyntax(CommandHandler handler, CommandSender sender) {
        sender.sendMessage("/changepassword <currentPassword> <newPassword> <newPassword>");
    }
}
