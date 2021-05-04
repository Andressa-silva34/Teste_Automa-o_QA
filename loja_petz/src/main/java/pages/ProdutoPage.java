package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;


public class ProdutoPage {

	private WebDriver driver;
	
	private By meuCarrinho = By.className("minha-sacola");
	
	private By nomeProdutoSelecionado = By.cssSelector(".col-md-7 h1");
	
	private By nomeFabricante = By.cssSelector(".col-md-6 span.blue");
	
	private By valorProdutoNormal = By.className("price-current");
	
	private By valorProdutoAssinante = By.className("price-subscriber");
	
	private By botaoCarrinho = By.id("adicionarAoCarrinho");
	
	
	
	
	public ProdutoPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String imprimirNomeProduto(){
		return driver.findElement(nomeProdutoSelecionado).getText();
		
	}
	
	public String imprimirNomeFabricante(){
		return driver.findElement(nomeFabricante).getText();
		
	}
	
	public String imprimirValorNormal(){
		return driver.findElement(valorProdutoNormal).getText();
		
	}
	
	public String imprimirValorAssinate(){
		return driver.findElement(valorProdutoAssinante).getText();
		
	}
	
	public ProdutoCarrinho botaoCarrinho(){
		driver.findElement(botaoCarrinho).click();
		FluentWait wait = new FluentWait(driver).withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofSeconds(1))
				.ignoring(NoSuchElementException.class);
		wait.until(ExpectedConditions.visibilityOfElementLocated(meuCarrinho));
		
		return new ProdutoCarrinho(driver);
	}
	
	public boolean produtoNoCarrinho(String texto){
		return texto.contentEquals(driver.findElement(meuCarrinho).getText());
	}

	
}
