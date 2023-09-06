package pl.myku.simplifiedAuth;

import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.server.entity.player.EntityPlayerMP;
import java.util.HashMap;

public class PlayerManager extends HashMap<String, Player>{
    public Player get(EntityPlayer player){
        String username = player.username;
        if (containsKey(username)){
            return super.get(username);
        }
        Player newPlayer = new Player(player);
        put(username, newPlayer);
        return newPlayer;
    }
}
