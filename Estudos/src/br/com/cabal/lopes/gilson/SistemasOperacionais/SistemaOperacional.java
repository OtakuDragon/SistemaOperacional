package br.com.cabal.lopes.gilson.SistemasOperacionais;

import java.util.ArrayList;
import java.util.List;

import br.com.cabal.lopes.gilson.SistemasOperacionais.abstractClasses.Processo;
import br.com.cabal.lopes.gilson.SistemasOperacionais.abstractClasses.Programa;
import br.com.cabal.lopes.gilson.SistemasOperacionais.enums.TipoEscalonamento;
import br.com.cabal.lopes.gilson.SistemasOperacionais.ui.MonitorDeProcessos;


public class SistemaOperacional {

	public List<Processo> processos = new ArrayList<Processo>();
	public int palavra;//bits - Tamanho da instrução que a cpu pode ler por vez
	public int quantum;//seg - Quantum do processador
	private TipoEscalonamento tipo;
	
	
	public SistemaOperacional(int palavra,int quantum,TipoEscalonamento tipo) {
		
		this.palavra = palavra;
		this.quantum = quantum;
		this.tipo = tipo;
		

		
	}
	
	public void startSO(){
		
	
		processos.add(new Processo(new Programa("Excel",1000l)));
		processos.add(new Processo(new Programa("Word",16000l)));
		processos.add(new Processo(new Programa("Chrome",1600l)));
		processos.add(new Processo(new Programa("Skype",2600l)));
//		processos.add(new Processo(new Programa("P1",10000l)));
//		processos.add(new Processo(new Programa("P2",14600l)));
//		processos.add(new Processo(new Programa("P3",14800l)));
//		processos.add(new Processo(new Programa("P4",14900l)));
//		processos.add(new Processo(new Programa("P5",18000l)));
//		processos.add(new Processo(new Programa("P6",16000l)));
//		processos.add(new Processo(new Programa("P7",90000l)));
//		processos.add(new Processo(new Programa("P8",50000l)));
//		processos.add(new Processo(new Programa("P9",20000l)));
//		processos.add(new Processo(new Programa("P10",10000l)));
////		
		MonitorDeProcessos.labelQuantum.setText(String.valueOf(quantum-1));
		MonitorDeProcessos.labelTamanhoDaPalavra.setText(String.valueOf(palavra));
		MonitorDeProcessos.labelTamanhoDaLista.setText(String.valueOf(processos.size()));
		MonitorDeProcessos.print(this,"Execução de processos iniciada");
		
		
		
		
		
		Escalonador esc = new Escalonador(processos);
		
		esc.setMode(tipo);
	}
	
}
