package pl.myku.simplifiedAuth.mixin;

import net.minecraft.core.net.NetworkManager;
import net.minecraft.core.net.packet.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Set;
import java.util.HashSet;

@Mixin(value = NetworkManager.class, remap = false)
final class NetworkManagerMixin {
//    private static final Set<Class<?>> processedPackets = new HashSet<>();
//    static {
//            processedPackets.add(Packet5PlayerInventory.class);
//            processedPackets.add(Packet7UseEntity.class);
//            processedPackets.add(Packet11PlayerPosition.class);
//            processedPackets.add(Packet13PlayerLookMove.class);
//            processedPackets.add(Packet14BlockDig.class);
//            processedPackets.add(Packet15Place.class);
//            processedPackets.add(Packet17Sleep.class);
//            processedPackets.add(Packet18Animation.class);
////            processedPackets.add(Packet16BlockItemSwitch.class);
//            processedPackets.add(Packet70Bed.class);
//            processedPackets.add(Packet102WindowClick.class);
//
//    }
//
//    @Inject(method="processReadPackets",at = @At(value = "INVOKE",
//            target = "Lnet/minecraft/core/net/packet/Packet;processPacket(Lnet/minecraft/core/net/handler/NetHandler;)V", ordinal = 0),
//            locals = LocalCapture.CAPTURE_FAILSOFT,
//            cancellable = true)
//    public void beforeRemovePacket(CallbackInfo ci, Packet packet) {
//        if(processedPackets.contains(packet.getClass())){
////            ci.cancel();
//            return;
//        }
//        System.out.println(packet.getClass().getName());
//    }
}

