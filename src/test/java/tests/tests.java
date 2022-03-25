package tests;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import helpers.ScreenShotsTest;
import helpers.WebDriverManager;
import helpers.helpers;
import pages.PageLogin;
import pages.ReservationPage;

public class tests {
	private WebDriver driver;
	ArrayList<String> tabs;

	@BeforeMethod
	public void setUp() {
		DesiredCapabilities caps = new DesiredCapabilities();
		System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		// driver.manage().window().maximize();// para manipular diferentes tipós de
		// tamaño del browser
		// driver.manage().window().fullscreen(); // pntalla completa
		// driver.manage().window().setSize(new Dimension(800, 600));// size específicos
		// usando la clase Dimensions de
		// Selenium
		// OEDENANDO CÓDIGO
		WebDriverManager.setWindowSize(driver, "maximized");
		// Poistion del Browser
		driver.manage().window().setPosition(new Point(400, 300));
		driver.navigate().to("https://demo.guru99.com/test/newtours/");
		// VARIOS TABS se usa JAVASCRIPT
		JavascriptExecutor javaScript = (JavascriptExecutor) driver;
		String googleWindow = "window.open('http://www.google.com')";
		javaScript.executeScript(googleWindow);
		tabs = new ArrayList<String>(driver.getWindowHandles());

		helpers help = new helpers();
		help.sleepSeconds(5);

	}

	@Test
	public void pruebaUno() {
		// Manipulando tabs
		driver.switchTo().window(tabs.get(1)).navigate().to("http://www.google.com");
		driver.switchTo().window(tabs.get(0)); // para dejar el foco en la primera tab donde se ejecutan los test
		PageLogin loginP = new PageLogin(driver);
		loginP.login("user", "user");
		Assert.assertTrue(driver.findElement(By.xpath(
				"/html/body/div[2]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[1]/td/h3"))
				.getText().contains("Login Successfully"));
		// Assert.assertTrue(driver.findElement(By.className("container-fluid")).getText().contains("Login
		// Successfully"));

	}

	@Test
	public void pruebaDos() {
		PageLogin loginP = new PageLogin(driver);
		ReservationPage reservation = new ReservationPage(driver);
		loginP.login("user", "user");
		// helpers help = new helpers();
		// help.sleepSeconds(5);
		driver.navigate().to("https://demo.guru99.com/test/newtours/reservation.php");
		// NO SE PQ CARAJO NO ENCUENTRA EL SELECTOR
		// JEJEJEEJEJE***************************
		// driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[1]/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/a")).click();
		reservation.assertPage();

	}

	@Test
	public void pruebaTres() {
		WebDriverManager.setWindowSize(driver, 400, 300);
		PageLogin loginP = new PageLogin(driver);
		ReservationPage reservation = new ReservationPage(driver);
		loginP.login("user", "user");
		helpers help = new helpers();
		help.sleepSeconds(5);
		// Para ir a flight section
		driver.navigate().to("https://demo.guru99.com/test/newtours/reservation.php");
		reservation.assertPage();
		reservation.selectPAssengers(2);
		reservation.selectFromPort(3);
		reservation.selectToPort("London");
	}

	@Test
	public void testSumaNumeros() {
		int numero1 = 5;
		int numero2 = 1;
		Assert.assertTrue(numero1 + numero2 == 8, "La suma no es igual a 8");
	}

	@Test
	public void testNulo() {
		Personas persona = new Personas("Pepe", "Perez");
		Assert.assertNull(persona);// retorna F pq el objeto persona no es null
	}

	@Test
	public void mismoObeject() {
		Personas persona = new Personas("Pepe", "Perez");
		Personas personDos = new Personas("Pepe", "Perez");
		Assert.assertSame(persona, personDos);// Va a retornar F pq los objetos no son iguales, son diferentes obejtos
												// con los mismo datos
	}

	@Test
	public void mismoObejectTrue() {
		Personas persona = new Personas("Pepe", "Perez");
		Personas personDos = persona;
		Assert.assertSame(persona, personDos);// Va a retornar V pq los objetos son iguales
	}

	@Test
	public void pruebaCantidadCampos() {
		PageLogin pageLoguin = new PageLogin(driver);
		pageLoguin.verifyFields();
	}

	@AfterMethod
	public void tearDown(ITestResult result) {
		if (!result.isSuccess()) {
			ScreenShotsTest.takeScreenShot("Error", driver);
		}
		// driver.close();
		driver.switchTo().window(tabs.get(1)).close();// cerrar el tab adicional
		driver.switchTo().window(tabs.get(0)).close();// cerrar el tab de los test

	}

}
