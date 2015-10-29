
import java.util.ArrayList;

public class MapGenerateurFixe implements MapGenerateur {

	public char[][] map;

	public MapGenerateurFixe(char[][] map) {
		String carte = "************************************************\n*      *              *                    A   *\n* ** A  *   ********** *             *   *     *\n* **    *     *      * * *******     *****     *\n*  *    ****  * G*   *   GGG      *  *         *\n*  *        *  * G*   *******     *  ******    *\n*               GG* *       *     *       **   *\n*****G********************  *  ********   *    *\n*  GGGG  * GGGG   *         *         *   *  * *\n* *** GG * G*G*****  ***    ******    *   *    *\n*GGGGGG  * G*G*       *          *    *   **   *\n* G****  *        *************  ******   *    *\n* GGGGGGGGG *   P      GGGG  *           *     *\n****** ******     ****  GGGG  *      ********  *\n*           *     *       GGG *                *\n*  ******   *                                  *\n*   *            P                      D      *\n*    D      *                                  *\n************************************************";
		String[] carte2 = carte.split("\n");
		int i = 0;
		int y = 0;
		for (String s : carte2) {
			for (char ch : s.toCharArray()) {
				this.map[i++][y] = ch;
			}
			y++;
		}
		// this.map[1][1] = '*';
	}

	public IGraph getMap() {
		IGraph map = new Graphe();
		int i = 0;
		int y = 0;

		for (char[] cs : this.map) {
			for (char cs2 : cs) {
				switch (cs2) {

				case CaseEtatBloquee.getInstance().toChar():
					map.registerNode(new Case(i++, y), CaseEtatBloquee.getInstance());
					break;

				case CaseEtatHerbe.getInstance().toChar():
					map.registerNode(new Case(i++, y), CaseEtatHerbe.getInstance());
					break;

				case CaseEtatStandard.getInstance().toChar():
					map.registerNode(new Case(i++, y), CaseEtatStandard.getInstance());
					break;

				case CaseEtatDepart.getInstance().toChar():
					map.registerNode(new Case(i++, y), CaseEtatDepart.getInstance());
					break;

				case CaseEtatArrive.getInstance().toChar():
					map.registerNode(new Case(i++, y), CaseEtatArrivee.getInstance());
					break;
				
					//
				}

			}
			y++;

		}

		return map;
	}
}
