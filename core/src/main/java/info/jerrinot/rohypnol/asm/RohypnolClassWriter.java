package info.jerrinot.rohypnol.asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

public class RohypnolClassWriter extends ClassVisitor {

    public RohypnolClassWriter(int api, ClassVisitor cv) {
        super(api, cv);
        this.cv = cv;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc,
                                     String signature, String[] exceptions) {

        MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
        RohypnolMethodVisitor mvw = new RohypnolMethodVisitor(api, mv);
        return mvw;
    }

}
