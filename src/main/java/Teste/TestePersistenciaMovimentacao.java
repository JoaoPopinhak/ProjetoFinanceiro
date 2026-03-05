package Teste;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import domain.movimentacao.Movimentacao;
import domain.movimentacao.TipoMovimentacao;
import infrastructure.persistence.JpaMovimentacao;
import infrastructure.persistence.JpaUtil;

public class TestePersistenciaMovimentacao {

	public static void main(String[] args) {
		
		JpaMovimentacao persistencia = new JpaMovimentacao(JpaUtil.getEntityManagerFactory());
		
		Movimentacao movimentacaoDomain = new Movimentacao(
				null,
				TipoMovimentacao.DESPESA,
				new BigDecimal("1200"),
				LocalDateTime.now(),
				1L,
				"MovimentacaoTeste"
				);
		
		
		persistencia.salvar(movimentacaoDomain);
		
		
		
		
		Optional<Movimentacao> movimentacaoLocalizada = persistencia.buscarPorId(2L);
		
		movimentacaoLocalizada.ifPresent(m ->
				System.out.println(
						
					"Id: " + m.getId() +
					"Tipo Movimentacao: " + m.getTipoMovimentacao().getDescricao() +
					"Valor Movimentacao: " + m.getValorMovimentacao() +
					"Data Hora Movimentacao: " + m.getDataHoraMovimentacao() +
					"Id conta movimentacao: " + m.getIdContaMovimentacao() + 
					"Descrição: " + m.getDescricao()
						));
		
		persistencia.deletar(4L);
	}
}
