package simufoule;

import java.util.List;


public class ParcoursObservateurAleatoire implements ParcoursObservateur {

	@Override
	public void faireTour(Personne unePersonne, Case uneCase, List<Case> desDestinations, Graphe unGraphe) {
		for(IEdge<Integer, Integer> edge : (List<? extends IEdge<Integer, Integer>>)uneCase.getEdges()) {
			if(((Case)edge.getOther(uneCase)).estCirculable() && !((Case)edge.getOther(uneCase)).estOccupee()) {
				uneCase.deplacer(unePersonne, (Case)edge.getOther(uneCase));
				break;
			}
		}
	}

}
