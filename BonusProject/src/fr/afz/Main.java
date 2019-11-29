package fr.afz;

public class Main {
	//Main pour la version console ( /!\ Warning : non testé, utiliser plutôt GuiMain
	public static void main(String[] args) {
		Joueur p1 = new Joueur();
		SlotGame slt = new SlotGame(p1);

		// Starts 10 classicGame
		for (int i = 0; i < 10; i++) {

			try {
				slt.classicGame();
			} catch (CasinoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(p1.getArgent());
		}
	}

}
