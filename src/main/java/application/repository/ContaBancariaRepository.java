package application.repository;

import java.util.Optional;

import domain.conta.Conta;
import domain.conta.ContaBancaria;

public interface ContaBancariaRepository extends ContaRepository{
	
	public ContaBancaria salvar(Conta conta);
	
	public Optional<Conta> BuscarPorId (Long id);
	
	public void deletar(Long id);

}
