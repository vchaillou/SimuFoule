
public class CaseEtatBloquee implements CaseEtat {

	private static CaseEtatBloquee ETAT = new CaseEtatBloquee();
	
	private CaseEtatBloquee() {
	}
	
	@Override
	public boolean estCirculable() {
		return false;
	}

	@Override
	public int getAttente() {
		return 0;
	}
	
	public static CaseEtatBloquee getInstance() {
		return ETAT;
	}

	@Override
	public void faireTour(Simulateur unSimulateur, Case uneCase) {
		// Rien à faire
	}

	@Override
	public boolean estArrivee() {
		return false;
	}

	@Override
	public char toChar() {
		return '*';
	}
}
