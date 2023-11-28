package org.example.uitests.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;

public class FileDownloadDemoPage extends BasePage{

    @FindBy(id="textbox")
    private WebElement textInput;

    @FindBy(id="create")
    private WebElement generateFileButton;

    @FindBy(id="link-to-download")
    private WebElement linkToDownload;

    public FileDownloadDemoPage() {
        super();
    }
    private FileDownloadDemoPage setTextInput(String text) {
        textInput.clear();
        textInput.sendKeys(text);
        return this;
    }

    private void clickGenerateFileButton() {
        generateFileButton.click();
    }

    private void clickLinkToDownload() {
        linkToDownload.click();
    }

    public FileDownloadDemoPage generateFile(String text) throws IOException, InterruptedException {
        setTextInput(text).clickGenerateFileButton();
        clickLinkToDownload();
        return new FileDownloadDemoPage();
    }
}
