package ddd_sample;

import org.jetbrains.annotations.NotNull;

import java.time.OffsetDateTime;

public class Event implements Comparable<Event> {
    private EventId id;
    private OffsetDateTime timestamp;
    private String protocol;
    private Activity activity;

    @Override
    public int compareTo(@NotNull Event o) {
        return 0;
    }
}
