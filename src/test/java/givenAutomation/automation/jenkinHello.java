package givenAutomation.automation;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class jenkinHello extends EmailID {
	@Test
public void resumeUpDate() {
	launck(oldEmail);
	try {
		Thread.sleep(2000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	launck(newEmail);
	
}
	
	
	public static void launck(String email){

		System.out.println("Hello Mr. Virendra");


		ChromeOptions co = new ChromeOptions();
		co.addArguments("--remote-allow-origins=*");
//		co.addArguments("--headless");
		co.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
		WebDriver driver = new ChromeDriver(co);

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		driver.get("https://www.naukri.com/");
		driver.findElement(By.xpath("//a[@id='login_Layer']")).click();
//		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@placeholder='Enter your active Email ID / Username']"))
				.sendKeys(email);
		driver.findElement(By.xpath("//input[@placeholder='Enter your password']")).sendKeys("8542956411");
		driver.findElement(By.xpath("//button[text()='Login']")).click();

		driver.findElement(By.xpath("(//a[normalize-space()='View profile'])")).click();

		try {
			driver.findElement(By.xpath("(//span[@class='edit icon'][normalize-space()='editOneTheme'])[2]")).click();
		} catch (Exception e) {

		}
		try {
			driver.findElement(By.xpath("(//span[@class='edit icon'][normalize-space()='editOneTheme'])[2]")).click();
		} catch (Exception e) {

		}

		
		driver.findElement(By.xpath("//button[@id='saveKeySkills']")).click();
		driver.quit();
		
	}

}
