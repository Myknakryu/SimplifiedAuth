package pl.myku.simplifiedAuth.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pl.myku.simplifiedAuth.commands.ChangePasswordCommand;
import pl.myku.simplifiedAuth.commands.LoginCommand;
import pl.myku.simplifiedAuth.commands.RegisterCommand;
import pl.myku.simplifiedAuth.commands.VersionCommand;
import net.minecraft.core.net.command.Commands;

@Mixin(value = Commands.class, remap = false)
final class CommandsMixin {
    @Inject(method="initCommands", at=@At("TAIL"))
    private static void initCommands(CallbackInfo ci){
        Commands.commands.add(new LoginCommand());
        Commands.commands.add(new RegisterCommand());
        Commands.commands.add(new VersionCommand());
        Commands.commands.add(new ChangePasswordCommand());
    }

}
