package br.com.cabal.lopes.gilson.SistemasOperacionais;

import java.util.ArrayList;
import java.util.List;

import br.com.cabal.lopes.gilson.SistemasOperacionais.abstractClasses.Processo;
import br.com.cabal.lopes.gilson.SistemasOperacionais.abstractClasses.Programa;
import br.com.cabal.lopes.gilson.SistemasOperacionais.enums.TipoEscalonamento;
import br.com.cabal.lopes.gilson.SistemasOperacionais.processos.Processo1;

public class SistemaOperacional {

	public static List<Processo> processos = new ArrayList<Processo>();
	public static final int palavra = 5;//kb - Tamanho da instrução que a cpu pode ler por vez
	public static final int quantum = 10;//seg - Quantum do processador
	
	public static void main(String[] args) {
	
		processos.add(new Processo(new Programa("Word",25)));
		processos.add(new Processo(new Programa("Excel",30)));
		processos.add(new Processo(new Programa("Chrome",16)));
		processos.add(new Processo(new Programa("Skype",14)));
		
		Escalonador esc = new Escalonador(processos);
		
		esc.setMode(TipoEscalonamento.FIFO);
		
	}
}
