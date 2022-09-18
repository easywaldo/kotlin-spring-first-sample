import reactor.util.annotation.Nullable;

import javax.validation.constraints.NotNull;

public class Jhava {

    @NotNull
    public String utterGreeting() {
        return "BLARGH";
    }

    @Nullable
    public String determineFriendshipLevel() {
        return null;
    }
}
