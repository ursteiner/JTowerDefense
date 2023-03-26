package com.github.ursteiner;

import com.github.ursteiner.model.*;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class JTowerDefense extends JPanel implements Runnable, MouseListener, MouseMotionListener, KeyListener {

	private static final long serialVersionUID = 5661273172996533040L;

	public static final String VERSION = "1.10";

	private final int TOWER_WIDTH = 20;
	private final Point MOUSE_OFFSET = new Point(3, 25);
	private final Point UPGRADE_BUTTON = new Point(370, 350);
	private final Point RADIUS_BUTTON = new Point(400, 350);
	private final Point MENU_BUTTON = new Point(430, 350);
	private final Point FASTER_BUTTON = new Point(460, 350);
	private final Point SLOWER_BUTTON = new Point(460, 360);

	private final Point NEW_BUTTON = new Point(360, 45);
	private final Point BACK_BUTTON = new Point(360, 65);
	private final Point BLOOD_BUTTON = new Point(360, 85);
	private final Point HELP_BUTTON = new Point(360, 105);
	private final Point EXIT_BUTTON = new Point(360, 125);

	private Point selectedMenuButton;
	private final Point roundedMousePos = new Point();

	private final List<Tower> availableTowers = new ArrayList<>();
	private final List<Tower> buildTowers = new ArrayList<>();
	private final List<Blood> killList = new ArrayList<>();

	private int speed;
	private final int MIN_SPEED = 5;
	private final int MAX_SPEED = 45;

	private final List<Attacker> attackers = new ArrayList<>();
	private final List<Point> waypoints = new ArrayList<>();
	private final List<Point> sea = new ArrayList<>();

	private int selectedBuildTower;
	private int selectedTower;
	private int attackersEnergy;
	private int level;
	private int money;
	private int tmpMoney;
	private int score;
	private int tmpScore;
	private int fails;

	private Hint hint;

	private boolean gameOver = false;
	private boolean showRadius = false;
	private boolean pause = false;
	private boolean inMenu;
	private boolean gameStarted;
	private boolean bloodMode = false;

	private final Thread thread;

	private final TowerDefenseGraphics graphicsDriver = new TowerDefenseGraphics();

	public JTowerDefense() {

		waypoints.add(new Point(0, 40));
		waypoints.add(new Point(20, 40));
		waypoints.add(new Point(40, 40));
		waypoints.add(new Point(60, 40));
		waypoints.add(new Point(80, 40));
		waypoints.add(new Point(100, 40));
		waypoints.add(new Point(120, 40));
		waypoints.add(new Point(140, 40));
		waypoints.add(new Point(160, 40));
		waypoints.add(new Point(180, 40));
		waypoints.add(new Point(200, 40));
		waypoints.add(new Point(220, 40));
		waypoints.add(new Point(240, 40));
		waypoints.add(new Point(260, 40));
		waypoints.add(new Point(280, 40));
		waypoints.add(new Point(300, 40));
		waypoints.add(new Point(300, 60));
		waypoints.add(new Point(300, 80));
		waypoints.add(new Point(300, 100));
		waypoints.add(new Point(300, 120));
		waypoints.add(new Point(300, 140));
		waypoints.add(new Point(300, 160));
		waypoints.add(new Point(280, 160));
		waypoints.add(new Point(260, 160));
		waypoints.add(new Point(240, 160));
		waypoints.add(new Point(220, 160));
		waypoints.add(new Point(200, 160));
		waypoints.add(new Point(180, 160));
		waypoints.add(new Point(180, 140));
		waypoints.add(new Point(180, 120));
		waypoints.add(new Point(160, 120));
		waypoints.add(new Point(140, 120));
		waypoints.add(new Point(120, 120));
		waypoints.add(new Point(100, 120));
		waypoints.add(new Point(100, 140));
		waypoints.add(new Point(100, 160));
		waypoints.add(new Point(100, 180));
		waypoints.add(new Point(100, 200));
		waypoints.add(new Point(100, 220));
		waypoints.add(new Point(100, 240));
		waypoints.add(new Point(100, 260));
		waypoints.add(new Point(120, 260));
		waypoints.add(new Point(140, 260));
		waypoints.add(new Point(160, 260));
		waypoints.add(new Point(180, 260));
		waypoints.add(new Point(200, 260));
		waypoints.add(new Point(220, 260));
		waypoints.add(new Point(240, 260));
		waypoints.add(new Point(260, 260));
		waypoints.add(new Point(280, 260));
		waypoints.add(new Point(300, 260));
		waypoints.add(new Point(320, 260));
		waypoints.add(new Point(340, 260));
		waypoints.add(new Point(360, 260));
		waypoints.add(new Point(380, 260));
		waypoints.add(new Point(400, 260));
		waypoints.add(new Point(420, 260));
		waypoints.add(new Point(440, 260));
		waypoints.add(new Point(460, 260));
		waypoints.add(new Point(480, 260));
		waypoints.add(new Point(500, 260));

		sea.add(new Point(20, 150));
		sea.add(new Point(20, 170));
		sea.add(new Point(40, 150));
		sea.add(new Point(40, 170));

		sea.add(new Point(280, 220));
		sea.add(new Point(300, 220));
		sea.add(new Point(300, 200));
		sea.add(new Point(320, 220));

		inMenu = true;
		gameStarted = false;
		initGame();

		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		graphicsDriver.paintGameField(g, waypoints, killList, sea, bloodMode);

		graphicsDriver.paintAttackers(g, attackers);

		// paint build towers
		int tIndex = 0;
		for (Tower tower : buildTowers) {
			// draw radius of selected tower
			if (tIndex == selectedTower || showRadius) {
				graphicsDriver.paintTowerRadius(g, tower);
			}
			tIndex++;

			graphicsDriver.paintTower(g, tower, money, true, level, false);
		}

		if (!inMenu) {

			// paint available towers
			int index = 0;
			for (Tower tower : availableTowers) {
				if (index == selectedBuildTower) {
					graphicsDriver.paintTowerSelection(g, tower);
				}
				graphicsDriver.paintTower(g, tower, money, false, level, true);
				index++;
			}

			// paint upgrade button
			if (selectedTower != -1 && TowerDefHelper.canUpgrade(buildTowers.get(selectedTower), money, level)) {
				graphicsDriver.paintButton(g, UPGRADE_BUTTON, true);
			} else {
				graphicsDriver.paintButton(g, UPGRADE_BUTTON, false);
			}

			graphicsDriver.paintRadiusButton(g, RADIUS_BUTTON, showRadius);

			graphicsDriver.paintPauseButton(g, MENU_BUTTON, pause);
			graphicsDriver.paintFasterButton(g, FASTER_BUTTON);
			graphicsDriver.paintSlowerButton(g, SLOWER_BUTTON);
			graphicsDriver.paintSpeedBar(g, FASTER_BUTTON, speed, MIN_SPEED, MAX_SPEED);

			graphicsDriver.paintFails(g, fails);

			if (!"".equals(hint.getText())) {
				graphicsDriver.paintHint(g, hint);
			}

			// paint Tower to build
			if (selectedBuildTower != -1) {
				Tower tmp = new Tower(roundedMousePos, availableTowers.get(selectedBuildTower).getRadius(), 0, availableTowers.get(selectedBuildTower).getType());
				graphicsDriver.paintTower(g, tmp, money, true, level, false);
				if (!availableTowers.get(selectedBuildTower).getType().equals(Type.DETECTOR)) {
					graphicsDriver.paintTowerRadius(g, tmp);
				}
			}
		} else {
			graphicsDriver.paintMenu(g, gameOver);
			graphicsDriver.paintMenuButton(g, NEW_BUTTON, "New Game", selectedMenuButton);
			graphicsDriver.paintMenuButton(g, BACK_BUTTON, "Return", selectedMenuButton);
			graphicsDriver.paintMenuButton(g, EXIT_BUTTON, "Exit", selectedMenuButton);
			graphicsDriver.paintMenuButton(g, HELP_BUTTON, "About", selectedMenuButton);
			graphicsDriver.paintMenuButton(g, BLOOD_BUTTON, "Blood is " + (bloodMode ? "on" : "off"), selectedMenuButton);
		}

		if (!inMenu || gameOver) {
			// paint status
			graphicsDriver.paintStatus(g, level, tmpMoney, tmpScore, killList.size());
		}
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("TowerDefense " + VERSION);
		JTowerDefense td = new JTowerDefense();
		frame.setSize(500, 430);

		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (d.width - frame.getSize().width) / 2;
		int y = (d.height - frame.getSize().height) / 2;
		frame.setLocation(x, y);
		frame.addMouseMotionListener(td);
		frame.addKeyListener(td);

		frame.setIconImage(new ImageIcon(JTowerDefense.class.getResource("td.png")).getImage());
		frame.add(td);
		frame.addMouseListener(td);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);

	}

	@Override
	public void mouseClicked(MouseEvent e) {

		int mx = e.getX() - MOUSE_OFFSET.x;
		int my = e.getY() - MOUSE_OFFSET.y;

		int roundMx = mx - (mx % TOWER_WIDTH);
		int roundMy = my - (my % TOWER_WIDTH);
		Point mousePoint = new Point(mx, my);

		if (MouseEvent.BUTTON1 == e.getButton()) {
			if (!gameOver && !pause) {
				int index = 0;
				boolean towerSelected = false;
				// was a tower selected in build menu
				for (Tower tower : availableTowers) {
					if (TowerDefHelper.checkCollision(mousePoint, tower.getPos(), TOWER_WIDTH, TOWER_WIDTH)) {
						selectedBuildTower = index;
						towerSelected = true;
						break;
					}
					index++;
				}

				Tower towerToBuild = null;
				if (selectedBuildTower != -1) {
					towerToBuild = availableTowers.get(selectedBuildTower);
				}

				// build tower
				if (isPositionFree(new Point(roundMx, roundMy)) && selectedBuildTower != -1 && !towerSelected && money >= towerToBuild.getType().getCost() && selectedTower == -1 && roundMy < 340) {
					buildTowers.add(new Tower(new Point(roundMx, roundMy), 50, 0, towerToBuild.getType()));
					repaint();
					money -= towerToBuild.getType().getCost();
					selectedTower = buildTowers.size() - 1;
					selectedBuildTower = -1;
				}

				// Button upgrade
				if (TowerDefHelper.checkCollision(mousePoint, UPGRADE_BUTTON, TOWER_WIDTH, TOWER_WIDTH)) {
					doUpgrade();
				}

				// Button radius showRadius
				if (TowerDefHelper.checkCollision(mousePoint, RADIUS_BUTTON, TOWER_WIDTH, TOWER_WIDTH)) {
					showRadius = !showRadius;
				}

				int tIndex = 0;
				selectedTower = -1;
				for (Tower tower : buildTowers) {
					if (TowerDefHelper.checkCollision(mousePoint, tower.getPos(), TOWER_WIDTH, TOWER_WIDTH)) {
						selectedTower = tIndex;
						break;
					}
					tIndex++;
				}
			}

			// Button Menu
			if (TowerDefHelper.checkCollision(mousePoint, MENU_BUTTON, TOWER_WIDTH, TOWER_WIDTH)) {
				pause = true;
				inMenu = true;
			}

			if (inMenu) {
				// New game Button
				if (TowerDefHelper.checkCollision(mousePoint, NEW_BUTTON, 100, 15)) {
					inMenu = false;
					initGame();
					gameStarted = true;
				}

				// Button Back
				if (TowerDefHelper.checkCollision(mousePoint, BACK_BUTTON, 100, 15)) {
					if (gameStarted && !gameOver) {
						pause = false;
						inMenu = false;
					}
				}

				// Button Help
				if (TowerDefHelper.checkCollision(mousePoint, HELP_BUTTON, 100, 15)) {
					Desktop desktop = Desktop.getDesktop();

					if (desktop.isSupported(java.awt.Desktop.Action.BROWSE)) {
						URL url;
						try {
							url = new URL("https://github.com/ursteiner/JTowerDefense");
							desktop.browse(url.toURI());
						} catch (Exception ex) {
							ex.printStackTrace();
						}

					}
				}

				// Button blood
				if (TowerDefHelper.checkCollision(mousePoint, BLOOD_BUTTON, 100, 15)) {
					bloodMode = !bloodMode;
				}

				// Button Exit
				if (TowerDefHelper.checkCollision(mousePoint, EXIT_BUTTON, 100, 15)) {
					System.exit(0);
				}
			}

			// Button Faster
			if (TowerDefHelper.checkCollision(mousePoint, FASTER_BUTTON, 10, 10)) {
				if (speed > MIN_SPEED) {
					speed -= 5;
				}
			}

			// Button Slower
			if (TowerDefHelper.checkCollision(mousePoint, SLOWER_BUTTON, 10, 10)) {
				if (speed <= MAX_SPEED - 5) {
					speed += 5;
				}
			}

		} else if (MouseEvent.BUTTON3 == e.getButton() && !gameOver && !pause) {

			selectedBuildTower = -1;

			int index = 0;
			selectedTower = -1;
			for (Tower tower : buildTowers) {
				if (TowerDefHelper.checkCollision(mousePoint, tower.getPos(), TOWER_WIDTH, TOWER_WIDTH)) {
					selectedTower = index;
					break;
				}
				index++;
			}

			// remove tower
			if (selectedTower != -1) {
				if (buildTowers.get(selectedTower).getLevel() == 1) {
					money += 50;
				} else if (buildTowers.get(selectedTower).getLevel() == 2) {
					money += 130;
				} else if (buildTowers.get(selectedTower).getLevel() == 3) {
					money += 200;
				}
				buildTowers.remove(selectedTower);
				selectedTower = -1;
				repaint();
			}
		}

	}

	private boolean isPositionFree(Point p) {
		// no tower at position
		for (Tower tower : buildTowers) {
			if (tower.getPos().x == p.x && tower.getPos().y == p.y) {
				return false;
			}
		}
		// no way point at position
		for (Point wp : waypoints) {
			if (wp.x == p.x && wp.y == p.y) {
				return false;
			}
		}

		return true;
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	private boolean allAttackersDead() {
		boolean allDead = true;
		for (Attacker attacker : attackers) {
			if (!attacker.isDead()) {
				allDead = false;
				break;
			}
		}
		return allDead;
	}

	private boolean allAttackersMarchedThrough() {
		boolean allThrough = true;
		for (Attacker attacker : attackers) {
			if (!attacker.isMarchedThrough() && !attacker.isDead()) {
				allThrough = false;
				break;
			}
		}
		return allThrough;
	}

	@Override
	public void run() {

		while (true) {

			if (!gameOver && !pause) {

				if (tmpMoney < money) {
					tmpMoney += 5;
				} else if (tmpMoney > money) {
					tmpMoney -= 5;
				}

				countTmpScore();

				if (allAttackersDead()) {
					addAttackers();
				}

				if (fails <= 0) {
					gameOver = true;
					inMenu = true;
					tmpScore = score;
				}

				if (allAttackersMarchedThrough()) {
					addAttackers();
				}

				for (Blood b : killList) {
					b.dryBlood();
				}

				// move attackers
				for (Attacker attacker : attackers) {
					if (attacker.isInvisible()) {
						attacker.setDetected(false);
					}

					if (attacker.getWaypointIndex() >= waypoints.size() && !attacker.isMarchedThrough()) {
						attacker.setMarchedThrough(true);

						if (gameStarted) {
							fails--;
						}

					} else if (!attacker.isDead() && attacker.getWaypointIndex() < waypoints.size()) {
						Point targetPoint = waypoints.get(attacker.getWaypointIndex());

						if (attacker.getPos().y < targetPoint.y) {
							attacker.getPos().y += 1;
							attacker.setVx(0);
							attacker.setVy(1);
						} else if (attacker.getPos().y > targetPoint.y) {
							attacker.getPos().y -= 1;
							attacker.setVx(0);
							attacker.setVy(-1);
						} else if (attacker.getPos().x < targetPoint.x) {
							attacker.getPos().x += 1;
							attacker.setVx(1);
							attacker.setVy(0);
						} else if (attacker.getPos().x > targetPoint.x) {
							attacker.getPos().x -= 1;
							attacker.setVx(-1);
							attacker.setVy(0);
						}

						if (attacker.getPos().x == targetPoint.x && attacker.getPos().y == targetPoint.y) {
							attacker.setWaypointIndex(attacker.getWaypointIndex() + 1);
						}
					}
				}

				// detect attackers with by detector towers
				for (Tower tower : buildTowers) {
					for (Attacker attacker : attackers) {
						if (Type.DETECTOR.equals(tower.getType()) && TowerDefHelper.getDistance(tower.getPos(), attacker.getPos()) < tower.getRadius()) {
							// Detector tower has detected an attacker
							attacker.setDetected(true);
						}
					}
				}

				// shoot towers
				for (Tower tower : buildTowers) {

					handleTowerShots(tower);

					// upgrade counter
					if (tower.isInUpgrade()) {
						tower.doUpgrade();
					}

					if (tower.getCanonColor() > 10) {
						tower.setCanonColor(tower.getCanonColor() - 10);
					}

					boolean towerAnimated = false;

					for (Attacker attacker : attackers) {

						if (!attacker.isDead() && attacker.isDetected() && TowerDefHelper.getDistance(tower.getPos(), attacker.getPos()) < tower.getRadius() && !tower.isInUpgrade()) {

							if (tower.getType().equals(attacker.getType()) || tower.getType().equals(Type.MIXED)) {
								// move tower canon
								tower.setCanonEndpoint(TowerDefHelper.rotateCannon(attacker.getPos(), tower.getPos(), tower.getCanonLength()));
								tower.setLastAttackerPos(new Point(attacker.getPos().x, attacker.getPos().y));
								towerAnimated = true;

								if (tower.shoot()) {
									attacker.hit(tower.getStrength());
									tower.setCanonLength(1);
									score += tower.getStrength();
									if (attacker.isDead()) {
										money += 45;
										killList.add(new Blood(attacker.getPos()));
									}
									tower.setShot(new Shot(attacker.getPos(), bloodMode));

								}
								break;
							}
						}
					}

					if (!towerAnimated) {
						tower.setCanonEndpoint(TowerDefHelper.rotateCannon(tower.getLastAttackerPos(), tower.getPos(), tower.getCanonLength()));
					}
				}
			}

			repaint();

			try {
				Thread.sleep(speed);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void countTmpScore() {
		if (tmpScore < score) {
			if (score > tmpScore + 10) {
				tmpScore += 5;
			} else {
				tmpScore++;
			}
		}
	}

	private void handleTowerShots(Tower t) {
		boolean removeShot = false;
		Shot shot = t.getShots();

		if (t.getCanonLength() < Tower.MAX_CANNON_LENGTH) {
			t.setCanonLength(t.getCanonLength() + 1);
		}

		if (shot != null) {

			if (shot.getOpacity() == 0.0f) {
				removeShot = true;
			} else {
				shot.fadeShot();
			}

			if (removeShot) {
				t.setShot(null);
			}
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {

		int x = e.getX() - MOUSE_OFFSET.x;
		int y = e.getY() - MOUSE_OFFSET.y;
		int roundMx = x - (x % TOWER_WIDTH);
		int roundMy = y - (y % TOWER_WIDTH);
		roundedMousePos.x = roundMx;
		roundedMousePos.y = roundMy;

		selectedMenuButton = null;
		// set info texts;

		hint = new Hint("", new Point(0, 0));

		if (TowerDefHelper.checkCollision(new Point(x, y), UPGRADE_BUTTON, TOWER_WIDTH, TOWER_WIDTH)) {
			// Button upgrade
			hint = new Hint("upgrade", UPGRADE_BUTTON);
		} else if (TowerDefHelper.checkCollision(new Point(x, y), RADIUS_BUTTON, TOWER_WIDTH, TOWER_WIDTH)) {
			// Button radius
			hint = new Hint("show radius", RADIUS_BUTTON);
		} else if (TowerDefHelper.checkCollision(new Point(x, y), NEW_BUTTON, 100, 15)) {
			// Button new game
			selectedMenuButton = NEW_BUTTON;
		} else if (TowerDefHelper.checkCollision(new Point(x, y), BACK_BUTTON, 100, 15)) {
			// Button back to game
			if (gameStarted && !gameOver) {
				selectedMenuButton = BACK_BUTTON;
			}
		} else if (TowerDefHelper.checkCollision(new Point(x, y), HELP_BUTTON, 100, 15)) {
			// Button Help
			selectedMenuButton = HELP_BUTTON;
		} else if (TowerDefHelper.checkCollision(new Point(x, y), BLOOD_BUTTON, 100, 15)) {
			// Button Help
			selectedMenuButton = BLOOD_BUTTON;
		} else if (TowerDefHelper.checkCollision(new Point(x, y), EXIT_BUTTON, 100, 15)) {
			// Button exit
			selectedMenuButton = EXIT_BUTTON;
		} else if (TowerDefHelper.checkCollision(new Point(x, y), MENU_BUTTON, TOWER_WIDTH, TOWER_WIDTH)) {
			hint = new Hint("   menu", new Point(MENU_BUTTON.x - 2, MENU_BUTTON.y));
		} else if (TowerDefHelper.checkCollision(new Point(x, y), FASTER_BUTTON, TOWER_WIDTH, TOWER_WIDTH)) {
			hint = new Hint("   speed", new Point(FASTER_BUTTON.x - 5, FASTER_BUTTON.y));
		} else if (TowerDefHelper.checkCollision(new Point(x, y), new Point(360, 350), 5, 20)) {
			hint = new Hint("life " + fails + "/5", new Point(360, 350));
		} else {
			for (Tower tower : availableTowers) {
				if (TowerDefHelper.checkCollision(new Point(x, y), tower.getPos(), TOWER_WIDTH, TOWER_WIDTH)) {

					Type type = tower.getType();
					hint = new Hint("  " + type.getCost() + "$ " + type.getDescription(), tower.getPos());
					break;
				}
			}
		}
	}

	private void initGame() {
		availableTowers.clear();
		attackers.clear();
		killList.clear();

		availableTowers.add(new Tower(new Point(10, 350), 50, 5, Type.BLUE));
		availableTowers.add(new Tower(new Point(35, 350), 50, 5, Type.MIXED));
		availableTowers.add(new Tower(new Point(60, 350), 50, 5, Type.BROWN));
		availableTowers.add(new Tower(new Point(85, 350), 50, 5, Type.DETECTOR));

		selectedBuildTower = -1;
		selectedTower = -1;
		attackersEnergy = 4;
		level = 1;
		money = 150;
		tmpMoney = 0;
		fails = 5;
		speed = 35;

		attackers.add(new Attacker(new Point(-20, 40), attackersEnergy, Type.BLUE, false));
		attackers.add(new Attacker(new Point(-60, 40), attackersEnergy, Type.BLUE, false));
		attackers.add(new Attacker(new Point(-100, 40), attackersEnergy, Type.BLUE, false));

		gameOver = false;
		showRadius = false;
		pause = false;

		score = 0;

		buildTowers.clear();

		hint = new Hint("", new Point(0, 0));
	}

	private void addAttackers() {
		// init new Round
		attackers.clear();
		level++;

		if (level >= 20) {
			attackers.add(new Attacker(new Point(-20, 40), attackersEnergy, Type.BROWN, true));
			attackers.add(new Attacker(new Point(-45, 40), attackersEnergy, Type.BLUE, true));
			attackers.add(new Attacker(new Point(-70, 40), attackersEnergy, Type.BROWN, true));
			attackers.add(new Attacker(new Point(-95, 40), attackersEnergy, Type.BLUE, true));
			attackers.add(new Attacker(new Point(-120, 40), attackersEnergy, Type.BROWN, true));
			attackers.add(new Attacker(new Point(-145, 40), attackersEnergy, Type.BLUE, true));
			attackers.add(new Attacker(new Point(-170, 40), attackersEnergy, Type.BROWN, true));
			attackers.add(new Attacker(new Point(-195, 40), attackersEnergy, Type.BLUE, true));
			attackers.add(new Attacker(new Point(-120, 40), attackersEnergy, Type.BROWN, true));
			attackersEnergy += 25;
		} else if (level >= 15) {
			attackers.add(new Attacker(new Point(-20, 40), attackersEnergy, Type.BROWN, true));
			attackers.add(new Attacker(new Point(-45, 40), attackersEnergy, Type.BLUE, false));
			attackers.add(new Attacker(new Point(-70, 40), attackersEnergy, Type.BROWN, false));
			attackers.add(new Attacker(new Point(-95, 40), attackersEnergy, Type.BLUE, true));
			attackers.add(new Attacker(new Point(-120, 40), attackersEnergy, Type.BROWN, false));
			attackers.add(new Attacker(new Point(-145, 40), attackersEnergy, Type.BLUE, false));
			attackers.add(new Attacker(new Point(-170, 40), attackersEnergy, Type.BROWN, true));
			attackersEnergy += 20;
		} else if (level >= 10) {
			attackers.add(new Attacker(new Point(-20, 40), attackersEnergy, Type.BROWN, false));
			attackers.add(new Attacker(new Point(-45, 40), attackersEnergy, Type.BLUE, false));
			attackers.add(new Attacker(new Point(-70, 40), attackersEnergy, Type.BROWN, false));
			attackers.add(new Attacker(new Point(-95, 40), attackersEnergy, Type.BLUE, false));
			attackers.add(new Attacker(new Point(-120, 40), attackersEnergy, Type.BROWN, false));
			attackersEnergy += 15;
		} else if (level >= 5) {
			attackers.add(new Attacker(new Point(-20, 40), attackersEnergy, Type.BLUE, false));
			attackers.add(new Attacker(new Point(-60, 40), attackersEnergy, Type.BROWN, false));
			attackers.add(new Attacker(new Point(-100, 40), attackersEnergy, Type.BLUE, false));
			attackersEnergy += 10;
		} else {
			attackers.add(new Attacker(new Point(-20, 40), attackersEnergy, Type.BLUE, false));
			attackers.add(new Attacker(new Point(-60, 40), attackersEnergy, Type.BLUE, false));
			attackers.add(new Attacker(new Point(-100, 40), attackersEnergy, Type.BLUE, false));
			attackersEnergy += 5;
		}
	}

	private void doUpgrade() {
		if (selectedTower != -1) {
			if (money >= 100 && TowerDefHelper.canUpgrade(buildTowers.get(selectedTower), money, level)) {
				buildTowers.get(selectedTower).upgrade();
				money -= 100;
				repaint();
			} else if (money >= 250 && TowerDefHelper.canUpgrade(buildTowers.get(selectedTower), money, level)) {
				buildTowers.get(selectedTower).upgrade();
				money -= 300;
				repaint();
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();

		switch (keyCode) {
		case KeyEvent.VK_SUBTRACT:
			if (speed <= MAX_SPEED - 5) {
				speed += 5;
			}
			break;
		case KeyEvent.VK_ADD:
			if (speed > MIN_SPEED) {
				speed -= 5;
			}
			break;
		case KeyEvent.VK_ESCAPE:
			pause = true;
			inMenu = true;
			break;
		case KeyEvent.VK_1:
			selectedTower = -1;
			selectedBuildTower = 0;
			break;
		case KeyEvent.VK_2:
			selectedTower = -1;
			selectedBuildTower = 1;
			break;
		case KeyEvent.VK_3:
			selectedTower = -1;
			selectedBuildTower = 2;
			break;
		case KeyEvent.VK_4:
			selectedTower = -1;
			selectedBuildTower = 3;
			break;
		case KeyEvent.VK_U:
			doUpgrade();
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
