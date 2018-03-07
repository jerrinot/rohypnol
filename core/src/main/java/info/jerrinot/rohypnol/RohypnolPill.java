package info.jerrinot.rohypnol;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.LockSupport;

import static info.jerrinot.rohypnol.Config.MAXIMUM_PAUSE_NANOS;
import static info.jerrinot.rohypnol.Config.MINIMUM_INTERVAL_BETWEEN_PAUSES_NANOS;


public final class RohypnolPill {
    private static final ThreadLocal<Long> LAST_PAUSE = new ThreadLocal<>();

    public static void use() {
        long now = System.nanoTime();
        if (!shouldPause(now)) {
            return;
        }
        long pauseNanos = calculatePauseNanos();
        LockSupport.parkNanos(pauseNanos);
        LAST_PAUSE.set(now + pauseNanos);
    }

    private static boolean shouldPause(long now) {
        Long lastPause = LAST_PAUSE.get();
        if (lastPause == null) {
            return true;
        } else if (lastPause < now - MINIMUM_INTERVAL_BETWEEN_PAUSES_NANOS) {
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
