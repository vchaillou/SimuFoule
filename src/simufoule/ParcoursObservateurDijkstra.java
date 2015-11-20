package simufoule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParcoursObservateurDijkstra implements ParcoursObservateur {

	@Override
	public void faireTour(Personne unePersonne, Case uneCase, List<Case> desDestinations, Graphe unGraphe) {
		Case unNoeud = null;
		LinkedPriorityQueue<Case> uneFileDePriorite = new LinkedPriorityQueue<Case>(new CaseComparateur());
		List<Case> casesVues = new ArrayList<Case>();
		Map<Case, Case> uneRoute = new HashMap<Case, Case>();
		
		// On remet les valeurs à leur valeur initiale (infini)
		unGraphe.razValeurs();
		
		uneCase.setValeur(0);
		uneFileDePriorite.add(uneCase);
		
		while(uneFileDePriorite.peek() != null && !desDestinations.contains(uneFileDePriorite.peek())) {
			unNoeud = uneFileDePriorite.remove();
			
			if(!casesVues.contains(unNoeud)) {
				casesVues.add(unNoeud);
				int uneNouvelleDistance = unNoeud.getValeur() + 1 + unNoeud.getAttente();
				for(Lien edge : (List<Lien>)unNoeud.getEdges()) {
					if(edge.getOther(uneCase).estCirculable()) {
						int uneDistanceNoeud = edge.getOther(unNoeud).getValeur();
						if(uneNouvelleDistance < uneDistanceNoeud) {
							if(edge.getOther(unNoeud).estOccupee())
								edge.getOther(unNoeud).setValeur(uneNouvelleDistance+1);
							else
								edge.getOther(unNoeud).setValeur(uneNouvelleDistance);
							uneRoute.put(edge.getOther(unNoeud), unNoeud);
						}
						uneFileDePriorite.add(edge.getOther(unNoeud));
					}
				}
			}
		}
		if(uneFileDePriorite.peek() != null && uneFileDePriorite.peek().estCirculable()) {
			unNoeud = uneFileDePriorite.peek();
			while(uneRoute.get(unNoeud) != uneCase) {
				unNoeud = uneRoute.get(unNoeud);
			}
			uneCase.deplacer(unePersonne, unNoeud);
		}
	}
}
