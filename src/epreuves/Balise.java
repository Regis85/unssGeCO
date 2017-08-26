package epreuves;

import java.time.LocalTime;

public class Balise {
	
	protected int code;
	protected boolean trouveBonif;
	protected int trouvePoints;
	protected LocalTime trouveHeure = LocalTime.of(0, 0);
	protected boolean pmBonif;
	protected int pmPoints;
	protected LocalTime pmHeure = LocalTime.of(0, 0);
	

	public Balise() {
		// TODO Auto-generated constructor stub
	}
	
	public Balise(int pCode) {
		code = pCode;
	}
	
	public void setCode(int pCode) {
		code = pCode;
	}
	
	public int getCode() {
		return code;
	}
	
	public void setTrouveBonif(boolean pTrouveBonif) {
		trouveBonif = pTrouveBonif;
	}
	public boolean getTrouveBonif() {
		return trouveBonif;
	}
	
	public void setTrouvePoints(int pTrouvePoints) {
		trouvePoints = pTrouvePoints;
	}
	public int getTrouvePoints() {
		return trouvePoints;
	}
	
	public void setTrouveHeure(LocalTime pTrouveHeure) {
		trouveHeure = pTrouveHeure;
	}
	public LocalTime getTrouveHeure() {
		return trouveHeure;
	}
	
	public void setPmBonif(boolean pPmBonif) {
		pmBonif = pPmBonif;
	}
	public boolean getPmBonif() {
		return pmBonif;
	}
	
	public void setPmPoints(int pPmPoints) {
		pmPoints = pPmPoints;
	}
	public int getPmPoints() {
		return pmPoints;
	}
	
	public void setPmHeure(LocalTime pPmHeure) {
		pmHeure =  pPmHeure;
	}
	public LocalTime getPmHeure() {
		return pmHeure;
	}

}
