package hotel;

public abstract class Klient extends Osoba{
	public Klient(String imię, String nazwisko) {
		super(imię, nazwisko);
	}
	
	/*
	 * Odpowiada, czy akceptuje pokój. Ponieważ nie pamięta ankiety,
	 * jest mu ona "przypominana"
	 */
	
	public abstract boolean czyAkceptujePokój(Pokoj pokój, Ankieta ankieta);
}
