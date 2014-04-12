package br.com.cabal.lopes.gilson.SistemasOperacionais.abstractClasses;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import br.com.cabal.lopes.gilson.SistemasOperacionais.Escalonador;
import br.com.cabal.lopes.gilson.SistemasOperacionais.Quantum;
import br.com.cabal.lopes.gilson.SistemasOperacionais.SistemaOperacional;
import br.com.cabal.lopes.gilson.SistemasOperacionais.enums.TipoEscalonamento;
import br.com.cabal.lopes.gilson.SistemasOperacionais.exceptions.UniqueRunnerException;

public class Processo implements Runnable,Comparable<Processo> {
	
	private String nome;
	private int tamanho;
	public int quantum;
	private int quantumRefer = SistemaOperacional.quantum;
	private int palavra = SistemaOperacional.palavra;
	boolean estado = false;//true = ativo false = inativo
	private boolean finished = false;
	private List<Processo> listaProcs = SistemaOperacional.processos;
	

	
	public Processo(Programa p){
		setTamanho(p.getTamanho());
		setQuantum(quantumRefer);
		setNome(p.getNome());
	}
	
	private synchronized void ticTac() throws InterruptedException{
		while(quantum > 0 && estado == true || finished == false && tamanho > 0){
			
			
			System.out.println("================================================");
			System.out.println("Nome do Processo em execução: "+this.getNome()+".");
			System.out.println("Segundos Restantes no Quantum: " +this.getQuantum()+" Segundos.");
			System.out.println("Tamanho Restante para a Execução:" +this.getTamanho()+" bits.");

			
			tamanho -= palavra;
			
			if(tamanho>0 && this.quantum<=0 &&  Escalonador.tipo == TipoEscalonamento.FIFO || Escalonador.tipo == TipoEscalonamento.SJF){
				quantum = quantumRefer;
			}
			
			if(tamanho <= 0 || quantum <=0 ){	
				this.stop();
				return;
			}
		}
	}
	
	@Override
	public void run() {
		try {
			new Thread(new Quantum(this)).start();
			ticTac();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public void activate() throws UniqueRunnerException{
		for (Processo processo : listaProcs) {
			if(processo.estado){
				throw new UniqueRunnerException("O Processo não pode ser executado pois outro processo ja está sendo executado");
			}	
		}
		this.setEstado(true);
	}
	
	public synchronized void stop(){
	
		
		this.setEstado(false);
		this.setQuantum(this.getQuantumRefer());
		System.out.println("================================================");
		System.out.println("PROCESSO PARADO");
		System.out.println("Nome do Processo em parado: "+this.getNome()+".");
		if(this.getQuantum() <= 0 && this.getTamanho()>0){
			System.out.println("Motivo: O Quantum Zerou");
		}else{
			System.out.println("Motivo: O Processo foi completamente executado");
			listaProcs.remove(this);
			this.setFinished(true);
			
		}
		
		int tam;
		
		if(this.getTamanho()<0){
			tam = 0;
		}else{
			tam = this.getTamanho();
		}
		
		System.out.println("Tamanho Restante para a Execução:" +tam+" Kb.");
		if(listaProcs.size() == 0){
			
			System.out.println("============================================");
			Escalonador.printTime(2);
			long diferenca = (Escalonador.fim.getTime()/1000) - (Escalonador.inicio.getTime()/1000);
			long diffMillis = (Escalonador.fim.getTime()) - (Escalonador.inicio.getTime());
			System.out.println("Tempo de execução: "+ diferenca  +" Segundos" );
			System.out.println("Tempo de execução: "+ diffMillis  +" Millisegundos" );
		}
		
		
	}
	
	@Override
	public int compareTo(Processo p) {
		
		//retorna -1 se this menor p
		//retorna 0 se this igual p
		//retorna 1 se this maior p
		
		
		
		switch (Escalonador.tipo) {
		case FIFO:
			break;
			
		case SJF:
			
			if(this.getTamanho()< p.getTamanho()){
				return -1;
			}else if(this.getTamanho()> p.getTamanho()){
				return 1;
				
			}
				return 1;
			
		case SRT:
			
			break;
			
		case RR:
			
			break;
			
		default:
			break;
		}
		
		return 0;
	}
	
	
	public int getTamanho() {
		return tamanho;
	}

	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}

	public int getQuantum() {
		return quantum;
	}

	public void setQuantum(int quantum) {
		this.quantum = quantum;
	}

	private void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	public boolean getEstado() {
		return this.estado;
	}

	public int getQuantumRefer() {
		return quantumRefer;
	}

	public void setQuantumRefer(int quantumRefer) {
		this.quantumRefer = quantumRefer;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}


}
