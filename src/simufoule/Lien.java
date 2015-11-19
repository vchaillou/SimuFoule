package simufoule;

public class Lien implements IEdge<Integer, Integer> {

	private Case debut;
	private Case fin;
	private int cost;
	
	public Lien(Case unDebut, Case uneFin, int unCost) {
		debut = unDebut;
		if(debut != null)
			debut.addEdge(this);
		fin = uneFin;
		if(fin != null)
			fin.addEdge(this);
		cost = unCost;
	}
	
	public Lien(Case unDebut, Case uneFin) {
		this(unDebut, uneFin, 1);
	}
	
	@Override
	public Case getOther(INode<Integer, Integer> me) {
		if(me == debut)
			return fin;
		return debut;
	}

	@Override
	public void setAttribute(String key, Object value) {
		switch(key) {
		case "cost":
			cost = (int)value;
			break;
		}
	}

	@Override
	public Object getAttribute(String value) {
		Object oResultat = null;
		switch(value) {
		case "cost":
			oResultat = cost;
			break;
		}
		return oResultat;
	}

}
