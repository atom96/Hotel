package hotel;
import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;

public class Hotel {
	private Pokoj[] pokoje;
	private Recepcjonista[] recepcjoniści;
	
	public Hotel(Pokoj [] pokoje, Recepcjonista [] recepcjonisci) {
		this.pokoje = Arrays.copyOf(pokoje, pokoje.length);
		this.recepcjoniści = Arrays.copyOf(recepcjonisci, recepcjonisci.length);
	}
	
	public static void main(String[] args) {
		Recepcjonista[] rec = {
				new RecepcjonistaAproksymacyjny("Adrianna", "Jacyków"),
				new RecepcjonistaAproksymacyjny("Diana", "Robótka"),
				new RecepcjonistaLosowy("Głowosław", "Wesołkowski"),
				new RecepcjonistaPerfekcjonista("Damian", "Jankowski"),
				new RecepcjonistaZłośliwy("Damian", "Radziłow"),
				new RecepcjonistaZłośliwy("Damian", "Klepka"),
				};
		
		Pokoj[] pokoje = {				
				new Pokoj(167, 7, 422, Styl.nowoczesny, Kolor.jasnozielony, Kierunek.południe, true),
				new Pokoj(33, 6, 98, Styl.morski, Kolor.morski, Kierunek.wschód, false),
				new Pokoj(599, 3, 237, Styl.rustykalny, Kolor.seledynowy, Kierunek.zachód, false),
				new Pokoj(3, 3, 483, Styl.nowoczesny, Kolor.purpurowy, Kierunek.wschód, true),
				new Pokoj(950, 6, 322, Styl.orientalny, Kolor.stalowy, Kierunek.południe, false),
				new Pokoj(268, 7, 56, Styl.morski, Kolor.stalowy, Kierunek.północ, false),
				new Pokoj(839, 7, 110, Styl.nowoczesny, Kolor.szary, Kierunek.wschód, true),
			};
		
		Hotel hotel = new Hotel(pokoje, rec);
		
		Klient[] klienci = {
				new KlientUgodowy("Adrianna", "Jacyków"),
				new KlientPerfekcjonista("Diana", "Jagiełło"),
				new KlientBudżetowy("Amanda", "Jankowski"),
				new KlientWidokowy("Adrianna", "Klepka"),
				new KlientWidokowy("Głowosław", "Jacyków"),
				new KlientBudżetowy("Radziwił", "Radziłow"),
				new KlientPerfekcjonista("Helena", "Radziłow"),
				new KlientUgodowy("Radziwił", "Król"),
				new KlientWidokowy("Damian", "Król"),
				new KlientWidokowy("Helena", "Robótka"),
				new KlientBudżetowy("Helena", "Wesołkowski"),
				new KlientUgodowy("Amanda", "Radziłow"),
				new KlientUgodowy("Damian", "Robótka"),
				new KlientBudżetowy("Adrianna", "Wesołkowski"),
				new KlientBudżetowy("Amanda", "Jankowski"),
		};
		
		Ankieta[] ankiety = {
				new Ankieta(new Data(10, 8, 2017), 2, 2, 404, Styl.rustykalny, Kolor.purpurowy, Kierunek.wschód, false),
				new Ankieta(new Data(4, 3, 2017), 5, 1, 186, Styl.nowoczesny, Kolor.purpurowy, Kierunek.południe, false),
				new Ankieta(new Data(10, 12, 2017), 13, 2, 214, Styl.orientalny, Kolor.morski, Kierunek.wschód, true),
				new Ankieta(new Data(19, 9, 2017), 3, 5, 253, Styl.nowoczesny, Kolor.stalowy, Kierunek.południe, true),
				new Ankieta(new Data(24, 6, 2017), 23, 1, 87, Styl.morski, Kolor.morski, Kierunek.południe, false),
				new Ankieta(new Data(5, 9, 2017), 4, 6, 354, Styl.orientalny, Kolor.seledynowy, Kierunek.wschód, false),
				new Ankieta(new Data(20, 9, 2017), 15, 1, 99, Styl.orientalny, Kolor.morski, Kierunek.zachód, false),
				new Ankieta(new Data(3, 6, 2017), 18, 3, 330, Styl.morski, Kolor.jasnozielony, Kierunek.południe, false),
				new Ankieta(new Data(23, 4, 2017), 19, 3, 196, Styl.nowoczesny, Kolor.purpurowy, Kierunek.południe, true),
				new Ankieta(new Data(13, 12, 2017), 21, 4, 140, Styl.secesyjny, Kolor.purpurowy, Kierunek.południe, false),
				new Ankieta(new Data(12, 4, 2017), 1, 6, 286, Styl.morski, Kolor.seledynowy, Kierunek.zachód, false),
				new Ankieta(new Data(26, 3, 2017), 17, 4, 352, Styl.orientalny, Kolor.szary, Kierunek.północ, false),
				new Ankieta(new Data(16, 8, 2017), 1, 9, 400, Styl.secesyjny, Kolor.seledynowy, Kierunek.południe, true),
				new Ankieta(new Data(11, 10, 2017), 1, 1, 442, Styl.orientalny, Kolor.stalowy, Kierunek.zachód, false),
				new Ankieta(new Data(11, 6, 2017), 24, 2, 307, Styl.secesyjny, Kolor.seledynowy, Kierunek.północ, false),
		};
		
		Zamowienie[] zamówienia= new Zamowienie[ankiety.length];
		
		for(int i = 0; i < zamówienia.length; ++i) {
			zamówienia[i] = hotel.przyjmijZamowienie(klienci[i], ankiety[i]);
		}
		
		hotel.akceptuj(zamówienia, pokoje, rec);
	}
	
	/*
	 * Zwraca nowy obiekt zamówienie, korzystając z jego konstruktora.
	 */
	
	public Zamowienie przyjmijZamowienie(Klient klient, Ankieta ankieta) {
		return new Zamowienie(ankieta, klient);
	}
	
	/*
	 * Sprawdza, czy podana tablica pokoi jest zgodna z tą przechowywaną przez Hotel
	 */
	
	private boolean czyJestZgodnaZPosiadanąTabPokoi(Pokoj[] pokoje) {
		for (int i = 0; i < pokoje.length; ++i) {
			boolean znalezione = false;
			
			for (int j = 0; j < this.pokoje.length; ++j) {
				if (pokoje[i] == this.pokoje[j]) {
					znalezione = true;
					break;
				}
			}
			
			if(!znalezione) {
				return false;
			}
		}
		return true;
	}
	
	/*
	 * Sprawdza, czy podana tablica recepcjonistów jest zgodna z tą przechowywaną przez Hotel
	 */
	
	private boolean czyJestZgodnaZPosiadanąTabRecepcjonistów(Recepcjonista[] recepcjoniści) {
		for (int i = 0; i < recepcjoniści.length; ++i) {
			boolean znalezione = false;
			
			for (int j = 0; j < this.recepcjoniści.length; ++j) {
				if (this.recepcjoniści[j]==recepcjoniści[i]) {
					znalezione = true;
					break;
				}
			}
			
			if(!znalezione) {
				return false;
			}
		}
		return true;
	}
	
	/*
	 * Przyjmuje zamówienia z tablicy @zamowienie komunikując się z recepcjonistami 
	 * z tablicy @recepcjonista
	 */
	
	public void akceptuj(Zamowienie[] zamowienie, Pokoj[] pokoj, Recepcjonista[] recepcjonista ) {
		Queue<Zamowienie> zamowienia = new LinkedList<Zamowienie>();
		int aktRecepcjonista= 0;
		Zamowienie aktZamowienie;
		
		if(czyJestZgodnaZPosiadanąTabRecepcjonistów(recepcjonista) && czyJestZgodnaZPosiadanąTabPokoi(pokoj)) {
			for(int i = 0; i < zamowienie.length; ++i) {
				zamowienia.add(zamowienie[i]);
			}
			
			while(!zamowienia.isEmpty()) {
				aktZamowienie = zamowienia.poll();
				
				if(aktZamowienie.czyMożnaPrzetwarzać()) {
					aktZamowienie.zaczętoNowePrzetwarzanie();
					if (recepcjonista[aktRecepcjonista++].dobierzPokójICzyPrzetwarzaćPonownie(aktZamowienie, pokoj)) {
						zamowienia.add(aktZamowienie);
					}
					
					aktRecepcjonista %= recepcjonista.length;
				}	
			}
		} else {
			System.out.println("Podane tablice nie zawierają danych zgodnych z posiadanymi");
		}
	}
}
