package lol.sylvie.lauve.events.entity;

import lol.sylvie.lauve.events.api.CancellableEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.LivingEntity;

import java.util.List;

@AllArgsConstructor
@Getter
public class AreaEffectCloudApplyEvent extends CancellableEvent {
    final AreaEffectCloud entity;

    @Setter
    List<LivingEntity> affected;
}
