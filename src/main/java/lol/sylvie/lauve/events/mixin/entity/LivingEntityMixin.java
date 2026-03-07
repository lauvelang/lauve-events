package lol.sylvie.lauve.events.mixin.entity;

import lol.sylvie.lauve.events.api.EventBus;
import lol.sylvie.lauve.events.entity.BodyArrowCountChangeEvent;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @ModifyVariable(method = "setArrowCount", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    public int levents$setArrowCount(int count) {
        LivingEntity entity = (LivingEntity) (Object) this;
        BodyArrowCountChangeEvent event = EventBus.post(new BodyArrowCountChangeEvent(entity, entity.getArrowCount(), count));

        return event.getNewAmount();
    }
}
