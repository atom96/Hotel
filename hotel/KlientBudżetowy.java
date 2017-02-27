package hotel;

public class KlientBudżetowy extends Klient {

	public KlientBudżetowy(String imię, String nazwisko) {
		super(imię, nazwisko);
	}
	
	@Override
	public String toString() {
		return super.toString() + ", budżetowa.";
	}
	
	@Override
	public boolean czyAkceptujePokój(Pokoj pokoj, Ankieta ankieta) {
		return !ankieta.czyDroższyOdWymaganego(pokoj);
	}
}
