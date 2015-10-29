
public class CaseEtatStandard implements CaseEtat {

	private static CaseEtatStandard ETAT = new CaseEtatStandard();
	
	private CaseEtatStandard() {
	}
	
	@Override
	public boolean estCirculable() {
		return true;
	}

	@Override
	public int getAttente() {
		return 0;
	}
	
	public static CaseEtatStandard getInstance() {
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
		return ' ';
	}

}
