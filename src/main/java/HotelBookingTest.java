import com.sun.javafx.PlatformUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HotelBookingTest {

    WebDriver driver;

    @FindBy(linkText = "Hotels")
    private WebElement hotelLink;

    @FindBy(id = "Tags")
    private WebElement localityTextBox;

    @FindBy(id = "SearchHotelsButton")
    private WebElement searchButton;

    @FindBy(id = "travellersOnhome")
    private WebElement travellerSelection;
    
    @BeforeClass
    public void setUp()
    {
    	ChromeOptions option = new ChromeOptions();
    	option.addArguments("--disable-notifications");
    	driver =new ChromeDriver(option);
    	
    }

    @Test
    public void shouldBeAbleToSearchForHotels() {
        setDriverPath();
        driver.manage().window().maximize();
        driver.get("https://www.cleartrip.com/");
        WebDriverWait wait = new WebDriverWait (driver, 25);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.linkText("Your trips"))));
        
        hotelLink.click();
        waitFor(10,By.linkText("Hotels"));
        localityTextBox.sendKeys("Indiranagar, Bangalore");

        new Select(travellerSelection).selectByVisibleText("1 room, 2 adults");
        searchButton.click();

        

    }
    
    private void waitFor(int durationInSeconds , By by) {
		
    	WebDriverWait wait = new WebDriverWait (driver, durationInSeconds);
    	//Wait for durationInSeconds until element is clickable
    	wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(by)));
    }
    
    @AfterClass
    public void tearDown()
    {
    	//close the browser
    	driver.quit();
    	
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
