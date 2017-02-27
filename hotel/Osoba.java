package hotel;

public abstract class Osoba {
	private String imię;
	private String nazwisko;
	
	public Osoba(String imię, String nazwisko) {
		this.imię = imię;
		this.nazwisko = nazwisko;
	}
	
	@Override
	public String toString() {
		return imię + " " + nazwisko;
	}
	
	public boolean czyTakieSameDane(Osoba osoba) {
		if(!this.imię.equals(osoba.imię)) {
			return false;
		}
		if(!this.nazwisko.equals(osoba.nazwisko)) {
			return false;
		}
		return true;
	}
}
