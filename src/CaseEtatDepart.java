package simufoule;

public class CaseEtatDepart implements CaseEtat {

	private static CaseEtatDepart ETAT = new CaseEtatDepart();
	
	private CaseEtatDepart() {
	}
	
	@Override
	public boolean estCirculable() {
		return true;
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
		if(uneCase.estCirculable()) {
			unSimulateur.traiterPorte(uneCase);
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

}
