package fr.afz;

import java.util.*;
import java.util.regex.Pattern;
/**
 *
 * @author Alban_fooz
 * SlotGame is for launching a game in the console from the Main class
 * The methode verif() is used in GuiMain as well
 *
 */
public class SlotGame {
	protected Joueur j1;
	protected int nbRoue;
	protected double holding;
	protected double gain;

	public SlotGame() {
		this.nbRoue = 3;
		this.j1 = new Joueur();
	}
	public SlotGame(Joueur j) {
		this.nbRoue = 3;
		this.j1 = j;
	}
	public SlotGame(int nbrRoue) {
		this.nbRoue = nbrRoue;
		this.j1 = new Joueur();
	}
	public SlotGame(int nbrRoue, Joueur j) {
		this.nbRoue = nbrRoue;
		this.j1 = j;
	}

	//Quick game, do all at once (start & stop & display)
	public void fastGame() throws CasinoException {
		System.out.println("Mise : 1$ (par defaut)");
		SlotMachine slot1 = new SlotMachine(nbRoue);
		holding = j1.mise(1.0);
		slot1.roll();
		slot1.stopAll();
		slot1.displayResultDev();
		gain = SlotGame.verif(slot1.getiList());
		j1.gagne(gain*holding);
		System.out.println("Gagne " +gain+"*"+holding);
	}
	//read input
	public double readMise(Scanner in) {
		String regex ="([0-9]+)(\\.|,)([0-9]+)";
		double ret = 1.0;
		boolean matches = false;
		do {
			System.out.println("Mise combien ? (ENTRER pour confirmer)");
			String s = in.nextLine();
			if (Pattern.matches(regex, s)) {
				matches = true;
				ret = Double.parseDouble(s);
			}else {
				if(s.isEmpty()){
					matches = true;
					ret = 1.0;
				}

			}
		} while (!matches);

		return ret;

	}

	//Start a game & asks the user to stop one Roue at a time
	public void classicGame() throws CasinoException {
		SlotMachine slot1 = new SlotMachine(nbRoue);
		Scanner in = new Scanner(System.in);
		holding = j1.mise(this.readMise(in));

		slot1.roll();

		while (!slot1.isEveryRoueStopped()) {
			System.out.println("type ENTER to stop one.");
			slot1.stopOneRoue();
			slot1.displayResult();

		}
		gain = SlotGame.verif(slot1.getiList());
		j1.gagne(gain*holding);
		System.out.println("Gagne " +gain+"*"+holding);
	}
	//Verification si le résultat est gagnant (suite de 2 ou 3 chiffres)
	public static double verif (List<Integer> integers) {
		int comboSame = -1;					// 1 passage min dans la boucle

		int nbCombo2 = 0; 				//à convertir en map si plus de combo
		int nbCombo3 =0;
		double ret = 0.0;
		Integer precedent = integers.get(0);
		if(integers.isEmpty()) {
			throw new IllegalArgumentException("expected a List of min size 3");
		}


		for (Integer integer : integers) {
			if(integer == precedent) {
				comboSame++;
				if(comboSame == 1) {
					nbCombo2++;
				}else {
					if(comboSame >= 2) {
						nbCombo3++;
					}
				}
			}else {
				comboSame = 0;
			}
			//			if(integer == (prec+1)%10) {
			//				comboSuitePlus++;
			//			}
			//			if(integer == (prec-1)%10) {
			//				comboSuiteMoins++;
			//			}
			precedent = integer;
		}


		//if(nbCombo3!=0) {	//should be changed when dealing with more than 3 Roues
		if(comboSame == integers.size()-1){
			ret = 2.0;
		} else {
			if(nbCombo2 !=0) {
				ret = 1.5;
			}
		}
		return ret;

	}

}
