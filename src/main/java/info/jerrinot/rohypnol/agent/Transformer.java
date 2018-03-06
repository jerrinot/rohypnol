package info.jerrinot.rohypnol.agent;

import info.jerrinot.rohypnol.asm.RohypnolClassWriter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

public class Transformer implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer)  {

        //todo: better selector
        if (className.startsWith("com/hazelcast/")) {
//            System.out.println("processing " + className);
            ClassReader reader = new ClassReader(classfileBuffer);
            ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
            RohypnolClassWriter mcw = new RohypnolClassWriter(Opcodes.ASM4, cw);
            reader.accept(mcw, 0);
            return cw.toByteArray();
        } else {
//            System.out.println("skipping " + className);
            return classfileBuffer;
        }
    }
}
