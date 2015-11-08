package simufoule;

public class CaseEtatArrivee implements CaseEtat {
	
	private static CaseEtatArrivee ETAT = new CaseEtatArrivee();
	
	private CaseEtatArrivee() {
	}

	@Override
	public boolean estCirculable() {
		return true;
	}

	@Override
	public int getAttente() {
		return 0;
	}

	public static CaseEtatArrivee getInstance() {
		return ETAT;
	}

	@Override
	public void faireTour(Simulateur unSimulateur, Case uneCase) {
		unSimulateur.viderCase(uneCase);
		uneCase.setOccupee(false);
	}

	@Override
	public boolean estArrivee() {
		return true;
	}

	@Override
	public char toChar() {
		return 'A';
	}

	@Override
	public void initCase(Case uneCase) {
		// Rien à faire
	}
}
