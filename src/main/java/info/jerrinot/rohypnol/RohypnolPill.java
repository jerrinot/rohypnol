package info.jerrinot.rohypnol;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.LockSupport;

import static info.jerrinot.rohypnol.Constants.MAXIMUM_PAUSE_NANOS;
import static info.jerrinot.rohypnol.Constants.MINIMUM_INTERVAL_BETWEEN_PAUSES_NANOS;

public class RohypnolPill {
    private static final ThreadLocal<Long> LAST_PAUSE = new ThreadLocal<>();

    public static void use() {
//        System.out.println("Using a rohypol pill");

        if (!shouldPause()) {
            return;
        }
        long pauseNanos = calculatePauseNanos();
        LockSupport.parkNanos(pauseNanos);
    }

    private static boolean shouldPause() {
        Long lastPause = LAST_PAUSE.get();
        long now = System.nanoTime();
        if (lastPause == null) {
            LAST_PAUSE.set(now);
            return true;
        } else if (lastPause < now - MINIMUM_INTERVAL_BETWEEN_PAUSES_NANOS) {
            LAST_PAUSE.set(now);
            return true;
        } else {
            return false;
        }
    }

    private static long calculatePauseNanos() {
        // todo: different pause distributions
        return ThreadLocalRandom.current().nextLong(MAXIMUM_PAUSE_NANOS);
    }
}
