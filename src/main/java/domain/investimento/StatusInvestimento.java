package domain.investimento;

public enum StatusInvestimento {
	
	INVESTIDO(true),
	VENDIDO(false);
	
	private final boolean investido;
	
	StatusInvestimento(boolean investido){
		this.investido = investido;
	}
	
	public boolean investido() {
		return investido;
	}

}
