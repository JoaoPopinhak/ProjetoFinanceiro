package infrastructure.mapper;

import domain.movimentacao.Movimentacao;
import infrastructure.entity.ContaEntity;
import infrastructure.entity.MovimentacaoEntity;
import infrastructure.entity.TransacaoEntity;
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
		
		if(movimentacaoDomain.getIdTransacao() != null) {
			TransacaoEntity transacaoEntity = em.getReference(TransacaoEntity.class, movimentacaoDomain.getIdTransacao());
			movimentacaoEntity.setTransacao(transacaoEntity);
		}
		movimentacaoEntity.setDescricaoMovimentacao(movimentacaoDomain.getDescricao());
		
		return movimentacaoEntity;
	}
	
	public static Movimentacao toDomain(MovimentacaoEntity movimentacaoEntity) {
		
		Long idTransacao = null;
		
		if(movimentacaoEntity == null) {
			return null;
		}
		
		if(movimentacaoEntity.getTransacao() != null) {
			idTransacao = movimentacaoEntity.getTransacao().getId();
		}
		
		Movimentacao movimentacaoDomain = new Movimentacao(
				movimentacaoEntity.getId(),
				movimentacaoEntity.getTipoMovimentacao(),
				movimentacaoEntity.getValorMovimentacao(),
				movimentacaoEntity.getDataHoraMovimentacao(),
				movimentacaoEntity.getContaMovimentacao().getId(),
				idTransacao,
				movimentacaoEntity.getDescricaoMovimentacao()
				);
		return movimentacaoDomain;
	}

}
