package application.repository;

import java.util.Optional;

import domain.conta.Conta;


public interface ContaRepository {
	
	public Conta salvar(Conta contaInvestimentoDomain);
	
	public Optional<Conta> BuscarPorId (Long id);
	
	public void deletar(Long id);

}
