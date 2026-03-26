package domain.exception;

@SuppressWarnings("serial")
public class OperacaoNaoPermitidaException extends RuntimeException{
	
	public OperacaoNaoPermitidaException(String mensagem) {
		super(mensagem);
	}
}
