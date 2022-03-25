package pages;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import helpers.helpers;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class PageLogin<WebElement> {
	private WebDriver driver;
	private By userField;
	private By passField;
	private By loginButton;
	private By fields;

	public PageLogin(WebDriver driver) {
		this.driver = driver;
		userField = By.name("userName");
		passField = By.name("password");
		loginButton = By.name("submit");
		fields = By.tagName("input");
	}

	public void login(String user, String pass) {
		driver.findElement(userField).sendKeys(user);
		driver.findElement(passField).sendKeys(pass);
		driver.findElement(loginButton).click();
		// SCREENSHOTS a un Escenario específico LOGIN ejemplo
		File mySS = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(mySS, new File("LOGIN" + System.currentTimeMillis() + ".png"));
		} catch (IOException e) {
			// TODO autogenerated
			e.printStackTrace();
		}
		// SCREENSHOTS a un Escenario que falle ver en After Methos en Tests

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// helpers test = new helpers();
		// test.sleepSeconds(4);
	}

	// FIND ELEMENTS
	public void fields_login(String user, String pass) {
		List<org.openqa.selenium.WebElement> loginFields = driver.findElements(fields);
		loginFields.get(1).sendKeys(user); // input 2 del html
		loginFields.get(2).sendKeys(pass);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public void verifyFields() {
		List<org.openqa.selenium.WebElement> loginFields = driver.findElements(fields);
		// System.out.println(loginFields.size(loginFields.size()));//poner para ver si
		// hay resultados
		Assert.assertTrue(loginFields.size() == 5); // verificar que hay 5 input en el html
	}

}
