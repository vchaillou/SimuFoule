package simufoule;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InterfaceModeGraphique implements Interface {

	private Simulateur simulateur;
	private int vitesse;		// en ms
	
	public JPanel panel_principal;
	public JPanel panel_info;
	public JPanel panel_map;
	public JPanel panel_bas;
	
	public JFrame f;

	public JTextField nb_souris_1;
	public JTextField nb_souris_2;
	public JTextField speed;
	
	public InterfaceModeGraphique(Simulateur unSimulateur) {
		simulateur = unSimulateur;
		vitesse = 1000;
	}
	
	
	@Override
	public void afficherMap() {
		
		JFrame f = new JFrame("Simufoule");
		
		panel_principal = new JPanel(new BorderLayout());
		f.setContentPane(panel_principal);
		panel_bas = new   JPanel();
		
		
		nb_souris_1 = new JTextField();
		nb_souris_1.setColumns(10); //On lui donne un nombre de colonnes à afficher
		
		nb_souris_2 = new JTextField();
		nb_souris_2.setColumns(10); //On lui donne un nombre de colonnes à afficher
		
		speed = new JTextField();
		speed.setColumns(10); //On lui donne un nombre de colonnes à afficher
		
		panel_bas.add(new JLabel("Porte 1 "));
		panel_bas.add(nb_souris_1);
		panel_bas.add(new JLabel("Porte 2 "));
		panel_bas.add(nb_souris_2);
		panel_bas.add(new JLabel("Vitesse "));
		panel_bas.add(speed);
		JButton btn_lancer = new JButton("Lancez");
		panel_bas.add(btn_lancer);
		
		panel_principal.add(panel_bas, BorderLayout.SOUTH);
		panel_map = new JPanel(new GridLayout(19, 49));
        
		panel_info = new   JPanel();
        
        panel_principal.add(panel_map, BorderLayout.NORTH);
        panel_principal.add(panel_info, BorderLayout.CENTER);
        
        
        Graphe uneMap = simulateur.getMap();
     
      		for(int i=0 ; i<=uneMap.getNbLignes() ; i++) {
      			for(int j=0 ; j<=uneMap.getNbColonnes() ; j++) {
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
      				
      				panel_map.add(l);
      			}
      			
      		}
        
		 f.setSize(1250, 580);
	     f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     f.setVisible(true);
		 //Fenetre non redimentionnable
	     f.setResizable(false);
	     
	  btn_lancer.addActionListener(new ActionListener()
	 
	    {
			@Override
			public void actionPerformed(ActionEvent arge0) {
				System.out.println("Clic");
				int nb_souris1 = (Integer.parseInt(nb_souris_1.getText()));
				simulateur.setNbPersonnes(0, nb_souris1);
				int nb_souris2 = (Integer.parseInt(nb_souris_2.getText()));
				simulateur.setNbPersonnes(1, nb_souris2);
				
				vitesse = (Integer.parseInt(speed.getText()));
				
				panel_principal.remove(panel_bas);
				
				
				new Thread ( new Runnable ()
	            {
	                public void run ()
	                {
	                	System.out.println("thread");
	                	while(true){

	    					System.out.println("Nouveau Tour");
	    					panel_map.removeAll();
	    					
	    					simulateur.lancerTour();
	    					Graphe uneMap = simulateur.getMap();

	    					for(int i=0 ; i<=uneMap.getNbLignes() ; i++) {
	    						for(int j=0 ; j<=uneMap.getNbColonnes() ; j++) {
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
	    							
	    							panel_map.add(l);
	    						}
	    					}
	    					
	    					panel_info.removeAll();
	    					panel_info.add(new JLabel("Nombre de tours : " + simulateur.getNbTours() + "                         Nombres de personne : " + simulateur.getNbPersonnes())
	    							);
	    					
	    					panel_map.updateUI();
	    					try {
	    						Thread.sleep(vitesse);
	    						
	    					} catch(Exception e) {
	    						System.out.println(e.toString());
	    					} 	
	    				}
	                }
	            } ).start ();
			}
			
	    });

	}

}
