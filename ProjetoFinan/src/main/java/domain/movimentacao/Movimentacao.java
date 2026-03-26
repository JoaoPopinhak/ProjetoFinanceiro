package domain.movimentacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Movimentacao {
	
	private Long id;
	private TipoMovimentacao tipoMovimentacao;
	private BigDecimal valorMovimentacao;
	private LocalDateTime dataHoraMovimentacao;
	private Long idContaOrigem;
	private Long idContaDestino;
	private String descricaoMovimentacao;
	
	public Movimentacao(Long id, TipoMovimentacao tipoMovimentacao, BigDecimal valorMovimentacao, LocalDateTime dataHoraMovimentacao, Long contaOrigem, Long contaDestino, String descricaoMovimentacao) {
		this.setId(id);
		this.setTipoMovimentacao(tipoMovimentacao);
		this.setValorMovimentacao(valorMovimentacao);
		this.setDataHoraMovimentacao(dataHoraMovimentacao);
		this.setIdContaOrigem(contaOrigem);
		this.setIdContaDestino(contaDestino);
		this.setDescricaoMovimentacao(descricaoMovimentacao);	
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoMovimentacao getTipoMovimentacao() {
		return tipoMovimentacao;
	}

	public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}

	public BigDecimal getValorMovimentacao() {
		return valorMovimentacao;
	}

	public void setValorMovimentacao(BigDecimal valorMovimentacao) {
		this.valorMovimentacao = valorMovimentacao;
	}

	public LocalDateTime getDataHoraMovimentacao() {
		return dataHoraMovimentacao;
	}

	public void setDataHoraMovimentacao(LocalDateTime dataHoraMovimentacao) {
		this.dataHoraMovimentacao = dataHoraMovimentacao == null ? LocalDateTime.now() : dataHoraMovimentacao;;
	}

	public Long getIdContaOrigem() {
		return idContaOrigem;
	}

	public void setIdContaOrigem(Long idContaOrigem) {
		this.idContaOrigem = idContaOrigem;
	}

	public Long getIdContaDestino() {
		return idContaDestino;
	}

	public void setIdContaDestino(Long idContaDestino) {
		this.idContaDestino = idContaDestino;
	}

	public String getDescricaoMovimentacao() {
		return descricaoMovimentacao;
	}

	public void setDescricaoMovimentacao(String descricaoMovimentacao) {
		this.descricaoMovimentacao = descricaoMovimentacao;
	}
	
	public void atualizarMovimentacao(TipoMovimentacao tipoMovimentacao, 
                                      BigDecimal valorMovimentacao,
                                      LocalDateTime dataHoraMovimentacao,
                                      Long idContaOrigem,
                                      Long idContaDestino,
                                      String descricaoMovimentacao) {
		
		if(tipoMovimentacao != null) {
			this.tipoMovimentacao = tipoMovimentacao;
		}
		
		if(valorMovimentacao != null) {
			this.valorMovimentacao = valorMovimentacao;
		}
		
		if(dataHoraMovimentacao != null) {
			this.dataHoraMovimentacao = dataHoraMovimentacao;
		}
		
		if(idContaOrigem != null) {
			this.idContaOrigem = idContaOrigem;
		}
		
		if(idContaDestino != null) {
			this.idContaDestino = idContaDestino;
		}
		
		if(descricaoMovimentacao != null) {
			this.descricaoMovimentacao = descricaoMovimentacao;
		}	
	}

	
	
	
	
	//Criar validações necessárias...
	
	/*
	Tipo movimentacao
	Valor movimentacao
	Data Hora Movimentacao
	Conta Movimentacao
	Validar o tipo transferencia se não for transferencia o campo Conta destino pode ser null
	*/
	

}
