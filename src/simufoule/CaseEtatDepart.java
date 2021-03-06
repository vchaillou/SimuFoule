package simufoule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CaseEtatDepart implements CaseEtat {

	private static CaseEtatDepart ETAT = new CaseEtatDepart();
	
	private List<Case> cases;
	private Map<Case, Integer> casesPersonnes;
	
	private CaseEtatDepart() {
		cases = new ArrayList<Case>();
		casesPersonnes = new HashMap<Case, Integer>();
	}
	
	@Override
	public boolean estCirculable() {
		return false;
	}

	@Override
	public int getAttente() {
		return 0;
	}
	
	public static CaseEtatDepart getInstance() {
		return ETAT;
	}

	@Override
	public void faireTour(Simulateur unSimulateur, Case uneCase) {
		for(Lien edge : (List<Lien>)uneCase.getEdges()) {
			if(casesPersonnes.get(uneCase) > 0 && edge.getOther(uneCase).estCirculable() && !edge.getOther(uneCase).estOccupee()) {
				unSimulateur.ajouterPersonne(new Personne(edge.getOther(uneCase)));
				casesPersonnes.put(uneCase, casesPersonnes.get(uneCase)-1);
			}
		}
	}

	@Override
	public boolean estArrivee() {
		return false;
	}

	@Override
	public char toChar() {
		return 'D';
	}

	@Override
	public void initCase(Case uneCase) {
		cases.add(uneCase);
		casesPersonnes.put(uneCase, 0);
	}
	
	public int getNbCasePersonnes(int unNumCase) {
		return casesPersonnes.get(cases.get(unNumCase));
	}
	
	public void setNbCasePersonnes(int unNumCase, int unNbPersonnes) {
		if(unNumCase < cases.size()) {
			casesPersonnes.put(cases.get(unNumCase), unNbPersonnes);
		}
	}
	
	@Override
	public boolean estDepart() {
		return true;
	}

}
