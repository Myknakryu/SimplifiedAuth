package pl.myku.simplifiedAuth.mixin;

import net.minecraft.core.entity.Entity;
import net.minecraft.core.util.helper.DamageType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import pl.myku.simplifiedAuth.Player;
import pl.myku.simplifiedAuth.SimplifiedAuth;
import pl.myku.simplifiedAuth.commands.VersionCommand;
import net.minecraft.server.entity.player.EntityPlayerMP;
import net.minecraft.core.entity.player.EntityPlayer;

@Mixin(value = EntityPlayerMP.class, remap = false)
final class EntityPlayerMPMixin {
    @Inject(method="hurt", at = @At("HEAD"), cancellable = true)
    private void authorizationInvulnerability(Entity entity, int i, DamageType type, CallbackInfoReturnable<Boolean> cir){
        Player player = SimplifiedAuth.playerManager.get((EntityPlayerMP) (Object) this);

        if (player != null && !player.isAuthorized()) {
            cir.setReturnValue(false);
            return;
        }
    }
}
