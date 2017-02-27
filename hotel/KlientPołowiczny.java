package hotel;

public class KlientPołowiczny extends Klient {
	public KlientPołowiczny(String imię, String nazwisko) {
		super(imię, nazwisko);
	}
	
	@Override
	public String toString() {
		return super.toString() + ", połowiczna.";
	}
	
	@Override
	public boolean czyAkceptujePokój(Pokoj pokój, Ankieta ankieta) {
		return ankieta.czySpełniaWymaganiaWPołowie(pokój);
	}
}
