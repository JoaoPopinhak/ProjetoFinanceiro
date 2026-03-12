package infrastructure.mapper;

import domain.conta.Conta;
import domain.conta.ContaBancaria;
import domain.conta.ContaInvestimento;
import infrastructure.entity.ContaBancariaEntity;
import infrastructure.entity.ContaEntity;
import infrastructure.entity.ContaInvestimentoEntity;


public class ContaMapper {

		private ContaMapper() {		
		}
		
		public static ContaEntity toEntity(Conta contaDomain) {
			
			if(contaDomain == null) {
				return null;
			}
			
			if(contaDomain instanceof ContaBancaria) {
				return ContaBancariaMapper.toEntity(contaDomain);
			}
			if(contaDomain instanceof ContaInvestimento) {
				return ContaInvestimentoMapper.toEntity(contaDomain);
			}
			 throw new IllegalArgumentException("Tipo de conta não suportado: " + contaDomain.getClass());
		}
		
		
		public static Conta toDomain(ContaEntity contaEntity) {
			
			if(contaEntity == null) {
				return null;
			}
			
			if(contaEntity instanceof ContaBancariaEntity) {
				return ContaBancariaMapper.toDomain(contaEntity);
			}
			if(contaEntity instanceof ContaInvestimentoEntity) {
				return ContaInvestimentoMapper.toDomain(contaEntity);
			}
		
			throw new IllegalArgumentException("Tipo de conta não suportado: " + contaEntity.getClass());
		}	
}
