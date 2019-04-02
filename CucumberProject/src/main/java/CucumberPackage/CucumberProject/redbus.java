package CucumberPackage.CucumberProject;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



public class redbus {
	
	public static void main() throws InterruptedException
	{
			
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\dell\\Downloads\\chromedriver_win32\\chromedriver.exe");
			WebDriver driver =new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			driver.get("https://www.redbus.in/");
			Thread.sleep(5000);
			driver.findElement(By.id("src")).sendKeys("Mumbai");
			Thread.sleep(2000);

			driver.findElement(By.id("src")).sendKeys(Keys.DOWN);
		
	

}
}
