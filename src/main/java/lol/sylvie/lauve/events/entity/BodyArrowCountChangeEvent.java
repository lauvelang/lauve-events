package lol.sylvie.lauve.events.entity;

import lol.sylvie.lauve.events.api.CancellableEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.world.entity.LivingEntity;

@AllArgsConstructor
@Getter
public class BodyArrowCountChangeEvent extends CancellableEvent {
    LivingEntity entity;

    int oldAmount;

    @Setter
    int newAmount;
}
