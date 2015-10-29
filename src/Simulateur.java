package simufoule;

import java.util.List;

public class Simulateur{

	public Graphe map;
	public List<Personne> personne;
	public List<ParcoursObservateur> observateurs;
	
	
	public Graphe getMap(){
		return this.map;
	}
	
	public Case getCase(int x, int y){
		return this.map.getNode(x, y);
	}

	//parcours la liste des personne et faireTour();
	public void lancerTour() {
		for (Personne pers : personne) {
			pers.faireTour(observateurs);
		}
	}
	
	//si la liste des personne est vide 
	public boolean estTermine(){
		if(personne.isEmpty())
			return true;
		else
			return false;
	}

	public void viderCase(Case uneCase){
		uneCase.setOccupee(false);
	}
	
	public void ajouterPersonne(Personne unePersonne){
		//A faire
	}
}
