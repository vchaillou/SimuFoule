

import java.util.List;

public interface INode<K,V>{
    public K getX();
    public K getY();
    public V getValeur();
    public void setValeur(V uneValeur);
    public List<? extends IEdge<K,V>> getEdges();
    public void addEdge(IEdge<K,V> unLien);
}
