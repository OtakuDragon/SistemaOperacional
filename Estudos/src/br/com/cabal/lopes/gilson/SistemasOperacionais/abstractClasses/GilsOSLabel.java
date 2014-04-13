package br.com.cabal.lopes.gilson.SistemasOperacionais.abstractClasses;

import javax.swing.JLabel;

import br.com.cabal.lopes.gilson.SistemasOperacionais.ui.MonitorDeProcessos;

public class GilsOSLabel extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4588194799666289507L;
	
	public GilsOSLabel(){
		super();
	};
	
	@Override
	public void setText(String text) {
		super.setText(text);
		MonitorDeProcessos.update(this);
	}
	
	

	
}
