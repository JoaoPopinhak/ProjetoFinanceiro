package domain.exception;

@SuppressWarnings("serial")
public class StatusInvestimentoInvalidaException extends RuntimeException{
	
	public StatusInvestimentoInvalidaException(String mensagem) {
		super(mensagem);
	}
}
