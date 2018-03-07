package info.jerrinot.rohypnol;

import info.jerrinot.rohypnol.agent.Transformer;

import java.lang.instrument.Instrumentation;

public class RohypnolAgent {
    public static void premain(String args, Instrumentation instrumentation){
        if (Config.CLASS_PREFIX == null || Config.CLASS_PREFIX.isEmpty()) {
            throw new IllegalArgumentException("Class prefix is not configured. " +
                    "Use property '" + Config.CLASS_PREFIX_KEY + "' to select classes to instrument. ");
        }

        System.out.println("Running Rohypnol agent with class prefix '" + Config.CLASS_PREFIX + "'"
            + ", minimum interval " + Config.MINIMUM_INTERVAL_BETWEEN_PAUSES_NANOS + "ns"
                + " and maximum pause " + Config.MAXIMUM_PAUSE_NANOS + "ns.");
        Transformer transformer = new Transformer();
        instrumentation.addTransformer(transformer);
    }
}
