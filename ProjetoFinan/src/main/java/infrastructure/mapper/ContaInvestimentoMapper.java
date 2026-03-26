package infrastructure.mapper;

import domain.conta.Conta;
import domain.conta.ContaInvestimento;
import infrastructure.entity.ContaEntity;
import infrastructure.entity.ContaInvestimentoEntity;

public class ContaInvestimentoMapper {
	
	private ContaInvestimentoMapper() {	
	}

	public static ContaInvestimentoEntity toEntity(Conta contaInvestimentoDomain) {
		
		if(contaInvestimentoDomain == null) {
			return null;
		}
		
		ContaInvestimentoEntity contaInvestimentoEntity = new ContaInvestimentoEntity();
		contaInvestimentoEntity.setId(contaInvestimentoDomain.getId());
		contaInvestimentoEntity.setDataCriacao(contaInvestimentoDomain.getDataCriacao());
		contaInvestimentoEntity.setNomeBanco(contaInvestimentoDomain.getNomeBanco());
		contaInvestimentoEntity.setCodigoBanco(contaInvestimentoDomain.getCodigoBanco());
		contaInvestimentoEntity.setSaldo(contaInvestimentoDomain.getSaldo());
		
	
		return contaInvestimentoEntity;
	}
	
	public static ContaInvestimento toDomain(ContaEntity contaInvestimentoEntity) {
		
		if(contaInvestimentoEntity == null) {
			return null;
		}
		
		ContaInvestimento contaInvestimentoDomain = new ContaInvestimento(
				contaInvestimentoEntity.getId(),
				contaInvestimentoEntity.getDataCriacao(),
				contaInvestimentoEntity.getNomeBanco(),
				contaInvestimentoEntity.getCodigoBanco(),
				contaInvestimentoEntity.getSaldo()
				);
		
		return contaInvestimentoDomain;
	}
}
