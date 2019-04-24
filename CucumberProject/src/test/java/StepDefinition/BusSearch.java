package StepDefinition;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;


import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.junit.Assertions;


public class BusSearch {

	public WebDriver driver;

	@Given("^Open the chrome$")
	public void open_the_chrome() throws Throwable {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\sugupta\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Given("^Launch the application$")
	public void launch_the_application() throws Throwable {
		
		driver.manage().window().maximize();
		driver.get("https://www.redbus.in");
		Thread.sleep(1000);
		
	}

	@When("^Search for Bus Tickets from Mumbai to Nasik$")
	public void search_for_Bus_Tickets_from_Mumbai_to_Nasik() throws Throwable {

		driver.findElement(By.id("src")).sendKeys("Mumbai");
		driver.findElement(By.xpath("//ul[@class='autoFill']//li[4]")).click();
		Thread.sleep(1000);

		driver.findElement(By.id("src")).sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		driver.findElement(By.id("dest")).sendKeys("Nasik");
		Thread.sleep(2000);

		driver.findElement(By.id("dest")).sendKeys(Keys.ENTER);
		Thread.sleep(1000);
	}

	@When("^Select the journey date as date \"([^\"]*)\" , month \"([^\"]*)\" and year \"([^\"]*)\"$")
	public void select_the_journey_date_as_date_month_and_year(String arg1, int arg2, int arg3) throws Throwable {
		
		
		((JavascriptExecutor)driver).executeScript ("document.getElementById('onward_cal').removeAttribute('readonly',0);"); // Enables the from date box

		WebElement fromDateBox= driver.findElement(By.id("onward_cal"));
		fromDateBox.clear();
		fromDateBox.sendKeys("07-Jun-2019"); 
		//fromDateBox.sendKeys(Keys.ENTER);
		
		
		((JavascriptExecutor)driver).executeScript ("document.getElementById('return_cal').removeAttribute('readonly',0);"); // Enables the to date box

		WebElement toDateBox= driver.findElement(By.id("return_cal"));
		toDateBox.clear();
		toDateBox.sendKeys("14-Jun-2019"); 
		//toDateBox.sendKeys(Keys.ENTER);
		
		
	}
	
	@When("^Click on Search Buses button$")
	public void click_on_Search_Buses_button() throws Throwable {
		
		driver.findElement(By.xpath(".//*[@id='search_btn']")).click();
		Thread.sleep(5000);
	}

	@When("^Select Departure time as After (\\d+) PM$")
	public void select_Departure_time_as_After_PM(int arg1) throws Throwable {
		
		String s="After 6 pm";
		
		driver.findElement(By.xpath("//ul[contains(@class,'dt-time-filter')]/li/label[@title='"+s+"']")).click();
		
	}

	@When("^Select Bus Type as Non AC$")
	public void select_Bus_Type_as_Non_AC() throws Throwable {
		
		String s="bt_NONAC";
		driver.findElement(By.xpath("//ul[@class='list-chkbox']/li/label[@for='"+s+"']")).click();
		
	}

	@When("^Select the (\\d+) Available Seats$")
	public void select_the_Available_Seats(int arg1) throws Throwable {
		
		List<WebElement> busList=driver.findElements(By.xpath("//ul[@class='bus-items']/div"));
		int size=busList.size();
		System.out.println(size);
		int x=1;
		for(WebElement bt : busList)
		{
		
			String myString=driver.findElement(By.xpath("//ul[@class='bus-items']/div["+x+"]/li/div/div/div/div[7]/div[2]")).getText();
			//String myString=bt.findElement(By.xpath("//li/div/div/div/div[7]/div[2]")).getText();
			System.out.println("loop inside");
			System.out.println(myString);
			String split[]=myString.split(" ");
			System.out.println(split[0]);
		
			int i = Integer.parseInt(split[0]);
			String id1=driver.findElement(By.xpath("//ul[@class='bus-items']/div["+x+"]/li")).getAttribute("id");
			System.out.println(id1);
	
			if(i>40)
			{
				System.out.println(x);
				System.out.println(id1);
				
				WebElement element = driver.findElement(By.xpath("//ul[@class='bus-items']/div["+x+"]/li/div/div[2]/div[contains(@class,'view-seats') and text()='View Seats']"));
				JavascriptExecutor executor = (JavascriptExecutor)driver;
				executor.executeScript("arguments[0].click();", element);
				Thread.sleep(2000);
				System.out.println("view seats");
				
				break;
				
			}
			x++;
		}
		
		 Thread.sleep(10000);
	}

	@When("^Select the Boarding and Dropping Point$")
	public void select_the_Boarding_and_Dropping_Point() throws Throwable {
		List <WebElement> boardingPoint=driver.findElements(By.xpath("//ul[@data-value=\"bp\"]//li"));
		int bp=driver.findElements(By.xpath("//ul[@data-value=\"bp\"]//li")).size();
		System.out.println(bp);
		
		for(int i=1;i<bp;i++)
		{
			String boardingPoint1=driver.findElement(By.xpath("//ul[@data-value=\"bp\"]//li["+i+"]//div[3]//span")).getText();
			System.out.println(boardingPoint1);
			
			if(driver.findElement(By.xpath("//ul[@data-value=\"bp\"]//li["+i+"]//div[3]//span")).getText().contains("Chembur-(E) Yogi Restaurant")) {
				
				System.out.println("inside loop");
				WebElement element=driver.findElement(By.xpath("//ul[@data-value=\"bp\"]//li["+i+"]//div[1]"));
				JavascriptExecutor executor = (JavascriptExecutor)driver;
				executor.executeScript("arguments[0].click();", element);
				Thread.sleep(2000);
				break;
			}
			
			
		}
		Thread.sleep(1000);
		List <WebElement> droppingPoint=driver.findElements(By.xpath("//ul[@data-value=\"dp\"]//li"));
		int dp=driver.findElements(By.xpath("//ul[@data-value=\"dp\"]//li")).size();
		
		for(int i=1;i<dp;i++)
		{
			if(driver.findElement(By.xpath("//ul[@data-value=\"dp\"]//li["+i+"]//div[3]//span")).getText().equals("Nashik")) {
				
				WebElement element=driver.findElement(By.xpath("//ul[@data-value=\"dp\"]//li["+i+"]//div[1]"));
				element.click();
				/*JavascriptExecutor executor = (JavascriptExecutor)driver;
				executor.executeScript("arguments[0].click();", element);
				Thread.sleep(2000);*/
				break;
				
			}
			
		}
		Thread.sleep(1000);
	}
	
	public String amount;
	public int amount1;
	
	@When("^Click on Proceed to Book$")
	public void click_on_Proceed_to_Book() throws Throwable {
		
		 
		driver.findElement(By.xpath("//button[text()='continue']")).click();
		Thread.sleep(1000);
		 amount=driver.findElement(By.xpath("//div[@class='fare-summary-container']//span[2]//span[2]")).getText();
		amount1=Integer.parseInt(amount);
		 System.out.println(amount1);
		driver.findElement(By.xpath("//button[text()='Proceed to book']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id=\"seatno-04\"]")).sendKeys("surbhi");
	}

	@When("^On Passenger Details, Select I don't want insurance$")
	public void on_Passenger_Details_Select_I_don_t_want_insurance() throws Throwable {
		
		//WebElement Element=driver.findElement(By.xpath("//input[@id='insuranceNotOpted']//span"));
		//driver.findElement(By.xpath("//div[@id='mBWrapper']//div[1]//div[1]//div[@data-view=\"custinfoView\"]"));
	
		JavascriptExecutor js = (JavascriptExecutor)driver;
		//js.executeScript("arguments[0].scrollIntoView();",Element );
		js.executeScript("window.scrollBy(0,1000)");
		//*[@id="RUCFeatureCheckBox"]
		driver.findElement(By.xpath("//input[@id='insuranceNotOpted']//span")).click();
	}

	@Then("^Verify whether the Total Amount Displayed on Passenger Details is the same as displayed on Select the Boarding and Dropping Point$")
	public void verify_whether_the_Total_Amount_Displayed_on_Passenger_Details_is_the_same_as_displayed_on_Select_the_Boarding_and_Dropping_Point()
			throws Throwable {
	
		
		String s=driver.findElement(By.xpath("//div[contains(@class,'booking-amt')]//span")).getText();
		int s1=Integer.parseInt(s);
		
		Assert.assertEquals(amount, s1);
	
	}
	
	
	
	/*
	@When("^Click on Hotels button$")
	public void click_on_Hotels_button() throws Throwable {
		
	    driver.findElement(By.xpath("//*[@id=\"page_main_header\"]/nav/ul/li[2]/a")).click();
	    Thread.sleep(2000);
	}

	@When("^Search for Hotels in Mumbai$")
	public void search_for_Hotels_in_Mumbai() throws Throwable {
		
		driver.findElement(By.id("search_key")).sendKeys("Mumbai");
		Thread.sleep(2000);

		driver.findElement(By.id("search_key")).sendKeys(Keys.ENTER);
	}


	@When("^Click on Search button$")
	public void click_on_Search_button() throws Throwable {
		
	    driver.findElement(By.id("search_hotel")).click();
	    Thread.sleep(2000);
	}

	@When("^Select location as \"([^\"]*)\"$")
	public void select_location_as(String arg1) throws Throwable {
		driver.findElement(By.id("location_search")).sendKeys(arg1);
		List<WebElement> locationList=driver.findElements(By.xpath("//div[@id='all_location']//ul//li"));
		int locationCount=locationList.size();
		
		for(int i=0;i<locationCount;i++)
		{
			if (driver.findElement(By.xpath("//div[@id='all_location']//ul//li["+i+"]")).getAttribute("data-value").equals("ANDHERI"))
			{
				driver.findElement(By.xpath("//div[@id='all_location']//ul//li["+i+"]//child::label[1]")).click();
				break;
			}
		}
	    
	}

	@When("^Verify whether the results are getting displayed for Andheri, Mumbai Location$")
	public void verify_whether_the_results_are_getting_displayed_for_Andheri_Mumbai_Location() throws Throwable {
	  
	}
*/
}
