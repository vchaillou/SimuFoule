package simufoule;

public class MapGenerateurFixe implements MapGenerateur {

	private char[][] map;

	public MapGenerateurFixe() {
		String carte = "************************************************\n*      *              *                    A   *\n* ** A  *   ********** *             *   *     *\n* **    *     *      * * *******     *****     *\n*  *    ****  * G*   *   GGG      *  *         *\n*  *        *  * G*   *******     *  ******    *\n*               GG* *       *     *       **   *\n*****G********************  *  ********   *    *\n*  GGGG  * GGGG   *         *         *   *  * *\n* *** GG * G*G*****  ***    ******    *   *    *\n*GGGGGG  * G*G*       *          *    *   **   *\n* G****  *        *************  ******   *    *\n* GGGGGGGGG *   P      GGGG  *           *     *\n****** ******     ****  GGGG  *      ********  *\n*           *     *       GGG *                *\n*  ******   *                                  *\n*   *            P                      D      *\n*    D      *                                  *\n************************************************";
		String[] carte2 = carte.split("\n");
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
						if(i > 0 && (uneCase.estCirculable() || uneCase.estDepart()) && (map.getNode(i-1, y).estCirculable() || map.getNode(i-1, y).estDepart())) {
							new Lien(uneCase, map.getNode(i-1, y), 1);
						}
						if(y > 0 && (uneCase.estCirculable() || uneCase.estDepart()) && (map.getNode(i, y-1).estCirculable() || map.getNode(i, y-1).estDepart())) {
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
