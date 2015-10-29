

public interface IGraph<K, V> {
	public INode<K,V> getNode(K unX, K unY);
	public void registerNode(INode<K,V> node);
	public void unregisterNode(K unX, K unY);
}
