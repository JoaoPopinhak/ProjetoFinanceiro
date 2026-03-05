package domain.investimento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import domain.conta.ContaInvestimento;
import domain.exception.ContaInvalidaException;
import domain.exception.DataInvalidaException;
import domain.exception.OperacaoNaoPermitidaException;
import domain.exception.ProdutoInvalidoException;
import domain.exception.StatusInvestimentoInvalidaException;
import domain.exception.ValorInvalidoException;

/**
 * Representa a posição de um produto de investimento vinculada a uma conta específica.
 *
 * <p>Uma posição controla o estado atual de um investimento, permitindo:
 * <ul>
 *   <li>Compras adicionais do mesmo produto</li>
 *   <li>Vendas parciais ou totais</li>
 *   <li>Controle de quantidade, valor investido e status</li>
 * </ul>
 *
 * <p>A posição é encerrada automaticamente quando todo o valor ou quantidade
 * do investimento é vendido, alterando seu status para {@link StatusInvestimento#VENDIDO}.
 */


public class PosicaoInvestimento {
	
	private ProdutoInvestimento produtoInvestimento;
	private ContaInvestimento contaInvestimento;
	private BigDecimal quantidadeTotal;
	private BigDecimal valorTotal;
	private LocalDateTime dataPrimeiraCompra;
	private StatusInvestimento status;
	private LocalDateTime dataVendaCompleta;
	
	
	public ProdutoInvestimento getProdutoInvestimento() {
		return produtoInvestimento;
	}

	public void setProdutoInvestimento(ProdutoInvestimento produtoInvestimento) {
		validarProduto(produtoInvestimento);
		this.produtoInvestimento = produtoInvestimento;
	}

	public ContaInvestimento getContaInvestimento() {
		return contaInvestimento;
	}

	public void setContaInvestimento(ContaInvestimento contaInvestimento) {
		validarContaInvestimento(contaInvestimento);
		this.contaInvestimento = contaInvestimento;
	}

	public BigDecimal getQuantidadeTotal() {
		return quantidadeTotal;
	}

	private void setQuantidadeTotal(BigDecimal quantidadeTotal) {
		validarQuantidade(quantidadeTotal);
		this.quantidadeTotal = quantidadeTotal;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	private void setValorTotal(BigDecimal valorTotal) {
		validarValor(valorTotal);
		this.valorTotal = valorTotal;
	}

	public LocalDateTime getDataPrimeiraCompra() {
		return dataPrimeiraCompra;
	}

	public void setDataPrimeiraCompra(LocalDateTime dataPrimeiraCompra) {
		validarDataPrimeiraCompra(dataPrimeiraCompra);
		this.dataPrimeiraCompra = dataPrimeiraCompra;
	}

	public StatusInvestimento getStatusInvestimento() {
		return status;
	}

	public void setStatus(StatusInvestimento status) {
		validarStatusInvestimento(status);
		this.status = status;
	}

	public LocalDateTime getDataVenda() {
		return dataVendaCompleta;
	}

	public void setDataVenda(LocalDateTime dataVenda) {
		validarDataVenda(status, dataVenda);
		this.dataVendaCompleta = dataVenda;
	}

	
	
	/**
	 * Cria uma nova posição de investimento.
	 *
	 * <p>Ao ser criada, a posição:
	 * <ul>
	 *   <li>É associada a um produto e a uma conta de investimento</li>
	 *   <li>Registra a data da primeira compra</li>
	 *   <li>Inicia com o status {@link StatusInvestimento#INVESTIDO}</li>
	 * </ul>
	 *
	 * @param produtoInvestimento produto adquirido
	 * @param contaInvestimento conta utilizada na compra
	 * @param quantidadeTotal quantidade inicial adquirida (quando aplicável)
	 * @param valorTotal valor total investido
	 */
	public PosicaoInvestimento(
			ProdutoInvestimento produtoInvestimento, 
			ContaInvestimento contaInvestimento, 
			BigDecimal quantidadeTotal,
			BigDecimal valorTotal) {
		
		
		this.setProdutoInvestimento(produtoInvestimento);
		this.setContaInvestimento(contaInvestimento);
		this.setQuantidadeTotal(quantidadeTotal);
		this.setValorTotal(valorTotal);
		this.setDataPrimeiraCompra(LocalDateTime.now());
		this.setStatus(StatusInvestimento.INVESTIDO);
	}
	
	/**
	 * Realiza uma nova compra para uma posição de investimento já existente.
	 *
	 * <p>Para produtos que exigem quantidade, a quantidade comprada é somada à posição.
	 * Para produtos que não exigem quantidade, apenas o valor total é incrementado.
	 *
	 * @param qtdCompra quantidade adquirida (obrigatória para produtos que exigem quantidade)
	 * @param valorUnitario valor unitário do produto no momento da compra
	 *
	 * @throws OperacaoNaoPermitidaException se o investimento já estiver vendido
	 * @throws ValorInvalidoException se os valores informados forem inválidos
	 */
	public void compraInvestimentoExistente(BigDecimal qtdCompra, BigDecimal valorUnitario) {
		if(status == StatusInvestimento.VENDIDO) {
			throw new OperacaoNaoPermitidaException("Investimento já foi vendido.");
		}
		
		validarValor(valorUnitario);
		
		if(produtoInvestimento.getTipoProduto().exigeQuantidade()) {
			if(qtdCompra == null || qtdCompra.compareTo(BigDecimal.ZERO) <= 0) {
				throw new ValorInvalidoException("Quantidade obrigatória para este tipo de investimento.");
			}
			this.valorTotal = this.valorTotal.add(calcularValor(qtdCompra, valorUnitario));
			this.quantidadeTotal = this.quantidadeTotal.add(qtdCompra);		
		}else {
			this.valorTotal = this.valorTotal.add(valorUnitario);
		}
	}
	
	
	/**
	 * Realiza a venda parcial ou total de uma posição de investimento.
	 *
	 * <p>Ao vender completamente a posição, o status é alterado para
	 * {@link StatusInvestimento#VENDIDO} e a data de venda é registrada.
	 *
	 * @param qtdVenda quantidade vendida (quando aplicável)
	 * @param valorUnitarioVenda valor unitário do produto no momento da venda
	 *
	 * @throws OperacaoNaoPermitidaException se o investimento já estiver vendido
	 * @throws ValorInvalidoException se os valores informados forem inválidos
	 */
	public void vendaInvestimentoExistente(BigDecimal qtdVenda, BigDecimal valorUnitarioVenda) {
		if(status == StatusInvestimento.VENDIDO) {
			throw new OperacaoNaoPermitidaException("Investimento já foi vendido.");
		}
		
		validarValor(valorUnitarioVenda);
		
		if(produtoInvestimento.getTipoProduto().exigeQuantidade()) {
			if(qtdVenda == null || qtdVenda.compareTo(BigDecimal.ZERO) <= 0) {
				throw new OperacaoNaoPermitidaException("Quantidade obrigatória para este tipo de investimento.");
			}
			
			if(qtdVenda.compareTo(this.quantidadeTotal) > 0) {
				throw new OperacaoNaoPermitidaException("Quantidade de venda maior que a posição atual.");
			}
			this.valorTotal = this.valorTotal.subtract(calcularValor(qtdVenda, valorUnitarioVenda));
			this.quantidadeTotal = this.quantidadeTotal.subtract(qtdVenda);
		} else {
			
			if(valorUnitarioVenda.compareTo(valorTotal) > 0) {
				throw new OperacaoNaoPermitidaException("Valor de venda maior que o valor investido.");
			}
			
			this.valorTotal = this.valorTotal.subtract(valorUnitarioVenda);	
		}
		
		verificarEncerramento();
	}
	

	/*Métodos de apoio*/
	
	private BigDecimal calcularValor(BigDecimal qtdCompra, BigDecimal valorUnitario) {
		
		return qtdCompra.multiply(valorUnitario);
	}
	
	private void validarProduto(ProdutoInvestimento produto) {
		if(produto == null) {
			throw new ProdutoInvalidoException("Produto não informado.");
		}
	}
	
	private void validarContaInvestimento(ContaInvestimento contaInv) {
		if(contaInv == null) {
			throw new ContaInvalidaException("Conta não definida ou inválida.");
		}
	}
	
	private void validarQuantidade(BigDecimal quantidadeTotal) {
		if(produtoInvestimento.getTipoProduto().exigeQuantidade()) {
			if(quantidadeTotal == null || quantidadeTotal.compareTo(BigDecimal.ZERO) <= 0) {
				throw new ValorInvalidoException("Quantidade não informada ou quantidade menor ou igual a zero.");
			}
		}
	}
	
	private void validarValor(BigDecimal valor) {
		if(valor == null || valor.compareTo(BigDecimal.ZERO) <= 0)  {
			throw new ValorInvalidoException("Valor não informado ou valor menor ou igual a zero.");
		}
	}
	
	private void validarDataPrimeiraCompra(LocalDateTime dataPrimeiraCompra) {
		if(dataPrimeiraCompra == null) {
			throw new DataInvalidaException("Data de compra não informada");
		}
	}
	
	private void validarStatusInvestimento(StatusInvestimento status) {
		if(status == null) {
			throw new StatusInvestimentoInvalidaException("Status não informado.");
		}
	}
	
	private void validarDataVenda(StatusInvestimento status,LocalDateTime dataVenda) {
		if(status == StatusInvestimento.VENDIDO && dataVenda == null) {
			throw new DataInvalidaException("Data de venda não informada.");
		}
	}
	
	private void verificarEncerramento() {
		boolean encerrouPorQuantidade = produtoInvestimento.getTipoProduto().exigeQuantidade() && this.quantidadeTotal.compareTo(BigDecimal.ZERO) == 0;
		boolean encerrouPorValor = !produtoInvestimento.getTipoProduto().exigeQuantidade() && this.valorTotal.compareTo(BigDecimal.ZERO) == 0;
		
		if(encerrouPorQuantidade || encerrouPorValor) {
			setStatus(StatusInvestimento.VENDIDO);
			setDataVenda(LocalDateTime.now());
		}
	}
}
