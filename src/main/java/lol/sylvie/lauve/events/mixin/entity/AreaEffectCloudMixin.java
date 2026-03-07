package lol.sylvie.lauve.events.mixin.entity;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import lol.sylvie.lauve.events.api.EventBus;
import lol.sylvie.lauve.events.entity.AreaEffectCloudApplyEvent;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;

@Mixin(AreaEffectCloud.class)
public class AreaEffectCloudMixin {
    @WrapOperation(method = "serverTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;getEntitiesOfClass(Ljava/lang/Class;Lnet/minecraft/world/phys/AABB;)Ljava/util/List;"))
    public List<LivingEntity> levents$areaEffectCloudApplyEventHook(Level instance, Class aClass, AABB aabb, Operation<List<LivingEntity>> original) {
        List<LivingEntity> affected = original.call(instance, aClass, aabb);
        AreaEffectCloudApplyEvent event = EventBus.post(new AreaEffectCloudApplyEvent((AreaEffectCloud) (Object) this, affected));

        return event.isCancelled() ? List.of() : event.getAffected();
    }
}
