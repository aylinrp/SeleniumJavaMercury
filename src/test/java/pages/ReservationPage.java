package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ReservationPage {
	private WebDriver driver;
	private By passengersdrop;
	private By flight;
	private By fromDrop;
	private By toDrop;
	
	public ReservationPage(WebDriver driver) {
		this.driver = driver;
		passengersdrop = By.name("passCount");
		fromDrop = By.name("fromPort");	
		toDrop = By.name("toPort");
		
	}
	public void assertPage() {
				Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[3]/td/font")).getText().contains("Flight Finder to search"));
	}
	
	public void selectPAssengers (int cant) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement cantidadPasajeros = wait.until(ExpectedConditions.presenceOfElementLocated(passengersdrop));
		Select selectPassengers = new Select(cantidadPasajeros);
		selectPassengers.selectByVisibleText(Integer.toString(cant));
		
	}
	
	public void selectFromPort(int index) {
		Select selectFrom = new Select (driver.findElement(fromDrop));
		selectFrom.selectByIndex(index);
	}
	
	public void selectToPort(String city) {
		Select selectTo= new Select(driver.findElement(toDrop));
		selectTo.selectByValue(city);
	}
	
}
