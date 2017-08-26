package modele;

public enum Categorie {
	College ("Collège"),
	Lycee ("Lycée");
	
	private String name = "";
	
	Categorie(String name) {
		this.name = name;
	}
	
	public String intitule() {
		return name;
	}
	
	/*
	public String toString() {
		return name;
	}
	*/
	
	public static Categorie valueOfByCategorie(String pCategorie) throws IllegalArgumentException {
	    for (Categorie value : values()) {
	         String categorie = value.intitule();
	         if (categorie.equals(pCategorie)) {
	              return value;
	         }
	    }
	    throw new IllegalArgumentException("La limite " + pCategorie + " n'est pas disponible.");
	}
	
	public static boolean estConnu(String pCategorie) {
	    for (Categorie value : values()) {
	         String categorie = value.intitule();
	         if (categorie.equals(pCategorie)) {
	        	 System.out.println(pCategorie + " - " + categorie);
	        	 return true;
	         }
	    }
		return false;
	}
	
}
