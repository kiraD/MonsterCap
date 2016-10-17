import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Fenetre extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Panneau pan = new Panneau();
	String[][] d = { { "anim2", "58" }, { "CuttingEdge", "30" },
			{ "HydroSlash", "60" }, { "mattack2", "132" },
			{ "MysticFlame", "62" }, { "Pyroclasm", "87" },
			{ "RisingDragonAgatio", "182" }, { "Zagan", "150" },
			{ "anim3", "70" }, { "DragonCloud", "144" },
			{ "LetheAlbion", "47" }, { "Megiddo", "189" },
			{ "Odyssey", "111" }, { "RadiantFire", "57" }, { "Thor", "363" },
			{ "anim4", "30" }, { "glace", "133" }, { "Liquifier", "105" },
			{ "Meteor", "244" }, { "Purgatory", "53" },
			{ "RisingDragon", "42" }, { "Volcano", "87" } };

	String[] d2 = { "AkaManah", "Chimera", "Dinox", "Dullahan", "FlameDragon",
			"Ghoul", "KingScorpion", "Kobold", "LivingArmor", "LizardMan",
			"mag", "SeaDragon", "Sentinel", "UnusedEnemy", "UrchinBeast",
			"WoodWalker", "Wyvern", "GhostArmy" };

	int[] d3 = { 10, 14, 10, 16, 14, 10, 14, 10, 10, 10, 16, 18, 10, 14, 16,
			10, 10, 4 };
	JTextField jtf;
	JPanel container = new JPanel();
	int choice = 2;// thor 14

	Thread[] thread = new Thread[10];
	Thread attack;

	public Fenetre() {
		// setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		container.setLayout(new BorderLayout());
		container.add(pan, BorderLayout.CENTER);

		setIconImage(new ImageIcon(this.getClass().getResource(
				"monsta/mag/1.png")).getImage());
		jtf = new JTextField();

		JPanel south = new JPanel();
		south.add(jtf);

		jtf.setPreferredSize(new Dimension(0, 0));
		jtf.addKeyListener(new ClavierListener());
		container.add(south, BorderLayout.SOUTH);

		this.setContentPane(container);

		setExtendedState(Frame.MAXIMIZED_BOTH);
		setUndecorated(true);
		// music(Panneau.class.getResource("/battle.mid"));
		setVisible(true);
		// animate(1, tr(d[choice][1]), d[choice][0], true);
		animate(1, tr(d[choice][1]), d[choice][0], false);

		// faire une animation de base
		init();
		// anate(1, tr(d[12][1]), d[12][0]);

	}

	ArrayList<Integer> lis2 = new ArrayList<Integer>();

	public void melange(ArrayList<Integer> lis) {
		// melange le deck initiale
		// ecrire("Le retour des duels de monstres");
		int i = 18;
		while (pan.mon.size() != 0) {
			pan.deck.add(pan.mon.remove((int) (Math.random() * i)));
			i--;
		}

		i = 22;
		while (lis.size() != 0) {
			lis2.add(lis.remove((int) (Math.random() * i)));
			i--;
		}
		for (int ii = 0; ii < pan.deck.size(); ii++) {
			if (ii % 2 == 0) {
				pan.main.add(pan.deck.get(ii));
			} else {
				pan.main2.add(pan.deck.get(ii));
			}
		}
		for (int i2 = 0; i2 < lis2.size(); i2++) {
			if (i2 % 2 == 0) {
				pan.magie.add(lis2.get(i2));
			} else {
				pan.magie2.add(lis2.get(i2));
			}
		}
		pan.x = pan.w - 9 * (pan.w / 10);
		pan.y = pan.h - 9 * (pan.h / 10);
	}

	public void affichemonsta(boolean a) {
		if (a) {
			int ii = 0;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					pan.menu[i][j] = pan.main.get(ii).name;
					ii++;
				}
			}
			pan.monstacado = true;
		} else {
			pan.monstacado = false;
		}
	}

	public void affichemagie() {
		pan.monstacado = false;
		int ii = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (pan.magie.get(ii).equals("Thor")) {
					pan.menu[i][j] = d[pan.magie.get(ii - 1)][0];
				} else
					pan.menu[i][j] = d[pan.magie.get(ii)][0];
				ii++;
			}
		}
	}

	public void aleat() {
		int u = 9;
		for (int ip = 3; ip < 6; ip++) {
			pan.monsta[ip] = true;
			int ran = (int) (Math.random() * u);
			switch (ip) {
			case 3:
				pan.ename = pan.main2.get(ran).name;
				pan.field2.add(pan.main2.remove(ran));
				break;
			case 4:
				pan.ename2 = pan.main2.get(ran).name;
				pan.field2.add(pan.main2.remove(ran));
				break;
			case 5:
				pan.ename3 = pan.main2.get(ran).name;
				pan.field2.add(pan.main2.remove(ran));
				break;
			}
			u--;
		}

		pan.repaint();
	}

	public void init() {
		ArrayList<Integer> lis = new ArrayList<Integer>();
		for (int i = 0; i < 18; i++) {
			pan.mon.add(new Monsta(d2[i], 140, d3[i]));

		}
		// remplissage des sorts
		for (int i = 0; i < 22; i++) {
			lis.add(i);
		}
		melange(lis);
		aleat();
		affichemonsta(true);
		// init des positions de nos 3 monstres(lim)
		pan.xo[0] = pan.w - 2 * (pan.w / 10);
		pan.yo[0] = pan.h - 2 * (pan.h / 5);
		pan.xo[1] = pan.w - 5 * (pan.w / 10);
		pan.yo[1] = pan.h - 2 * (pan.h / 5);
		pan.xo[2] = pan.w - 8 * (pan.w / 10);
		pan.yo[2] = pan.h - 2 * (pan.h / 5);

		// init de l'autre champs l'adverse
		pan.xo[3] = pan.w - 3 * (pan.w / 10);
		pan.yo[3] = pan.h - 3 * (pan.h / 5);
		pan.xo[4] = pan.w - 6 * (pan.w / 10);
		pan.yo[4] = pan.h - 3 * (pan.h / 5);
		pan.xo[5] = pan.w - 9 * (pan.w / 10);
		pan.yo[5] = pan.h - 3 * (pan.h / 5);

		for (int i = 0; i < 6; i++) {
			thread[i] = new Thread(new Anim(i));
			thread[i].start();
		}
		pan.affiche = true;
		pan.repaint();
	}

	class Anim implements Runnable {
		int o;

		public Anim(int o) {
			this.o = o;
		}

		public void run() {
			// gere lanimation
			int z = 0;
			// if (pan.monsta[o]) {
			while (true) {
				if (o == 0 || o == 1 || o == 2) {
					z = 1;
				} else {
					z = 0;
				}
				for (int i = z; i <= 5; i = i + 2) {
					// pan.id[0] = i;
					switch (o) {
					case 0:
						pan.num2 = i + "";
						break;
					case 1:
						pan.num3 = i + "";
						break;
					case 2:
						pan.num4 = i + "";
						break;
					case 3:
						pan.enum1 = i + "";
						break;
					case 4:
						pan.enum2 = i + "";
						break;
					case 5:
						pan.enum3 = i + "";
						break;
					}

					pause(400);
					pan.repaint();
				}
				// }
			}

		}
	}

	public int tr(String a) {
		return Integer.parseInt(a);
	}

	public void animate(int dep, int lim, String name, boolean sens) {
		pan.sens = sens;
		pan.bool[0] = true;
		pan.name = name;
		for (int i = dep; i < lim; i++) {
			if (i < 10) {
				pan.num = "00" + i;
			} else if (i >= 10 && i < 100) {
				pan.num = "0" + i;
			} else if (i >= 100) {
				pan.num = "" + i;
			}
			pause(80);
			pan.repaint();
		}
		pan.bool[0] = false;
	}

	// fonction qui permet decrire
	public void ecrire(String f) {
		pan.bool[1] = true;
		// Texte a droite

		pan.TEXTE = "";
		String cont = "";

		for (int i = 0; i < f.length(); i++) {
			cont += f.charAt(i);
			pan.TEXTE = cont;
			pan.repaint();
			pause(120);
		}
		pause(1000);
		pan.repaint();

		pan.bool[1] = false;
	}

	// fonction qui met en pause
	public void pause(int a) {
		try {
			Thread.sleep(a);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	boolean tour = true;// c'est notre tour
	int interator = 0;

	class ClavierListener implements KeyListener {
		int i = 0;
		int j = 0;

		@Override
		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub
			if (arg0.getKeyChar() == 'q') {
				// quitte la partie
				System.exit(0);

			}
			if (arg0.getKeyChar() == 'm') {
				if (sequencer != null)
					sequencer.stop();
				else
					sequencer.start();

			}

			if (arg0.getKeyChar() == 'v') {
				if (pan.affiche)
					pan.affiche = false;
				else
					pan.affiche = true;
			}
			if (arg0.getKeyChar() == 'c') {

			}

			if (arg0.getKeyCode() == 37 && pan.affiche) {

				if (j > 0) {
					pan.menu2[i][j] = false;
					j--;
					pan.menu2[i][j] = true;
				} else {
					pan.menu2[i][j] = false;
					j = 2;
					pan.menu2[i][j] = true;
					affichemagie();
					pan.repaint();
				}
			}
			if (arg0.getKeyCode() == 39 && pan.affiche) {
				if (j <= 1) {
					pan.menu2[i][j] = false;
					j++;
					pan.menu2[i][j] = true;
				} else {
					pan.menu2[i][j] = false;
					j = 0;
					pan.menu2[i][j] = true;
					affichemonsta(true);
					pan.repaint();
				}
			}
			if (arg0.getKeyCode() == 38 && pan.affiche) {
				if (i >= 1) {
					pan.menu2[i][j] = false;
					i--;
					pan.menu2[i][j] = true;
				}
			}
			if (arg0.getKeyCode() == 40 && pan.affiche) {
				if (i <= 1) {
					pan.menu2[i][j] = false;
					i++;
					pan.menu2[i][j] = true;
				}
			}
			if (tour) {
				if (arg0.getKeyCode() == 10 && pan.monstacado) {
					for (int ii = 0; ii < 3; ii++) {
						if (!pan.monsta[ii]) {

							pan.monsta[ii] = true;
							int man = 0;
							if (i == 0) {
								man = j;
							} else if (i == 1) {
								man = i + j + 1 + 1;
							} else if (i == 2) {
								man = 5 + j + 1;
							}
							if (pan.main.get(man).pva > 0) {
								switch (ii) {
								case 0:
									pan.name2 = pan.main.get(man).name;

									break;
								case 1:
									pan.name3 = pan.main.get(man).name;

									break;
								case 2:
									pan.name4 = pan.main.get(man).name;

									break;
								}
								pan.field1.add(pan.main.get(man));
							}
							break;
						}
					}
					System.out.println(pan.monstacado);

				}

				if (arg0.getKeyCode() == 37 && !pan.affiche) {
					attack = new Thread(new AttackS(interator, 2));
					attack.start();
					interator++;
					if (interator == 3) {
						tour = false;
						interator = 0;
						Thread en = new Thread(new attackennemy());
						en.start();
					}
				}
				if (arg0.getKeyCode() == 40 && !pan.affiche) {
					attack = new Thread(new AttackS(interator, 1));
					attack.start();
					interator++;
					if (interator == 3) {
						interator = 0;
						tour = false;
						Thread en = new Thread(new attackennemy());
						en.start();
					}
				}
				if (arg0.getKeyCode() == 39 && !pan.affiche) {
					attack = new Thread(new AttackS(interator, 0));
					attack.start();
					interator++;
					if (interator == 3) {
						interator = 0;
						tour = false;
						Thread en = new Thread(new attackennemy());
						en.start();
					}
				}

				if (arg0.getKeyCode() == 10 && !pan.monstacado) {

					int man = 0;
					if (i == 0) {
						man = j;
					} else if (i == 1) {
						man = i + j + 1 + 1;
					} else if (i == 2) {
						man = 5 + j + 1;
					}
					if (!done) {
						pan.affiche = false;
						attack = new Thread(new Attack(pan.magie.get(man)));
						attack.start();
						done = true;

					}
				}
			}

			pan.repaint();
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub

		}

	}

	class attackennemy implements Runnable {
		public void run() {
			// instructions qui donne des ordre
			pause(3000);
			if (Math.random() <= 0.1) {
				for (int i = 0; i < 3; i++) {
					if (pan.monsta[i + 3]) {
						int tmp = (int) (Math.random() * 3);
						attacksyn(i, tmp);
						pause(1000);
						pan.field1.get(tmp).pva = pan.field1.get(tmp).pva - 50;
						if (pan.field1.get(i).pva <= 0) {
							switch (i) {
							case 0:
								pan.monsta[0] = false;
								break;
							case 1:
								pan.monsta[1] = false;
								break;
							case 2:
								pan.monsta[2] = false;
								break;
							}
						}
						pan.repaint();
					}
				}
			} else {
				int size = pan.magie.size();
				int o = pan.magie2.get((int) (Math.random() * size));

				if (d[o][0].equals("Thor")) {
					animate(155, tr(d[o][1]), d[o][0], true);
				} else
					animate(1, tr(d[o][1]), d[o][0], true);

				for (int i = 0; i < 3/* pan.field1.size() */; i++) {

					thread[i].stop();
					switch (i) {
					case 0:
						pan.num2 = pan.field1.get(i).hurt + "";
						pause(800);
						break;
					case 1:
						pan.num3 = pan.field1.get(i).hurt + "";
						pause(800);
						break;
					case 2:
						pan.num4 = pan.field1.get(i).hurt + "";
						pause(800);
						break;
					}
					pause(300);
					pan.repaint();
					thread[i] = new Thread(new Anim(i));
					thread[i].start();

					pan.field1.get(i).pva = pan.field1.get(i).pva
							- (tr(d[o][1]) - i * 10);
					if (pan.field1.get(i).pva <= 0) {
						switch (i) {
						case 0:
							pan.monsta[0] = false;
							break;
						case 1:
							pan.monsta[1] = false;
							break;
						case 2:
							pan.monsta[2] = false;
							break;
						}
					}
					pan.repaint();
				}
			}
			tour = true;

		}
	}

	public void attacksyn(int num, int cible) {
		thread[num + 3].stop();
		int xp = pan.xo[num + 3];
		int yp = pan.yo[num + 3];
		for (int i = 6; i < pan.field2.get(num).hurt; i = i + 2) {
			pause(210);
			pan.repaint();
			switch (num + 3) {
			case 3:
				pan.enum1 = i + "";
				break;
			case 4:
				pan.enum2 = i + "";
				break;
			case 5:
				pan.enum3 = i + "";
				break;
			}
			if (num == 0) {
				switch (cible) {
				case 0:
					// pan.xo[num] -= (pan.w / 29);
					pan.yo[num + 3] += (pan.h / 29);
					break;
				case 1:
					pan.xo[num + 3] -= 2 * (pan.w / 29);
					pan.yo[num + 3] += (pan.h / 29);
					break;
				case 2:
					pan.xo[num + 3] -= 5 * (pan.w / 29);
					pan.yo[num + 3] += (pan.h / 29);
					break;
				}
				// pan.xo[num] -= 2 * (pan.w / 29);
				// pan.yo[num] -= (pan.h / 29);
			} else if (num == 1) {
				switch (cible) {
				case 0:
					pan.xo[num + 3] += 3 * (pan.w / 29);
					pan.yo[num + 3] += (pan.h / 29);
					break;
				case 1:

					pan.yo[num + 3] += (pan.h / 29);
					break;
				case 2:
					pan.xo[num + 3] -= 3 * (pan.w / 29);
					pan.yo[num + 3] += (pan.h / 29);
					break;
				}
			} else if (num == 2) {
				switch (cible) {
				case 0:
					pan.xo[num + 3] += 5 * (pan.w / 29);
					pan.yo[num + 3] += (pan.h / 29);
					break;
				case 1:
					pan.xo[num + 3] += 2 * (pan.w / 29);
					pan.yo[num + 3] += (pan.h / 29);
					break;
				case 2:
					pan.yo[num + 3] += (pan.h / 29);
					break;
				}
			}
		}

		thread[cible].stop();
		switch (cible) {
		case 0:
			pan.num2 = pan.field1.get(cible).hurt + "";
			pause(300);
			break;
		case 1:
			pan.num3 = pan.field1.get(cible).hurt + "";
			pause(300);
			break;
		case 2:
			pan.num4 = pan.field1.get(cible).hurt + "";
			pause(300);
			break;
		}
		pan.repaint();
		thread[cible] = new Thread(new Anim(cible));
		thread[cible].start();
		pan.xo[num + 3] = xp;
		pan.yo[num + 3] = yp;
		thread[num + 3] = new Thread(new Anim(num + 3));
		thread[num + 3].start();

	}

	public void attacksimple(int num, int cible) {
		// num est lidentifiant
		thread[num].stop();
		int xp = pan.xo[num];
		int yp = pan.yo[num];
		for (int i = 5; i < pan.field1.get(num).hurt; i = i + 2) {
			switch (num) {
			case 0:
				pan.num2 = i + "";
				break;
			case 1:
				pan.num3 = i + "";
				break;
			case 2:
				pan.num4 = i + "";
				break;
			}
			if (num == 0) {
				switch (cible) {
				case 0:
					// pan.xo[num] -= (pan.w / 29);
					pan.yo[num] -= (pan.h / 29);
					break;
				case 1:
					pan.xo[num] -= 2 * (pan.w / 29);
					pan.yo[num] -= (pan.h / 29);
					break;
				case 2:
					pan.xo[num] -= 5 * (pan.w / 29);
					pan.yo[num] -= (pan.h / 29);
					break;
				}
				// pan.xo[num] -= 2 * (pan.w / 29);
				// pan.yo[num] -= (pan.h / 29);
			} else if (num == 1) {
				switch (cible) {
				case 0:
					pan.xo[num] += 3 * (pan.w / 29);
					pan.yo[num] -= (pan.h / 29);
					break;
				case 1:

					pan.yo[num] -= (pan.h / 29);
					break;
				case 2:
					pan.xo[num] -= 3 * (pan.w / 29);
					pan.yo[num] -= (pan.h / 29);
					break;
				}
			} else if (num == 2) {
				switch (cible) {
				case 0:
					pan.xo[num] += 5 * (pan.w / 29);
					pan.yo[num] -= (pan.h / 29);
					break;
				case 1:
					pan.xo[num] += 2 * (pan.w / 29);
					pan.yo[num] -= (pan.h / 29);
					break;
				case 2:
					pan.yo[num] -= (pan.h / 29);
					break;
				}
			}
			pause(200);
			pan.repaint();
		}
		thread[cible + 3].stop();
		switch (cible + 3) {
		case 3:
			pan.enum1 = pan.field2.get(cible).hurt + "";
			pause(300);
			break;
		case 4:
			pan.enum2 = pan.field2.get(cible).hurt + "";
			pause(300);
			break;
		case 5:
			pan.enum3 = pan.field2.get(cible).hurt + "";
			pause(300);
			break;
		}
		pan.repaint();
		thread[cible + 3] = new Thread(new Anim(cible + 3));
		thread[cible + 3].start();
		pan.xo[num] = xp;
		pan.yo[num] = yp;
		thread[num] = new Thread(new Anim(num));
		thread[num].start();
	}

	boolean done = false;

	class Attack implements Runnable {
		int o;

		public Attack(int o) {
			this.o = o;
		}

		public void run() {

			if (d[o][0].equals("Thor")) {
				animate(155, tr(d[o][1]), d[o][0], false);
			} else
				animate(1, tr(d[o][1]), d[o][0], false);

			for (int i = 0; i < pan.field2.size(); i++) {
				thread[i + 3].stop();
				switch (i + 3) {
				case 3:
					pan.enum1 = pan.field2.get(i).hurt + "";
					pause(800);
					break;
				case 4:
					pan.enum2 = pan.field2.get(i).hurt + "";
					pause(800);
					break;
				case 5:
					pan.enum3 = pan.field2.get(i).hurt + "";
					pause(800);
					break;
				}
				pause(300);
				pan.repaint();
				thread[i + 3] = new Thread(new Anim(i + 3));
				thread[i + 3].start();

				pan.field2.get(i).pva = pan.field2.get(i).pva
						- (tr(d[o][1]) - i * 10);

				if (pan.field2.get(i).pva <= 0) {
					switch (i + 3) {
					case 3:
						pan.monsta[3] = false;
						break;
					case 4:
						pan.monsta[4] = false;
						break;
					case 5:
						pan.monsta[5] = false;
						break;
					}
				}
			}

		}
	}

	Sequencer sequencer;

	class AttackS implements Runnable {
		int a;
		int b;

		public AttackS(int a, int b) {
			this.a = a;
			this.b = b;
		}

		public void run() {
			attacksimple(a, b);
			pan.field2.get(b).pva = pan.field2.get(b).pva - 30
					* (int) (Math.random() * 3 + 1);

			if (pan.field2.get(b).pva <= 0) {
				switch (b + 3) {
				case 3:
					pan.monsta[3] = false;
					break;
				case 4:
					pan.monsta[4] = false;
					break;
				case 5:
					pan.monsta[5] = false;
					break;
				}
			}
			pan.repaint();
		}
	}

	public void music(URL i) {
		try {
			Sequence sequence = MidiSystem.getSequence(new File(i.getFile()));
			sequencer = MidiSystem.getSequencer();
			sequencer.open();
			sequencer.setSequence(sequence);
			sequencer.setLoopCount(100);
			sequencer.start();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	boolean[][] utilise = new boolean[3][3];

	public static void main(String[] args) {
		new Fenetre();
	}
}
