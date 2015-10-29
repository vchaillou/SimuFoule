import java.util.ArrayList;
import java.util.List;

public class Case implements INode<Integer, CaseEtat> {

	private int x;
	private int y;
	private CaseEtat etat;
	private List<Lien> edges;
	
	private boolean occupee;
	private int attente;
	
	public Case(int unX, int unY, CaseEtat unEtat) {
		x = unX;
		y = unY;
		etat = unEtat;
		
		edges = new ArrayList<Lien>();
		occupee = false;
		attente = etat.getAttente();
	}
	
	public Case(int unX, int unY) {
		this(unX, unY, CaseEtatStandard.getInstance());
	}
	
	public boolean estCirculable() {
		return !occupee & etat.estCirculable();
	}
	
	@Override
	public Integer getX() {
		return x;
	}

	@Override
	public Integer getY() {
		return y;
	}

	@Override
	public CaseEtat getValeur() {
		return etat;
	}

	@Override
	public void setValeur(CaseEtat uneValeur) {
		etat = uneValeur;
	}

	@Override
	public List<Lien> getEdges() {
		return edges;
	}

	@Override
	public void addEdge(IEdge<Integer, CaseEtat> unLien) {
		if(!(unLien instanceof Lien))
			return;
		edges.add((Lien)unLien);
	}
	
	public void setOccupee(boolean estOccupee) {
		occupee = estOccupee;
	}
	
	public void deplacer(Personne unePersonne, Case uneCase) {
		if(attente > 0) {
			attente--;
		}
		else {
			unePersonne.setCase(uneCase);
			attente = etat.getAttente();
		}
	}
	
	public void faireTour(Simulateur unSimulateur) {
		etat.faireTour(unSimulateur, this);
	}

}
