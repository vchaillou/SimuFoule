package simufoule;

import java.util.ArrayList;
import java.util.List;

public class Case implements INode<Integer, Integer> {

	private int x;
	private int y;
	private int valeur;
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
		etat.initCase(this);
		valeur = Integer.MAX_VALUE;
	}
	
	public Case(int unX, int unY) {
		this(unX, unY, CaseEtatStandard.getInstance());
	}
	
	public boolean estCirculable() {
		return etat.estCirculable();
	}
	
	public boolean estOccupee() {
		return occupee;
	}
	
	public boolean estArrivee() {
		return etat.estArrivee();
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
	public Integer getValeur() {
		return valeur;
	}

	@Override
	public void setValeur(Integer uneValeur) {
		valeur = uneValeur;
	}

	@Override
	public List<Lien> getEdges() {
		return edges;
	}

	@Override
	public void addEdge(IEdge<Integer, Integer> unLien) {
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
			if(uneCase.estCirculable() && !uneCase.estOccupee()) {
				unePersonne.setCase(uneCase);
				attente = etat.getAttente();
			}
		}
	}
	
	public void faireTour(Simulateur unSimulateur) {
		etat.faireTour(unSimulateur, this);
	}
	
	public char toChar() {
		return occupee ? 'P' : etat.toChar();
	}
	
	public int getAttente() {
		return etat.getAttente();
	}
	
	public boolean estDepart() {
		return etat.estDepart();
	}

}
