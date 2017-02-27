package hotel;

public class KlientPerfekcjonista extends Klient {
	public KlientPerfekcjonista(String imię, String nazwisko) {
		super(imię, nazwisko);
	}
	
	@Override
	public String toString() {
		return super.toString() + ", perfekcjonistyczna.";
	}
	
	@Override
	public boolean czyAkceptujePokój(Pokoj pokój, Ankieta ankieta) {
		return ankieta.czySpełniaWymagania(pokój);
	}
}
