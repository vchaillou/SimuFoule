package simufoule;

import java.util.List;


public class ParcoursObservateurAleatoire implements ParcoursObservateur {

	@Override
	public void faireTour(Personne unePersonne, Case uneCase) {
		for(IEdge<Integer, CaseEtat> edge : (List<? extends IEdge<Integer, CaseEtat>>)uneCase.getEdges()) {
			if(((Case)edge.getOther(uneCase)).estCirculable()) {
				uneCase.deplacer(unePersonne, (Case)edge.getOther(uneCase));
				break;
			}
		}
	}

}
