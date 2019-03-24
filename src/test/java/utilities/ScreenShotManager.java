package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ScreenShotManager {

    private static void createNewFolder(String path, String folder) {
        File fold = new File(path + folder);
        fold.mkdir();
    }

    public static void takeScreenShotTest(WebDriver driver, String imageName, String folder) {
        String path = "images/";

        createNewFolder(path, folder);

        File directory = new File(path + folder);
        try {
            if (directory.isDirectory()) {
                File image = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(image, new File(directory.getAbsolutePath() + "\\" + imageName + ".png"));
            } else {
                throw new IOException("ERROR : the path is not a directory!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void takeAlertScreenshot() {
        String path = "images";
        String folder = "/successfulRegister";
        createNewFolder(path, folder);
        path += folder;

        BufferedImage image = null;
        try {
            image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        } catch (AWTException e) {
            e.printStackTrace();
        }
        try {
            ImageIO.write(image, "png", new File(path + "/Test.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
