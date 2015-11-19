package simufoule;

import java.util.ArrayList;
import java.util.List;

public class Simulateur{

	private Graphe map;
	private List<Personne> personnes;
	private List<ParcoursObservateur> observateurs;
	private List<Case> destinations;
	
	private int nbTours;
	private int nbArrivees;
	private int nbDeplacements;
	
	public Simulateur() {
		MapGenerateur unGenerateur = new MapGenerateurFixe();
		map = unGenerateur.getMap();
		personnes = new ArrayList<Personne>();
		observateurs = new ArrayList<ParcoursObservateur>();
		destinations = new ArrayList<Case>();
		for(int i=0 ; i<map.getNbLignes() ; i++) {
			for(int j=0 ; j<map.getNbColonnes() ; j++) {
				if(map.getNode(i, j).estArrivee()) {
					destinations.add(map.getNode(i, j));
				}
			}
		}
		nbTours=0;
		nbArrivees=0;
		nbDeplacements=0;
		
		// Juste pour les tests
		//observateurs.add(new ParcoursObservateurAleatoire());
		observateurs.add(new ParcoursObservateurDijkstra());
		CaseEtatDepart.getInstance().setNbCasePersonnes(1, 1);
		//CaseEtatDepart.getInstance().setNbCasePersonnes(1, 10);
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
			pers.faireTour(observateurs, destinations, map);
			nbDeplacements++;
			break;
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
		List<Personne> desPersonnesASupp = new ArrayList<Personne>();
		uneCase.setOccupee(false);
		for (Personne pers : personnes) {
			if(pers.getCase() == uneCase) {
				desPersonnesASupp.add(pers);
				nbArrivees++;
			}
		}
		for(Personne pers : desPersonnesASupp) {
			personnes.remove(pers);
		}
	}
	
	public void setNbPersonnes(int unNumCase, int unNbPersonnes) {
		CaseEtatDepart.getInstance().setNbCasePersonnes(unNumCase, unNbPersonnes);
	}
	
	public int getNbPersonnes(int unNumCase) {
		return CaseEtatDepart.getInstance().getNbCasePersonnes(unNumCase);
	}
	
	public void ajouterPersonne(Personne unePersonne) {
		personnes.add(unePersonne);
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