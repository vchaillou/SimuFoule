
public interface CaseEtat {
	
	public boolean estCirculable();
	public int getAttente();
	public void faireTour(Simulateur unSimulateur, Case uneCase);
	public boolean estArrivee();
	public char toChar();
}
