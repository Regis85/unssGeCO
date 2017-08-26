package temps;

import java.util.Vector;

import net.gecosi.dataframe.SiDataFrame;

public class TempsCourse {
	
	private String puce;
	private int jour;
	private long depart;
	private long arrive;
	//private long efface;
	private Vector<BaliseTrouvee> balisesTrouvees = new Vector<BaliseTrouvee>();

	public TempsCourse(SiDataFrame tempsCourseLues) {
		puce = tempsCourseLues.getSiNumber();
		depart = tempsCourseLues.getStartTime();
		arrive = tempsCourseLues.getFinishTime();
		//efface = tempsCourseLues.getCheckTime();
		for(int i=0;i<tempsCourseLues.getPunches().length;i++) {
			System.out.print(tempsCourseLues.getPunches()[i].code() + " - ");
			System.out.println(tempsCourseLues.getPunches()[i].timestamp());
			balisesTrouvees.add(new BaliseTrouvee(tempsCourseLues.getPunches()[i].code(), tempsCourseLues.getPunches()[i].timestamp()));
		}
	}
	
	public String getPuce() {
		return puce;
	}
	
	public void setJour(int pJour) {
		jour = pJour;
	}
	
	public int getJour() {
		return jour;
	}
	
	public long getDepart() {
		return depart;
	}
	
	public long getArrive() {
		return arrive;		
	}
	
	public Vector<BaliseTrouvee> getBalisesTrouvees() {
		return balisesTrouvees;
	}
	
	
	public String toString() {
		String retour;
		retour = puce + " départ : " + depart + " - arrivée : " + arrive + " - " + balisesTrouvees.size() +" balises trouvées";
		return retour;
	}
}
