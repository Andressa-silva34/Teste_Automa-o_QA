package base;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.google.common.io.Files;

import pages.PaginaInicial;

public class BaseTeste {

	private static WebDriver driver;

	protected PaginaInicial paginaInicial;

	@BeforeAll
	public static void abrirNavegador() {
		System.setProperty("webdriver.chrome.driver", "C:\\webdrivers\\chromedriver\\90\\chromedriver\\chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	@BeforeEach
	public void carregarPaginaInicial(){
		driver.get("https://www.petz.com.br/");
		paginaInicial = new PaginaInicial(driver);
	}
	
	public void capturarTela(){
		var capt = (TakesScreenshot) driver;
		File capturaDeTela = capt.getScreenshotAs(OutputType.FILE);
		try {
			Files.move(capturaDeTela, new File ("resources/screenshots/Teste_" + dataHora() + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public  static  String  dataHora() {
		return new SimpleDateFormat("yyyy-MM-dd HH-mm").format(new Date());
    }
	
	@AfterAll
	public static void fecharNavegador(){
		driver.quit();
	}
}
