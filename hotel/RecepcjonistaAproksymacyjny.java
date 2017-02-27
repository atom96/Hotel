package hotel;

public class RecepcjonistaAproksymacyjny extends Recepcjonista{
	public RecepcjonistaAproksymacyjny(String imię, String nazwisko) {
		super(imię, nazwisko);
	}
	
	@Override
	public String toString() {
		return super.toString() + ", aproksymacyjna.";
	}
	
	@Override
	public boolean dobierzPokójICzyPrzetwarzaćPonownie(Zamowienie zamówienie, Pokoj[] pokoje) {
		int minNumer = 0;
		Pokoj wybranyPokoj = null;
		Ankieta ankieta = zamówienie.getAnkieta();
		Klient klient = zamówienie.getKlient();
		double maxDopasowanie = 0.0;
		boolean czyIstniejeWolnyPokój = false;
		
		/*
		 * Szuka pierwszego wolnego pokoju - jest on kandydatem na @wybranyPokoj
		 */
		
		for(int i = 0; i < pokoje.length; ++i) {
			if(pokoje[i].czyWolnyWOkresie(ankieta.getOkresRezerwacji())) {
				minNumer = pokoje[i].getNumer();
				wybranyPokoj = pokoje[i];
				maxDopasowanie = ankieta.naIleSpełniaWymagania(pokoje[i]);
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
		
		for(int i = 0; i < pokoje.length; ++i) {
			if(pokoje[i].czyWolnyWOkresie(ankieta.getOkresRezerwacji())){
				if (ankieta.naIleSpełniaWymagania(pokoje[i]) > maxDopasowanie) {
					wybranyPokoj = pokoje[i];
					minNumer = pokoje[i].getNumer();
					maxDopasowanie = ankieta.naIleSpełniaWymagania(pokoje[i]);
				} else if (ankieta.naIleSpełniaWymagania(pokoje[i]) == maxDopasowanie) {
					if (pokoje[i].czyDroższy(wybranyPokoj)) {
						wybranyPokoj = pokoje[i];
						minNumer = pokoje[i].getNumer();
						maxDopasowanie = ankieta.naIleSpełniaWymagania(pokoje[i]);
					} else if (!wybranyPokoj.czyDroższy(pokoje[i]) && pokoje[i].getNumer() < minNumer) {
						wybranyPokoj = pokoje[i];
						minNumer = pokoje[i].getNumer();
						maxDopasowanie = ankieta.naIleSpełniaWymagania(pokoje[i]);
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
