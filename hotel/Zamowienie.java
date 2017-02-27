package hotel;

public class Zamowienie {
	private static final int MAX_ILOŚĆ_PRZETWARZAŃ = 3;
	private Ankieta ankieta;
	private Klient klientZamawiający;
	private int ileRazyPrzetwarzane;
	
	public Zamowienie(Ankieta ankieta, Klient klientZamawiający) {
		this.ankieta = ankieta;
		this.klientZamawiający = klientZamawiający;
		this.ileRazyPrzetwarzane = 0;
	}
	@Override
	public String toString() {
		return ankieta.toString();
	}
	
	/**
	 * Odpowiada, czy może być dalej przetwarzane.
	 */
	
	public boolean czyMożnaPrzetwarzać() {
		return ileRazyPrzetwarzane < MAX_ILOŚĆ_PRZETWARZAŃ;
	}
	
	/*
	 * Reaguje na to, że za chwilę będzie przetwarzany
	 */
	
	public void zaczętoNowePrzetwarzanie() {
		++ileRazyPrzetwarzane;
	}

	/*
	 * Zwraca przechowywaną ankiete
	 */
	
	public Ankieta getAnkieta() {
		return ankieta;
	}
	
	/*
	 * Zwraca przechowywanego klienta
	 */
	
	public Klient getKlient() {
		return klientZamawiający;
	}
	
}
