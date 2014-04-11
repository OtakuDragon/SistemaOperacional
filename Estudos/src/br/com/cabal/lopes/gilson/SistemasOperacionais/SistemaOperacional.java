package br.com.cabal.lopes.gilson.SistemasOperacionais;

import java.util.ArrayList;
import java.util.List;

import br.com.cabal.lopes.gilson.SistemasOperacionais.abstractClasses.Processo;
import br.com.cabal.lopes.gilson.SistemasOperacionais.enums.TipoEscalonamento;
import br.com.cabal.lopes.gilson.SistemasOperacionais.processos.Processo1;

public class SistemaOperacional {

	static List<Processo> processos = new ArrayList<Processo>();
	
	
	public static void main(String[] args) {
	
		processos.add(new Processo1(50,15));
		
		Escalonador esc = new Escalonador(processos);
		
		esc.setMode(TipoEscalonamento.SJF);
		
	}
}
