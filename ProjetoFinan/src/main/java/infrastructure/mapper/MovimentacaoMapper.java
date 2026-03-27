package infrastructure.mapper;

import domain.movimentacao.Movimentacao;
import infrastructure.entity.ContaEntity;
import infrastructure.entity.MovimentacaoEntity;
import jakarta.persistence.EntityManager;

public class MovimentacaoMapper {
	
	private MovimentacaoMapper() {}
	
	public static MovimentacaoEntity toEntity(Movimentacao movimentacaoDomain, EntityManager em) {
		
		if(movimentacaoDomain == null) {
			return null;
		}
		
		if(movimentacaoDomain.getIdContaOrigem() == null) {
			throw new IllegalArgumentException("Conta de origem é obrigatória");
		}
		
		MovimentacaoEntity movimentacaoEntity = new MovimentacaoEntity();
		
		movimentacaoEntity.setId(movimentacaoDomain.getId());
		movimentacaoEntity.setTipoMovimentacao(movimentacaoDomain.getTipoMovimentacao());
		movimentacaoEntity.setValorMovimentacao(movimentacaoDomain.getValorMovimentacao());
		movimentacaoEntity.setDataHoraMovimentacao(movimentacaoDomain.getDataHoraMovimentacao());
		movimentacaoEntity.setContaOrigem(em.getReference(ContaEntity.class, movimentacaoDomain.getIdContaOrigem()));
		movimentacaoEntity.setContaDestino(movimentacaoDomain.getIdContaDestino() == null ? null : em.getReference(ContaEntity.class, movimentacaoDomain.getIdContaDestino()));
		movimentacaoEntity.setDescricaoMovimentacao(movimentacaoDomain.getDescricaoMovimentacao());
		
		return movimentacaoEntity;
	}
	
	public static Movimentacao toDomain(MovimentacaoEntity movimentacaoEntity) {
		
		if(movimentacaoEntity == null) {
			return null;
		}
		
		return new Movimentacao(
				movimentacaoEntity.getId(),
				movimentacaoEntity.getTipoMovimentacao(),
				movimentacaoEntity.getValorMovimentacao(),
				movimentacaoEntity.getDataHoraMovimentacao(),
				movimentacaoEntity.getContaOrigem() == null ? null : movimentacaoEntity.getContaOrigem().getId(),
				movimentacaoEntity.getContaDestino() == null ? null : movimentacaoEntity.getContaDestino().getId(),
				movimentacaoEntity.getDescricaoMovimentacao()
				);
	}
}
