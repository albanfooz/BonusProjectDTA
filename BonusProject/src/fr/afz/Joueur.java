package fr.afz;
/**
 *
 * @author utilisateur2
 *
 * Class Joueur
 */
public class Joueur {
	private double argent;

	public Joueur() {
		argent = 100.0;
	}

	//verify if the Joueur has enough money, then returns laMise if everything went right
	public double mise(double laMise) throws CasinoException {
		if(argent-laMise <=0.0) {
			throw new CasinoException("Not enough money!");
		}else {
			argent-=laMise;
			return laMise;
		}
	}
	//adds leGain to argent
	public void gagne(double leGain) {
		this.argent+=leGain;
	}

	public double getArgent() {
		return argent;
	}




}
