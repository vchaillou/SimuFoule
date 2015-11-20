package simufoule;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Simulateur{

	private Graphe map;
	private List<Personne> personnes;
	private List<ParcoursObservateur> observateurs;
	private List<Case> destinations;
	private List<Case> departs;
	
	private int nbTours;
	private int nbArrivees;
	private int nbDeplacements;
	
	public Simulateur(boolean estFixe) {
		personnes = new ArrayList<Personne>();
		observateurs = new ArrayList<ParcoursObservateur>();
		destinations = new ArrayList<Case>();
		departs = new ArrayList<Case>();
		
		if(estFixe){
			MapGenerateur unGenerateur = new MapGenerateurFixe();
			map = unGenerateur.getMap();
			for(int i=0 ; i<map.getNbLignes() ; i++) {
				for(int j=0 ; j<map.getNbColonnes() ; j++) {
					if(map.getNode(i, j).estArrivee()) {
						destinations.add(map.getNode(i, j));
					}
					if (map.getNode(i, j).estDepart()) {
						System.out.println("EST DEPART");
						departs.add(map.getNode(i, j));
					}
				}
			}
		}
		
		nbTours=0;
		nbArrivees=0;
		nbDeplacements=0;
		
		// Juste pour les tests
		//observateurs.add(new ParcoursObservateurAleatoire());
		//observateurs.add(new ParcoursObservateurDijkstra());
		observateurs.add(new ParcoursObservateurAStar());
	}
	
	public Simulateur() {
		this(true);
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
		return (nbTours > 0) && personnes.isEmpty();
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
	
	public void setMap(File unFichier) {
		MapGenerateur unGenerateur2 = new MapGenerateurVariable(unFichier);
		this.map = unGenerateur2.getMap();
		for(int i=0 ; i<map.getNbLignes() ; i++) {
			for(int j=0 ; j<map.getNbColonnes() ; j++) {
				if(map.getNode(i, j).estArrivee()) {
					destinations.add(map.getNode(i, j));
				}
				if(map.getNode(i, j).estDepart()) {
					departs.add(map.getNode(i, j));
				}
			}
		}
		/*this.setNbPersonnes(0, 5);
		this.setNbPersonnes(1, 5);*/
	}
	
	/*
	 * Valeurs de retour :
	 * - 0 => tout va bien
	 * - 1 => Aucune destination trouvée
	 * - 2 => Aucun départ trouvé
	 * - 4 => Aucune route trouvée pour un certain départ
	 */
	public int checkMap() {
		int iResultat = 0;
		if(destinations.size() == 0) {			// Au moins une destination
			iResultat += 1;
		}
		if(departs.size() == 0) {			// Au moins un départ
			//System.out.println("NB DEPART"+departs.size());
			iResultat += 2;
		}/*
		if(destinations.size() == 0) {			// Au moins une route dispo depuis chaque départ
			iResultat += 4;
		}*/
		return iResultat;
	}
}
