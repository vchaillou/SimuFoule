package simufoule;

public class InterfaceModeTexte implements Interface {

	private Simulateur simulateur;
	private int vitesse;		// en ms
	
	public InterfaceModeTexte(Simulateur unSimulateur) {
		simulateur = unSimulateur;
		vitesse = 1000;
	}
	
	@Override
	public void afficherMap() {
		while(true) {
			System.out.println("\n\n\n");
			simulateur.lancerTour();
			Graphe uneMap = simulateur.getMap();
			for(int i=0 ; i<uneMap.getNbLignes() ; i++) {
				for(int j=0 ; j<=uneMap.getNbColonnes() ; j++) {
					System.out.print(uneMap.getNode(i, j).toChar());
				}
				System.out.println("");
			}
			try {
				Thread.sleep(vitesse);
			} catch(Exception e) {
				System.out.println(e.toString());
			}
		}
	}
}
