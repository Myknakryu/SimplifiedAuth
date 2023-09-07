package pl.myku.simplifiedAuth.mixin;

import net.minecraft.core.net.packet.*;
import net.minecraft.core.util.helper.AES;
import net.minecraft.server.entity.player.EntityPlayerMP;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.server.net.handler.NetServerHandler;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pl.myku.simplifiedAuth.SimplifiedAuth;

@Mixin(value = NetServerHandler.class, remap = false)
abstract class NetServerHandlerMixin {

    private static long teleportTimeout = 0;
    private static int timeout = 600;
    @Shadow private EntityPlayerMP playerEntity;

    @Shadow public abstract void teleportTo(double d, double d1, double d2, float f, float f1);

    @Shadow private int playerInAirTime;

    @Shadow public abstract void kickPlayer(String s);

    @Inject(method="handleBlockDig", at=@At("HEAD"), cancellable = true)
    public void handleBlockDig(Packet14BlockDig packet, CallbackInfo ci){
        if(SimplifiedAuth.playerManager.get(playerEntity).isAuthorized()) {
            return;
        }
        ci.cancel();
    }

    @Inject(method="handleMovementTypePacket", at=@At("HEAD"), cancellable = true)
    public void handleMovementTypePacket(Packet27Position packet, CallbackInfo ci){
        if(SimplifiedAuth.playerManager.get(playerEntity).isAuthorized()) {
            return;
        }
        ci.cancel();
    }

    @Inject(method="handleWindowClick", at=@At("HEAD"), cancellable = true)
    public void handleInventoryAndGui(Packet102WindowClick packet, CallbackInfo ci){
        if(SimplifiedAuth.playerManager.get(playerEntity).isAuthorized()) {
            return;
        }
        ci.cancel();
    }

    @Inject(method="handleFlying", at=@At("HEAD"), cancellable = true)
    public void handleFlying(Packet10Flying packet, CallbackInfo ci){
        if(SimplifiedAuth.playerManager.get(playerEntity).isAuthorized()) {
            return;
        }
        if(timeout < 1){
            timeout = 600;
            this.kickPlayer("Didn't authorize, timeout..");
        }
        if(System.nanoTime() >= (teleportTimeout + 5000000L)){
            playerInAirTime = 0;
            teleportTo(playerEntity.x, playerEntity.y, playerEntity.z, playerEntity.yRot, playerEntity.xRot);
            teleportTimeout = System.nanoTime();
            timeout--;
        }
        ci.cancel();
    }

    @Inject(method="handlePlace", at=@At("HEAD"), cancellable = true)
    public void handlePlace(Packet15Place packet, CallbackInfo ci){
        if(SimplifiedAuth.playerManager.get(playerEntity).isAuthorized()) {
            return;
        }
        ci.cancel();
    }

    @Inject(method="handleChat", at=@At("HEAD"), cancellable = true)
    public void handleChat(Packet3Chat packet, CallbackInfo ci) throws Exception {
        if(SimplifiedAuth.playerManager.get(playerEntity).isAuthorized()) {
            return;
        }
        try {
            String msg = AES.decrypt(packet.message, AES.keyChain.get(this.playerEntity.username));
            if(msg.contains("/login") || msg.contains("/register")){
                return;
            }
        } catch (Exception e) {
            SimplifiedAuth.LOGGER.error(e.getMessage());
        }
        ci.cancel();
    }

    @Inject(method="handleAnimation", at=@At("HEAD"), cancellable = true)
    public void handleAnimation(Packet18Animation packet, CallbackInfo ci){
        if(SimplifiedAuth.playerManager.get(playerEntity).isAuthorized()) {
            return;
        }
        ci.cancel();
    }

    @Inject(method="handleEntityAction", at=@At("HEAD"), cancellable = true)
    public void handleEntityAction(Packet19EntityAction packet, CallbackInfo ci){
        if(SimplifiedAuth.playerManager.get(playerEntity).isAuthorized()) {
            return;
        }
        ci.cancel();
    }

    @Inject(method="handleUseEntity", at=@At("HEAD"), cancellable = true)
    public void handleUseEntity(Packet7UseEntity packet, CallbackInfo ci){
        if(SimplifiedAuth.playerManager.get(playerEntity).isAuthorized()) {
            return;
        }
        ci.cancel();
    }

    @Inject(method="handleTransaction", at=@At("HEAD"), cancellable = true)
    public void handleTransaction(Packet106Transaction packet, CallbackInfo ci){
        if(SimplifiedAuth.playerManager.get(playerEntity).isAuthorized()) {
            return;
        }
        ci.cancel();
    }
}
