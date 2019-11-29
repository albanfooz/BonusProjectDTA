package fr.afz;
/**
 *
 * @author utilisateur2
 *
 *	CasinoException, only used when Joueur doesn't have enough money
 */
public class CasinoException extends Exception {

	/**
	 * default
	 */
	private static final long serialVersionUID = 1L;

	public CasinoException() {
		// TODO Auto-generated constructor stub
	}

	public CasinoException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public CasinoException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public CasinoException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public CasinoException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

}
