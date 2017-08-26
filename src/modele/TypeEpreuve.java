package modele;

public enum TypeEpreuve {
	groupe ("groupé"),
	relais ("relais"),
	reseau ("réseau");
	
	private String name = "";
	
	TypeEpreuve(String name) {
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
	
	public static TypeEpreuve valueOfByEpreuve(String pEpreuve) throws IllegalArgumentException {
	    for (TypeEpreuve value : values()) {
	         String epreuve = value.toString();
	         if (epreuve.equals(pEpreuve)) {
	              return value;
	         }
	    }
	    throw new IllegalArgumentException("La limite " + pEpreuve + " n'est pas disponible.");
	}
	
	
	
	
}
