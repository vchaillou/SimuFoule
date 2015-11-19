package simufoule;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
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
        
        
        //Graphe uneMap = simulateur.getMap();
     
      	/*	for(int i=0 ; i<=uneMap.getNbLignes() ; i++) {
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
      			
      		}*/
        
		 f.setSize(1250, 600);
	     f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     f.setVisible(true);
		 //Fenetre non redimentionnable
	     f.setResizable(false);
	     
	  btn_lancer.addActionListener(new ActionListener()
	 
	    {
			@Override
			public void actionPerformed(ActionEvent arge0) {
				System.out.println("Clic");
				
				
				String content = "************************************************\n*      *              *                    A   *\n* ** A  *   ********** *             *   *     *\n* **    *     *      * * *******     *****     *\n*  *    ****  * G*   *   GGG      *  *         *\n*  *        *  * G*   *******     *  ******    *\n*               GG* *       *     *       **   *\n*****G********************  *  ********   *    *\n*  GGGG  * GGGG   *         *         *   *  * *\n* *** GG * G*G*****  ***    ******    *   *    *\n*GGGGGG  * G*G*       *          *    *   **   *\n* G****  *        *************  ******   *    *\n* GGGGGGGGG *   P      GGGG  *           *     *\n****** ******     ****  GGGG  *      ********  *\n*           *     *       GGG *                *\n*  ******   *                                  *\n*   *            P                      D      *\n*    D      *                                  *\n************************************************";
				
				
				
				String sSouris_1 = nb_souris_1.getText();
				String sSouris_2 = nb_souris_2.getText();
				String sVitesse = speed.getText();
				
				JFileChooser fileChooser = new JFileChooser();
				int returnValue = fileChooser.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					
					//String map_import = 
					System.out.println(selectedFile.getName());
					try {
						content = readFile(selectedFile.getPath(), StandardCharsets.UTF_8);
						System.out.println(content);

					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
				//ENTRE COMMENTAIRE CAR SINON NB PERSONNES VIDE
				simulateur.setMap(content);
				
				//simulateur.setNbPersonnes(0, 50);
				System.out.println(simulateur.getNbPersonnes());
				
				
				if(!isInteger(sSouris_1) || !isInteger(sSouris_2)|| !isInteger(sVitesse)){
					System.out.println("Valeur non int");
					panel_info.removeAll();
					
					new Thread ( new Runnable ()
		            {
		                public void run ()
		                {
		                	panel_info.add(new JLabel("Une erreur est survenue : au moins un de champs est mal renseigné"));
		                	panel_map.updateUI();
		                }
		            } ).start ();
				}
				else{
					//f.setSize(1250, 580);	
				int nb_souris1 = (Integer.parseInt(sSouris_1));
				simulateur.setNbPersonnes(0, nb_souris1);
				int nb_souris2 = (Integer.parseInt(sSouris_2));
				simulateur.setNbPersonnes(1, nb_souris2);
				
				vitesse = (Integer.parseInt(sVitesse));
				
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
	    					simulateur.setNbPersonnes(0, 5);
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
	    					panel_info.add(new JLabel("Nombre de tours : " + simulateur.getNbTours() + "                         Nombre de personne(s) : " + simulateur.getNbPersonnes())
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
			}
			
	    });

	}
	

	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    // only got here if we didn't return false
	    return true;
	}
	
	public static String readFile(String path, Charset encoding) 
			  throws IOException 
	{
	  byte[] encoded = Files.readAllBytes(Paths.get(path));
	  return new String(encoded, encoding);
	}
	
}
