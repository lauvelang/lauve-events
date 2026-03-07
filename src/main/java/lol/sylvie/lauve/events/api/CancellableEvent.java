package lol.sylvie.lauve.events.api;

import lombok.Getter;
import lombok.Setter;

public abstract class CancellableEvent implements Event {
    @Getter
    @Setter
    private boolean cancelled = false;

    public void cancel() {
        this.cancelled = true;
    }
}