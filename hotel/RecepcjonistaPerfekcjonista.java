package hotel;

public class RecepcjonistaPerfekcjonista extends Recepcjonista{
	public RecepcjonistaPerfekcjonista(String imię, String nazwisko) {
		super(imię, nazwisko);
	}
	
	@Override
	public String toString() {
		return super.toString() + ", perfekcjonistyczna.";
	}
	
	@Override
	public boolean dobierzPokójICzyPrzetwarzaćPonownie(Zamowienie zamówienie, Pokoj[] pokoje) {
		int ileDostępnychWOkresie = 0;
		int minNumer = 0;
		Pokoj wybranyPokoj = null;
		Ankieta ankieta = zamówienie.getAnkieta();
		Klient klient = zamówienie.getKlient();
		
	    /**
	     * Główny moment realizacji strategii
	     */
		
		for(int i = 0; i < pokoje.length; ++i) {
			if(pokoje[i].czyWolnyWOkresie(ankieta.getOkresRezerwacji())){
				++ileDostępnychWOkresie;
				if(ankieta.czySpełniaWymagania(pokoje[i])) {
					if (wybranyPokoj != null) {
						if (pokoje[i].getNumer() < minNumer) {
							wybranyPokoj = pokoje[i];
							minNumer = pokoje[i].getNumer();
						}
					} else {
						wybranyPokoj = pokoje[i];
						minNumer = pokoje[i].getNumer();
					}
				}
			}
		}
		
	    /**
	     * Jeżeli nie znalazł żadnego wolnego pokoju w podanym terminie, wypisuje dane
	     * zgodnie ze specyfikacją podaną na Moodle'u 
	     */
		
		if(ileDostępnychWOkresie == 0) {
			wypiszStanZamówienia(zamówienie, null, false);
			return false;
		}
		
	    /**
	     * Jeżeli nie znalazł żadnego pokoju, ale istniały pokoje wolne w podanym terminie,
	     * wypisuje dane zgodnie ze specyfikacją podaną na Moodle'u 
	     */
		
		if(wybranyPokoj == null) {
			wypiszStanZamówienia(zamówienie, null, false);
			return true;
		}
		
		/*
		 * Pyta klienta czy akcpetuje wybór oraz w zalezności od odpowiedzi,
		 * wypisuje stosowne dane na wyjście.
		 */
		
		if(klient.czyAkceptujePokój(wybranyPokoj, ankieta)) {
			wypiszStanZamówienia(zamówienie, wybranyPokoj, true);
			wybranyPokoj.zarezerwujPokój(ankieta.getOkresRezerwacji());
			return false;
		} else {
			wypiszStanZamówienia(zamówienie, wybranyPokoj, false);
			return true;
		}
	}
}
