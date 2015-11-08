package simufoule;


import java.util.ArrayList;
import java.util.List;

public class Graphe implements IGraph<Integer, CaseEtat> {
	
	private List<Case> noeuds;
	private int nbColonnes;
	private int nbLignes;
	
	public Graphe() {
		noeuds = new ArrayList<Case>();
		nbColonnes = -1;
		nbLignes = -1;
	}

	@Override
	public Case getNode(Integer unX, Integer unY) {
		Case nResultat = null;
		for(Case noeud : noeuds) {
			if(unX.equals(noeud.getX()) && unY.equals(noeud.getY())) {
				nResultat = noeud;
				break;
			}
		}
		return nResultat;
	}

	@Override
	public void registerNode(INode<Integer, CaseEtat> node) {
		if(!(node instanceof Case))
			return;
		Case uneCase = (Case)node;
		noeuds.add(uneCase);
		
		if(uneCase.getX() > nbLignes)
			nbLignes = uneCase.getX();
		if(uneCase.getY() > nbColonnes)
			nbColonnes = uneCase.getY();
	}

	@Override
	public void unregisterNode(Integer unX, Integer unY) {
		for(Case noeud : noeuds) {
			if(unX.equals(noeud.getX()) && unY.equals(noeud.getY())) {
				noeuds.remove(noeud);
			}
		}
	}
	
	public int getNbColonnes() {
		return nbColonnes;
	}
	
	public int getNbLignes() {
		return nbLignes;
	}

}
