package fr.afz;

import javax.swing.JTextField;
/**
 *
 * LecteurRoue, affiche l'état de chaque roue dans un TextField (Runnable)
 * @author utilisateur2
 *
 */
public class LecteurRoue implements Runnable{
	volatile boolean flag = true;
	JTextField txt;

	public LecteurRoue(JTextField txt) {
		super();
		this.txt = txt;
	}
	public void stopRunning()
	{
		flag = false;
		//OR
		//this.t.interrupt();
	}
	@Override
	public void run() {
		try {
			while(flag) {
				txt.setText(Roue.listCountRoues.toString());

				Thread.sleep(10);

			}
			System.out.println("hello");
		} catch (InterruptedException e) {
			//System.out.println("Lecteur Ended");
			//flag = false;
		}
	}
}
