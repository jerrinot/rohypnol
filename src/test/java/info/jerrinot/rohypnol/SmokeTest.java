package info.jerrinot.rohypnol;

import info.jerrinot.rohypnol.asm.RohypnolClassWriter;
import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class SmokeTest {

    @Test
    public void smoke() throws Exception {
        InputStream in = SmokeTest.class.getResourceAsStream("/info/jerrinot/rohypnol/MyClass.class");

        ClassReader classReader = new ClassReader(in);
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);

        RohypnolClassWriter mcw = new RohypnolClassWriter(Opcodes.ASM4, cw);
        classReader.accept(mcw, 0);

        byte[] bytes = cw.toByteArray();
        URLClassLoader loader = new URLClassLoader(new URL[0], getClass().getClassLoader()) {
            @Override
            protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
                if (name.equals(MyClass.class.getName())) {
                    Class<?> aClass = defineClass(name, bytes, 0, bytes.length);
                    return aClass;
                } else {
                    return super.loadClass(name, resolve);
                }
            }
        };

        Class<?> clazz = loader.loadClass(MyClass.class.getName());
        callMethod(clazz, "regularMethod");
    }

    private void callMethod(Class<?> clazz, String name) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Method method = clazz.getMethod(name);
        method.invoke(null);
    }
}
