package application.repository;

import java.util.Optional;

import domain.movimentacao.Movimentacao;

public interface MovimentacaoRepository {
	
	Movimentacao salvar(Movimentacao movimentacao);
	
	Optional<Movimentacao> buscarPorId (Long id);
	
	void deletar(Long id);
	
	void deletarPorConta(Long idConta);
}
