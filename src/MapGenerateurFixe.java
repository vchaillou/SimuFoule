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
				for(CaseEtat unEtat : etats) {
					if(cs2 == unEtat.toChar()) {
						map.registerNode(new Case(i++, y, unEtat));
					}
				}
			}
			y++;
			i=0;
		}
		return map;
	}

}
