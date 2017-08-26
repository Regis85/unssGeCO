package temps;

public class BaliseTrouvee {
	
	private int code;
	private long temps; //les temps sont en millisecondes

	public BaliseTrouvee(int pCode, long pTemps) {
		code = pCode;
		temps = pTemps;
	}
	
	public int getCode() {
		return code;
	}
	
	public long getTemps() {
		return temps;
	}

}
