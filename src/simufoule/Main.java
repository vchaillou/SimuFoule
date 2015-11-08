package simufoule;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;


public class Main {

    public static void main(String[] args) {
        final JFrame f = new JFrame("Frame Test");
        Simulateur simulateur = new Simulateur();
        JPanel panel = new JPanel(new GridLayout(19, 49));

       /*for (int i = 0; i < 16; i++) {
            JLabel l = new JLabel("f" + i, JLabel.CENTER);
            //JLabel l = new JLabel(new ImageIcon("image_file.png"), JLabel.CENTER);
            l.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            l.setFont(l.getFont().deriveFont(20f));
            panel.add(l);
        }*/
        String carte = "************************************************\n*      *              *                    A   *\n* ** A  *   ********** *             *   *     *\n* **    *     *      * * *******     *****     *\n*  *    ****  * G*   *   GGG      *  *         *\n*  *        *  * G*   *******     *  ******    *\n*               GG* *       *     *       **   *\n*****G********************  *  ********   *    *\n*  GGGG  * GGGG   *         *         *   *  * *\n* *** GG * G*G*****  ***    ******    *   *    *\n*GGGGGG  * G*G*       *          *    *   **   *\n* G****  *        *************  ******   *    *\n* GGGGGGGGG *          GGGG  *           *     *\n****** ******     ****  GGGG  *      ********  *\n*           *     *       GGG *                *\n*  ******   *                                  *\n*   *                                   D      *\n*    D      *                                  *\n************************************************";
		String[] carte2 = carte.split("\n");
		
		for (String s : carte2) {
			for (char ch : s.toCharArray()) {
				System.out.println(ch);
				//JLabel l = new JLabel("" + ch, JLabel.CENTER);
				//JLabel l = new JLabel(new ImageIcon("C:/Users/faisal/Downloads/Cours/SimuFoule/standard.png"));
				JLabel l = new JLabel();
				
				switch(ch){
				case ' ':
					l = new JLabel(new ImageIcon("C:/Users/faisal/Downloads/Cours/SimuFoule/standard.png"));
					break;
				case 'G':
					l = new JLabel(new ImageIcon("C:/Users/faisal/Downloads/Cours/SimuFoule/herbe.png"));
					break;
				case '*':
					l = new JLabel(new ImageIcon("C:/Users/faisal/Downloads/Cours/SimuFoule/mur.png"));
					break;
				case 'D':
					l = new JLabel(new ImageIcon("C:/Users/faisal/Downloads/Cours/SimuFoule/depart.png"));
					break;
				case 'A':
					l = new JLabel(new ImageIcon("C:/Users/faisal/Downloads/Cours/SimuFoule/arrive.png"));
					break;
				}
				//l.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
	            /*l.setFont(l.getFont().deriveFont(20f));
	            l.setPreferredSize(new Dimension(26, 26));*/
	            panel.add(l);
			}
		}
        f.setContentPane(panel);
        f.setSize(1250, 500);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}