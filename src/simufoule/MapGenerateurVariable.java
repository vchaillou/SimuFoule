package simufoule;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MapGenerateurVariable implements MapGenerateur {
	
	private static final boolean _DIAGONALES_ = true;
	//private static final boolean _DIAGONALES_ = false;

	private char[][] map;
	
	private static String readFile(String path, Charset encoding) throws IOException 
	{
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}

	public MapGenerateurVariable(File unFichier) {
		String content = "";
		try {
			content = readFile(unFichier.getPath(), StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
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
						if(i > 0 && (uneCase.estCirculable() || uneCase.estDepart()) && map.getNode(i-1, y) != null && (map.getNode(i-1, y).estCirculable() || map.getNode(i-1, y).estDepart())) {
							new Lien(uneCase, map.getNode(i-1, y), 1);
						}
						if(y > 0 && (uneCase.estCirculable() || uneCase.estDepart()) && map.getNode(i, y-1) != null && (map.getNode(i, y-1).estCirculable() || map.getNode(i, y-1).estDepart())) {
							new Lien(uneCase, map.getNode(i, y-1), 1);
						}
						if(_DIAGONALES_) {
							if(i > 0 && (uneCase.estCirculable() || uneCase.estDepart()) && map.getNode(i-1, y-1) != null && (map.getNode(i-1, y-1).estCirculable() || map.getNode(i-1, y-1).estDepart())) {
								new Lien(uneCase, map.getNode(i-1, y-1), 1);
							}
							if(y > 0 && (uneCase.estCirculable() || uneCase.estDepart()) && map.getNode(i+1, y-1) != null && (map.getNode(i+1, y-1).estCirculable() || map.getNode(i+1, y-1).estDepart())) {
								new Lien(uneCase, map.getNode(i+1, y-1), 1);
							}
						}
						
						i++;
						bOk = true;
					}
				}
				if(bOk == false && cs2 != '\0' && cs2 != '\r' && cs2 != '\t') {
					map.registerNode(new Case(i++, y, CaseEtatBloquee.getInstance()));	// si erreur, case bloquée par défaut
				}
			}
			y++;
			i=0;
		}
		return map;
	}

}
