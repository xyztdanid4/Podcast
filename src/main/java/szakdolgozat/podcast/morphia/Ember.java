package szakdolgozat.podcast.morphia;

import java.util.ArrayList;
import java.util.List;

public class Ember {
	private String nev;
	private int kor;
	private String varos;
	private String foglalkozas;
	private List<String> stringek;

	public Ember(String nev, int kor, String varos, String foglalkozas) {
		this.nev = nev;
		this.kor = kor;
		this.varos = varos;
		this.foglalkozas = foglalkozas;
		stringek = new ArrayList<String>();
		stringek.add(nev);
		stringek.add(varos);
		stringek.add(foglalkozas);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Ember [nev=" + nev + ", kor=" + kor + ", varos=" + varos
				+ ", foglalkozas=" + foglalkozas + ", stringek=" + stringek
				+ "]";
	}

	public Ember() {

	}

}
