package info.jerrinot.rohypnol.asm;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class RohypnolMethodVisitor extends MethodVisitor {

    public RohypnolMethodVisitor(int api, MethodVisitor mv) {
        super(api, mv);
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
        super.visitMethodInsn(opcode, owner, name, desc, itf);
        // inject rohypnol just after a method call
        // todo:
        //      1. configurable frequency
        //      2. perhaps we can inject the pauses into other places
        super.visitMethodInsn(Opcodes.INVOKESTATIC, "info/jerrinot/rohypnol/RohypnolPill", "use", "()V", false);
    }
}
