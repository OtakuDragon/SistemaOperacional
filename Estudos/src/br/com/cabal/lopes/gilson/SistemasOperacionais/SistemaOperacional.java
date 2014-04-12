package br.com.cabal.lopes.gilson.SistemasOperacionais;

import java.util.ArrayList;
import java.util.List;

import br.com.cabal.lopes.gilson.SistemasOperacionais.abstractClasses.Processo;
import br.com.cabal.lopes.gilson.SistemasOperacionais.abstractClasses.Programa;
import br.com.cabal.lopes.gilson.SistemasOperacionais.enums.TipoEscalonamento;


public class SistemaOperacional {

	public static List<Processo> processos = new ArrayList<Processo>();
	public static final int palavra = 25;//bits - Tamanho da instrução que a cpu pode ler por vez
	public static final int quantum = 11;//seg - Quantum do processador
	
	public static void main(String[] args) {
	
		processos.add(new Processo(new Programa("Excel",35000)));
		processos.add(new Processo(new Programa("Word",30000)));
		processos.add(new Processo(new Programa("Chrome",16000)));
		processos.add(new Processo(new Programa("Skype",26000)));
//		processos.add(new Processo(new Programa("P1",10000)));
//		processos.add(new Processo(new Programa("P2",146000)));
//		processos.add(new Processo(new Programa("P3",148000)));
//		processos.add(new Processo(new Programa("P4",149000)));
//		processos.add(new Processo(new Programa("P5",180000)));
//		processos.add(new Processo(new Programa("P6",160000)));
//		processos.add(new Processo(new Programa("P7",90000)));
//		processos.add(new Processo(new Programa("P8",50000)));
//		processos.add(new Processo(new Programa("P9",20000)));
//		processos.add(new Processo(new Programa("P10",10000)));
//		
		Escalonador esc = new Escalonador(processos);
		
		esc.setMode(TipoEscalonamento.RR);
		
	}
}
