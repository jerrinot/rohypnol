package info.jerrinot.rohypnol;

public class MyClass {

    public static void regularMethod() {
        System.out.println("one command");
        System.out.println("another one");
    }

    public static void toBeInstrumented() {
        System.out.println("Calling instrumented method");
    }
}
