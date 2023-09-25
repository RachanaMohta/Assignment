package Assignment;
import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class bookMyShow {

	public static void main(String[] args) throws InterruptedException {
		// Set the path to your ChromeDriver executable
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win32\\chromedriver.exe");

		// Initialize ChromeDriver
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		// Step 1: Open https://in.bookmyshow.com/explore/home/
		driver.get("https://in.bookmyshow.com/explore/home/");

		// Step 2: Select Bengaluru as the city (if asked)
		WebElement cityDropdown = driver.findElement(By.xpath("//input[@class='bwc__sc-1iyhybo-6 ilhhay']"));
		cityDropdown.sendKeys("Bengaluru");
		WebElement dropDownvalue= driver.findElement(By.xpath("//li[@class='bwc__sc-1iyhybo-9 fMpEag']//span[@class='bwc__sc-ttnkwg-14 flGQbT']/strong"));
		//cityDropdown.click();
		Thread.sleep(2000);
		dropDownvalue.click();

		// Step 3: Click on Sign In
		WebElement signInButton = driver.findElement(By.xpath("//div[contains(text(),'Sign in')]"));
		signInButton.click();
		Thread.sleep(2000);

		// Step 4: Click on Continue with Email
		WebElement continueWithEmailButton = driver.findElement(By.xpath("(//div[@class='bwc__sc-dh558f-14 fPrBPf'])[2]"));
		continueWithEmailButton.click();
		Thread.sleep(2000);

		// Step 5: Enter selauto@yopmail.com and click on continue
		WebElement emailField = driver.findElement(By.xpath("//input[@id='emailId']"));
		emailField.sendKeys("selauto@yopmail.com");
		Thread.sleep(2000);
		WebElement continueButton = driver.findElement(By.xpath("//div[@class='bwc__sc-dh558f-1 kQRABK']"));
		continueButton.click();
		Thread.sleep(2000);
		String bookMyShowAdd= driver.getWindowHandle();

		driver.switchTo().newWindow(WindowType.TAB);

		// Step 6: Go to Yopmail.com and access inbox
		driver.get("http://www.yopmail.com/en/");
		WebElement yopmailEmailField = driver.findElement(By.id("login"));
		yopmailEmailField.sendKeys("selauto@yopmail.com");
		WebElement checkInboxButton = driver.findElement(By.xpath("//i[@class='material-icons-outlined f36']"));
		checkInboxButton.click();
		Thread.sleep(2000);

		driver.switchTo().frame(2);
		WebElement copyOtpValue=driver.findElement(By.cssSelector("body > main:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1)"));
		System.out.println(copyOtpValue.getText());
		String otpValue=copyOtpValue.getText();

		driver.switchTo().window(bookMyShowAdd);
		System.out.println("Main window");


		//Step 7: Come back to Sign in Page and enter the OTP

		WebElement otpField = driver.findElement(By.xpath("//input[@class='bwc__sc-rwpctr-1 jLBVFy']"));
		otpField.sendKeys(otpValue);
		Thread.sleep(2000);
		WebElement otpBtn = driver.findElement(By.xpath("//button[contains(text(),'Continue')]"));
		otpBtn.click();
		System.out.println("Button clicked");

		// Step 8: Validate user is successfully signed in and "Hi, Guest" is displayed.
		WebElement greetingMessage = driver.findElement(By.xpath("//span[@class='bwc__sc-1nbn7v6-12 cQWvYS']"));
		if (greetingMessage.isDisplayed()) {
			System.out.println("User successfully signed in: " + greetingMessage.getText());
		}

		// Close the browser
		driver.quit();
	}
}

