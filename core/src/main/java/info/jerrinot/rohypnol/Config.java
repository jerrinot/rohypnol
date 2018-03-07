package info.jerrinot.rohypnol;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public final class Config {
    public static final long DEFAULT_MAXIMUM_PAUSE_NANOS = MILLISECONDS.toNanos(5000);
    public static final long DEFAULT_MINIMUM_INTERVAL_BETWEEN_PAUSES_NANOS = MILLISECONDS.toNanos(1000);

    public static final String MAXIMUM_PAUSE_NANOS_KEY = "rohypnol.max.pause.nanos";
    public static final String MINIMUM_INTERVAL_BETWEEN_PAUSES_KEY = "rohypnol.min.interval.nanos";
    public static final String CLASS_PREFIX_KEY = "rohypnol.class.prefix";

    public static final long MAXIMUM_PAUSE_NANOS = Long.getLong(MAXIMUM_PAUSE_NANOS_KEY, DEFAULT_MAXIMUM_PAUSE_NANOS);
    public static final long MINIMUM_INTERVAL_BETWEEN_PAUSES_NANOS = Long.getLong(MINIMUM_INTERVAL_BETWEEN_PAUSES_KEY,
            DEFAULT_MINIMUM_INTERVAL_BETWEEN_PAUSES_NANOS);
    public static final String CLASS_PREFIX = System.getProperty(CLASS_PREFIX_KEY);

    private Config() {

    }

}
