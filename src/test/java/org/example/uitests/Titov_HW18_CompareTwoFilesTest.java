package org.example.uitests;

import org.apache.commons.io.FileUtils;
import org.example.uitests.utils.MyFilesUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;

public class Titov_HW18_CompareTwoFilesTest extends BaseTestClassUseProperties{

    @Test
    public void compareTwoFilesTest () throws IOException, InterruptedException {
        File localFile = new File("files/HW18_lorem.txt");
        String dataFromLocalFile = FileUtils.readFileToString(localFile, "UTF-8");

        driver.get("https://demo.seleniumeasy.com/generate-file-to-download-demo.html");

        WebElement inputField = driver.findElement(By.id("textbox"));
        inputField.clear();
        inputField.sendKeys(dataFromLocalFile);

        driver.findElement(By.id("create")).click();
//        //wiiter
        driver.findElement(By.id("link-to-download")).click();
        File downloadedFile = MyFilesUtils.waitTillFileIsLoaded(new File("/Users/dmytro/Downloads", "easyinfo.txt")); //website always generate file with this filename
        String dataFromDownloadedFile = FileUtils.readFileToString(downloadedFile, "UTF-8");

        Assert.assertEquals(dataFromLocalFile, dataFromDownloadedFile);

        downloadedFile.deleteOnExit();
    }
}
