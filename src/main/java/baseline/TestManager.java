package baseline;

import java.io.*;
import java.util.Properties;
public class TestManager implements Expectations {
    private Properties properties;
    private final String fileName;

    public TestManager(String testName) {
        fileName = testName;
        getProperties();
    }

    @Override
    public String expectedValue(String name, String actualValue) {
        if (properties.getProperty(name) != null) {
            return properties.getProperty(name);
        } else {
            properties.setProperty(name, actualValue);
            write();
            return ""; // test will fail if no cached value was found, but it will pass on the second attempt
        }
    }

    // Get the properties and assign to field, if file doesn't exist, create a new one
    private void getProperties() {

        // create the file with this name if it doesn't already exist
        if (! fileExists()) {
            create();
        }

        // read the file and assign properties to properties field
        try (InputStream input = new FileInputStream(GlobalManager.getBaselinePath() + fileName + ".properties")) {
            properties = new Properties();
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // return true if the file exists of the specified name in the default location
    private boolean fileExists() {
        return new File(GlobalManager.getBaselinePath() + fileName + ".properties").exists();
    }

    // Create a new Properties file using the name provided
    private void create() {
        properties = new Properties();
        try (OutputStream output = new FileOutputStream(GlobalManager.getBaselinePath() + fileName + ".properties")) {
            properties.store(output, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void write() {
        try (OutputStream output = new FileOutputStream(GlobalManager.getBaselinePath() + fileName + ".properties")) {
            properties.store(output, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
