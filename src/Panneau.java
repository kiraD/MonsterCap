import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Panneau extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	boolean[] bool = { true, false };
	String name = "Megiddo";// nom de lattack
	String num = "022";// incrementateur danim
	String TEXTE = "";
	String num2 = "1";
	String name2 = "Dinox";
	String num3 = "1";
	String name3 = "UrchinBeast";
	String num4 = "1";
	String name4 = "Dullahan";
	int[] xo = new int[6];
	int[] yo = new int[6];

	int w = 0;
	int h = 0;

	// permet le retour a la ligne
	private void drawString(Graphics g, String text, int x, int y) {
		for (String line : text.split("\n"))
			g.drawString(line, x, y += g.getFontMetrics().getHeight());
	}

	boolean[] monsta = { false, false, false, false, false, false };

	boolean sens = true;

	public void paintComponent(Graphics g) {
		// image de fon
		ImageIcon imagef = new ImageIcon(

		Panneau.class.getResource("/monsta/fond.jpg"));
		Image df = imagef.getImage();
		g.drawImage(df, 0, 0, this.getWidth(), this.getHeight(), this);
		// Animation d'une diaha
		w = this.getWidth();
		h = this.getHeight();

		if (monsta[3]) {
			ImageIcon image = new ImageIcon(
					Panneau.class.getResource("/monsta/" + ename + "/" + enum1
							+ ".png"));
			Image d = image.getImage();
			g.drawImage(d, xo[3], yo[3], 200, 300, this);
		}
		if (monsta[4]) {
			ImageIcon image = new ImageIcon(
					Panneau.class.getResource("/monsta/" + ename2 + "/" + enum2
							+ ".png"));
			Image d = image.getImage();
			g.drawImage(d, xo[4], yo[4], 200, 300, this);
		}
		if (monsta[5]) {
			ImageIcon image = new ImageIcon(
					Panneau.class.getResource("/monsta/" + ename3 + "/" + enum3
							+ ".png"));
			Image d = image.getImage();
			g.drawImage(d, xo[5], yo[5], 200, 300, this);
		}
		// notre champs

		if (bool[0]) {
			ImageIcon image = new ImageIcon(

			Panneau.class.getResource("/monsta/anim/" + name + "/" + num
					+ ".PNG"));
			Image d = image.getImage();

			if (sens)
				g.drawImage(d, this.getWidth(), 0, -1 * (this.getWidth()),
						this.getHeight(), this);
			else
				g.drawImage(d, 0, 0, this.getWidth(), this.getHeight(), this);
		}
		// our team

		if (monsta[0]) {
			ImageIcon image = new ImageIcon(
					Panneau.class.getResource("/monsta/" + name2 + "/" + num2
							+ ".png"));
			Image d = image.getImage();
			g.drawImage(d, xo[0], yo[0], 200, 300, this);
		}
		if (monsta[1]) {
			ImageIcon image = new ImageIcon(
					Panneau.class.getResource("/monsta/" + name3 + "/" + num3
							+ ".png"));
			Image d = image.getImage();
			g.drawImage(d, xo[1], yo[1], 200, 300, this);
		}
		if (monsta[2]) {
			ImageIcon image = new ImageIcon(
					Panneau.class.getResource("/monsta/" + name4 + "/" + num4
							+ ".png"));
			Image d = image.getImage();
			g.drawImage(d, xo[2], yo[2], 200, 300, this);
		}

		// boolean pour l'ecriture
		if (bool[1]) {
			g.setColor(Color.RED);
			g.setFont(new Font("Arail", Font.BOLD, 40));
			Graphics2D g21 = (Graphics2D) g;
			drawString(g21, TEXTE, 50, 100);
		}
		int cord = w - 2 * (w / 10);
		Graphics2D g2d = (Graphics2D) g;
		// g2d.setColor(new Color(22,123,23)) ;

		for (int i = 0; i < monstrejeu(0); i++) {

			if (i == 0) {
				if (monsta[0]) {
					g2d.setStroke(new BasicStroke(1));
					g2d.setColor(new Color(80, 170, 120, 170));
					g2d.fillRect(cord, 10, 2 * (w / 10), 1 * (w / 10));
					g.setColor(Color.WHITE);
					g.drawString("PV  de " + name2 + " : " + field1.get(i).pva,
							cord, (h / 15));
				}
			}
			if (i == 1) {
				if (monsta[1]) {
					g2d.setStroke(new BasicStroke(1));
					g2d.setColor(new Color(80, 170, 120, 170));
					g2d.fillRect(cord, 10, 2 * (w / 10), 1 * (w / 10));
					g.setColor(Color.WHITE);
					g.drawString("PV de" + name3 + " : " + field1.get(i).pva,
							cord, (h / 15));
				}
			}
			if (i == 2) {
				if (monsta[2]) {
					g2d.setStroke(new BasicStroke(1));
					g2d.setColor(new Color(80, 170, 120, 170));
					g2d.fillRect(cord, 10, 2 * (w / 10), 1 * (w / 10));
					g.setColor(Color.WHITE);
					g.drawString("PV de" + name4 + " : " + field1.get(i).pva,
							cord, (h / 15));
				}
			}
			cord -= 2 * (w / 10) + (w / 20);
		}

		int cord2 = w - 4 * (w / 10);
		for (int i = 0; i < /* monstrejeu(1) */3; i++) {

			if (i == 0) {
				if (monsta[3]) {
					g2d.setStroke(new BasicStroke(1));
					g2d.setColor(new Color(180, 50, 70, 170));
					g2d.fillRect(cord2, h / 4, 2 * (w / 10), 1 * (w / 10));
					g.setColor(Color.BLUE);
					g.drawString("PV  de " + ename + " : " + field2.get(i).pva,
							cord2, (h / 3));
				}
			}
			if (i == 1) {
				if (monsta[4]) {
					g2d.setStroke(new BasicStroke(1));
					g2d.setColor(new Color(180, 50, 70, 170));
					g2d.fillRect(cord2, h / 4, 2 * (w / 10), 1 * (w / 10));
					g.setColor(Color.BLUE);
					g.drawString("PV de" + ename2 + " : " + field2.get(i).pva,
							cord2, (h / 3));
				}
			}
			if (i == 2) {
				if (monsta[5]) {
					g2d.setStroke(new BasicStroke(1));
					g2d.setColor(new Color(180, 50, 70, 170));
					g2d.fillRect(cord2, h / 4, 2 * (w / 10), 1 * (w / 10));
					g.setColor(Color.BLUE);
					g.drawString("PV de" + ename3 + " : " + field2.get(i).pva,
							cord2, (h / 3));
				}
			}
			cord2 -= 2 * (w / 10) + (w / 20);
		}
		if (affiche) {

			// g2d.setColor(new Color(22,123,23)) ;
			g2d.setStroke(new BasicStroke(1));
			g2d.setColor(new Color(0, 10, 240, 70));
			// g2d.fillPolygon(px, py, px.length);
			g2d.fillRect(this.getWidth() - 9 * (this.getWidth() / 10),
					this.getHeight() - 9 * (this.getHeight() / 10),
					this.getWidth() - 2 * (this.getWidth() / 10),
					this.getHeight() - 2 * (this.getHeight() / 10));

			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					g2d.setColor(new Color(0, 10, 140, 70));
					g2d.fillRect(
							x,
							y,
							(int) ((this.getWidth() - 2 * ((this.getWidth() / 10))) / 3) - 10,
							((this.getHeight() - 2 * ((this.getHeight() / 10))) / 3) - 5);

					if (monstacado) {
						ImageIcon image = new ImageIcon(
								Panneau.class.getResource("/monsta/"
										+ menu[i][j] + "/0.png"));
						Image d = image.getImage();
						g.drawImage(
								d,
								x,
								y,
								(int) ((this.getWidth() - 2 * ((this.getWidth() / 10))) / 3) - 10,
								((this.getHeight() - 2 * ((this.getHeight() / 10))) / 3) - 5,
								this);
					} else {
						ImageIcon image = new ImageIcon(
								Panneau.class.getResource("/monsta/anim/"
										+ menu[i][j] + "/015.PNG"));
						Image d = image.getImage();
						g.drawImage(
								d,
								x,
								y,
								(int) ((this.getWidth() - 2 * ((this.getWidth() / 10))) / 3) - 10,
								((this.getHeight() - 2 * ((this.getHeight() / 10))) / 3) - 5,
								this);
					}

					if (menu2[i][j]) {
						g2d.setColor(new Color(122, 170, 140, 180));
						g2d.fillRect(
								x,
								y,
								(int) ((this.getWidth() - 2 * ((this.getWidth() / 10))) / 3) - 10,
								((this.getHeight() - 2 * ((this.getHeight() / 10))) / 3) - 5);

					}
					x += (int) ((this.getWidth() - 2 * (this.getWidth() / 10)) / 3);

				}
				x = this.getWidth() - 9 * (this.getWidth() / 10);
				y += (((this.getHeight() - 2 * (this.getHeight() / 10))) / 3);
			}
			x = this.getWidth() - 9 * (this.getWidth() / 10);
			y = this.getHeight() - 9 * (this.getHeight() / 10);

		}
	}

	// table des attack du jeu
	String[][] menu = new String[3][3];
	boolean[][] menu2 = new boolean[3][3];
	// Var de la position adverse
	String ename = "Chimera";
	String ename2 = "mag";
	String ename3 = "FlameDragon";
	String enum1 = "0";
	String enum2 = "0";
	String enum3 = "0";
	boolean affiche = false;
	boolean monstacado = true;
	ArrayList<Monsta> deck = new ArrayList<Monsta>();
	ArrayList<Monsta> main = new ArrayList<Monsta>();
	ArrayList<Monsta> main2 = new ArrayList<Monsta>();
	ArrayList<Monsta> mon = new ArrayList<Monsta>();
	ArrayList<Integer> magie = new ArrayList<Integer>();
	ArrayList<Integer> magie2 = new ArrayList<Integer>();
	ArrayList<Monsta> field1 = new ArrayList<Monsta>();
	ArrayList<Monsta> field2 = new ArrayList<Monsta>();
	int x = 0;
	int y = 0;

	public int monstrejeu(int o) {
		int a = 0;
		if (o == 0) {
			for (int i = 0; i < 3; i++) {
				if (monsta[i]) {
					a++;
				}
			}
		} else {
			for (int i = 3; i < 6; i++) {
				if (monsta[i]) {
					a++;
				}
			}
		}
		return a;
	}

}
