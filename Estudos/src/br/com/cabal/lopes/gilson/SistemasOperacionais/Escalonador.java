package br.com.cabal.lopes.gilson.SistemasOperacionais;

import java.util.List;

import br.com.cabal.lopes.gilson.SistemasOperacionais.abstractClasses.Processo;
import br.com.cabal.lopes.gilson.SistemasOperacionais.enums.TipoEscalonamento;
import br.com.cabal.lopes.gilson.SistemasOperacionais.exceptions.UniqueRunnerException;

public class Escalonador {

	List<Processo> processos;
	
	
	public Escalonador(List<Processo> processos) {
		this.processos = processos;
	}
	
	public void setMode(TipoEscalonamento tipo){
		
		switch (tipo) {
		case FIFO:
			
			try {
				organizeFIFO(processos);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			break;
			
		case SJF:
			
			try {
				organizeSJF(processos);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
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

	private void organizeFIFO(List<Processo> procList) throws UniqueRunnerException,InterruptedException{
		System.out.println("Organizando em First In First Out");
		for (Processo processo : procList) {
			
			processo.activate();
			new Thread(processo).start();
		
			while(processo.getEstado()){
				Thread.sleep(3000);
			}
			
			procList.remove(this);
		
		}
		
		
	}

	private void organizeSJF(List<Processo> procList) throws UniqueRunnerException,InterruptedException {
		System.out.println("Organizando em Short Job First");
		
//		Thread.sleep(2000);
//		procList.get(0).stop();
//		Thread.sleep(2000);
//		procList.get(0).activate(procList);
		procList.get(0).activate();
		
		new Thread(procList.get(0)).start();
	}

	private void organizeRR(List<Processo> procList) {
		System.out.println("Organizando em Round Robin");
		
	}

	private void organizeSRT(List<Processo> procList) {
		System.out.println("Organizando em Shortest Remaining Time");
		
	}


}
