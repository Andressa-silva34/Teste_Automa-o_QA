package paginainicial;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import base.BaseTeste;
import pages.ProdutoCarrinho;
import pages.ProdutoPage;


public class PaginaInicialTeste extends BaseTeste {

	ProdutoPage produtoPage;
	ProdutoCarrinho produtoCarrinho;
	
	//A - Simples
	
	//1) Consulte por "Ração" 
	@Test
	public void testPesquisarProduto(){
		carregarPaginaInicial();
		paginaInicial.digitarProduto("Ração");
		paginaInicial.clicarBotaoPesquisar();
		assertThat(paginaInicial.encontrarProduto("Ração"), is(true));
		
		capturarTela();
	}
	//2) Selecione o terceiro produto da lista retornada.
	@Test
	public void selecionarTerceiroProduto(){
		testPesquisarProduto();
		int indice=2;
		String PaginaInicial_NomeProduto = paginaInicial.imprimirNomeProduto(indice);
		String PaginaInicial_ValorNormal = paginaInicial.imprimirValorNormal(indice);
		String PaginaInicial_ValorAssinante = paginaInicial.imprimirValorAssinate(indice);
				
		System.out.println(PaginaInicial_NomeProduto);
		System.out.println(PaginaInicial_ValorNormal);
		System.out.println(PaginaInicial_ValorAssinante);
		
		produtoPage	= paginaInicial.clicarProduto(indice);
		
		capturarTela();
	}	
	//3) Valide o nome do produto, fornecedor, preço normal e preço para assinante
	@Test
	public void validarDadosProduto() {
		selecionarTerceiroProduto();
			
		assertThat(produtoPage.imprimirNomeProduto(), is("Ração Golden Fórmula Senior para Cães Adultos Sabor Frango e Arroz - 15kg"));
		assertThat(produtoPage.imprimirNomeFabricante(), is("Premier Pet"));
		assertThat(produtoPage.imprimirValorNormal(), is("R$ 144,90"));
		assertThat(produtoPage.imprimirValorAssinate(), is("R$ 130,41"));
		
		capturarTela();
	}
	//4) Insira o produto no carrinho de compras
	@Test
	public void incluirProdutoNoCarrinho(){
		selecionarTerceiroProduto();
		produtoCarrinho = produtoPage.botaoCarrinho();
		assertThat(produtoPage.produtoNoCarrinho("Meu Carrinho"), is(true));
		
		capturarTela();
	}
	
	//5) Valide se os dados do item 3 continuam idênticos
	@Test
	public void validarProdutoCarrinho() {
		incluirProdutoNoCarrinho();
		assertThat(produtoCarrinho.imprimirValorTotal(), is("R$ 144,90"));
		produtoCarrinho.clicarNomeProduto();
			
		assertThat(produtoPage.imprimirNomeProduto(), is("Ração Golden Fórmula Senior para Cães Adultos Sabor Frango e Arroz - 15kg"));
		assertThat(produtoPage.imprimirNomeFabricante(), is("Premier Pet"));
		assertThat(produtoPage.imprimirValorNormal(), is("R$ 144,90"));
		assertThat(produtoPage.imprimirValorAssinate(), is("R$ 130,41"));
		capturarTela();
	}
	
	
	
	
	//B - Com Massa de Teste
	
	@ParameterizedTest
	@CsvFileSource(resources = "/Massa_de_teste.csv", numLinesToSkip = 1, delimiter = ';')
	public void testarMassaDeTeste(String nomeTeste, String nomeProduto, String descricaoProduto, String nomeFabricante, String valorSemDesconto, String valorComDesconto){
		
		//Etapa 1
		carregarPaginaInicial();
		paginaInicial.digitarProduto(nomeProduto);
		paginaInicial.clicarBotaoPesquisar();
		assertThat(paginaInicial.encontrarProduto(nomeProduto), is(true));
		capturarTela();
		
		//Etapa 2
		int indice= 2;
		
		produtoPage	= paginaInicial.clicarProduto(indice);
		capturarTela();
		
		//Etapa 3
		String ProdutoPage_NomeProduto = produtoPage.imprimirNomeProduto();
		String ProdutoPage_NomeFabricante = produtoPage.imprimirNomeFabricante();
		String ProdutoPage_ValorNormal = produtoPage.imprimirValorNormal();
		String ProdutoPage_ValorAssinante = produtoPage.imprimirValorAssinate();
		
		System.out.println(ProdutoPage_NomeProduto);
		System.out.println(ProdutoPage_NomeFabricante);
		System.out.println(ProdutoPage_ValorNormal);
		System.out.println(ProdutoPage_ValorAssinante);
		
		
		assertThat(ProdutoPage_NomeProduto, is(descricaoProduto));
		assertThat(ProdutoPage_NomeFabricante, is(nomeFabricante));
		assertThat(ProdutoPage_ValorNormal, is(valorSemDesconto));
		assertThat(ProdutoPage_ValorAssinante, is(valorComDesconto));
		
		capturarTela();
		
		//Etapa 4
		produtoCarrinho = produtoPage.botaoCarrinho();
		assertThat(produtoPage.produtoNoCarrinho("Meu Carrinho"), is(true));
		
		//Etapa 5
		
		assertThat(valorSemDesconto, is(produtoCarrinho.imprimirValorTotal()));
		
				
		capturarTela();
		
			
		produtoCarrinho.limparQuantidade();
		
	}
	
}
