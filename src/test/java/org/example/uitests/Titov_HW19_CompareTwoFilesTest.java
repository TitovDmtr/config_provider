package org.example.uitests;

import org.apache.commons.io.FileUtils;
import org.example.uitests.pages.FileDownloadDemoPage;
import org.example.uitests.utils.MyFilesUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;

public class Titov_HW19_CompareTwoFilesTest extends BaseTestClassUseProperties{

    @BeforeMethod
    public void beforeMethod() {
        goToUrl();
    }

    @Test
    public void compareTwoFilesTest () throws IOException, InterruptedException {
        File generatedFile = MyFilesUtils.generateLoremFile();
        String dataFromGeneratedFile = FileUtils.readFileToString(generatedFile, "UTF-8");

        FileDownloadDemoPage downloadPage = new FileDownloadDemoPage().generateFile(dataFromGeneratedFile);

        File downloadedFile = MyFilesUtils.waitTillFileIsLoaded(new File("/Users/dmytro/Downloads", "easyinfo.txt")); //website always generate file with this filename
        String dataFromDownloadedFile = FileUtils.readFileToString(downloadedFile, "UTF-8");

        Assert.assertEquals(dataFromGeneratedFile, dataFromDownloadedFile);

        generatedFile.deleteOnExit();
        downloadedFile.deleteOnExit();
    }
}
