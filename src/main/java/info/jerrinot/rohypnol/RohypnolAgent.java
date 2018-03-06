package info.jerrinot.rohypnol;

import info.jerrinot.rohypnol.agent.Transformer;

import java.lang.instrument.Instrumentation;

public class RohypnolAgent {
    public static void premain(String args, Instrumentation instrumentation){
        System.out.println("Running Rohypnol agent");

        Transformer transformer = new Transformer();
        instrumentation.addTransformer(transformer);
    }
}
