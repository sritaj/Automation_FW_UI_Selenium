package utils;

import driver.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public final class TakeScreenshotImpl {

    private TakeScreenshotImpl(){}

    /**
     * Method to take Screenshot
     *
     * @return Screenshot as Base64 image
     */
    public static String takeScreenshotAsBase64() {

        String scnShot = null;
        try {
            scnShot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BASE64);
        } catch (Exception e) {
            System.err.println("Exception while taking screenshot " + e.getMessage());
        }
        return scnShot;
    }
}
