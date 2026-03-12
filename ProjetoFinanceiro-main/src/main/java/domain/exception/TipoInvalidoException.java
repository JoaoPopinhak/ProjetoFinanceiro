package domain.exception;

@SuppressWarnings("serial")
public class TipoInvalidoException extends RuntimeException{
	
	public TipoInvalidoException(String mensagem) {
		super(mensagem);
	}

}
