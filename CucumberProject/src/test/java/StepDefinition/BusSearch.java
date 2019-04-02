package StepDefinition;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class BusSearch {

	public WebDriver driver;

	@Given("^Open the chrome$")
	public void open_the_chrome() throws Throwable {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\dell\\Desktop\\New folder\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Given("^Launch the application$")
	public void launch_the_application() throws Throwable {
		driver.get("https://www.redbus.in/");
		Thread.sleep(5000);
		driver.manage().window().maximize();
	}

	@When("^Search for Bus Tickets from Mumbai to Nasik$")
	public void search_for_Bus_Tickets_from_Mumbai_to_Nasik() throws Throwable {

		driver.findElement(By.id("src")).sendKeys("Mumbai");
		Thread.sleep(2000);

		driver.findElement(By.id("src")).sendKeys(Keys.ENTER);
		driver.findElement(By.id("dest")).sendKeys("Nasik");
		Thread.sleep(2000);

		driver.findElement(By.id("dest")).sendKeys(Keys.ENTER);
		Thread.sleep(1000);
	}

	@When("^Select the journey date as date \"([^\"]*)\" , month \"([^\"]*)\" and year \"([^\"]*)\"$")
	public void select_the_journey_date_as_date_month_and_year(String arg1, int arg2, int arg3) throws Throwable {
		List<String> monthList = Arrays.asList("Jan", "Feb", "Mar", "Apr", "May", "Jun", "July", "Aug", "Sept", "Oct",
				"Nov", "Dec");

		String expDate = null;

		int expMonth;

		int expYear;

		String calDate = null;

		boolean dateNotFound;

		WebElement calendar = driver.findElement(By.xpath(".//input[@id='onward_cal']"));

		calendar.click();

		expDate = arg1;

		expMonth = arg2;

		expYear = arg3;

		dateNotFound = true;

		while (dateNotFound) {

			WebElement monthYearEle = driver
					.findElement(By.xpath(".//*[@id='rb-calendar_onward_cal']//table//td[@class='monthTitle']"));

			String monthYear = monthYearEle.getAttribute("innerHTML");

			String[] s = monthYear.split(" ");

			String calMonth = s[0];

			int calYear = Integer.parseInt(s[1]);

			if (monthList.indexOf(calMonth) + 1 == expMonth && expYear == calYear) {

				WebElement datePicker = driver.findElement(By.xpath(".//*[@id='rb-calendar_onward_cal']"));

				List<WebElement> dates = datePicker.findElements(By.tagName("td"));

				for (WebElement temp : dates) {

					if (temp.getText().equals(expDate)) {

						temp.click();

						break;

					}

				}

				dateNotFound = false;

			}

			else if (monthList.indexOf(calMonth) + 1 < expMonth && expYear == calYear || expYear > calYear) {

				calendar.findElement(By.xpath(".//*[@id='rb-calendar_onward_cal']//button[.='>']")).click();

			}

			else if (monthList.indexOf(calMonth) + 1 > expMonth && expYear == calYear || expYear < calYear) {

				calendar.findElement(By.xpath(".//*[@id='rb-calendar_onward_cal']//button[.='<']")).click();

			}

		}

	}
	@When("^Click on Search Buses button$")
	public void click_on_Search_Buses_button() throws Throwable {
		
		driver.findElement(By.xpath(".//*[@id='search_btn']")).click();
		Thread.sleep(2000);
	}

	@When("^Select Departure time as After (\\d+) PM$")
	public void select_Departure_time_as_After_PM(int arg1) throws Throwable {
		
		driver.findElement(By.id("dtAfter 6 pm")).click();
		Thread.sleep(2000);
	}

	@When("^Select Bus Type as Non AC$")
	public void select_Bus_Type_as_Non_AC() throws Throwable {
		
		driver.findElement(By.id("bt_NONAC")).click();
		Thread.sleep(2000);
	}

	@When("^Select the (\\d+) Available Seats$")
	public void select_the_Available_Seats(int arg1) throws Throwable {
		
		List<WebElement> busList=driver.findElements(By.cssSelector("div[class='button view-seats fr']"));
		int busCount=driver.findElements(By.cssSelector("div[class='button view-seats fr']")).size();
		driver.findElements(By.cssSelector("div[class='button view-seats fr']")).get(1).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("canvas[data-type='lower']")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("canvas[data-type='lower']")).click();
		Thread.sleep(1000);
				
		 
	}

	@When("^Select the Boarding and Dropping Point$")
	public void select_the_Boarding_and_Dropping_Point() throws Throwable {
		List <WebElement> boardingPoint=driver.findElements(By.xpath("//ul[@data-value=\"bp\"]//li"));
		int bp=driver.findElements(By.xpath("//ul[@data-value=\"bp\"]//li")).size();
		
		for(int i=0;i<bp;i++)
		{
			if(driver.findElement(By.xpath("//ul[@data-value=\"bp\"]//li["+i+"]//div[3]//span")).getText().equals("Borivali-(w) Neeta Travels,  Opp. Gokul Hotel")) {
				driver.findElement(By.xpath("//ul[@data-value=\"bp\"]//li["+i+"]//div[1]")).click();
				break;
			}
			
		}
		
		List <WebElement> droppingPoint=driver.findElements(By.xpath("//ul[@data-value=\"dp\"]//li"));
		int dp=driver.findElements(By.xpath("//ul[@data-value=\"dp\"]//li")).size();
		
		for(int i=0;i<bp;i++)
		{
			if(driver.findElement(By.xpath("//ul[@data-value=\"dp\"]//li["+i+"]//div[3]//span")).getText().equals("Saroj Travels- Mumbai Naka, Mylan Circle")) {
				driver.findElement(By.xpath("//ul[@data-value=\"dp\"]//li["+i+"]//div[1]")).click();
				break;
			}
			
		}
		
	}

	@When("^Click on Proceed to Book$")
	public void click_on_Proceed_to_Book() throws Throwable {
		
		driver.findElement(By.xpath("//*[@id=\"10628569\"]/div[2]/div[2]/div[2]/div[2]/div/div[2]/div/div/div[2]/button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//*[@id=\"10628569\"]/div[2]/div[2]/div[2]/div[2]/div/div[2]/div/div/div[1]/div[2]/div[6]/button")).click();
		Thread.sleep(1000);
	}

	@When("^On Passenger Details, Select I don't want insurance$")
	public void on_Passenger_Details_Select_I_don_t_want_insurance() throws Throwable {
		
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[4]/div[2]/div/div[5]/div/div[2]/div/label[2]/span")).click();
	}

	@Then("^Verify whether the Total Amount Displayed on Passenger Details is the same as displayed on Select the Boarding and Dropping Point$")
	public void verify_whether_the_Total_Amount_Displayed_on_Passenger_Details_is_the_same_as_displayed_on_Select_the_Boarding_and_Dropping_Point()
			throws Throwable {
		String amount=driver.findElement(By.xpath("//*[@id=\"10628569\"]/div[2]/div[2]/div[2]/div[2]/div/div[2]/div/div/div[2]/div[2]/span[3]/span[2]")).getText();
		String amountFinal=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[4]/div[3]/div/div[1]/div[2]/span")).getText();
		
		if(amount.equals(amountFinal))
		{
			System.out.println("Validated:- Amount same ");
		}
	
	}
	
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

}
