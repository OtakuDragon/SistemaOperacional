package br.com.cabal.lopes.gilson.SistemasOperacionais;

import java.util.List;

import br.com.cabal.lopes.gilson.SistemasOperacionais.abstractClasses.Processo;
import br.com.cabal.lopes.gilson.SistemasOperacionais.enums.TipoEscalonamento;

public class Escalonador {

	List<Processo> processos;
	
	
	public Escalonador(List<Processo> processos) {
		this.processos = processos;
	}
	
	public void setMode(TipoEscalonamento tipo){
		
		switch (tipo) {
		case FIFO:
			
			organizeFIFO(processos);
			
			break;
			
		case SJF:
			
			organizeSJF(processos);
			
			break;

		case SRT:
			
			organizeSRT(processos);
			
			break;
			
		case RR:
			
			organizeRR(processos);
			
			break;
			
		default:
			break;
		}
	}

	private void organizeFIFO(List<Processo> procList) {
		System.out.println("Organizando em First In First Out");
		
	}

	private void organizeSJF(List<Processo> procList) {
		System.out.println("Organizando em Short Job First");
		procList.get(0).setEstado(true);
		
		new Thread(procList.get(0)).start();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		procList.get(0).setEstado(false);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		procList.get(0).setEstado(true);
		
		new Thread(procList.get(0)).start();
	}

	private void organizeRR(List<Processo> procList) {
		System.out.println("Organizando em Round Robin");
		
	}

	private void organizeSRT(List<Processo> procList) {
		System.out.println("Organizando em Shortest Remaining Time");
		
	}


}
