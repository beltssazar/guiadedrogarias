package br.devnoite.projetodrog.guiadrog.model;

public enum Tipo {
	DROGARIA("Drogaria"), FARMACIA("Farmácia");
	
	String desc;
	
	private Tipo(String desc) {
		this.desc = desc;
	}
	
	@Override
	public String toString() {
		return this.desc;
	}
}
