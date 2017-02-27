package hotel;

import java.util.Random;

public class RecepcjonistaLosowy extends Recepcjonista{
	public RecepcjonistaLosowy(String imię, String nazwisko) {
		super(imię, nazwisko);
	}
	
	@Override
	public String toString() {
		return super.toString() + ", losowa.";
	}
	
	@Override
	public boolean dobierzPokójICzyPrzetwarzaćPonownie(Zamowienie zamówienie, Pokoj[] pokoje) {
	    Random randomGenerator = new Random();
	    Pokoj[] wolnePokoje = new Pokoj[pokoje.length];
	    Ankieta ankieta = zamówienie.getAnkieta();
	    Klient klient = zamówienie.getKlient();
	    int j = 0;
	    int wybranyPokój;
	    
	    /*
	     * Sprawdza wolne pokoje w ustalonym przez klienta terminie
	     * i zapisuje je do tablicy
	     */
	    
	    for(int i = 0; i < pokoje.length; ++i) {
	    	if(pokoje[i].czyWolnyWOkresie(ankieta.getOkresRezerwacji())){
	    		wolnePokoje[j++] = pokoje[i] ;
	    	}
	    }
	    /**
	     * Jeżeli nie znalazł żadnego pokoju w podanym terminie, wypisuje dane
	     * zgodnie ze specyfikacją podaną na Moodle'u 
	     */
	    
	    if(j == 0) {
	    	wypiszStanZamówienia(zamówienie, null, false);
	    	return false;
	    }
	    
	    /**
	     * Główny moment realizacji strategii
	     */
	    
		wybranyPokój = randomGenerator.nextInt(j);
		
		/*
		 * Pyta klienta czy akcpetuje wybór oraz w zalezności od odpowiedzi,
		 * wypisuje stosowne dane na wyjście.
		 */
		
		if(klient.czyAkceptujePokój(pokoje[wybranyPokój], ankieta)) {
			wypiszStanZamówienia(zamówienie, pokoje[wybranyPokój], true);
			pokoje[wybranyPokój].zarezerwujPokój(ankieta.getOkresRezerwacji());
			return false;
		} else {
			wypiszStanZamówienia(zamówienie, pokoje[wybranyPokój], false);
			return true;
		}
	}
}