package fr.afz;

import java.awt.Point;
import java.awt.event.*;
import java.util.Random;
import java.util.regex.*;

import javax.swing.*;
/**
 * GuiMain() <=> SlotGame(), mais adaptée pour la GUI
 * @author utilisateur2
 *
 */
class GuiMain extends JFrame {
	private static final long serialVersionUID = 1L;
	Joueur p1 = new Joueur();
	SlotMachine slt;
	private Double mise,gain;
	private boolean isLudicrous = false;
	Pattern MY_PATTERN = Pattern.compile("((([0-9]+)(\\.|,)([0-9]+))|([0-9]+))");

	private JButton btnExit  = new JButton("Exit");
	private JButton btnQuickGame = new JButton("QuickGame");
	private JButton btnClassicGame = new JButton("ClassicGame");
	private JButton btnStopOne = new JButton("StopOne");
	private JButton btnStopAll = new JButton("StopAll");
	private JButton btnLudicrous = new JButton("Ludicrous Mode is OFF");

	private JTextField txtA = new JTextField();
	private JTextField txtB = new JTextField();
	private JTextField txtC = new JTextField();
	private JTextField txtD = new JTextField();
	private JTextField txtE = new JTextField();

	private JLabel lblA = new JLabel("Mise :");
	volatile private JLabel lblB = new JLabel("Result :");
	private JLabel lblC = new JLabel("Gain :");
	private JLabel lblD = new JLabel("Argent :");
	private JLabel lblE = new JLabel("NbRoue :");
	private JLabel lblF = new JLabel("Pour des raisons de performance, nbRoue est limité à 1000");
	private JLabel lblF2 = new JLabel("Sauf si vous choisissez [ludicrous mode]");

	Thread t;
	public GuiMain(){
		this.setTitle("Casino - SlotGame");
		this.setSize(720,480);
		this.setLocation(new Point(300,200));
		this.setLayout(null);
		this.setResizable(true);

		this.initComponent();
		this.initEvent();
	}

	private void initComponent(){
		//TODO use smart coordinate OR smart auto layout
		btnExit.setBounds(300,130, 80,25);
		btnQuickGame.setBounds(300,100,130,25);
		btnQuickGame.setEnabled(false);
		btnClassicGame.setBounds(300,70,130,25);
		btnStopOne.setBounds(300,70,90,25);
		btnStopAll.setBounds(300,100,90,25);
		btnLudicrous.setBounds(30,230,170,25);

		txtA.setBounds(100,10,100,20);
		txtB.setBounds(100,35,400,20);
		txtB.setEditable(false);
		txtC.setBounds(100,65,100,20);
		txtC.setEditable(false);
		txtD.setBounds(100,95,100,20);
		txtD.setEditable(false);
		txtE.setBounds(100,125,100,20);

		lblA.setBounds(20,10,100,20);
		lblB.setBounds(20,35,100,20);
		lblC.setBounds(20,65,100,20);
		lblD.setBounds(20,95,100,20);
		lblE.setBounds(20,125,100,20);
		lblF.setBounds(20,165,400,20);
		lblF2.setBounds(20,185,300,20);


		this.add(btnExit);
		this.add(btnQuickGame);
		this.add(btnLudicrous);

		this.add(btnClassicGame);


		this.add(lblA);
		this.add(lblB);
		this.add(lblC);
		this.add(lblD);
		this.add(lblE);
		this.add(lblF);
		this.add(lblF2);

		this.add(txtA);
		this.add(txtB);
		this.add(txtC);
		this.add(txtD);
		this.add(txtE);

		//Affiche l'argent du joueur
		txtD.setText(((Double)p1.getArgent()).toString());
	}
	//init les AcitonListeners pour les boutons
	private void initEvent(){

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e){
				System.exit(1);
			}
		});

		btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GuiMain.this.btnExitClick(e);
			}
		});

		btnQuickGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GuiMain.this.btnQuickClick(e);
			}
		});
		btnClassicGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GuiMain.this.btnClassicClick(e);
			}
		});
		btnStopOne.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GuiMain.this.btnStopOne(e);
			}
		});
		btnStopAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GuiMain.this.btnStopAll(e);
			}
		});
		btnLudicrous.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GuiMain.this.btnLudicrousClick(e);
			}
		});
	}
	//Toggle isLudicrous
	protected void btnLudicrousClick(ActionEvent e) {
		if(isLudicrous) {
			btnLudicrous.setText("Ludicrous Mode is OFF");
		}else {
			btnLudicrous.setText("Ludicrous Mode is ON");
		}
		isLudicrous = !isLudicrous;
	}
	/*Unused : caused bugs
	protected double readTextOrDef(JTextField txt,double defaut) throws NumberFormatException { //unused, because, unknown bug when used
		double ret = defaut;

		if(txt.getText().isEmpty()) {
			txt.setText(((Double)defaut).toString());
		}else {
			Matcher m = MY_PATTERN.matcher(txt.getText());
			if(m.find()) {
				ret = Double.parseDouble(m.group(1));
			}else {
				txt.setText(((Double)defaut).toString());
			}

		}
		return ret;
	}
	 */

	//Read textField si TextField a le bon format, sinon retourne defaut
	protected int readTextOrDef(JTextField txt,int defaut) throws NumberFormatException{
		int ret = defaut;
		Pattern MY_PATTERN = Pattern.compile("(([0-9]+))");
		if(txt.getText().isEmpty()) {
			//txt.setText(((Integer)defaut).toString());
		}else {
			Matcher m = MY_PATTERN.matcher(txt.getText());
			if(m.find()) {
				ret = Integer.parseInt(m.group(1));
				//txt.setText(m.group(1));
			}

		}
		return ret;
	}
	//Demarre et init la SlotMachine avec des parametres correct
	protected void startup() throws NumberFormatException, CasinoException {
		Double defaut = 1.0;
		if(txtA.getText().isEmpty()) {
			txtA.setText(defaut.toString());
			mise = defaut;
		}else {
			//ne retient que la valeur correspondant à MY_PATTERN (Regex)
			Matcher m = MY_PATTERN.matcher(txtA.getText());
			if(m.find()) {
				mise = Double.parseDouble(m.group(1));
			}else {

				this.mise = defaut;
			}
			if(mise<0.0) {
				this.mise = defaut;
			}
		}
		this.mise = p1.mise(mise);
		txtA.setText(this.mise.toString());
		int nbRoue = this.readTextOrDef(txtE, 3);
		if(nbRoue<3) {
			nbRoue = 3;
		}else {
			if(!isLudicrous) {
				if(nbRoue>1000 ) {
					nbRoue = 100;
				}
			}
		}
		txtE.setText(((Integer)nbRoue).toString());
		slt = new SlotMachine(nbRoue);
		slt.roll();
		t = new Thread(new LecteurRoue(txtB));
		t.start();
	}

	//refresh l'affichage (si on ajoute/retire des boutons)
	private void refresh() {
		this.revalidate();
		this.repaint();
	}
	//Termine la partie correctement et affiche les resultats
	private void fin() {
		t.interrupt();
		gain = SlotGame.verif(slt.getiList());
		gain*=mise;
		p1.gagne(gain);
		Roue.listCountRoues.clear();
		//txtB.setText(slt.getiList().toString());
		txtC.setText(gain.toString());
		txtD.setText(((Double)p1.getArgent()).toString());
		this.remove(btnStopOne);
		this.remove(btnStopAll);
		this.add(btnClassicGame);
		this.add(btnQuickGame);
		this.refresh();
	}

	//Stop toutes les roues d'un coup
	protected void btnStopAll(ActionEvent e) {
		slt.stopAll();
		this.fin();
	}
	//Stop une roue à la fois
	protected void btnStopOne(ActionEvent e) {
		Random rand = new Random();

		try {
			for (int i = 0; i < rand.nextInt(500) ; i++) {//bricolage pour eviter que le compteur cesse de s'afficher
				Thread.sleep(1);
			}

		} catch (InterruptedException e1) {
			JOptionPane.showMessageDialog(null,
					e1.toString(),
					"Error",
					JOptionPane.ERROR_MESSAGE);
		}
		slt.stopOneRoue();
		if(slt.isEveryRoueStopped()) {
			this.fin();
		}else {
			//txtB.setText(slt.getiList().toString());
			this.remove(btnStopAll);
			this.refresh();
		}
	}
	//Start une game
	protected void btnClassicClick(ActionEvent e) {
		try {
			this.startup();
			this.add(btnStopOne);
			this.add(btnStopAll);
			this.remove(btnClassicGame);
			this.remove(btnQuickGame);
			this.refresh();


		} catch (NumberFormatException e1) {
			System.out.println(e1);
			JOptionPane.showMessageDialog(null,
					e1.toString(),
					"Error",
					JOptionPane.ERROR_MESSAGE);
		}catch(CasinoException e1) {
			JOptionPane.showMessageDialog(null,
					e1.toString(),
					"Plus d'argent - ¯\\_(ツ)_/¯",
					JOptionPane.ERROR_MESSAGE);
		}


	}
	//Exit
	private void btnExitClick(ActionEvent evt){
		System.exit(0);
	}

	//Start et Termine une game (Disabled car buggy)
	private void btnQuickClick(ActionEvent evt){
		try {
			this.startup();
			slt.stopAll();
			Thread.sleep(1);
			this.fin();
		}catch(Exception e){
			System.out.println(e);
			JOptionPane.showMessageDialog(null,
					e.toString(),
					"Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	//Main
	public static void main(String[] args) {
		GuiMain f = new GuiMain();
		f.setVisible(true);
	}
}

