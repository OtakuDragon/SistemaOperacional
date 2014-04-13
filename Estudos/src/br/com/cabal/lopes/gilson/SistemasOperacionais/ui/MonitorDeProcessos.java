package br.com.cabal.lopes.gilson.SistemasOperacionais.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import java.awt.Component;
import javax.swing.border.CompoundBorder;
import javax.swing.JComboBox;

import br.com.cabal.lopes.gilson.SistemasOperacionais.SistemaOperacional;
import br.com.cabal.lopes.gilson.SistemasOperacionais.abstractClasses.GilsOSLabel;
import br.com.cabal.lopes.gilson.SistemasOperacionais.abstractClasses.Processo;
import br.com.cabal.lopes.gilson.SistemasOperacionais.enums.TipoEscalonamento;
import javax.swing.JButton;
import javax.swing.ScrollPaneConstants;

public class MonitorDeProcessos extends JFrame {

	private JPanel contentPane;
	public static GilsOSLabel labelTamanhoDaPalavra;
	public static GilsOSLabel labelQuantum;
	public static GilsOSLabel labelTamanhoDaLista;
	public static GilsOSLabel labelNomeProcesso;
	public static GilsOSLabel labelTamanhoProcesso;
	public static GilsOSLabel labelQuantumRestante;
	public static SistemaOperacional so;
	public static JComboBox<TipoEscalonamento> comboBox;
	public static JTextArea console;
	public static JScrollPane consoleContainer;
	public static JButton btnExecutar;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MonitorDeProcessos frame = new MonitorDeProcessos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static synchronized void update(Component c){
		c.update(c.getGraphics());
		try {
			Thread.sleep(40);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static synchronized void print(Object o,String message) {
		
		console.append(o.getClass().getSimpleName() +" Says: "+message+"\n");
		update(MonitorDeProcessos.console);
		console.setCaretPosition(console.getDocument().getLength());
		
	}
	
		
		

	/**
	 * Create the frame.
	 */
	
	
	
	public MonitorDeProcessos() {
		setTitle("GilsOS: Monitorador de Processos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1078, 477);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{307, 742, 0};
		gbl_contentPane.rowHeights = new int[]{429, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		
				
				JPanel infoContainer = new JPanel();
				infoContainer.setAlignmentY(0.0f);
				GridBagConstraints gbc_infoContainer = new GridBagConstraints();
				gbc_infoContainer.fill = GridBagConstraints.BOTH;
				gbc_infoContainer.insets = new Insets(0, 0, 0, 5);
				gbc_infoContainer.gridx = 0;
				gbc_infoContainer.gridy = 0;
				contentPane.add(infoContainer, gbc_infoContainer);
				GridBagLayout gbl_infoContainer = new GridBagLayout();
				gbl_infoContainer.columnWidths = new int[] {150, 0};
				gbl_infoContainer.rowHeights = new int[] {0, 32, 0, 37, 0, 0, 0, 0, 0, 0, 0};
				gbl_infoContainer.columnWeights = new double[]{0.0, 0.0};
				gbl_infoContainer.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
				infoContainer.setLayout(gbl_infoContainer);
				
				JLabel labelInfoSOTitulo = new JLabel("Informa\u00E7\u00F5es Sobre o Sistema Operacional:");
				labelInfoSOTitulo.setBorder(null);
				labelInfoSOTitulo.setFont(new Font("Times New Roman", Font.BOLD, 14));
				GridBagConstraints gbc_labelInfoSOTitulo = new GridBagConstraints();
				gbc_labelInfoSOTitulo.insets = new Insets(0, 0, 5, 0);
				gbc_labelInfoSOTitulo.fill = GridBagConstraints.HORIZONTAL;
				gbc_labelInfoSOTitulo.gridwidth = 2;
				gbc_labelInfoSOTitulo.gridx = 0;
				gbc_labelInfoSOTitulo.gridy = 0;
				infoContainer.add(labelInfoSOTitulo, gbc_labelInfoSOTitulo);
				
				JLabel labelTamanhoDaPalavraTitulo = new JLabel("Tamanho da Palavra:");
				labelTamanhoDaPalavraTitulo.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(10, 10, 10, 10)));
				GridBagConstraints gbc_labelTamanhoDaPalavraTitulo = new GridBagConstraints();
				gbc_labelTamanhoDaPalavraTitulo.insets = new Insets(0, 0, 5, 5);
				gbc_labelTamanhoDaPalavraTitulo.fill = GridBagConstraints.BOTH;
				gbc_labelTamanhoDaPalavraTitulo.gridx = 0;
				gbc_labelTamanhoDaPalavraTitulo.gridy = 1;
				infoContainer.add(labelTamanhoDaPalavraTitulo, gbc_labelTamanhoDaPalavraTitulo);
				
				labelTamanhoDaPalavra = new GilsOSLabel();
				labelTamanhoDaPalavra.setDoubleBuffered(true);
				labelTamanhoDaPalavra.setFont(new Font("Times New Roman", Font.BOLD, 11));
				labelTamanhoDaPalavra.setOpaque(true);
				labelTamanhoDaPalavra.setBackground(Color.WHITE);
				labelTamanhoDaPalavra.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(10, 55, 10, 10)));
				GridBagConstraints gbc_labelTamanhoDaPalavra = new GridBagConstraints();
				gbc_labelTamanhoDaPalavra.insets = new Insets(0, 0, 5, 0);
				gbc_labelTamanhoDaPalavra.fill = GridBagConstraints.BOTH;
				gbc_labelTamanhoDaPalavra.gridx = 1;
				gbc_labelTamanhoDaPalavra.gridy = 1;
				infoContainer.add(labelTamanhoDaPalavra, gbc_labelTamanhoDaPalavra);
				
				JLabel labelQuantumTitulo = new JLabel("Tempo de Quantum:");
				labelQuantumTitulo.setMinimumSize(new Dimension(102, 14));
				labelQuantumTitulo.setMaximumSize(new Dimension(102, 14));
				labelQuantumTitulo.setPreferredSize(new Dimension(102, 14));
				labelQuantumTitulo.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(10, 10, 10, 10)));
				GridBagConstraints gbc_labelQuantumTitulo = new GridBagConstraints();
				gbc_labelQuantumTitulo.insets = new Insets(0, 0, 5, 5);
				gbc_labelQuantumTitulo.fill = GridBagConstraints.BOTH;
				gbc_labelQuantumTitulo.gridx = 0;
				gbc_labelQuantumTitulo.gridy = 2;
				infoContainer.add(labelQuantumTitulo, gbc_labelQuantumTitulo);
				
				labelQuantum = new GilsOSLabel();
				labelQuantum.setDoubleBuffered(true);
				labelQuantum.setFont(new Font("Times New Roman", Font.BOLD, 11));
				labelQuantum.setBackground(new Color(255, 255, 255));
				labelQuantum.setOpaque(true);
				labelQuantum.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(10, 55, 10, 10)));
				GridBagConstraints gbc_labelQuantum = new GridBagConstraints();
				gbc_labelQuantum.insets = new Insets(0, 0, 5, 0);
				gbc_labelQuantum.fill = GridBagConstraints.BOTH;
				gbc_labelQuantum.gridx = 1;
				gbc_labelQuantum.gridy = 2;
				infoContainer.add(labelQuantum, gbc_labelQuantum);
				
				JLabel labelTamanhoDaListaTitulo = new JLabel("<html>Tamanho da lista de pro-<br/>cessos:</html>");
				labelTamanhoDaListaTitulo.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(10, 10, 10, 10)));
				labelTamanhoDaListaTitulo.setHorizontalTextPosition(SwingConstants.CENTER);
				labelTamanhoDaListaTitulo.setMinimumSize(new Dimension(130, 14));
				labelTamanhoDaListaTitulo.setMaximumSize(new Dimension(130, 14));
				labelTamanhoDaListaTitulo.setHorizontalAlignment(SwingConstants.CENTER);
				labelTamanhoDaListaTitulo.setSize(new Dimension(1, 0));
				GridBagConstraints gbc_labelTamanhoDaListaTitulo = new GridBagConstraints();
				gbc_labelTamanhoDaListaTitulo.insets = new Insets(0, 0, 5, 5);
				gbc_labelTamanhoDaListaTitulo.fill = GridBagConstraints.BOTH;
				gbc_labelTamanhoDaListaTitulo.gridx = 0;
				gbc_labelTamanhoDaListaTitulo.gridy = 3;
				infoContainer.add(labelTamanhoDaListaTitulo, gbc_labelTamanhoDaListaTitulo);
				
				labelTamanhoDaLista = new GilsOSLabel();
				labelTamanhoDaLista.setDoubleBuffered(true);
				labelTamanhoDaLista.setFont(new Font("Times New Roman", Font.BOLD, 11));
				labelTamanhoDaLista.setBackground(Color.WHITE);
				labelTamanhoDaLista.setOpaque(true);
				labelTamanhoDaLista.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(10, 55, 10, 10)));
				GridBagConstraints gbc_labelTamanhoDaLista = new GridBagConstraints();
				gbc_labelTamanhoDaLista.insets = new Insets(0, 0, 5, 0);
				gbc_labelTamanhoDaLista.fill = GridBagConstraints.BOTH;
				gbc_labelTamanhoDaLista.gridx = 1;
				gbc_labelTamanhoDaLista.gridy = 3;
				infoContainer.add(labelTamanhoDaLista, gbc_labelTamanhoDaLista);
				
				JLabel labelProcInfoTitulo = new JLabel("<html>Informa\u00E7\u00F5es Sobre  o Processo  em Execu\u00E7\u00E3o:</html>");
				labelProcInfoTitulo.setFont(new Font("Times New Roman", Font.BOLD, 14));
				GridBagConstraints gbc_labelProcInfoTitulo = new GridBagConstraints();
				gbc_labelProcInfoTitulo.insets = new Insets(0, 0, 5, 0);
				gbc_labelProcInfoTitulo.gridwidth = 2;
				gbc_labelProcInfoTitulo.gridx = 0;
				gbc_labelProcInfoTitulo.gridy = 4;
				infoContainer.add(labelProcInfoTitulo, gbc_labelProcInfoTitulo);
				
				JLabel labelNomeProcessoTitulo = new JLabel("Nome Do Processo:");
				labelNomeProcessoTitulo.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(10, 10, 10, 10)));
				GridBagConstraints gbc_labelNomeProcessoTitulo = new GridBagConstraints();
				gbc_labelNomeProcessoTitulo.insets = new Insets(0, 0, 5, 5);
				gbc_labelNomeProcessoTitulo.fill = GridBagConstraints.BOTH;
				gbc_labelNomeProcessoTitulo.gridx = 0;
				gbc_labelNomeProcessoTitulo.gridy = 5;
				infoContainer.add(labelNomeProcessoTitulo, gbc_labelNomeProcessoTitulo);
				
				labelNomeProcesso = new GilsOSLabel();
				labelNomeProcesso.setDoubleBuffered(true);
				labelNomeProcesso.setFont(new Font("Times New Roman", Font.BOLD, 10));
				labelNomeProcesso.setOpaque(true);
				labelNomeProcesso.setBackground(Color.WHITE);
				labelNomeProcesso.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(10, 30, 10, 10)));
				GridBagConstraints gbc_labelNomeProcesso = new GridBagConstraints();
				gbc_labelNomeProcesso.insets = new Insets(0, 0, 5, 0);
				gbc_labelNomeProcesso.fill = GridBagConstraints.BOTH;
				gbc_labelNomeProcesso.gridx = 1;
				gbc_labelNomeProcesso.gridy = 5;
				infoContainer.add(labelNomeProcesso, gbc_labelNomeProcesso);
				
				JLabel labelTamanhoProcessoTitulo = new JLabel("<html>Tamanho Restante para<br/> a Execução:</html>");
				labelTamanhoProcessoTitulo.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(10, 10, 10, 10)));
				GridBagConstraints gbc_labelTamanhoProcessoTitulo = new GridBagConstraints();
				gbc_labelTamanhoProcessoTitulo.insets = new Insets(0, 0, 5, 5);
				gbc_labelTamanhoProcessoTitulo.fill = GridBagConstraints.BOTH;
				gbc_labelTamanhoProcessoTitulo.gridx = 0;
				gbc_labelTamanhoProcessoTitulo.gridy = 6;
				infoContainer.add(labelTamanhoProcessoTitulo, gbc_labelTamanhoProcessoTitulo);
				
				labelTamanhoProcesso = new GilsOSLabel();
				labelTamanhoProcesso.setDoubleBuffered(true);
				labelTamanhoProcesso.setHorizontalAlignment(SwingConstants.LEFT);
				labelTamanhoProcesso.setFont(new Font("Times New Roman", Font.BOLD, 11));
				labelTamanhoProcesso.setBackground(Color.WHITE);
				labelTamanhoProcesso.setOpaque(true);
				labelTamanhoProcesso.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(10, 10, 10, 10)));
				GridBagConstraints gbc_labelTamanhoProcesso = new GridBagConstraints();
				gbc_labelTamanhoProcesso.insets = new Insets(0, 0, 5, 0);
				gbc_labelTamanhoProcesso.fill = GridBagConstraints.BOTH;
				gbc_labelTamanhoProcesso.gridx = 1;
				gbc_labelTamanhoProcesso.gridy = 6;
				infoContainer.add(labelTamanhoProcesso, gbc_labelTamanhoProcesso);
				
				JLabel labelQuantumRestanteTitulo = new JLabel("<html>Tempo Restante para<br/> a execução do processo:</html>");
				labelQuantumRestanteTitulo.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(10, 10, 10, 10)));
				labelQuantumRestanteTitulo.setHorizontalTextPosition(SwingConstants.CENTER);
				labelQuantumRestanteTitulo.setMinimumSize(new Dimension(130, 14));
				labelQuantumRestanteTitulo.setMaximumSize(new Dimension(130, 14));
				labelQuantumRestanteTitulo.setHorizontalAlignment(SwingConstants.CENTER);
				labelQuantumRestanteTitulo.setSize(new Dimension(1, 0));
				GridBagConstraints gbc_labelQuantumRestanteTitulo = new GridBagConstraints();
				gbc_labelQuantumRestanteTitulo.insets = new Insets(0, 0, 5, 5);
				gbc_labelQuantumRestanteTitulo.fill = GridBagConstraints.BOTH;
				gbc_labelQuantumRestanteTitulo.gridy = 7;
				gbc_labelQuantumRestanteTitulo.gridx = 0;
				gbc_labelTamanhoDaListaTitulo.fill = GridBagConstraints.BOTH;
				gbc_labelTamanhoDaListaTitulo.gridx = 0;
				gbc_labelTamanhoDaListaTitulo.gridy = 7;
				infoContainer.add(labelQuantumRestanteTitulo, gbc_labelQuantumRestanteTitulo);
				
				labelQuantumRestante = new GilsOSLabel();
				labelQuantumRestante.setDoubleBuffered(true);
				labelQuantumRestante.setFont(new Font("Times New Roman", Font.BOLD, 11));
				labelQuantumRestante.setBackground(Color.WHITE);
				labelQuantumRestante.setOpaque(true);
				labelQuantumRestante.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(10, 40, 10, 10)));
				GridBagConstraints gbc_labelQuantumRestante = new GridBagConstraints();
				gbc_labelQuantumRestante.insets = new Insets(0, 0, 5, 0);
				gbc_labelQuantumRestante.fill = GridBagConstraints.BOTH;
				gbc_labelQuantumRestante.gridx = 1;
				gbc_labelQuantumRestante.gridy = 7;
				infoContainer.add(labelQuantumRestante, gbc_labelQuantumRestante);
				
				JLabel labelExecutarProcessosTitulo = new JLabel("Executar Processos:");
				labelExecutarProcessosTitulo.setFont(new Font("Times New Roman", Font.BOLD, 14));
				GridBagConstraints gbc_labelExecutarProcessosTitulo = new GridBagConstraints();
				gbc_labelExecutarProcessosTitulo.insets = new Insets(0, 0, 5, 0);
				gbc_labelExecutarProcessosTitulo.gridwidth = 2;
				gbc_labelExecutarProcessosTitulo.gridx = 0;
				gbc_labelExecutarProcessosTitulo.gridy = 8;
				infoContainer.add(labelExecutarProcessosTitulo, gbc_labelExecutarProcessosTitulo);
				
				comboBox = new JComboBox<TipoEscalonamento>();
				comboBox.addItem(TipoEscalonamento.FIFO);
				comboBox.addItem(TipoEscalonamento.SJF);
				comboBox.addItem(TipoEscalonamento.RR);
				comboBox.addItem(TipoEscalonamento.SRT);
				
				JLabel lblAlgoritmoDeEscalonamento = new JLabel("Algoritmo de Escalonamento:");
				GridBagConstraints gbc_lblAlgoritmoDeEscalonamento = new GridBagConstraints();
				gbc_lblAlgoritmoDeEscalonamento.insets = new Insets(0, 0, 5, 5);
				gbc_lblAlgoritmoDeEscalonamento.gridx = 0;
				gbc_lblAlgoritmoDeEscalonamento.gridy = 9;
				infoContainer.add(lblAlgoritmoDeEscalonamento, gbc_lblAlgoritmoDeEscalonamento);
				GridBagConstraints gbc_comboBox = new GridBagConstraints();
				gbc_comboBox.insets = new Insets(0, 0, 0, 5);
				gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
				gbc_comboBox.gridx = 0;
				gbc_comboBox.gridy = 10;
				infoContainer.add(comboBox, gbc_comboBox);
				
				btnExecutar = new JButton("Executar!");
				GridBagConstraints gbc_btnExecutar = new GridBagConstraints();
				gbc_btnExecutar.gridx = 1;
				gbc_btnExecutar.gridy = 10;
				btnExecutar.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						new Thread(new Runnable(){

							@Override
							public void run() {
								btnExecutar.setEnabled(false);
								so = new SistemaOperacional(128,11,(TipoEscalonamento)comboBox.getSelectedItem());
								so.startSO();
								
							}}).start();
						

						
					}});
				infoContainer.add(btnExecutar, gbc_btnExecutar);
		
		
		consoleContainer = new JScrollPane();
		consoleContainer.setVerifyInputWhenFocusTarget(false);
		consoleContainer.setAutoscrolls(true);
		consoleContainer.setDoubleBuffered(true);
		consoleContainer.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		consoleContainer.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		GridBagConstraints gbc_consoleContainer = new GridBagConstraints();
		gbc_consoleContainer.fill = GridBagConstraints.BOTH;
		gbc_consoleContainer.gridx = 1;
		gbc_consoleContainer.gridy = 0;
		contentPane.add(consoleContainer, gbc_consoleContainer);
		
		console = new JTextArea();
		console.setEditable(false);
		consoleContainer.setViewportView(console);
		
	}

}
