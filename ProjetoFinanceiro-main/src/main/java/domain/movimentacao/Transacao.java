package domain.movimentacao;

import java.time.LocalDateTime;

public class Transacao {
	
	Long id;
	LocalDateTime dataHoraTransacao;
	String descricaoTransacao;
	
	public Transacao(Long id, LocalDateTime dataHoraTransacao, String descricaoTransacao) {
		this.id = id;
		this.setDataHoraTransacao(dataHoraTransacao);
		this.setDescricaoTransacao(descricaoTransacao);
	}
	
	public Long getId() {
		return this.id;
	}
	
	public LocalDateTime getDataHoraTransacao() {
		return this.dataHoraTransacao;
	}
	
	public void setDataHoraTransacao(LocalDateTime dataHoraTransacao) {
		this.dataHoraTransacao = dataHoraTransacao;
	}
	
	public String getDescricaoTransacao() {
		return this.descricaoTransacao;
	}
	
	public void setDescricaoTransacao(String descricaoTransacao) {
		this.descricaoTransacao = descricaoTransacao;
	}
	
}
