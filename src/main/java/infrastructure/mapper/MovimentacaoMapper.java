package infrastructure.mapper;

import domain.movimentacao.Movimentacao;
import infrastructure.entity.ContaEntity;
import infrastructure.entity.MovimentacaoEntity;
import jakarta.persistence.EntityManager;

public class MovimentacaoMapper {
	
	private MovimentacaoMapper() {
		
	}
	
	public static MovimentacaoEntity toEntity(Movimentacao movimentacaoDomain, EntityManager em) {
		
		if(movimentacaoDomain == null) {
			return null;
		}
				
		MovimentacaoEntity movimentacaoEntity = new MovimentacaoEntity();
		movimentacaoEntity.setId(movimentacaoDomain.getId());
		movimentacaoEntity.setTipoMovimentacao(movimentacaoDomain.getTipoMovimentacao());
		movimentacaoEntity.setValorMovimentacao(movimentacaoDomain.getValorMovimentacao());
		movimentacaoEntity.setDataHoraMovimentacao(movimentacaoDomain.getDataHoraMovimentacao());
		
		ContaEntity contaEntity = em.getReference(ContaEntity.class, movimentacaoDomain.getIdContaMovimentacao());
		
		movimentacaoEntity.setContaMovimentacao(contaEntity);
		movimentacaoEntity.setDescricaoMovimentacao(movimentacaoDomain.getDescricao());
		
		return movimentacaoEntity;
	}
	
	public static Movimentacao toDomain(MovimentacaoEntity movimentacaoEntity) {
		
		if(movimentacaoEntity == null) {
			return null;
		}
		
		Movimentacao movimentacaoDomain = new Movimentacao(
				movimentacaoEntity.getId(),
				movimentacaoEntity.getTipoMovimentacao(),
				movimentacaoEntity.getValorMovimentacao(),
				movimentacaoEntity.getDataHoraMovimentacao(),
				movimentacaoEntity.getContaMovimentacao().getId(),
				movimentacaoEntity.getDescricaoMovimentacao()
				);
		return movimentacaoDomain;
	}

}
