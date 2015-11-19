package simufoule;

public class LinkSimple<V> implements ILinkSimple<V> {
	
	private V valeur;
	private ILinkSimple<V> suivant;		// Listre hétérogène
	
	public LinkSimple() {
		this.valeur = null;
		this.suivant = null;
	}
	
	@Override
	public V getValue() {
		return valeur;
	}

	@Override
	public void setValue(V value) {
		valeur = value;
	}

	@Override
	public ILinkSimple<V> getNext() {
		return suivant;
	}

	@Override
	public void setNext(ILinkSimple<V> next) {
		suivant = next;
	}

}
