package com.github.ursteiner.graphics;

import com.github.ursteiner.TowerDefHelper;
import com.github.ursteiner.model.*;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.ImageIcon;

public class TowerDefenseGraphics {

	private final int TOWER_WIDTH = 20;
	private final int TOWER_HEIGHT = 20;
	private final BasicStroke LINE_WIDTH_3 = new BasicStroke(3);
	private static final BasicStroke LINE_WIDTH_2 = new BasicStroke(2);
	private final BasicStroke LINE_WIDTH_1 = new BasicStroke(1);
	public static final Font FONT_GAME_OVER = new Font("SansSerif", Font.BOLD, 25);
	private static String FONT_NAME = "SansSerif";
	public static final Font FONT_MENU = new Font(FONT_NAME, Font.BOLD, 14);
	private static final Font BASIC_FONT = new Font(FONT_NAME, Font.PLAIN, 10);
	private final Image GRASS = new ImageIcon(getClass().getResource("grass.png")).getImage();
	private final Image WAY = new ImageIcon(getClass().getResource("way.png")).getImage();
	private final Image TOWER_BLUE = new ImageIcon(getClass().getResource("towerblue.png")).getImage();
	private final Image TOWER_BROWN = new ImageIcon(getClass().getResource("towerbrown.png")).getImage();
	private final Image TOWER_MIXED = new ImageIcon(getClass().getResource("towermixed.png")).getImage();
	private final Image SEA = new ImageIcon(getClass().getResource("sea.png")).getImage();
	private final Image DETECTOR = new ImageIcon(getClass().getResource("detector.png")).getImage();

	private final BufferedImage background = new BufferedImage(500, 340, BufferedImage.TYPE_INT_RGB);

	private boolean backgroundGenerated = false;

	private void paintShots(Graphics g, Tower tower) {

		Shot shot = tower.getShots();
		if (shot != null) {
			Graphics2D g2d = (Graphics2D) g;
			g2d.setStroke(new BasicStroke(tower.getLevel()));
			g.setColor(new Color(0.9f, 1.0f, 0.3f, shot.getOpacity()));
			g.drawLine(tower.getPos().x + TOWER_WIDTH / 2, tower.getPos().y + TOWER_WIDTH / 2, shot.getP2().x + TOWER_WIDTH / 2, shot.getP2().y + TOWER_WIDTH / 2);

			// attacker hit animation

			if (shot.isBloodMode()) {
				for (Particle p : shot.getParticles()) {
					g.setColor(new Color(p.getIntensity(), 0, 0));
					g.fillRect(p.getX() + shot.getP2().x + 10, shot.getP2().y - p.getY() + 10, 3, 3);
					TowerDefHelper.calculateNewParticlePos(p);
				}
			}
		}
	}

	public static void paintMenuButton(Graphics g, Point b, String text, boolean isMouseOver) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(LINE_WIDTH_2);
		g.setColor(Color.LIGHT_GRAY);
		g.drawRect(b.x, b.y, 105, 15);

		if (isMouseOver) {
			g.setColor(Color.WHITE);
		} else {
			g.setColor(Color.LIGHT_GRAY);
		}
		g.setFont(FONT_MENU);
		g.drawString(text, b.x + 15, b.y + 12);
	}

	public void paintTowerSelection(Graphics g, Tower tower) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(LINE_WIDTH_2);
		g.setColor(Color.RED);
		g.drawRect(tower.getPos().x - 1, tower.getPos().y - 1, TOWER_WIDTH + 2, TOWER_WIDTH + 2);
	}

	public void paintStatus(Graphics g, GameData gameData, int kills) {
		g.setColor(Color.DARK_GRAY);
		g.setFont(BASIC_FONT);
		g.drawString("lvl: " + gameData.getLevel(), 110, 365);
		g.drawString("" + gameData.getMoney() + " $", 150, 365);
		g.drawString("score: " + gameData.getScore(), 210, 365);
		g.drawString("kills: " + kills, 300, 365);
	}

	public static void paintHint(Graphics g, Hint h) {
		g.setColor(Color.DARK_GRAY);
		g.setFont(BASIC_FONT);
		g.drawString(h.getText(), h.getP().x - 10, h.getP().y + 35);
	}

	public void paintFasterButton(Graphics g, Point button) {
		g.setColor(Color.BLACK);
		g.fillRect(button.x, button.y, 10, 9);

		g.setColor(Color.WHITE);

		g.fillRect(button.x + 1, button.y + 4, 8, 2);
		g.fillRect(button.x + 4, button.y + 1, 2, 8);
	}

	public void paintSlowerButton(Graphics g, Point button) {
		g.setColor(Color.BLACK);
		g.fillRect(button.x, button.y, 10, 10);

		g.setColor(Color.WHITE);

		g.fillRect(button.x + 1, button.y + 4, 8, 2);
	}

	public void paintSpeedBar(Graphics g, Point p, int speed, int min, int max) {

		double factor = 20d / max;

		g.setColor(Color.DARK_GRAY);
		g.fillRect(p.x + 11, p.y, 5, 20);
		g.setColor(new Color(77, 167, 232));
		g.fillRect(p.x + 11, -2 + (int) (p.y + (speed) * factor), 5, 20 - (int) ((speed - min) * factor));
	}

	public void paintTowerRadius(Graphics g, Tower tower) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(LINE_WIDTH_2);
		g.setColor(new Color(0.5f, 0.5f, 0.5f, 0.4f));
		Point towerCenter = new Point(tower.getPos().x + TOWER_WIDTH / 2, tower.getPos().y + TOWER_WIDTH / 2);
		int d = tower.getRadius() * 2;
		g.fillOval(towerCenter.x - tower.getRadius(), towerCenter.y - tower.getRadius(), d, d);
	}

	public void paintFails(Graphics g, int fails) {
		g.setColor(Color.RED);
		g.fillRect(360, 350, 5, 20);
		g.setColor(Color.GREEN);
		g.fillRect(360, 350 + (4 * (5 - fails)), 5, 20 - (4 * (5 - fails)));
	}

	public void paintGameField(Graphics g, GameMap gameMap, GameData gameData) {
		// background
		if (!backgroundGenerated) {
			// background is only generated once to a buffered Image
			for (int x = 0; x < 500; x += 20) {
				for (int y = 0; y < 340; y += 20) {
					background.getGraphics().drawImage(GRASS, x, y, null);
				}
			}

			for (Point s : gameMap.getSea()) {
				background.getGraphics().drawImage(SEA, s.x, s.y, null);
			}

			// way
			for (Point p : gameMap.getWaypoints()) {
				background.getGraphics().drawImage(WAY, p.x, p.y, null);
			}

		}
		g.drawImage(background, 0, 0, null);

		if (gameData.isBloodMode()) {
			// killed attackers
			for (Blood b : gameData.getKillList()) {
				g.setColor(new Color(b.getBloodColor(), 0, 0));
				g.fillRect(b.getPos().x + 6, b.getPos().y + 2, 4, 4);
				g.fillRect(b.getPos().x + 4, b.getPos().y + 4 + 10, 3, 3);
				g.fillRect(b.getPos().x + 12, b.getPos().y + 11, 3, 2);
			}
		}

		backgroundGenerated = true;
	}

	public void paintTower(Graphics g, Tower tower, GameData gameData, boolean showupgrade, boolean drawAvailableTower) {

		// paint towers
		if (drawAvailableTower) {
			if (gameData.getMoney() >= tower.getType().getCost()) {
				((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
			} else {
				((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
			}
		}

		if (tower.getType().equals(Type.BLUE)) {
			g.drawImage(TOWER_BLUE, tower.getPos().x, tower.getPos().y, null);
		} else if (tower.getType().equals(Type.BROWN)) {
			g.drawImage(TOWER_BROWN, tower.getPos().x, tower.getPos().y, null);
		} else if (tower.getType().equals(Type.MIXED)) {
			g.drawImage(TOWER_MIXED, tower.getPos().x, tower.getPos().y, null);
		} else {
			g.drawImage(DETECTOR, tower.getPos().x, tower.getPos().y, null);
		}

		// paint upgrade progress
		if (tower.isInUpgrade()) {
			g.setColor(Color.YELLOW);
			int barWidth = (int) ((20d / (double) tower.getType().getUpgradeTime()) * tower.getUpgradeProgress());
			g.fillRect(tower.getPos().x, tower.getPos().y - 7, barWidth, 3);
		}

		// paint shots
		paintShots(g, tower);

		// paint canon red after fire
		if (!tower.getType().equals(Type.DETECTOR)) {
			g.setColor(new Color(tower.getCanonColor(), 0, 0));
			Graphics2D g2d = (Graphics2D) g;
			if (tower.getLevel() == 1) {
				g2d.setStroke(LINE_WIDTH_1);
			} else if (tower.getLevel() == 2) {
				g2d.setStroke(LINE_WIDTH_2);
			} else if (tower.getLevel() == 3) {
				g2d.setStroke(LINE_WIDTH_3);
			}

			g.drawLine(tower.getPos().x + TOWER_WIDTH / 2, tower.getPos().y + TOWER_WIDTH / 2, tower.getCanonEndpoint().x, tower.getCanonEndpoint().y);

			// paint Level
			for (int i = 1; i <= Tower.MAX_UPGRADE_LEVEL; i++) {
				if (i <= tower.getLevel()) {
					g.setColor(Color.decode("#FFE524"));
				} else {
					g.setColor(Color.decode("#FFFFFF"));
				}
				g.fillRect(tower.getPos().x + (i * 3), tower.getPos().y + 17, 2, 2);
			}

			if (showupgrade && TowerDefHelper.canUpgrade(tower, gameData)) {
				g.setColor(Color.GREEN);
				g.fillRect(tower.getPos().x + 14, tower.getPos().y + 2, 6, 2);
				g.fillRect(tower.getPos().x + 14 + 2, tower.getPos().y, 2, 6);
			}

		} else {
			if (!drawAvailableTower) {
				// draw detector animation
				int posX = (tower.getPos().x - (tower.getRadius()) + 10);
				int posY = (tower.getPos().y - (tower.getRadius()) + 10);

				g.setColor(new Color(0.0f, 1.0f, 0.0f, 0.2f));
				g.fillOval(posX, posY, tower.getRadius() * 2, tower.getRadius() * 2);
			}
		}

		((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
	}

	public void paintAttackers(Graphics g, List<Attacker> attackers) {
		// paint attackers
		for (Attacker attacker : attackers) {
			// draw attacker energy
			if (!attacker.isDead() && attacker.getPos().y < 320) {
				g.setColor(Color.RED);
				g.fillRect(attacker.getPos().x, attacker.getPos().y - 5, TOWER_WIDTH, 3);
				g.setColor(Color.GREEN);
				g.fillRect(attacker.getPos().x, attacker.getPos().y - 5, TOWER_WIDTH - (int) (((double) TOWER_WIDTH / attacker.getMaxEnergy()) * attacker.getEnergy()), 3);

				// draw attacker
				if (attacker.getType().equals(Type.BLUE)) {
					Color c = Color.decode("#30A4E3");
					if (attacker.isInvisible() && !attacker.isDetected()) {
						c = new Color(c.getRed(), c.getGreen(), c.getBlue(), 100);
					}
					g.setColor(c);
				} else {
					Color c = Color.decode("#E36530");
					if (attacker.isInvisible() && !attacker.isDetected()) {
						c = new Color(c.getRed(), c.getGreen(), c.getBlue(), 100);
					}
					g.setColor(c);
				}

				g.fillOval(attacker.getPos().x + 2, attacker.getPos().y + 2, TOWER_WIDTH - 5, TOWER_WIDTH - 5);

				// draw eyes
				g.setColor(Color.WHITE);
				if (attacker.getVy() != -1) {
					if (attacker.getVx() == 0) {
						g.fillOval(attacker.getPos().x + 5, attacker.getPos().y + 8, 3, 3);
						g.fillOval(attacker.getPos().x + 12, attacker.getPos().y + 8, 3, 3);
						g.setColor(Color.BLACK);
						g.fillOval(attacker.getPos().x + 6, attacker.getPos().y + 9, 2, 2);
						g.fillOval(attacker.getPos().x + 13, attacker.getPos().y + 9, 2, 2);
					} else if (attacker.getVx() == 1) {
						g.fillOval(attacker.getPos().x + 7, attacker.getPos().y + 8, 3, 3);
						g.fillOval(attacker.getPos().x + 13, attacker.getPos().y + 8, 3, 3);
						g.setColor(Color.BLACK);
						g.fillOval(attacker.getPos().x + 8, attacker.getPos().y + 9, 2, 2);
						g.fillOval(attacker.getPos().x + 14, attacker.getPos().y + 9, 2, 2);
					} else {
						g.fillOval(attacker.getPos().x + 4, attacker.getPos().y + 8, 3, 3);
						g.fillOval(attacker.getPos().x + 10, attacker.getPos().y + 8, 3, 3);
						g.setColor(Color.BLACK);
						g.fillOval(attacker.getPos().x + 5, attacker.getPos().y + 9, 2, 2);
						g.fillOval(attacker.getPos().x + 11, attacker.getPos().y + 9, 2, 2);
					}
				}
			}
		}
	}
}
