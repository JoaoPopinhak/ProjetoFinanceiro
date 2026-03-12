package infrastructure.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TransacaoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "data_hora_transacao")
	private LocalDateTime dataHoraTransacao;
	
	@Column(name = "descricao_transacao")
	private String descricaoTransacao;
	
	
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
