package hotel;

public class KlientWidokowy extends Klient {
	public KlientWidokowy(String imię, String nazwisko) {
		super(imię, nazwisko);
	}
	@Override
	public String toString() {
		return super.toString() + ", widokowa.";
	}
	@Override
	public boolean czyAkceptujePokój(Pokoj pokój, Ankieta ankieta) {
		return ankieta.czyMaOknaWWymaganąStronę(pokój);
	}
}
