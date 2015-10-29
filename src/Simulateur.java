

import java.util.List;

public class Simulateur{

	public IGraph map;
	public List<Personne> personne;
	public List<ParcoursObservateur> observateurs;
	
	
	public IGraph getMap(){
		return this.map;
	}
	
	public IGraph getCase(int x, int y){
		return this.map.getNode(x, y);
	}

	public void lancerTour() {
		for (Personne pers : personne) {
			pers.faireTour(observateurs);
		}
	}

	public boolean estTermine(){
		if(personne.isEmpty())
			return true;
		else
			return false;
	}


}
