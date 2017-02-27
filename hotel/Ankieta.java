package hotel;

public class Ankieta {	
	private Data dataPrzyjazdu;
	private int długośćPobytu;
	private CechyPokoju wymaganeCechy;
	
	public Ankieta (Data dataPrzyjazdu, int długośćPobytu, int ilośćMiejsc, int cena, Styl stylWystroju, 
			Kolor kolorystyka, Kierunek kierunekOkien, Boolean dostępDoInternetu) {
		this.dataPrzyjazdu = dataPrzyjazdu;
		this.długośćPobytu = długośćPobytu;
		this.wymaganeCechy = new CechyPokoju(ilośćMiejsc, cena, stylWystroju, kolorystyka, kierunekOkien, dostępDoInternetu);
	}
	
	@Override
	public String toString() {
		return "Data przyjazdu: " + dataPrzyjazdu + ", " + "Długość pobytu: " + długośćPobytu + " dni, " + wymaganeCechy;
	}
	
	/*
	 * Zwraca okres czasu, na jaki klient chce zarezerwować pokój.
	 */
	
	public OkresCzasu getOkresRezerwacji() {
		return new OkresCzasu(dataPrzyjazdu, dataPrzyjazdu.dodajDni(długośćPobytu - 1));
	}
	
	/*
	 * Odpowiada, czy podany pokój spełnia wszystkie wymagania.
	 */
	
	public boolean czySpełniaWymagania(Pokoj pokój) {
		return pokój.czySpełniaWymagania(wymaganeCechy);
	}
	
	/*
	 * Odpowiada, czy podany pokój spełnia przynjamniej połowę wymagań.
	 */
	
	public boolean czySpełniaWymaganiaWPołowie(Pokoj pokój) {
		return pokój.czySpełniaWymaganiaWPołowie(wymaganeCechy);
	}
	
	/*
	 * Podaje stosunek ilości wymagań spełnionych przez pokój do wszystkich możliwych.
	 */
	
	public double naIleSpełniaWymagania(Pokoj pokój) {
		return pokój.naIleDopasowany(wymaganeCechy);
	}
	
	/*
	 * Odpowiada, czy podany pokój ma okna skierowane w wymaganą strone.
	 */
	
	public boolean czyMaOknaWWymaganąStronę(Pokoj pokój) {
		return pokój.czyOknaWTąSamąStronę(wymaganeCechy);
	}
	
	/*
	 * Odpowiada, czy podany pokój jest droższy niż jest to wymagane
	 */
	
	public boolean czyDroższyOdWymaganego(Pokoj pokój) {
		return pokój.czyDroższy(wymaganeCechy);
	}
}
