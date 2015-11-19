package simufoule;

public class MapGenerateurVariable implements MapGenerateur {

	private char[][] map;

	public MapGenerateurVariable(String content) {
		String[] carte2 = content.split("\n");
		int i = 0;
		int y = 0;
		map = new char[carte2[0].length()][carte2.length];
		
		for (String s : carte2) {
			for (char ch : s.toCharArray()) {
				map[y++][i] = ch;
			}
			i++;
			y=0;
		}
	}
	
	public Graphe getMap() {
		Graphe map = new Graphe();
		int i = 0;
		int y = 0;
		CaseEtat[] etats = {CaseEtatBloquee.getInstance(),
							CaseEtatHerbe.getInstance(),
							CaseEtatStandard.getInstance(),
							CaseEtatDepart.getInstance(),
							CaseEtatArrivee.getInstance()
							};

		for (char[] cs : this.map) {
			for (char cs2 : cs) {
				boolean bOk = false;
				for(CaseEtat unEtat : etats) {
					if(cs2 == unEtat.toChar() && bOk == false) {
						Case uneCase = new Case(i, y, unEtat);
						map.registerNode(uneCase);
						if(i > 0 && uneCase.estCirculable() && map.getNode(i-1, y).estCirculable()) {
							new Lien(uneCase, map.getNode(i-1, y), 1);
						}
						if(y > 0 && uneCase.estCirculable() && map.getNode(i, y-1).estCirculable()) {
							new Lien(uneCase, map.getNode(i, y-1), 1);
						}
						i++;
						bOk = true;
					}
				}
				if(bOk == false) {
					map.registerNode(new Case(i++, y, CaseEtatBloquee.getInstance()));	// si erreur, case bloquée par défaut
				}
			}
			y++;
			i=0;
		}
		return map;
	}

}
