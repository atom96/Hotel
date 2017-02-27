package hotel;

public abstract class Recepcjonista extends Osoba{
	
	public Recepcjonista(String imię, String nazwisko) {
		super(imię, nazwisko);
	}
	
	/*
	 * Dobiera pokój do wymagań klienta. Odpowiada, czy zamówienie
	 * ma być ponownie przetwarzane.
	 */
	
	public abstract boolean dobierzPokójICzyPrzetwarzaćPonownie(Zamowienie zamówienie, Pokoj[] pokoje);
	
	protected void wypiszStanZamówienia (Zamowienie zamówienie, Pokoj pokój, boolean czyKlientAkceptuje) {
		System.out.println("Recepcjonista: " + this.toString());
		System.out.println("Zamówienie: " + zamówienie);
		
		if (pokój != null) {
			System.out.println("Proponowany pokój: " + pokój);
		} else {
			System.out.println("Proponowany pokój: brak");
		}
		
		System.out.println("Klient: " + zamówienie.getKlient());
		
		if(czyKlientAkceptuje) {
			System.out.println("tak");
		} else {
			System.out.println("nie");
		}
		
		System.out.println();
	}
}
