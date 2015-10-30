package simufoule;

public class CaseEtatHerbe implements CaseEtat {

	private static CaseEtatHerbe ETAT=new CaseEtatHerbe();
	
	private CaseEtatHerbe(){
	}
	
	@Override
	public boolean estCirculable() {
		return true;
	}

	@Override
	public int getAttente() {
		return 1;
	}

	public static CaseEtatHerbe getInstance() {
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
		return 'G';
	}

	@Override
	public void initCase(Case uneCase) {
		// Rien à faire
	}

}
