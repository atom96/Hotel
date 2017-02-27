package hotel;

import java.util.Arrays;

public class Pokoj {
	private final static int domyślnyNumerPokoju = -1;
	private final static int rozmiarDomyślny = 5;
	private int numer;
	private CechyPokoju cechy; 
	private OkresCzasu[] okresyZajętości;
	private int przechowywaneOkresy;
	
	public Pokoj(int numer, int ilośćMiejsc, int cena, Styl stylWystroju, 
			Kolor kolorystyka, Kierunek kierunekOkien, Boolean dostępDoInternetu) {
		this.numer = numer;
		this.cechy = new CechyPokoju(ilośćMiejsc, cena, stylWystroju, kolorystyka, kierunekOkien, dostępDoInternetu);
		this.okresyZajętości = new OkresCzasu[rozmiarDomyślny];
		this.przechowywaneOkresy = 0;
	}

	public Pokoj(int ilośćMiejsc, int cena, Styl stylWystroju, 
			Kolor kolorystyka, Kierunek kierunekOkien, Boolean dostępDoInternetu) {
		this(domyślnyNumerPokoju, ilośćMiejsc, cena, stylWystroju, kolorystyka, kierunekOkien, dostępDoInternetu);
	}
	
	@Override
	public String toString() {
		return cechy.toString();
	}
	
	/*
	 * Rezerwuje pokój na podany okres czasu.
	 */
	public void zarezerwujPokój(OkresCzasu okres) {
		if(przechowywaneOkresy == okresyZajętości.length) {
			okresyZajętości = Arrays.copyOf(okresyZajętości, 2 * okresyZajętości.length);
		}
		okresyZajętości[przechowywaneOkresy++] = okres;
	}
	
	/*
	 * Odpowiada, czy pokój spełnia podane wymagania
	 */
	
	public boolean czySpełniaWymagania(CechyPokoju wymaganeCechy) {
		return cechy.czyTakiSamLubLepszy(wymaganeCechy);
	}
	
	/*
	 * 	 * Odpowiada, czy pokój spełnia podane wymagania w połowie
	 */
	
	public boolean czySpełniaWymaganiaWPołowie(CechyPokoju wymaganeCechy) {
		return cechy.czyChociażWPołowieTakDobryJak(wymaganeCechy);
	}
	
	/*
	 * Odpowiada, czy ma okna w tę samą stronę jak w podanych cechach.
	 */
	public boolean czyOknaWTąSamąStronę(CechyPokoju wymaganeCechy) {
		return cechy.czyOknaWTąSamąStronę(wymaganeCechy);
	}
	
	/*
	 * Odpowiada, czy jest droższy niż w podanych cechach.
	 */
	
	public boolean czyDroższy(CechyPokoju wymaganeCechy) {
		return cechy.czyDroższy(wymaganeCechy);
	}
	
	/*
	 * 	Odpowiada, czy jest droższy niż podany pokój.
	 */
	
	public boolean czyDroższy(Pokoj pokój) {
		return cechy.czyDroższy(pokój.cechy);
	}
	
	/*
	 * Odpowiada, czy jest wolny w podanym okresie
	 */
	
	public boolean czyWolnyWOkresie (OkresCzasu okres) {
		for (int i = 0; i < przechowywaneOkresy; ++i) {
			if (okresyZajętości[i].czySięPrzecina(okres)) {
				return false;
			}
		}
		return true;
	}
	
	/*
	 * Podaje stosunek ilości wymagań spełnionych przez pokój do wszystkich możliwych.
	 */
	
	public double naIleDopasowany(CechyPokoju wymaganeCechy) {
		return cechy.naIleDopasowany(wymaganeCechy);
	}
	
	/*
	 * Zwraca numer pokoju.
	 */
	
	public int getNumer() {
		return numer;
	}
	
}
