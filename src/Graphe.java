

import java.util.ArrayList;
import java.util.Iterator;
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
			if(noeud.getX() == unX && noeud.getY() == unY) {
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
		
		if(uneCase.getX() > nbColonnes)
			nbColonnes = uneCase.getX();
		if(uneCase.getY() > nbLignes)
			nbLignes = uneCase.getY();
	}

	@Override
	public void unregisterNode(Integer unX, Integer unY) {
		Iterator<Case> unIterateur = noeuds.iterator();
		Case unNoeud;
		while(unIterateur.hasNext()) {
			unNoeud = unIterateur.next();
			if(unNoeud.getX() == unX && unNoeud.getY() == unY) {
				unIterateur.remove();
				break;
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
