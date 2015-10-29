package simufoule;

import java.util.List;

public class Personne {

	public Case cCase;
	
	public void setCase(Case uneCase){
		cCase.setOccupee(false);
		this.cCase = uneCase;
		cCase.setOccupee(true);
	}
	
	
	public void faireTour(List<ParcoursObservateur> observateurs){
		for (ParcoursObservateur obs : observateurs) {
			obs.faireTour(this, cCase);
		}
	}
	
	public Case getCase(){
		return this.cCase;
	}
}
