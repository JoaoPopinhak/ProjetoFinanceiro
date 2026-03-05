package application.repository;

import java.util.Optional;

import domain.movimentacao.Movimentacao;

public interface MovimentacaoRepository {
	
	Movimentacao salvar(Movimentacao movimentacao);
	
	Optional<Movimentacao> buscarPorId (Long Id);
	
	void deletar(Long Id);
}
