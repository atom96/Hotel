package hotel;

public class OkresCzasu {
	private Data dataPoczątkowa;
	private Data dataKońcowa;
	
	@Override
	public String toString() {
		return dataPoczątkowa + " - " + dataKońcowa;
	}
	
	public OkresCzasu(Data dataPoczątkowa, Data dataKońcowa) {
		this.dataPoczątkowa = dataPoczątkowa;
		this.dataKońcowa = dataKońcowa;
	}
	
	public boolean czyPo(OkresCzasu okres) {
		return this.dataPoczątkowa.czyPo(okres.dataKońcowa);
	}
	
	public boolean czyPrzed (OkresCzasu okres) {
		return this.dataKońcowa.czyPrzed(okres.dataPoczątkowa);
	}
	
	public boolean czySięPrzecina(OkresCzasu okres) {
		return !(czyPo(okres) || czyPrzed(okres));
	}
}
