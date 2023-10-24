package org.example.uitests;

import org.example.uitests.utils.ConfigProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTestsUseProperties extends BaseTestClassUseProperties {

    private static final String USER_NAME = ConfigProvider.getInstance().getProperty("login.tests.username");
    private static final String USER_PASS = ConfigProvider.getInstance().getProperty("login.tests.password");

    @BeforeMethod
    public void beforeMethod() {
        goToPart("login");
    }

    @AfterMethod
    public void afterMethod() {
        driver.findElement(By.xpath("//*[@id='content']/div/a/i")).click(); // Logout
    }

    @Test
    public void loginTestWithXpath() {
        WebElement userNameField = driver.findElement(By.xpath("//*[@id='username']"));
        WebElement userPassField = driver.findElement(By.xpath("//*[@id='password']"));

        userNameField.clear();
        userNameField.sendKeys(USER_NAME);

        userPassField.clear();
        userPassField.sendKeys(USER_PASS);

        WebElement buttonLogin = driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']"));
        buttonLogin.click();
        WebElement textLogin = driver.findElement(By.xpath("//*[@id='content']/div/h4"));

        Assert.assertEquals(textLogin.getText(), "Welcome to the Secure Area. When you are done click logout below.");
    }

}
