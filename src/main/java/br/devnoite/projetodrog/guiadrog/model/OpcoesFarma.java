package br.devnoite.projetodrog.guiadrog.model;

public enum OpcoesFarma {
MANIPULACAO("Farmácia de Manipulação"), FITOTERAPIA("Farmácia de Fitoterapia"), HOMEOPATIA("Farmácia de Homeopatia"), HOSPITALAR("Farmácia Hospitalar");
	
	String desc;
	
	private OpcoesFarma(String desc) {
		this.desc = desc;
	}
	
	@Override
	public String toString() {
		return this.desc;
	}
}

