package epreuves;

import java.util.Vector;

public class Parcours {
	
	protected String nom;
	
	protected Vector<Balise> balises = new Vector<Balise>();
/*
	public Parcours() {
		// TODO Auto-generated constructor stub
	}
	*/
	
	public Parcours(String pNom) {
		nom = pNom;
	}
	
	public void setNom(String pNom) {
		nom = pNom;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void addBalise(Balise pBalise) {
		balises.add(pBalise);
	}
	
	public Vector<Balise> getBalises() {
		return balises;
	}

}
