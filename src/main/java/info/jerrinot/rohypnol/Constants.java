package info.jerrinot.rohypnol;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public final class Constants {
    public static final long MAXIMUM_PAUSE_NANOS = MILLISECONDS.toNanos(5000);
    public static final long MINIMUM_INTERVAL_BETWEEN_PAUSES_NANOS = MILLISECONDS.toNanos(1000);

    private Constants() {

    }

}
