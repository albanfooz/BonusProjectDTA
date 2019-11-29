package fr.afz;

import java.util.Random;
/**
 * 1 Roue : 1 Thread
 * @author utilisateur2
 *
 */
public class Roue implements Runnable {
	//Valeur de la roue
	private volatile int count;
	private int sleepTime;
	private Thread t;

	//Condition d'arret du thread
	private volatile boolean  flag = true;

	//Debug : si InterruptedException est catché ex = true
	private boolean ex = false;

	//Pour le LecteurRoue, liste toutes les valeurs de chaque roue
	public static ConcurrentMapCasino<Integer, Integer> listCountRoues = new ConcurrentMapCasino<Integer, Integer>();

	// nombre d'instance de Roue
	static volatile int nbRoue = 0;

	//Numero de l'instance de la roue
	int roueNb;

	Roue() {
		Random rand = new Random();
		this.count = 0;
		this.sleepTime = rand.nextInt(250);
		t = new Thread(this);
		t.start();
		roueNb = nbRoue++;
	}
	public void stopRunning()
	{
		flag = false;
		//OR
		//this.t.interrupt();
		nbRoue--;
	}

	public int getCount() {
		return count;
	}

	public boolean getEx() {
		return ex;
	}
	//Debug
	protected int getSleepTime() {
		return sleepTime;
	}
	//Count from 0 to 9 (and go back to 0)
	@Override
	public void run() {
		try {
			while (flag) {
				Thread.sleep(sleepTime);
				if(this.count>=9){
					this.count=0;
				} else {
					this.count++;
				}
				listCountRoues.put(roueNb, count);
			}
			//System.out.println(this.toString() + "stopped properly");
		} catch (InterruptedException e) {
			ex = true;

		}
	}


}
