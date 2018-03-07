package info.jerrinot.rohypnol.agent;

import info.jerrinot.rohypnol.Config;
import info.jerrinot.rohypnol.asm.RohypnolClassWriter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

public class Transformer implements ClassFileTransformer {

    private final String classPrefix;

    public Transformer() {
        this.classPrefix = Config.CLASS_PREFIX.replace('.', '/');
    }


    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer)  {

        if (className.startsWith(classPrefix)) {
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
