package br.com.cabal.lopes.gilson.SistemasOperacionais;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import br.com.cabal.lopes.gilson.SistemasOperacionais.abstractClasses.Processo;
import br.com.cabal.lopes.gilson.SistemasOperacionais.enums.TipoEscalonamento;
import br.com.cabal.lopes.gilson.SistemasOperacionais.exceptions.UniqueRunnerException;

public class Escalonador {

	List<Processo> processos;
	static public Date inicio;
	static public Date fim;
	static public TipoEscalonamento tipo;
	
	
	public Escalonador(List<Processo> processos) {
		this.processos = processos;
	}
	
	public void setMode(TipoEscalonamento tipo){
		
		switch (tipo) {
		case FIFO:
			
			try {
				Escalonador.tipo = TipoEscalonamento.FIFO;
				organizeFIFO(processos);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			break;
			
		case SJF:
			
			try {
				Escalonador.tipo = TipoEscalonamento.SJF;
				organizeSJF(processos);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			break;

		case SRT:
			tipo = TipoEscalonamento.SRT;
			organizeSRT(processos);
			
			break;
			
		case RR:
			tipo = TipoEscalonamento.RR;
			try {
				organizeRR(processos);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
			break;
			
		default:
			break;
		}
	}

	private void organizeFIFO(List<Processo> procList) throws UniqueRunnerException,InterruptedException{
		System.out.println("Organizando em First In First Out");
		printTime(1);
		
		int tamanhoLista = procList.size();
		
		for (int i=0;i<tamanhoLista;i++){
			
			Processo processo = procList.get(0); 
			
			processo.activate();
			new Thread(processo).start();
		
			while(processo.getEstado()){
				Thread.sleep(1);
			}
		}		
	}

	private void organizeSJF(List<Processo> procList) throws UniqueRunnerException,InterruptedException {
		System.out.println("Organizando em Short Job First");
		printTime(1);
		Collections.sort(procList);
		for (Processo processo : procList) {
			System.out.println(processo.getTamanho());
		}
		
		int tamanhoLista = procList.size();
		
		for (int i=0;i<tamanhoLista;i++){
			
			Processo processo = procList.get(0); 
			
			processo.activate();
			new Thread(processo).start();
		
			while(processo.getEstado()){
				Thread.sleep(1);
			}
		}
	}

	private void organizeRR(List<Processo> procList) throws UniqueRunnerException,InterruptedException {
		System.out.println("Organizando em Round Robin");
		printTime(1);
		
		int tamanhoLista = procList.size();
		
		while(procList.size()>0){
			
			Processo processo = procList.get(0); 
			
			processo.activate();
			new Thread(processo).start();
		
			while(processo.getEstado()){
				Thread.sleep(1);
			}
			if(processo.getTamanho()>0){
				processo.setQuantum(processo.getQuantumRefer());
				processo.setFinished(false);
				procList.add(processo);
				
			}
		}
		
		
	}

	private void organizeSRT(List<Processo> procList) {
		System.out.println("Organizando em Shortest Remaining Time");
		printTime(1);
		
	}
	
	public static void printTime(int quando){
		Calendar c = Calendar.getInstance();
		Date time = c.getTime();
		DateFormat f =  DateFormat.getTimeInstance();
		
		if (quando == 1){
			inicio = time;
			System.out.println("Execução iniciada às:  " + f.format(time));
			
		}else if(quando == 2){
			fim = time;
			System.out.println("Execução terminada às:  " + f.format(time));
		}
	}


}
