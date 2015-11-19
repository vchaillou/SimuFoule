package simufoule;

import java.util.Comparator;

class LinkedPriorityQueue<T> implements IPriorityQueue<T> {

	private Comparator<T> comparateur;
	private ILinkSimple<T> liste;
	
	public LinkedPriorityQueue(Comparator<T> unComparateur) {
		comparateur = unComparateur;
		liste = null;
	}
	
	public LinkedPriorityQueue() {
		this(null);
	}
	
	public Comparator<T> getComparator() {
		return comparateur;
	}
	
	public void setComparator(Comparator<T> unComparateur) {
		comparateur = unComparateur;
	}
	
	@Override
	public void add(T e) {
		ILinkSimple<T> lstTemp = liste;
		if(lstTemp == null) {			// pas encore de tête de liste
			liste = new LinkSimple<T>();
			liste.setValue(e);
			return;
		}
		if(comparateur.compare(lstTemp.getValue(), e)>0) {		// si tête de liste directement supérieure à e
			liste = new LinkSimple<T>();
			liste.setValue(e);
			liste.setNext(lstTemp);
			return;
		}
		while(lstTemp.getNext() != null && comparateur.compare(lstTemp.getNext().getValue(), e)<0) {		// On recherche le bon endroit pour l'ajout
			lstTemp = lstTemp.getNext();
		}
		ILinkSimple<T> newListe = new LinkSimple<T>();
		newListe.setValue(e);
		newListe.setNext(lstTemp.getNext());
		lstTemp.setNext(newListe);
	}

	@Override
	public T remove() {
		if(liste == null)
			return null;
		T tResultat = liste.getValue();
		liste = liste.getNext();
		return tResultat;
	}

	@Override
	public T peek() {
		if(liste == null)
			return null;
		return liste.getValue();
	}
	
	

}