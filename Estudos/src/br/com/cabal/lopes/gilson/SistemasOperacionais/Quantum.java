package br.com.cabal.lopes.gilson.SistemasOperacionais;

import br.com.cabal.lopes.gilson.SistemasOperacionais.abstractClasses.Processo;

public class Quantum implements Runnable {

	private Processo processo;
	
	public Quantum(Processo p){
		this.processo = p;
	}
	
	@Override
	public void run() {
		try {
			
			while(processo.getEstado()){			
			processo.setQuantum(--processo.quantum);
			Thread.sleep(1000);
			
			if(processo.quantum< -3){
				break;
			}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}

	}

}
