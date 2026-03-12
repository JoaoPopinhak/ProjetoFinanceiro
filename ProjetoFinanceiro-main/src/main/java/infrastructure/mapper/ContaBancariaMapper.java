package infrastructure.mapper;

import domain.conta.Conta;
import domain.conta.ContaBancaria;
import infrastructure.entity.ContaBancariaEntity;
import infrastructure.entity.ContaEntity;

public class ContaBancariaMapper {
	
	private ContaBancariaMapper() {		
	}
	
	public static ContaBancariaEntity toEntity(Conta contaBancaria) {
		
		if(contaBancaria == null) {
			return null;
		}
		
		ContaBancariaEntity contaBancariaEntity = new ContaBancariaEntity();
		contaBancariaEntity.setId(contaBancaria.getId());
		contaBancariaEntity.setDataCriacao(contaBancaria.getDataCriacao());
		contaBancariaEntity.setNomeBanco(contaBancaria.getNomeBanco());
		contaBancariaEntity.setCodigoBanco(contaBancaria.getCodigoBanco());
		contaBancariaEntity.setSaldo(contaBancaria.getSaldo());
		
		return contaBancariaEntity;
	}
	
	
	public static ContaBancaria toDomain(ContaEntity contaBancariaEntity) {
		
		if(contaBancariaEntity == null) {
			return null;
		}
		
		ContaBancaria contaBancariaDomain = new ContaBancaria(
				contaBancariaEntity.getId(),
				contaBancariaEntity.getDataCriacao(),
				contaBancariaEntity.getNomeBanco(),
				contaBancariaEntity.getCodigoBanco(),
				contaBancariaEntity.getSaldo()
				);
		
		return contaBancariaDomain;
	}
	
}
