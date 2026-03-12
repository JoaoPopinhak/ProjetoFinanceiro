package domain.conta;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import domain.exception.ContaInvalidaException;
import domain.exception.OperacaoNaoPermitidaException;
import domain.exception.ValorInvalidoException;


/**
 * A classe Conta é a classe principal para criação e alterações das informações de conta e suas principais ações
 * Creditar, Debitar, e transferir, além de realizar as validações operacionais para realizações das ações.
 * 
 * @author João Marcos Rodrigues
 * @version 1.0
 * 
 */


public abstract class Conta {
	

	private Long id;
	private LocalDateTime dataCriacao;
	private String nomeBanco;
	private String codigoBanco;
	private BigDecimal saldo;
	
	
	
	/**
	 * 
	 * @return
	 */
	public Long getId() {
		return id;
	}

	protected void setId(Long id) {
		if(id == null) {
			throw new ValorInvalidoException("Id vazio");
		}
		
		this.id = id;
	}
	
	/**
	 * Metodo getDataCriacao
	 * @return dataCriacao
	 */
	public LocalDateTime getDataCriacao(){
		return dataCriacao;
	}
	
	/**
	 * Metodo setDataCriacao
	 * @param NovaDataCriacao
	 */
	public void ajustarDataCriacao(LocalDateTime NovaDataCriacao){
		this.dataCriacao = Objects.requireNonNull(NovaDataCriacao, "Data de criação inválida.");
	}
	
	/**
	 * Metodo getNomeBanco
	 * @return nomeBanco
	 */
	public String getNomeBanco(){
		return nomeBanco;
	}
	
	/**
	 * Metodo setNomeBanco
	 * @param novoNomeBanco
	 */
	public void setNomeBanco(String novoNomeBanco){
		
		if(novoNomeBanco == null||novoNomeBanco.isBlank()) {
			throw new ValorInvalidoException("Nome do banco inválido");
		}
		
		this.nomeBanco = novoNomeBanco;
	}
	
	/**
	 * Metodo getCodigoBanco
	 * @return codigoBanco
	 */
	public String getCodigoBanco(){
		return codigoBanco;
	}
	
	/**
	 * Metodo setCodigoBanco
	 * @param novoCodigoBanco
	 */
	public void setCodigoBanco(String novoCodigoBanco) {
		
		if(novoCodigoBanco == null || novoCodigoBanco.isBlank()) {
			throw new ValorInvalidoException("Código do banco inválido");
		}
		
		this.codigoBanco = novoCodigoBanco;	
	}
	
	/**
	 * Metodo getSaldo
	 * @return saldo
	 */
	public BigDecimal getSaldo(){
		return saldo;
	}
	
	/**
	 * Metodo setSaldo
	 * @param novoSaldo
	 */
	public void ajustarSaldo(BigDecimal novoSaldo) {
		
		if(novoSaldo == null || novoSaldo.compareTo(BigDecimal.ZERO) < 0) {
			throw new ValorInvalidoException("Saldo inválido para ajuste");	
		}
		
		this.saldo = novoSaldo;
	}

	/**
	 * Construtor
	 * 
	 * @param codigoCriacao
	 * @param dataCriacao
	 * @param nomeBanco
	 * @param codigoBanco
	 * @param saldoInicial
	 */
	
	public Conta(Long id, LocalDateTime dataCriacao, String nomeBanco, String codigoBanco, BigDecimal saldoInicial){
		
		if(saldoInicial == null || saldoInicial.compareTo(BigDecimal.ZERO) < 0) {
			throw new ValorInvalidoException("Saldo inicial inválido");	
		}
		
		if(nomeBanco == null||nomeBanco.isBlank()) {
			throw new ValorInvalidoException("Nome do banco inválido");
		}
		
		if(codigoBanco == null || codigoBanco.isBlank()) {
			throw new ValorInvalidoException("Código do banco inválido");
		}
		this.id = id;
		this.dataCriacao = Objects.requireNonNull(dataCriacao, "Data de criação obrigatória.");
		this.nomeBanco = nomeBanco;
		this.codigoBanco = codigoBanco;
		this.saldo = saldoInicial;	
	}
		
	/**
	 * Metodo realiza a operacao de creditar o valor informado no valor no saldo da conta.
	 * @param valor da operacao de credito do saldo
	 * @throws IllegalStateException("Valor deve ser maior que zero")
	 */
	public void creditar (BigDecimal valor) {
		validarValor(valor);
		saldo = saldo.add(valor);	
	}
		
	/**
	 * Metodo realiza a operacao de debitar  o valor informado no valor no saldo da conta.
	 * @param valor da operacao de debito do saldo.
	 * @throws IllegalStateException("Valor deve ser maior que zero")
	 * @throws IllegalStateException("Saldo insuficiente")
	 */
	public void debitar(BigDecimal valor) {
		validarValor(valor);
		validarSaldo(valor);
		
		saldo = saldo.subtract(valor);
	}
	
	/**
	 * Metodo realiza a transferencia do valor da conta de origem para a conta de destino.
	 * @param origem Conta de origem da transferencia.
	 * @param destino Conta de destino da transferencia.
	 * @param valor da transferencia.
	 */
	// O método transferencia(Conta origem, Conta destino, BigDecimal valor) deve ser aplicado no serviço do dominio corrigir posteriormente
	@Deprecated
	public void transferencia(Conta origem, Conta destino, BigDecimal valor) {
		
		validarConta(origem, destino);
		
		try{
			origem.debitar(valor);
			destino.creditar(valor);
			
		}catch(RuntimeException e) {
			origem.creditar(valor);
			throw e;
		}
	}
	
	/**
	 * Metodo realiza a validacao do valor. 
	 * Se o valor for igual ou menor a 0. Lança a exceção ValorInvalidoException("Valor deve ser maior que zero")
	 * @param valor
	 * @throws ValorInvalidoException("Valor deve ser maior que zero")
	 * @throws ValorInvalidoException("Valor deve ser maior que zero")
	 */
	protected void validarValor(BigDecimal valor) {
		if(valor == null || valor.compareTo(BigDecimal.ZERO) <= 0 ) {
			throw new ValorInvalidoException("Valor deve ser maior que zero");
		}
	}
	
	/**
	 * Metodo realiza a validacao do saldo.
	 * Se o saldo for menor que o valor lança a ValorInvalidoException("Valor deve ser maior que zero")
	 * @param valor 
	 */
	protected void validarSaldo(BigDecimal valor) {
		if(this.saldo.compareTo(valor) < 0) {
			throw new ValorInvalidoException("Saldo insuficiente");
		}
	}
	
	/**
	 * Metodo realiza a validacao da Conta de origem e da Conta de destino.
	 * Se a Conta de origem ou a Conta de destino for igual a null lança ContaNaoEncontrada.
	 * Se a Conta de origem e a Conta de destino for igual lanca OperacaoNaoPermitidaException
	 * 
	 * @param origem
	 * @param destino
	 * @throws ContaNaoEncontrada 
	 * @throws OperacaoNaoPermitidaException
	 */
	protected void validarConta(Conta origem, Conta destino) {
		if(origem == null) {
			throw new ContaInvalidaException("Conta de origem não foi localizada.");
			}if(destino == null) {
				throw new ContaInvalidaException("Conta de destino não foi localizada.");
			}if(origem == destino) {
				throw new OperacaoNaoPermitidaException("Conta de origem e destino não podem ser iguais.");
			}
	}


	
}
