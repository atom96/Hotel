package hotel;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Data {
	private final static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	private Calendar calendar = Calendar.getInstance();
	
	public Data (int dzień, int miesiąc, int rok) {
		calendar.set(Calendar.DAY_OF_MONTH, dzień);
		calendar.set(Calendar.MONTH, miesiąc - 1);
		calendar.set(Calendar.YEAR, rok);
	}
	
	@Override
	public String toString() {
		return sdf.format(calendar.getTime());
	}
	/*
	 * Zwraca datę przesuniętą o @ile dni
	 */
	public Data dodajDni(int ile) {
		return new Data(
				calendar.get(Calendar.DAY_OF_MONTH) + ile, 
				calendar.get(Calendar.MONTH) + 1, 
				calendar.get(Calendar.YEAR)
				);
	}
	
	/*
	 * Odpowiada, czy jest po dacie @data
	 */
	
	public boolean czyPo(Data data) {
		return calendar.compareTo(data.calendar) > 0;
	}
	
	/*
	 * Odpowiada, czy jest przed datą @data
	 */
	
	public boolean czyPrzed(Data data) {
		return calendar.compareTo(data.calendar) < 0;
	}
	
	/*
	 * Odpowiada, czy jest tą samą datą co @data
	 */
	
	public boolean czyTaSama(Data data) {
		return calendar.compareTo(data.calendar) == 0;
	}
}
