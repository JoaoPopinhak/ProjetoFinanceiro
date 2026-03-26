package domain.investimento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import domain.exception.DataInvalidaException;
import domain.exception.NomeInvalidoException;
import domain.exception.TipoInvalidoException;
import domain.exception.ValorInvalidoException;

/**
 * A classe ProdutoInvestimento representa o Investimento comprado pelo usuário.
 * Onde esse produto é criado na classe ContaInvestimento no momento da sua compra.
 * Na classe contém os métodos de validação necessária para criar o produto.
 * 
 * @see ContaInvestimento
 * @see TipoProduto
 * 
 * @author João Marcos Rodrigues
 * @version 1.0
 */

public class ProdutoInvestimento {
	
	private TipoProduto tipoProduto;
	private String nomeProduto;
	private LocalDateTime dataVencimento;
	private BigDecimal percentualCdi;
	private BigDecimal valorUnitario;
	
	/**
	 * Métodos getters e setters
	 */
	public TipoProduto getTipoProduto() {
		return tipoProduto;
	}

	public void setTipoProduto(TipoProduto novoTipoProduto) {
		validarTipoProduto(novoTipoProduto);
		this.tipoProduto = novoTipoProduto;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String novoNomeProduto) {
		validarNomeProduto(novoNomeProduto);
		this.nomeProduto = novoNomeProduto;
	}

	public LocalDateTime getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDateTime novoDataVencimento) {
		validarDataVencimento(this.tipoProduto, novoDataVencimento);
		this.dataVencimento = novoDataVencimento;
	}

	public BigDecimal getPercentualCdi() {
		return percentualCdi;
	}

	public void setPercentualCdi(BigDecimal novoPercentualCdi) {
		validarPercentualCdi(novoPercentualCdi);
		this.percentualCdi = novoPercentualCdi;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal novoValorUnitario) {
		validarValorUnitario(novoValorUnitario);
		this.valorUnitario = novoValorUnitario;
	}
	
	/**
	 * Método construtor
	 * 
	 * @param nome
	 * @param tipo
	 * @param precoCompra
	 */
	
	public ProdutoInvestimento( 
			TipoProduto tipoProduto, 
			String nomeProduto, 
			LocalDateTime dataVencimento,
			BigDecimal percentualCdi,  
            BigDecimal valorUnitario) {
		
		validarDataVencimento(tipoProduto, dataVencimento);
		
		this.setTipoProduto(tipoProduto);
		this.setNomeProduto(nomeProduto);
		this.setDataVencimento(dataVencimento);
		this.setPercentualCdi(percentualCdi);
		this.setValorUnitario(valorUnitario);
	}
	
	/**
	 * Valida o tipo de produto. 
	 * Se for igual a null lança exceção.
	 * 
	 * @param tipoProduto
	 * @throws TipoInvalidoException
	 */
	private void validarTipoProduto(TipoProduto tipoProduto) {
		if(tipoProduto == null) {
			throw new TipoInvalidoException("Tipo não informado.");
		}
	}
	
	/**
	 * Valida o nome do produto.
	 * Se o nome for null ou estiver vazio lança exceção.
	 * 
	 * @param nomeProduto
	 * @throws NomeInvalidoException
	 */
	private void validarNomeProduto(String nomeProduto) {
		if(nomeProduto == null || nomeProduto.isBlank()) {
			throw new NomeInvalidoException("Nome não informado ou vazio;");
		}
	}
	
	/**
	 * Valida o percentual do CDI informado.
	 * Se for igual a null e se o percentual for igual ou menor que zero lança exceção
	 * 
	 * @param percentualCdi
	 * @throws ValorInvalidoException
	 */
	private void validarPercentualCdi(BigDecimal percentualCdi) {
		if(percentualCdi == null || percentualCdi.compareTo(BigDecimal.ZERO) <= 0) {
			throw new ValorInvalidoException("Porcentagem CDI não informado ou valor igual ou menor a ZERO.");
		}
	}
	
	/**
	 * Valida o valor unitário do produto.
	 * Se o valor for igual a null ou igual ou menor que zero lança exceção.
	 * 
	 * @param precoCompra
	 * @throws ValorInvalidoException
	 */
	private void validarValorUnitario(BigDecimal valorUnitario) {
		if(valorUnitario == null || valorUnitario.compareTo(BigDecimal.ZERO) <= 0) {
			throw new ValorInvalidoException("Valor não informado ou valor igual ou menor que ZERO.");
		}
	}
	
	/**
	 * Valida a data de vencimento.
	 * A validação da data de vencimento dependerá do Tipo do produto.
	 * Se o tipo do produto for RENDA_FIXA informar a Data de vencimento é obrigatório.
	 * Portanto se o tipo do produto for RENDA_FIXA e a data de vencimento for null lança exceção
	 * 
	 * @param tipoProduto
	 * @param dataVencimento
	 * @throws DataInvalidaException
	 */
	private void validarDataVencimento(TipoProduto tipoProduto, LocalDateTime dataVencimento) {
		if(tipoProduto.exigeVencimento() && dataVencimento == null) {
			throw new DataInvalidaException("Produto de renda fixa exige data de vencimento.");
		}
		
	}
		
}
