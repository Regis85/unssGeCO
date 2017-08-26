package modele;

public enum TypeDepart {
	Groupé ("Groupé"),
	Boitier ("Au boitier");
	
	private String name = "";
	
	TypeDepart(String name) {
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
	
	public static TypeDepart valueOfByDepart(String pDepart) throws IllegalArgumentException {
	    for (TypeDepart value : values()) {
	         String depart = value.intitule();
	         if (depart.equals(pDepart)) {
	              return value;
	         }
	    }
	    throw new IllegalArgumentException("La limite " + pDepart + " n'est pas disponible.");
	}
	
	
}
