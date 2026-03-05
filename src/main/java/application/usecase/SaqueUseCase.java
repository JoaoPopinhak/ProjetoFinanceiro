package application.usecase;

import application.repository.ContaRepository;
import application.repository.MovimentacaoRepository;

public class SaqueUseCase {
	
	private ContaRepository contaRepository;
	private MovimentacaoRepository movimentacaoRepository;
	
	public SaqueUseCase (ContaRepository contaRepository, MovimentacaoRepository movimentacaoRepository) {
		this.contaRepository = contaRepository;
		this.movimentacaoRepository = movimentacaoRepository;
	}
	
	

}
