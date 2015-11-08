package simufoule;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InterfaceModeGraphique implements Interface {

	private Simulateur simulateur;
	private int vitesse;		// en ms
	
	public InterfaceModeGraphique(Simulateur unSimulateur) {
		simulateur = unSimulateur;
		vitesse = 1000;
	}
	
	@Override
	public void afficherMap() {
		
		JFrame f = new JFrame("Simufoule");
		
		//Fenetre non redimentionnable
		f.setResizable(false);
        JPanel panel = new JPanel(new GridLayout(19, 49));
        
        f.setContentPane(panel);
        f.setSize(1250, 500);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
		while(true) {
			panel.removeAll();
			
			simulateur.lancerTour();
			Graphe uneMap = simulateur.getMap();
			for(int i=0 ; i<=uneMap.getNbLignes() ; i++) {
				for(int j=0 ; j<=uneMap.getNbColonnes() ; j++) {
					//System.out.print(uneMap.getNode(i, j).toChar());
				
					JLabel l = new JLabel();
					
					switch(uneMap.getNode(i, j).toChar()){
					case ' ':
						l = new JLabel(new ImageIcon("src/standard.png"));
						break;
					case 'G':
						l = new JLabel(new ImageIcon("src/herbe.png"));
						break;
					case '*':
						l = new JLabel(new ImageIcon("src/mur.png"));
						break;
					case 'D':
						l = new JLabel(new ImageIcon("src/depart.png"));
						break;
					case 'A':
						l = new JLabel(new ImageIcon("src/arrive.png"));
						break;
					case 'P':
						l = new JLabel(new ImageIcon("src/souris.png"));
						break;
					}
					panel.add(l);
					//panel.updateUI();

				}
			}
			panel.updateUI();
			try {
				Thread.sleep(vitesse);
				
			} catch(Exception e) {
				System.out.println(e.toString());
			}
			
		}

	}
}
