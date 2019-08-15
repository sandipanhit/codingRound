import com.sun.javafx.PlatformUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignInTest {

    WebDriver driver = new ChromeDriver();

    @Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() {

        setDriverPath();

        driver.get("https://www.cleartrip.com/");
        //waitFor(2000);
        
        waitFor(10,By.linkText("Your trips"));

        driver.findElement(By.linkText("Your trips")).click();
        driver.findElement(By.id("SignIn")).click();

        driver.findElement(By.id("signInButton")).click();

        String errors1 = driver.findElement(By.id("errors1")).getText();
        Assert.assertTrue(errors1.contains("There were errors in your submission"));
        driver.quit();
    }

    private void waitFor(int durationInSeconds , By by) {
		
    	WebDriverWait wait = new WebDriverWait (driver, durationInSeconds);
    	//Wait for durationInSeconds until element is clickable
    	wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(by)));
    }

    private void setDriverPath() {
        if (PlatformUtil.isMac()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver");
        }
        if (PlatformUtil.isWindows()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        }
        if (PlatformUtil.isLinux()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
        }
    }


}
