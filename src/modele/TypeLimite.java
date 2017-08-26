package modele;

public enum TypeLimite {
	sans ("Sans limite"),
	temps ("Avec limite de temps"),
	heure ("Avec limite horaire");
	
	private String name = "";
	
	TypeLimite(String name) {
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
	
	public static TypeLimite valueOfByLimite(String pLimite) throws IllegalArgumentException {
	    for (TypeLimite value : values()) {
	         String limite = value.intitule();
	         if (limite.equals(pLimite)) {
	              return value;
	         }
	    }
	    throw new IllegalArgumentException("La limite " + pLimite + " n'est pas disponible.");
	}
	
}
