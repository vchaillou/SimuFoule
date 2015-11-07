package simufoule;

public class Lanceur {

	public static void main(String[] args) {
		Interface uneInterface = new InterfaceModeTexte(new Simulateur());
		uneInterface.afficherMap();
	}

}
