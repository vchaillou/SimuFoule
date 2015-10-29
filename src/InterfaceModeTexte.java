package simufoule;

public class InterfaceModeTexte implements Interface {

	private Simulateur simulateur;
	
	public InterfaceModeTexte(Simulateur unSimulateur) {
		simulateur = unSimulateur;
	}
	
	@Override
	public void afficherMap() {
		while(true) {
			simulateur.lancerTour();
			Graphe uneMap = simulateur.getMap();
			for(int i=0 ; i<uneMap.getNbColonnes() ; i++) {
				for(int j=0 ; j<uneMap.getNbLignes() ; j++) {
					System.out.print(uneMap.getNode(i, j));
				}
				System.out.println("");
			}
			try {
				Thread.sleep(1000);
			} catch(Exception e) {
				System.out.println(e.toString());
			}
		}
	}
}
