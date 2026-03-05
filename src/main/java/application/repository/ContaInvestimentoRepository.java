package application.repository;

import java.util.Optional;

import domain.conta.Conta;
import domain.conta.ContaInvestimento;

public interface ContaInvestimentoRepository extends ContaRepository{
	
	public ContaInvestimento salvar(Conta contaInvestimentoDomain);
	
	public Optional<Conta> BuscarPorId (Long id);
	
	public void deletar(Long id);

}
