package infrastructure.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import domain.movimentacao.TipoMovimentacao;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "movimentacao")
public class MovimentacaoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_movimentacao")
	private TipoMovimentacao tipoMovimentacao;
	
	@Column(name = "valor_movimentacao")
	private BigDecimal valorMovimentacao;
	
	@Column(name = "data_hora_movimentacao")
	private LocalDateTime dataHoraMovimentacao;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_conta_FK", nullable = false)
	private ContaEntity contaMovimentacao;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_transacao")// Criar chave estrangeira na tabela movimentacao
	private TransacaoEntity transacao;
	
	@Column(name = "descricao")
	private String descricaoMovimentacao;
	
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public TipoMovimentacao getTipoMovimentacao() {
		return this.tipoMovimentacao;
	}
	
	public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}
	
	public BigDecimal getValorMovimentacao() {
		return this.valorMovimentacao;
	}
	
	public void setValorMovimentacao(BigDecimal valorMovimentacao) {
		this.valorMovimentacao = valorMovimentacao;
	}
	
	public LocalDateTime getDataHoraMovimentacao() {
		return this.dataHoraMovimentacao;
	}
	
	public void setDataHoraMovimentacao(LocalDateTime dataHoraMovimentacao) {
		this.dataHoraMovimentacao = dataHoraMovimentacao;
	}
	
	public ContaEntity getContaMovimentacao() {
		return this.contaMovimentacao;
	}
	
	public void setContaMovimentacao(ContaEntity contaMovimentacao) {
		this.contaMovimentacao = contaMovimentacao;
	}
	
	public TransacaoEntity getTransacao() {
		return this.transacao;
	}
	
	public void setTransacao(TransacaoEntity transacao) {
		this.transacao = transacao;
	}
	
	public String getDescricaoMovimentacao() {
		return this.descricaoMovimentacao;
	}
	
	public void setDescricaoMovimentacao(String descricaoMovimentacao) {
		this.descricaoMovimentacao = descricaoMovimentacao;
	}
	
}
