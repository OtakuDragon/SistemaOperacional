package br.com.cabal.lopes.gilson.SistemasOperacionais.abstractClasses;

public class Programa {

	private String nome;
	private int tamanho;
	
	
	
	public Programa(String nome,int tamanho){
		setTamanho(tamanho);
		setNome(nome);
	}
	
	public int getTamanho() {
		return tamanho;
	}
	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
