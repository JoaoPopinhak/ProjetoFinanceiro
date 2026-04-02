package domain.exception;

@SuppressWarnings("serial")
public class DataInvalidaException extends RuntimeException{
	public DataInvalidaException(String mensagem) {
		super(mensagem);
	}

}
