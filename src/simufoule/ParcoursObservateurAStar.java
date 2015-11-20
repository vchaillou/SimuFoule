package simufoule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.Math;

public class ParcoursObservateurAStar implements ParcoursObservateur {

	private int longueurCases(Case uneSource, Case uneDestination) {
		double longueur;
		int resultat = Integer.MAX_VALUE;
		longueur = (uneSource.getX() - uneDestination.getX()) * (uneSource.getX() - uneDestination.getX()) +
				(uneSource.getY() - uneDestination.getY()) * (uneSource.getY() - uneDestination.getY());
		resultat = ((Double)Math.sqrt((double)longueur)).intValue();
		return resultat;
	}
	
	@Override
	public void faireTour(Personne unePersonne, Case uneCase, List<Case> desDestinations, Graphe unGraphe) {
		Case unNoeud = null;
		LinkedPriorityQueue<Case> uneFileDePriorite = new LinkedPriorityQueue<Case>(new CaseComparateur());
		List<Case> casesVues = new ArrayList<Case>();
		Map<Case, Case> uneRoute = new HashMap<Case, Case>();
		Map<Case, Integer> desCouts = new HashMap<Case, Integer>();
		int longueur = uneCase.getValeur(), temp;
		
		// On remet les valeurs à leur valeur initiale (infini)
		unGraphe.razValeurs();
		
		for(Case uneDestination : desDestinations) {
			temp = longueurCases(uneCase, uneDestination);
			if(temp < longueur) {
				longueur = temp;
			}
		}
		uneCase.setValeur(longueur);
		uneFileDePriorite.add(uneCase);
		desCouts.put(uneCase, 0);
		while(uneFileDePriorite.peek() != null && !desDestinations.contains(uneFileDePriorite.peek())) {
			unNoeud = uneFileDePriorite.remove();
			
			if(!casesVues.contains(unNoeud)) {
				casesVues.add(unNoeud);
				//int uneNouvelleDistance = unNoeud.getValeur() + 1 + unNoeud.getAttente();
				Integer uneNouvelleDistance = desCouts.get(unNoeud) + 1 + unNoeud.getAttente();
				for(Lien edge : (List<Lien>)unNoeud.getEdges()) {
					if(edge.getOther(uneCase).estCirculable()) {
						//int uneDistanceNoeud = edge.getOther(unNoeud).getValeur();
						Integer uneDistanceNoeud = desCouts.get(edge.getOther(unNoeud));
						if(uneDistanceNoeud == null) {
							uneDistanceNoeud = Integer.MAX_VALUE;
						}
						//longueur = edge.getOther(unNoeud).getValeur();
						longueur = Integer.MAX_VALUE;
						for(Case uneDestination : desDestinations) {
							temp = longueurCases(edge.getOther(unNoeud), uneDestination);
							if(temp < longueur) {
								longueur = temp;
							}
						}
						if(uneNouvelleDistance < uneDistanceNoeud) {
							if(edge.getOther(unNoeud).estOccupee())
								desCouts.put(edge.getOther(unNoeud), uneNouvelleDistance+1);
							else
								desCouts.put(edge.getOther(unNoeud), uneNouvelleDistance);
							edge.getOther(unNoeud).setValeur(uneNouvelleDistance + longueur);
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
