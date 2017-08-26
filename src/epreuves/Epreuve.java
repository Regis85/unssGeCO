package epreuves;

import java.time.LocalTime;
import java.util.Vector;

import modele.Categorie;
import modele.TypeDepart;
import modele.TypeEpreuve;
import modele.TypeLimite;

public class Epreuve {
	
	protected boolean active;
	protected boolean departage;
	protected boolean enLigne;
	
	protected Categorie categorie;
	
	protected int coef;
	protected int id;
	protected int jour;
	protected int numOrdre;
	protected int pointsDureeMaxi;
	protected int pointsHeureMaxi;
	
	protected LocalTime dureeMaxi;
	protected LocalTime heureDepart;
	protected LocalTime heureDepart2;
	protected LocalTime heureMaxi;
	protected LocalTime parDureeMaxi;
	protected LocalTime parHeureMaxi;
	protected LocalTime penaliteDureeMaxi;
	protected LocalTime penaliteHeureMaxi;
	
	protected String nom;
	
	protected TypeDepart typeDepart;
	protected TypeLimite typeLimite;
	protected TypeEpreuve type;
	
	protected Vector<Parcours> parcours = new Vector<Parcours>();

	public Epreuve() {
	}
	
	public Epreuve(int pId, int pNumOrdre, String pNom, Categorie pCategorie) {
		id = pId;
		numOrdre = pNumOrdre;
		nom = pNom;
		categorie = pCategorie;
	}
	
	/**
	 * Affecte le nom de l'épreuve
	 * 
	 * @param pNom
	 */
	public void setNom(String pNom) {
		nom = pNom;
	}

	/**
	 * Retourne le nom de l'épreuve
	 * 
	 * @return String 
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * Affecte l'heure de départ
	 * 
	 * @param pHeureDepart
	 */
	public void setHeureDepart(LocalTime pHeureDepart) {
		heureDepart = pHeureDepart;
	}
	
	/**
	 * Retourne l'heure de départ
	 * 
	 * @return
	 */
	public LocalTime getHeureDepart() {
		return heureDepart;
	}
	
	/**
	 * Affecte l'heure de départ du 2ème départ du relais
	 * 
	 * @param pHeureDepart
	 */
	public void setHeureDepart2(LocalTime pHeureDepart2) {
		heureDepart2 = pHeureDepart2;
	}
	
	/**
	 * Retourne l'heure de départ du 2ème départ du relais
	 * 
	 * @return
	 */
	public LocalTime getHeureDepart2() {
		return heureDepart2;
	}
	
	/**
	 * affecte l'id de l'épreuve
	 * 
	 * @param pId int
	 */
	public void setId(int pId) {
		id = pId;
	}
	
	/**
	 * Retourne l'id de l'épreuve
	 * 
	 * @return int
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * 
	 */
	public void setNumOrdre(int pNumOrdre) {
		numOrdre = pNumOrdre;
	}
	
	public int getNumOrdre() {
		return numOrdre;
	}
	
	public void setCategorie(Categorie pCategorie) {
		categorie = pCategorie;
	}
	
	public Categorie getCategorie() {
		return categorie;
	}
	
	/**
	 * Affecte le type de l'épreuve
	 * 
	 * @param pType TypeEpreuve
	 */
	public void setType(TypeEpreuve pType) {
		type = pType;
	}
	
	/**
	 * Affecte le type de l'épreuve
	 * 
	 * @param pType String
	 */
	public void setType(String pType) {
		type = TypeEpreuve.valueOfByEpreuve(pType);
	}
	
	/**
	 * Retourne le type de l'épreuve
	 * 
	 * @return TypeEpreuve
	 */
	public TypeEpreuve getType() {
		return type;
	}
	
	/**
	 * Affecte le type de départ
	 * 
	 * @param pTypeDepart
	 */
	public void setTypeDepart(String pTypeDepart) {
		typeDepart = TypeDepart.valueOfByDepart(pTypeDepart);
	}
	
	/**
	 * Retourne le type de départ
	 * 
	 * @return
	 */
	public TypeDepart getTypeDepart() {
		return typeDepart;
	}
	
	/**
	 * Affecte le type de limite horaire
	 * 
	 * @param pTypeLimite String le type de limite
	 */
	public void setTypeLimite(String pTypeLimite) {
		typeLimite = TypeLimite.valueOfByLimite(pTypeLimite);
	}
	
	/**
	 * Affecte le type de limite horaire
	 * 
	 * @param pTypeLimite String le type de limite
	 */
	public void setTypeLimiteByValue(String pTypeLimite) {
		typeLimite = TypeLimite.valueOf(pTypeLimite);
	}
	
	/**
	 * Retourne le type de limite horaire
	 * 
	 * @return type de limite horaire
	 */
	public TypeLimite getTypeLimite() {
		return typeLimite;
	}
	
	/**
	 * Affecte la durée maximale de l'épreuve
	 * 
	 * @param pDureeMaxi LocalTime
	 */
	public void setDureeMaxi(LocalTime pDureeMaxi) {
		dureeMaxi = pDureeMaxi;
	}
	
	/**
	 * Retourne la durée maximale de l'épreuve
	 * 
	 * @return LocalTime
	 */
	public LocalTime getDureeMaxi() {
		return dureeMaxi;
	}
	
	/**
	 * Affecte le nombre de points de pénalité en cas de dépassement de la durée maximale de l'épreuve
	 * 
	 * @param pPointsDureeMaxi int
	 */
	public void setPointsDureeMaxi(int pPointsDureeMaxi) {
		pointsDureeMaxi = pPointsDureeMaxi;
	}
	
	/**
	 * Retourne le nombre de points de pénalité en cas de dépassement de la durée maximale de l'épreuve
	 * 
	 * @return int
	 */
	public int getPointsDureeMaxi() {
		return pointsDureeMaxi;
	}
	
	/**
	 * Affecte le nombre de minutes de pénalité en cas de dépassement de la durée maximale de l'épreuve
	 * 
	 * @param LocalTime
	 */
	public void setPenaliteDureeMaxi(LocalTime pPenaliteDureeMaxi) {
		penaliteDureeMaxi = pPenaliteDureeMaxi;
	}
	
	/**
	 * Retourne le nombre de minutes de pénalité en cas de dépassement de la durée maximale de l'épreuve
	 * 
	 * @return LocalTime
	 */
	public LocalTime getPenaliteDureeMaxi() {
		return penaliteDureeMaxi;
	}

	/**
	 * durée maximale de l'épreuve : affecte le nombre de minutes qui déclenche une pénalité
	 * 
	 * @param pParDureeMaxi
	 */
	public void setParDureeMaxi(LocalTime pParDureeMaxi) {
		parDureeMaxi = pParDureeMaxi;
	}

	/**
	 * durée maximale de l'épreuve : retourne le nombre de minutes qui déclenche une pénalité
	 * 
	 * @return LocalTime
	 */
	public LocalTime getParDureeMaxi() {
		return parDureeMaxi;
	}
	
	/**
	 * Affecte l'heure maximale de l'épreuve
	 * 
	 * @param pHeureMaxi LocalTime
	 */
	public void setHeureMaxi(LocalTime pHeureMaxi) {
		heureMaxi = pHeureMaxi;
	}
	
	/**
	 * Retourne l'heure maximale de l'épreuve
	 * 
	 * @return LocalTime
	 */
	public LocalTime getHeureMaxi() {
		return heureMaxi;
	}
	
	/**
	 * Affecte le nombre de points de pénalité en cas de dépassement de l'heure maximale de l'épreuve
	 * 
	 * @param pPointsDureeMaxi int
	 */
	public void setPointsHeureMaxi(int pPointsHeureMaxi) {
		pointsHeureMaxi = pPointsHeureMaxi;
	}
	
	/**
	 * Retourne le nombre de points de pénalité en cas de dépassement de l'heure maximale de l'épreuve
	 * 
	 * @return int
	 */
	public int getPointsHeureMaxi() {
		return pointsHeureMaxi;
	}
	
	/**
	 * Affecte le nombre de minutes de pénalité en cas de dépassement de l'heure maximale de l'épreuve
	 * 
	 * @param pPenaliteHeureMaxi LocalTime
	 */
	public void setPenaliteHeureMaxi(LocalTime pPenaliteHeureMaxi) {
		penaliteHeureMaxi = pPenaliteHeureMaxi;
	}
	
	/**
	 * Retourne le nombre de minutes de pénalité en cas de dépassement de l'heure maximale de l'épreuve
	 * 
	 * @return LocalTime
	 */
	public LocalTime getPenaliteHeureMaxi() {
		return penaliteHeureMaxi;
	}

	/**
	 * heure maximale de l'épreuve : affecte le nombre de minutes qui déclenche une pénalité
	 * 
	 * @param pParHeureMaxi LocalTime
	 */
	public void setParHeureMaxi(LocalTime pParHeureMaxi) {
		parHeureMaxi = pParHeureMaxi;
	}

	/**
	 * Heure maximale de l'épreuve : retourne le nombre de minutes qui déclenche une pénalité
	 * 
	 * @return LocalTime
	 */
	public LocalTime getParHeureMaxi() {
		return parHeureMaxi;
	}
	
	/**
	 * Affecte si les balises doivent être trouvées dans l'ordre ou pas
	 * 
	 * @param pEnLigne boolean
	 */
	public void setEnLigne(boolean pEnLigne) {
		enLigne = pEnLigne;
	}
	
	/**
	 * retourne si les balises doivent être trouvées dans l'ordre ou pas
	 * 
	 * @return boolean
	 */
	public boolean getEnLigne() {
		return enLigne;
	}
	
	/**
	 * Affecte le jour de l'épreuve si la compétition est sur plusieurs jours
	 * 
	 * On ajoutera 24 heures au temps de course par journée
	 * 
	 * @param pJour int
	 */
	public void setJour(int pJour) {
		jour = pJour;
	}
	
	/**
	 * retourne le jour de l'épreuve si la compétition est sur plusieurs jours
	 * 
	 * @return int
	 */
	public int getJour() {
		return jour;
	}
	
	/**
	 * Affecte le coefficient de l'épreuve
	 * 
	 * @param pCoef
	 */
	public void setCoef(int pCoef) {
		coef = pCoef;
	}
	
	/**
	 * retourne le coefficient de l'épreuve
	 * 
	 * @return
	 */
	public int getCoef() {
		return coef;
	}
	
	/**
	 * Affecte si l'épreuve sert pour le départage des ex-aequo
	 * @param pDepartage boolean
	 */
	public void setDepartage(boolean pDepartage) {
		departage = pDepartage;
	}
	
	/**
	 * 
	 * @return boolean si l'épreuve sert pour le départage des ex-aequo
	 */
	public boolean getDepartage() {
		return departage;
	}
	
	/**
	 * Affecte si l'épreuve est active ou pas
	 * 
	 * @param pActive boolean
	 */
	public void setActive(boolean pActive) {
		active = pActive;
	}

	/**
	 * retourne si l'épreuve est active ou pas
	 * 
	 * @return boolean
	 */
	public boolean getActive() {
		return active;
	}
	
	/**
	 * Ajoute un parcours
	 * @param pParcours
	 */
	public void addParcours(Parcours pParcours) {
		parcours.add(pParcours);
	}
	
	public Vector<Parcours> getParcours () {
		return parcours;
	}
	
	
}
