package hotel;

public class RecepcjonistaZłośliwy extends Recepcjonista{
	public RecepcjonistaZłośliwy(String imię, String nazwisko) {
		super(imię, nazwisko);
	}
	
	@Override
	public String toString() {
		return super.toString() + ", złośliwa.";
	}
	
	@Override
	public boolean dobierzPokójICzyPrzetwarzaćPonownie(Zamowienie zamówienie, Pokoj[] pokoje) {
		int maxNumer = 0;
		Pokoj wybranyPokoj = null;
		Ankieta ankieta = zamówienie.getAnkieta();
		Klient klient = zamówienie.getKlient();
		double minDopasowanie = 0.0;
		boolean czyIstniejeWolnyPokój = false;
		
		/*
		 * Szuka pierwszego wolnego pokoju - jest on kandydatem na @wybranyPokoj
		 */
		
		for(int i = 0; i < pokoje.length; ++i) {
			if(pokoje[i].czyWolnyWOkresie(ankieta.getOkresRezerwacji())) {
				maxNumer = pokoje[i].getNumer();
				wybranyPokoj = pokoje[i];
				minDopasowanie = ankieta.naIleSpełniaWymagania(pokoje[i]);
				czyIstniejeWolnyPokój = true;
				break;
			}
		}
		
	    /**
	     * Jeżeli nie znalazł żadnego pokoju w podanym terminie, wypisuje dane
	     * zgodnie ze specyfikacją podaną na Moodle'u 
	     */
		
		if(!czyIstniejeWolnyPokój) {
			wypiszStanZamówienia(zamówienie, null, false);
			return false;
		}
		
		
	    /**
	     * Główny moment realizacji strategii
	     */
		
		for(int i = 1; i < pokoje.length; ++i) {
			if(pokoje[i].czyWolnyWOkresie(ankieta.getOkresRezerwacji())){
				if (ankieta.naIleSpełniaWymagania(pokoje[i]) < minDopasowanie) {
					wybranyPokoj = pokoje[i];
					maxNumer = pokoje[i].getNumer();
					minDopasowanie = ankieta.naIleSpełniaWymagania(pokoje[i]);
				} else if (ankieta.naIleSpełniaWymagania(pokoje[i]) == minDopasowanie) {
					if (pokoje[i].czyDroższy(wybranyPokoj)) {
						wybranyPokoj = pokoje[i];
						maxNumer = pokoje[i].getNumer();
						minDopasowanie = ankieta.naIleSpełniaWymagania(pokoje[i]);
					} else if (!wybranyPokoj.czyDroższy(pokoje[i]) && pokoje[i].getNumer() > maxNumer) {
						wybranyPokoj = pokoje[i];
						maxNumer = pokoje[i].getNumer();
						minDopasowanie = ankieta.naIleSpełniaWymagania(pokoje[i]);
					}
				}
			}
		}
		
		/*
		 * Pyta klienta czy akcpetuje wybór oraz w zalezności od odpowiedzi,
		 * wypisuje stosowne dane na wyjście.
		 */
		
		if(klient.czyAkceptujePokój(wybranyPokoj, ankieta)){
			wypiszStanZamówienia(zamówienie, wybranyPokoj, true);
			wybranyPokoj.zarezerwujPokój(ankieta.getOkresRezerwacji());
			return false;
		} else {
			wypiszStanZamówienia(zamówienie, wybranyPokoj, false);
			return true;
		}
	}
}
