package pl.myku.simplifiedAuth;

import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.server.entity.player.EntityPlayerMP;
public class Player {
    private EntityPlayer player;
    private boolean authorized = false;
    public Player(EntityPlayer player){
        this.player = player;
    }

    public void authorize() {
        authorized = true;
    }
    public boolean isAuthorized() {
        return authorized;
    }
    public void destroy() { authorized = false; }
}
