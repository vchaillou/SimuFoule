package simufoule;

public class CaseEtatDepart implements CaseEtat {

	private static CaseEtatDepart ETAT = new CaseEtatDepart();
	private int nbPersonnes;
	
	private CaseEtatDepart() {
		this(10);
	}
	
	private CaseEtatDepart(int unNbPersonnes) {
		nbPersonnes = unNbPersonnes;
	}
	
	public void setNbPersonnes(int unNbPersonnes) {
		nbPersonnes = unNbPersonnes;
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
		if(uneCase.estCirculable() && nbPersonnes > 0) {
			unSimulateur.ajouterPersonne(new Personne(uneCase));
			uneCase.setOccupee(true);
			nbPersonnes--;
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
