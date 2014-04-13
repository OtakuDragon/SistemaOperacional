package br.com.cabal.lopes.gilson.SistemasOperacionais.abstractClasses;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import br.com.cabal.lopes.gilson.SistemasOperacionais.Escalonador;
import br.com.cabal.lopes.gilson.SistemasOperacionais.Quantum;
import br.com.cabal.lopes.gilson.SistemasOperacionais.SistemaOperacional;
import br.com.cabal.lopes.gilson.SistemasOperacionais.enums.TipoEscalonamento;
import br.com.cabal.lopes.gilson.SistemasOperacionais.exceptions.UniqueRunnerException;
import br.com.cabal.lopes.gilson.SistemasOperacionais.ui.MonitorDeProcessos;

public class Processo implements Runnable,Comparable<Processo> {
	
	private String nome;
	private long tamanho;
	public int quantum;
	public static int quantumRefer = MonitorDeProcessos.so.quantum;
	public static int palavra= MonitorDeProcessos.so.palavra;
	boolean estado = false;//true = ativo false = inativo
	private boolean finished = false;
	public static List<Processo> listaProcs = MonitorDeProcessos.so.processos;
	

	
	public Processo(Programa p){
		setTamanho(p.getTamanho());
		setQuantum(quantumRefer);
		setNome(p.getNome());
	}
	
	private synchronized void ticTac() throws InterruptedException{
		while(quantum > 0 && estado == true || finished == false && tamanho > 0){
			
			
			MonitorDeProcessos.labelNomeProcesso.setText(this.getNome());
			MonitorDeProcessos.labelQuantumRestante.setText(String.valueOf(this.getQuantum()));
			MonitorDeProcessos.labelTamanhoProcesso.setText(String.valueOf(getTamanho()));

			
			tamanho -= palavra;
			
			if(tamanho>0 && this.quantum<=0 &&  (Escalonador.tipo == TipoEscalonamento.FIFO || Escalonador.tipo == TipoEscalonamento.SJF)){
				quantum = quantumRefer-1;
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
	
		Processo ultimo = null;
		boolean podeTerminar = true;
		
		MonitorDeProcessos.print(this,"================================================");
		MonitorDeProcessos.print(this,"PROCESSO PARADO");
		MonitorDeProcessos.print(this,"Nome do Processo em parado: "+this.getNome()+".");
		if(listaProcs.size() == 1){
			ultimo = this;
			
			if(ultimo.getTamanho()>0){
				podeTerminar = false;
			}
		}
		if(this.getQuantum() <= 0 && this.getTamanho()>0){
			MonitorDeProcessos.print(this,"Motivo: O Quantum Zerou");
			this.setQuantum(this.getQuantumRefer());
			listaProcs.remove(this);
			this.setFinished(true);
		}else{
			MonitorDeProcessos.print(this,"Motivo: O Processo foi completamente executado");
			listaProcs.remove(this);
			this.setFinished(true);
			MonitorDeProcessos.labelTamanhoDaLista.setText(String.valueOf(listaProcs.size()));
			
		}
		
		
		long tam;
		
		if(this.getTamanho()<0){
			tam = 0;
		}else{
			tam = this.getTamanho();
		}
		
		MonitorDeProcessos.labelTamanhoProcesso.setText(String.valueOf(tam));
		if(listaProcs.size() == 0 && podeTerminar){
			
			MonitorDeProcessos.print(this,"============================================");
			Escalonador.printTime(2);
			long diferenca = (Escalonador.fim.getTime()/1000) - (Escalonador.inicio.getTime()/1000);
			long diffMillis = (Escalonador.fim.getTime()) - (Escalonador.inicio.getTime());
			MonitorDeProcessos.print(this,"Tempo de execução: "+ diferenca  +" Segundos" );
			MonitorDeProcessos.print(this,"Tempo de execução: "+ diffMillis  +" Millisegundos" );
			//MonitorDeProcessos.btnExecutar.setEnabled(true);
		}
		
		this.setEstado(false);
		
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
	
	
	public long getTamanho() {
		return tamanho;
	}

	public void setTamanho(long l) {
		this.tamanho = l;
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
