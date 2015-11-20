package simufoule;

public class Lanceur {

	public static void main(String[] args) {
		Interface uneInterface = new InterfaceModeGraphique(new Simulateur(false));
		uneInterface.afficherMap();
	}

}
