package org.example.uitests;

import org.example.uitests.utils.MyFilesUtils;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;

public class UploadDownloadFilesTests extends BaseTestClassUseProperties {

    @Test
    public void uploadTest() throws IOException {
        File file = MyFilesUtils.generateLoremFile();
        goToPart("upload");

        driver.findElement(By.id("file-upload")).sendKeys(file.getAbsolutePath());
        driver.findElement(By.id("file-submit")).click();

        Assert.assertEquals(driver.findElement(By.id("uploaded-files")).getText().trim(), file.getName());

        goToPart("download");
        Assert.assertTrue(driver.findElement(By.linkText(file.getName())).isDisplayed());

    }

    @Test
    public void downloadTest() throws IOException, InterruptedException {
        File file = MyFilesUtils.generateLoremFile();
        goToPart("upload");
        driver.findElement(By.id("file-upload")).sendKeys(file.getAbsolutePath());
        driver.findElement(By.id("file-submit")).click();

        Assert.assertEquals(driver.findElement(By.id("uploaded-files")).getText().trim(), file.getName());

        goToPart("download");
        driver.findElement(By.linkText(file.getName())).click();
        File file1 = MyFilesUtils.waitTillFileIsLoaded(new File("/Users/dmytro/Downloads", file.getName()));

        Assert.assertEquals(file.length(), file1.length());

        file1.deleteOnExit();

    }

}
