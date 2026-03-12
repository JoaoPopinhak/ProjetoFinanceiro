package domain.movimentacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public class Movimentacao {
	
	private Long id;
	private TipoMovimentacao tipoMovimentacao;
	private BigDecimal valorMovimentacao;
	private LocalDateTime dataHoraMovimentacao;
	private Long IdContaMovimentacao;
	private Long idTransacao;
	private String descricao;
	
	public Movimentacao(Long id, TipoMovimentacao tipoMovimentacao, BigDecimal valorMovimentacao, LocalDateTime dataHoraMovimentacao, Long IdContaMovimentacao, Long idTransacao ,String descricao) {
		this.setId(id);
		this.setTipoMovimentacao(tipoMovimentacao);
		this.setValorMovimentacao(valorMovimentacao);
		this.setDataHoraMovimentacao(dataHoraMovimentacao);
		this.setIdContaMovimentacao(IdContaMovimentacao);
		this.setIdTransacao(idTransacao);
		this.setDescricao(descricao);
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
		this.dataHoraMovimentacao = dataHoraMovimentacao;
	}

	public Long getIdContaMovimentacao() {
		return IdContaMovimentacao;
	}

	public void setIdContaMovimentacao(Long IdContaMovimentacao) {
		this.IdContaMovimentacao = IdContaMovimentacao;
	}
	
	public Long getIdTransacao() {
		return this.idTransacao;
	}
	
	public void setIdTransacao(Long idTransacao) {
		this.idTransacao = idTransacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	//Criar validações necessárias...
	
	/*
	Tipo movimentacao
	Valor movimentacao
	Data Hora Movimentacao
	Conta Movimentacao
	
	*/
	

}
