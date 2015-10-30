package simufoule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Simulateur{

	private Graphe map;
	private List<Personne> personnes;
	private List<ParcoursObservateur> observateurs;
	
	private List<Case> portes;
	private Map<Case, Integer> portesPersonnes;
	
	private int vitesse;		// en ms
	private int nbTours;
	private int nbArrivees;
	private int nbDeplacements;
	
	public Simulateur() {
		MapGenerateur unGenerateur = new MapGenerateurFixe();
		map = unGenerateur.getMap();
		portes = unGenerateur.getPortes();
		portesPersonnes = new HashMap<Case, Integer>();
		personnes = new ArrayList<Personne>();
		observateurs = new ArrayList<ParcoursObservateur>();
		for(Case uneCase : portes) {
			portesPersonnes.put(uneCase, 0);
		}
		vitesse=500;
		nbTours=0;
		nbArrivees=0;
		nbDeplacements=0;
	}
	
	public Graphe getMap(){
		return this.map;
	}
	
	public Case getCase(int x, int y){
		return this.map.getNode(x, y);
	}

	//parcours la liste des personne et faireTour();
	public void lancerTour() {
		for (Personne pers : personnes) {
			pers.faireTour(observateurs);
			nbDeplacements++;
		}
		for(int i=0 ; i<map.getNbLignes() ; i++) {
			for(int j=0 ; j<map.getNbColonnes(); j++) {
				map.getNode(i, j).faireTour(this);
			}
		}
		nbTours++;
	}
	
	//si la liste des personne est vide 
	public boolean estTermine(){
		if(personnes.isEmpty())
			return true;
		else
			return false;
	}

	public void viderCase(Case uneCase){
		uneCase.setOccupee(false);
		for (Personne pers : personnes) {
			if(pers.getCase() == uneCase) {
				personnes.remove(pers);
				nbArrivees++;
			}
		}
	}
	
	public void setNbPersonnes(Case uneCase, int unNbPersonnes) {
		if(portes.contains(uneCase)) {
			portesPersonnes.put(uneCase, unNbPersonnes);
		}
	}
	
	public int getNbPersonnes(Case uneCase) {
		int iResultat = -1;
		if(portes.contains(uneCase)) {
			iResultat = portesPersonnes.get(uneCase);
		}
		return iResultat;
	}
	
	public void traiterPorte(Case uneCase) {
		if(portes.contains(uneCase) && portesPersonnes.get(uneCase) > 0) {		// uneCase est circulable
			personnes.add(new Personne(uneCase));
			uneCase.setOccupee(true);
			portesPersonnes.put(uneCase, portesPersonnes.get(uneCase) - 1);
		}
	}
	
	public int getVitesse() {
		return vitesse;
	}
	
	public void setVitesse(int uneVitesse) {
		vitesse = uneVitesse;
	}
	
	public int getNbTours() {
		return nbTours;
	}
	
	public int getNbPersonnes() {
		return personnes.size();
	}
	
	public int getNbPersonnesArrivees() {
		return nbArrivees;
	}
	
	public int getNbDeplacements() {
		return nbDeplacements;
	}
}
