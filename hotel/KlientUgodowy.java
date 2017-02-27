package hotel;

public class KlientUgodowy extends Klient{
	public KlientUgodowy(String imię, String nazwisko) {
		super(imię, nazwisko);
	}
	
	@Override
	public String toString() {
		return super.toString() + ", ugodowa.";
	}
	
	@Override
	public boolean czyAkceptujePokój(Pokoj pokoj, Ankieta ankieta) {
		return true;
	}

}
