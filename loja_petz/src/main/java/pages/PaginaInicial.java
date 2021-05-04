package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PaginaInicial {

	private WebDriver driver;
	
	private By produtoPesquisado = By.className("inputPesquisa");
	
	private By botaoPesquisar = By.className("custom-icon");
	
	private By nomeProduto = By.cssSelector(".active span");
	
	private By nomeProdutoSelecionado = By.className("nome_produto");
			
	private By valorProdutoNormal = By.className("display-price");
	
	private By valorProdutoAssinante = By.className("signature-price");
			
	private By produtoClique = By.className("petzProduct");
	
	public PaginaInicial(WebDriver driver){
		this.driver = driver;
	}
	
	public void digitarProduto(String produto){
		driver.findElement(produtoPesquisado).sendKeys(produto);
	}
	
	public void clicarBotaoPesquisar(){
		driver.findElement(botaoPesquisar).click();
	}
	
	public boolean encontrarProduto(String produto){
		 return produto.contentEquals(driver.findElement(nomeProduto).getText());
	}
	
	public String imprimirNomeProduto(int indice){
		return driver.findElements(nomeProdutoSelecionado).get(indice).getText();
	}
	
	public String imprimirValorNormal(int indice){
		return driver.findElements(valorProdutoNormal).get(indice).getText();
	}
	
	public String imprimirValorAssinate(int indice){
		return driver.findElements(valorProdutoAssinante).get(indice).getText();
	}
	
	public ProdutoPage clicarProduto(int indice){
		driver.findElements(produtoClique).get(indice).click();
		return new ProdutoPage(driver);	}
}
