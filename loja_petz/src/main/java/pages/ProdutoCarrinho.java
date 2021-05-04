package pages;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class ProdutoCarrinho {

	private WebDriver driver;
	
	private By nomeProdutoCarrinho = By.className("td-resumo");
	
	private By valorTotal = By.cssSelector("td.preco");
	
	private By meuCarrinho = By.className("minha-sacola");
	
	private By limparQuantidade = By.className("delete-item-pedido");
	
	
	public ProdutoCarrinho(WebDriver driver) {
		this.driver = driver;
	}
	
	
	public void clicarNomeProduto(){
				
		driver.findElement(nomeProdutoCarrinho).click();
	}
	
	public String imprimirValorTotal(){
		return driver.findElement(valorTotal).getText();
	}
	
	public void limparQuantidade(){
		driver.findElement(limparQuantidade).click();
	}
	
}
