package fr.afz;

import java.util.concurrent.ConcurrentHashMap;
/**
 * Overrides toString(), in order to format the output,
 *
 * This : {1, 2, 3, 4...} instead of {0=1, 1=2, 2=3, 4=5...}
 * @author utilisateur2
 *
 * @param <K>
 * @param <V>
 */
public class ConcurrentMapCasino<K, V> extends ConcurrentHashMap<K, V> {
	/**
	 * default
	 */
	private static final long serialVersionUID = 1L;
	String regex ="(([0-9]+)=)";
	@Override
	public String toString() {
		String s = super.toString();
		s =s.replaceAll(regex, "");
		return s;
	}

}
