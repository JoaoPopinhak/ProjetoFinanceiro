package domain.exception;

@SuppressWarnings("serial")
public class NomeInvalidoException extends RuntimeException{	
	public NomeInvalidoException(String mensagem) {
		super(mensagem);
	}
}
