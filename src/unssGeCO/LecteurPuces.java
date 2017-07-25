package unssGeCO;

import net.gecosi.CommStatus;
import net.gecosi.SiHandler;
import net.gecosi.SiListener;
import net.gecosi.dataframe.SiDataFrame;

public class LecteurPuces {
	
	private SiHandler handler;
	
	protected SiDataFrame dataFrame;
	
	public LecteurPuces() {
		String pPort = "COM3";
		
		System.out.println("Connexion à la station de lecture");
		

		handler = new SiHandler(new SiListener() {
			public void handleEcard(SiDataFrame pDataFrame) {
				pDataFrame.printString();
				dataFrame = pDataFrame;
				afficheDonnees(dataFrame);
			}
			public void notify(CommStatus status) {
				System.out.println("Status -> " + status);
				switch(status.toString()) {
					case "READY":
						lecteurPret(pPort);
						break;
					case "STARTING":
					case "PROCESSING":
						lecteurLecture();
						break;
					case "OFF":
						lecteurArret();
						break;
					default:
						lecteurArret();
				}
				
			}
			public void notify(CommStatus errorStatus, String errorMessage) {
				System.out.println("Error -> " + errorStatus + " - " + errorMessage);
				//lblPuce.setText("Error -> " + errorStatus + " - " + errorMessage);
				arreteLecture();
			}
		});
		
		try {
			handler.connect(pPort);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

	/**
	 * Arrête la lecture
	 */
	public void arreteLecture() {
		handler.stop();
	}
	
	protected void lecteurPret(String pPort) {
		
	}
	
	protected void lecteurLecture() {
		
	}
	
	protected void lecteurArret() {
		
	}
	
	/**
	 * Affiche les informations lues de la puce
	 * @param dataFrame les données de la puce
	 */
	protected void afficheDonnees(SiDataFrame dataFrame) {
		System.out.println("Affichage des données");
		
	}

}
