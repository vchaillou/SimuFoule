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
		Map<Case, Integer> desValeurs = new HashMap<Case, Integer>();
		
		desValeurs.put(uneCase, 0);
		uneFileDePriorite.add(uneCase);
		
		while(uneFileDePriorite.peek() != null && !desDestinations.contains(uneFileDePriorite.peek())) {
			unNoeud = uneFileDePriorite.remove();
			
			if(!casesVues.contains(unNoeud)) {
				casesVues.add(unNoeud);
				Integer uneNouvelleDistance = desValeurs.get(unNoeud) + 1 + unNoeud.getAttente();
				for(Lien edge : (List<Lien>)unNoeud.getEdges()) {
					if(edge.getOther(uneCase).estCirculable()) {
						Integer uneDistanceNoeud = desValeurs.get(edge.getOther(unNoeud));
						if(uneDistanceNoeud == null) {
							uneDistanceNoeud = Integer.MAX_VALUE;
						}
						if(uneNouvelleDistance < uneDistanceNoeud) {
							desValeurs.put(edge.getOther(unNoeud), uneNouvelleDistance);
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
			if(!unNoeud.estOccupee()) {
				uneCase.deplacer(unePersonne, unNoeud);
			}
		}
	}
}
