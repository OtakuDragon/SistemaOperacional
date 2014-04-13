package br.com.cabal.lopes.gilson.SistemasOperacionais.abstractClasses;

public class Programa {

	private String nome;
	private long tamanho;
	
	
	
	public Programa(String nome,long tamanho){
		setTamanho(tamanho);
		setNome(nome);
	}
	
	public long getTamanho() {
		return tamanho;
	}
	public void setTamanho(long tamanho2) {
		this.tamanho = tamanho2;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
