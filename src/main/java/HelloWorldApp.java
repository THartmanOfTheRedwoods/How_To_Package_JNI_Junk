import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class HelloWorldApp {
    /*
     * Loading the native library by extracting it from the Jar file and writing it to /tmp to
     * avoid security issues and dynamic linking issues on some systems.
     */
    static {
        // This does not appear to be able to find the library when it's in a
        // jar file. It was attempt 1, however.
        //System.loadLibrary("HelloWorld");

        String libraryName = "HelloWorld";
        try {
            String libName = "native-lib/" + System.mapLibraryName(libraryName);
            InputStream is = ClassLoader.getSystemResourceAsStream(libName);
            /* Alternate ways to load files from a jar file.
             * Method 1:
             * ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
             * InputStream is = classLoader.getResourceAsStream(libName);
             * Method 2:
             * InputStream inputStream = YourClass.class.getResourceAsStream("/"+libName);
             */
            if (is == null) {
                throw new UnsatisfiedLinkError("Library not found on classpath: " + libraryName);
            }
            File tempFile = File.createTempFile(libraryName, "." + System.mapLibraryName(libraryName));
            tempFile.deleteOnExit();

            try (FileOutputStream out = new FileOutputStream(tempFile)) {
                byte[] buffer = new byte[8192];
                int bytesRead;
                while ((bytesRead = is.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
            }

            System.load(tempFile.getAbsolutePath());
        } catch (IOException e) {
            throw new UnsatisfiedLinkError("Failed to load library: " + e.getMessage());
        }

    }

    /**
     * Utility method to let me check if the classpath at runtime is what I expect. This method can be deleted.
     */
    public static void printClassPath() {
        // Get the classpath as a single string
        String classpath = System.getProperty("java.class.path");
        // Split the classpath into individual entries
        String[] classpathEntries = classpath.split(System.getProperty("path.separator"));

        System.out.println("Classpath entries:");
        for (String entry : classpathEntries) {
            System.out.println(entry);
        }
    }

    /**
     * Native method in C++ that just prints Hello Trevor to demonstrate JNI
     */
    private native void sayHello();

    /**
     * main method for this application
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        HelloWorldApp app = new HelloWorldApp();
        app.sayHello();
    }
}
