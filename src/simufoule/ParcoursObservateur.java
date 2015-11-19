package simufoule;

import java.util.List;

public interface ParcoursObservateur {

	public void faireTour(Personne unePersonne, Case uneCase, List<Case> desDestinations, Graphe unGraphe);
}
