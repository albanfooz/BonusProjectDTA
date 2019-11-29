package fr.afz;

import java.util.*;
/**
 * SlotMachine gere les roues (roll & stop)
 * @author utilisateur2
 *
 */
public class SlotMachine {
	private int nbRoue;
	private int nbStop = 0;
	List<Roue> rList = new ArrayList<Roue>();
	List<Integer> iList = new ArrayList<Integer>();
	List<Integer> iSleepList = new ArrayList<Integer>();



	public SlotMachine() {
		this.nbRoue = 3;
	}

	public SlotMachine(int nbRoue) throws IllegalArgumentException {
		if(nbRoue<3) {
			throw new IllegalArgumentException("At least 3 Roues");
		}else {
			this.nbRoue = nbRoue;
		}
	}
	//starts the required number of Roue
	public void roll() {
		for (int i = 0; i < nbRoue; i++) {
			Roue r1 = new Roue();
			rList.add(r1);
		}
		try {
			Thread.sleep(21);			//adds RNG in case of QuickGame
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void stopOneRoue() throws IndexOutOfBoundsException  {
		if(nbStop<rList.size()) {
			rList.get(nbStop).stopRunning();
			iList.add(rList.get(nbStop).getCount());
			nbStop++;
		}else {
			throw new IndexOutOfBoundsException("Can't Stop more than Roue than possible");
		}
	}

	public boolean isEveryRoueStopped() {
		if(nbStop<rList.size()) {
			return false;
		}else {
			return true;
		}
	}

	public void stopAll() {
		//try {
		for (Roue roue : rList) {
			roue.stopRunning();
			iList.add(roue.getCount());
			iSleepList.add(roue.getSleepTime());
			//Thread.sleep(5);
		}
		nbStop = rList.size();
		//		} catch (InterruptedException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}

	}
	public void displayResult(){
		System.out.println(iList.toString());
	}
	public void displayResultDev() {
		System.out.println("count     "+iList.toString());
		System.out.println("sleepTime "+ iSleepList.toString());
	}

	public List<Integer> getiList() {
		return iList;
	}

}
