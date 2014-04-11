package br.com.cabal.lopes.gilson.SistemasOperacionais.enums;

public enum TipoEscalonamento {

	FIFO(1)
	,SJF(2)
	,SRT(3)
	,RR(4);
	
	private int valor;

	private TipoEscalonamento(int valor){  //construtor
		this.valor = valor;
	}
}
