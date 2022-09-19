import reactor.util.annotation.Nullable;

import javax.validation.constraints.NotNull;

public class Jhava {

    public int hitPoints = 91204124;

    @NotNull
    public String utterGreeting() {
        return "BLARGH";
    }

    @Nullable
    public String determineFriendshipLevel() {
        return null;
    }
}
