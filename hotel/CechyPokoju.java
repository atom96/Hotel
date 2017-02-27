package hotel;

public class CechyPokoju {
	private final static int PORÓWNYWANE_ATRYBUTY = 6;

	private int ilośćMiejsc;
	private int cena;
	private Styl stylWystroju;
	private Kolor kolorystyka;
	private Kierunek kierunekOkien;
	private Boolean dostępDoInternetu;
	
	public CechyPokoju(int ilośćMiejsc, int cena, Styl stylWystroju, 
			Kolor kolorystyka, Kierunek kierunekOkien, Boolean dostępDoInternetu) {
		this.ilośćMiejsc = ilośćMiejsc;
		this.cena = cena;
		this.stylWystroju = stylWystroju;
		this.kolorystyka = kolorystyka;
		this.kierunekOkien = kierunekOkien;
		this.dostępDoInternetu = dostępDoInternetu;
	}
	
	@Override
	public String toString() {
		String dostępDoInternetu;
		if(this.dostępDoInternetu) {
			dostępDoInternetu = "tak";
		} else {
			dostępDoInternetu = "nie";
		}
		return "Ilość miejsc: " + ilośćMiejsc + ", " + "Cena: " + cena + ", " + "Styl wystroju: " + stylWystroju + ", " + "Kolorystyka: " + kolorystyka + ", " + "Kierunek okien: " + kierunekOkien + ", " +  "Dostęp do internetu: " + dostępDoInternetu;
	}
	
	/*
	 * Odpowiada, czy jest jest taki sam lub lepszy od oczekiwanych cech pokoju
	 * gdzie lepszy oznacza niższą cenę lub więcej miejsc oraz równość pozostałych
	 * parametrów. 
	 */
	
	public boolean czyTakiSamLubLepszy(CechyPokoju oczekiwanyPokój) {		
		if (this.ilośćMiejsc < oczekiwanyPokój.ilośćMiejsc) {
			return false;
		}
		if (this.cena > oczekiwanyPokój.cena) {
			return false;
		}
		if (this.stylWystroju != oczekiwanyPokój.stylWystroju) {
			return false;
		}
		if(this.kolorystyka != oczekiwanyPokój.kolorystyka) {
			return false;
		}
		if (this.kierunekOkien != oczekiwanyPokój.kierunekOkien) {
			return false;
		}
		if (this.dostępDoInternetu != oczekiwanyPokój.dostępDoInternetu) {
			return false;
		}
		return true;	
	}
	
	/*
	 * Odpowiada, czy jest chociaż w połowie tak dobry jak oczekiwane cechy.
	 */
	
	public boolean czyChociażWPołowieTakDobryJak(CechyPokoju oczekiwanyPokój) {
		int w = 0;
		
		if (this.ilośćMiejsc >= oczekiwanyPokój.ilośćMiejsc) {
			++w;
		}
		if (this.cena <= oczekiwanyPokój.cena) {
			++w;
		}
		if (this.stylWystroju == oczekiwanyPokój.stylWystroju) {
			++w;
		}
		if(this.kolorystyka == oczekiwanyPokój.kolorystyka) {
			++w;
		}
		if (this.kierunekOkien == oczekiwanyPokój.kierunekOkien) {
			++w;
		}
		if (this.dostępDoInternetu == oczekiwanyPokój.dostępDoInternetu) {
			++w;
		}
		return w >= (PORÓWNYWANE_ATRYBUTY + 1) / 2;
	}
	
	/*
	 * Podaje stosunek ilości wymagań spełnionych przez pokój do wszystkich możliwych.
	 */
	
	public double naIleDopasowany(CechyPokoju oczekiwanyPokój) {
		int w = 0;
		
		if (this.ilośćMiejsc >= oczekiwanyPokój.ilośćMiejsc) {
			++w;
		}
		if (this.cena <= oczekiwanyPokój.cena) {
			++w;
		}
		if (this.stylWystroju == oczekiwanyPokój.stylWystroju) {
			++w;
		}
		if(this.kolorystyka == oczekiwanyPokój.kolorystyka) {
			++w;
		}
		if (this.kierunekOkien == oczekiwanyPokój.kierunekOkien) {
			++w;
		}
		if (this.dostępDoInternetu == oczekiwanyPokój.dostępDoInternetu) {
			++w;
		}
		return (double) (w) / (double) (PORÓWNYWANE_ATRYBUTY);
	}
	
	/*
	 * Odpowiada, czy ma okna w tę samą stronę jak w podanych cechach.
	 */
	public boolean czyOknaWTąSamąStronę(CechyPokoju cechy) {
		return this.kierunekOkien == cechy.kierunekOkien;
	}
	
	/*
	 * Odpowiada, czy jest droższy niż w podanych cechach.
	 */
	
	public boolean czyDroższy(CechyPokoju cechy) {
		return this.cena > cechy.cena;
	}
}
