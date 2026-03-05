package domain.exception;

@SuppressWarnings("serial")
public class ContaInvalidaException extends RuntimeException{
	
	public ContaInvalidaException(String mensagem) {
		super(mensagem);
		}
	
}
