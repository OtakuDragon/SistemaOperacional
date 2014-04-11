package br.com.cabal.lopes.gilson.SistemasOperacionais.abstractClasses;

public class Processo implements Runnable {
	
	private int tamanho;
	private int quantum;;
	boolean estado;//true = ativo false = inativo
	

	
	public Processo(int tamanho, int quantum){
		this.tamanho = tamanho;
		this.quantum = quantum;
	}
	
	private void ticTac() throws InterruptedException{
		while(quantum > 0 && estado == true && tamanho > 0){
			Thread.sleep(1000);
			System.out.println(quantum);
			quantum--;
			tamanho -= 5;
		}
	}
	
	@Override
	public void run() {
		try {
			ticTac();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
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

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
}
