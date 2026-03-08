package lol.sylvie.lauve.events.mixin.server;

import lol.sylvie.lauve.events.api.EventBus;
import lol.sylvie.lauve.events.server.TickEvent;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.BooleanSupplier;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {
    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/server/MinecraftServer;tickChildren(Ljava/util/function/BooleanSupplier;)V"), method = "tickServer")
    private void onStartTick(BooleanSupplier shouldKeepTicking, CallbackInfo ci) {
        EventBus.post(new TickEvent.Start((MinecraftServer) (Object) this));
    }

    @Inject(at = @At("TAIL"), method = "tickServer")
    private void onEndTick(BooleanSupplier shouldKeepTicking, CallbackInfo info) {
        EventBus.post(new TickEvent.End((MinecraftServer) (Object) this));
    }
}
