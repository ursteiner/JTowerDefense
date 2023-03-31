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

	private static final int TOWER_WIDTH = 20 * GameData.ZOOM;
	private static final int TOWER_HEIGHT = 20 * GameData.ZOOM;
	private final BasicStroke LINE_WIDTH_3 = new BasicStroke(3 * GameData.ZOOM);
	private static final BasicStroke LINE_WIDTH_2 = new BasicStroke(2 * GameData.ZOOM);
	private final BasicStroke LINE_WIDTH_1 = new BasicStroke(GameData.ZOOM);
	public static final Font FONT_GAME_OVER = new Font("SansSerif", Font.BOLD, 25 * GameData.ZOOM);
	private static final String FONT_NAME = "SansSerif";
	public static final Font FONT_MENU = new Font(FONT_NAME, Font.BOLD, 14 * GameData.ZOOM);
	private static final Font BASIC_FONT = new Font(FONT_NAME, Font.PLAIN, 10 * GameData.ZOOM);
	private final Image GRASS = scaleImage(new ImageIcon(getClass().getResource("grass.png")).getImage());
	private final Image WAY = scaleImage(new ImageIcon(getClass().getResource("way.png")).getImage());
	private final Image TOWER_BLUE = scaleImage(new ImageIcon(getClass().getResource("towerblue.png")).getImage());
	private final Image TOWER_BROWN = scaleImage(new ImageIcon(getClass().getResource("towerbrown.png")).getImage());
	private final Image TOWER_MIXED = scaleImage(new ImageIcon(getClass().getResource("towermixed.png")).getImage());
	private final Image SEA = scaleImage(new ImageIcon(getClass().getResource("sea.png")).getImage());
	private final Image DETECTOR = scaleImage(new ImageIcon(getClass().getResource("detector.png")).getImage());

	private final BufferedImage background = new BufferedImage(500 * GameData.ZOOM, 340 * GameData.ZOOM, BufferedImage.TYPE_INT_RGB);

	private boolean backgroundGenerated = false;

	public static Image scaleImage(Image image){
		BufferedImage bi = new BufferedImage(image.getWidth(null) * GameData.ZOOM, image.getHeight(null) * GameData.ZOOM, BufferedImage.TYPE_INT_ARGB);
		Graphics g = bi.createGraphics();
		g.drawImage(image, 0, 0, image.getWidth(null) * GameData.ZOOM , image.getHeight(null) * GameData.ZOOM, null, null);
		return new ImageIcon(bi).getImage();
	}

	private static void paintShots(Graphics g, Tower tower) {

		Shot shot = tower.getShots();
		if (shot != null) {
			Graphics2D g2d = (Graphics2D) g;
			g2d.setStroke(new BasicStroke(tower.getLevel()));
			g.setColor(new Color(0.9f, 1.0f, 0.3f, shot.getOpacity()));
			g.drawLine(tower.getPos().x + TOWER_WIDTH / 2, tower.getPos().y + TOWER_HEIGHT / 2, shot.getP2().x + TOWER_WIDTH / 2, shot.getP2().y + TOWER_HEIGHT / 2);

			// attacker hit animation

			if (shot.isBloodMode()) {
				for (Particle p : shot.getParticles()) {
					g.setColor(new Color(p.getIntensity(), 0, 0));
					g.fillRect(p.getX() + shot.getP2().x + 10  * GameData.ZOOM, shot.getP2().y - p.getY() + 10  * GameData.ZOOM, 3  * GameData.ZOOM, 3  * GameData.ZOOM);
					TowerDefHelper.calculateNewParticlePos(p);
				}
			}
		}
	}

	public static void paintMenuButton(Graphics g, Point b, String text, boolean isMouseOver) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(LINE_WIDTH_2);
		g.setColor(Color.LIGHT_GRAY);
		g.drawRect(b.x, b.y, 105  * GameData.ZOOM, 15  * GameData.ZOOM);

		if (isMouseOver) {
			g.setColor(Color.WHITE);
			g.drawRect(b.x + 8 * GameData.ZOOM, (b.y + 15 * GameData.ZOOM / 2 - 1), 2 * GameData.ZOOM, 2 * GameData.ZOOM);
		} else {
			g.setColor(Color.LIGHT_GRAY);
		}
		g.setFont(FONT_MENU);
		g.drawString(text, b.x + 15 * GameData.ZOOM, b.y + 12 * GameData.ZOOM);
	}

	public static void paintTowerSelection(Graphics g, Tower tower) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(LINE_WIDTH_2);
		g.setColor(Color.RED);
		g.drawRect(tower.getPos().x - GameData.ZOOM, tower.getPos().y - GameData.ZOOM, TOWER_WIDTH + 2, TOWER_HEIGHT + 2);
	}

	public static void paintStatus(Graphics g, GameData gameData, int kills) {
		g.setColor(Color.black);
		g.fillRect(110 * GameData.ZOOM, 350 * GameData.ZOOM, 245 * GameData.ZOOM, 20 * GameData.ZOOM);

		g.setColor(Color.WHITE);
		g.setFont(BASIC_FONT);
		g.drawString(" lvl: " + gameData.getLevel(), 110 * GameData.ZOOM, 365 * GameData.ZOOM);
		g.drawString(gameData.getTmpMoney() + " $", 150 * GameData.ZOOM, 365 * GameData.ZOOM);
		g.drawString("score: " + gameData.getScore(), 210 * GameData.ZOOM, 365 * GameData.ZOOM);
		g.drawString("kills: " + kills, 300 * GameData.ZOOM, 365 * GameData.ZOOM);
	}

	public static void paintHint(Graphics g, Hint h) {
		g.setColor(Color.DARK_GRAY);
		g.setFont(BASIC_FONT);
		g.drawString(h.getText(), h.getP().x, h.getP().y + 35 * GameData.ZOOM);
	}

	public static void paintSpeedBar(Graphics g, Point p, int speed, int max) {

		double speedHeight = ((20d * GameData.ZOOM) / max) * speed;

		g.setColor(Color.DARK_GRAY);
		g.fillRect(p.x + 11 * GameData.ZOOM, p.y, 5 * GameData.ZOOM, 20 * GameData.ZOOM);
		g.setColor(new Color(77, 167, 232));
		g.fillRect(p.x + 11 * GameData.ZOOM, p.y + (20 * GameData.ZOOM) - (int)speedHeight, 5 * GameData.ZOOM, (int)speedHeight);

	}

	public static void paintTowerRadius(Graphics g, Tower tower) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(LINE_WIDTH_2);
		g.setColor(new Color(0.5f, 0.5f, 0.5f, 0.4f));
		Point towerCenter = new Point(tower.getPos().x + TOWER_WIDTH / 2, tower.getPos().y + TOWER_HEIGHT / 2);
		int d = tower.getRadius() * 2;
		g.fillOval(towerCenter.x - tower.getRadius(), towerCenter.y - tower.getRadius(), d, d);
	}

	public void paintGameField(Graphics g, GameMap gameMap, GameData gameData) {
		// background
		if (!backgroundGenerated) {
			// background is only generated once to a buffered Image
			for (int x = 0; x < 500 * GameData.ZOOM; x += 20 * GameData.ZOOM) {
				for (int y = 0; y < 340 * GameData.ZOOM; y += 20 * GameData.ZOOM) {
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
				g.fillRect(b.getPos().x + 6 * GameData.ZOOM, b.getPos().y + 2 * GameData.ZOOM, 4 * GameData.ZOOM, 4 * GameData.ZOOM);
				g.fillRect(b.getPos().x + 4 * GameData.ZOOM, b.getPos().y + 4 + 10 * GameData.ZOOM, 3 * GameData.ZOOM, 3 * GameData.ZOOM);
				g.fillRect(b.getPos().x + 12 * GameData.ZOOM, b.getPos().y + 11 * GameData.ZOOM, 3 * GameData.ZOOM, 2 * GameData.ZOOM);
			}
		}

		backgroundGenerated = true;
	}

	public void paintTower(Graphics g, Tower tower, GameData gameData, boolean showUpgrade, boolean drawAvailableTower) {

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
			g.fillRect(tower.getPos().x, tower.getPos().y - 7 * GameData.ZOOM, barWidth * GameData.ZOOM, 3 * GameData.ZOOM);
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

			g.drawLine(tower.getPos().x + TOWER_WIDTH / 2, tower.getPos().y + TOWER_HEIGHT / 2, tower.getCanonEndpoint().x, tower.getCanonEndpoint().y);

			// paint Level
			for (int i = 1; i <= Tower.MAX_UPGRADE_LEVEL; i++) {
				if (i <= tower.getLevel()) {
					g.setColor(Color.decode("#FFE524"));
				} else {
					g.setColor(Color.decode("#FFFFFF"));
				}
				g.fillRect(tower.getPos().x + (i * 3 * GameData.ZOOM), tower.getPos().y + 17 * GameData.ZOOM, 2 * GameData.ZOOM, 2 * GameData.ZOOM);
			}

			if (showUpgrade && TowerDefHelper.canUpgrade(tower, gameData)) {
				g.setColor(Color.GREEN);
				g.fillRect(tower.getPos().x + 14 * GameData.ZOOM, tower.getPos().y + 2 * GameData.ZOOM, 6 * GameData.ZOOM, 2 * GameData.ZOOM);
				g.fillRect(tower.getPos().x + (14 + 2) * GameData.ZOOM, tower.getPos().y, 2 * GameData.ZOOM, 6 * GameData.ZOOM);
			}

		} else {
			if (!drawAvailableTower) {
				// draw detector animation
				int posX = (tower.getPos().x - (tower.getRadius()) + 10 * GameData.ZOOM);
				int posY = (tower.getPos().y - (tower.getRadius()) + 10 * GameData.ZOOM);

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
			if (!attacker.isDead() && attacker.getPos().y < 320 * GameData.ZOOM) {
				g.setColor(Color.RED);
				g.fillRect(attacker.getPos().x, attacker.getPos().y - 5 * GameData.ZOOM, TOWER_WIDTH, 3 * GameData.ZOOM);
				g.setColor(Color.GREEN);
				g.fillRect(attacker.getPos().x, attacker.getPos().y - 5 * GameData.ZOOM, TOWER_WIDTH - (int) (((double) TOWER_WIDTH / attacker.getMaxEnergy()) * attacker.getEnergy()), 3 * GameData.ZOOM);

				// draw attacker

				Color c = Color.decode(attacker.getType().equals(Type.BLUE) ? "#30A4E3" : "#E36530");
				if (attacker.isInvisible() && !attacker.isDetected()) {
					c = new Color(c.getRed(), c.getGreen(), c.getBlue(), 100);
				}
				g.setColor(c);

				g.fillOval(attacker.getPos().x + 2 * GameData.ZOOM, attacker.getPos().y + 2 * GameData.ZOOM, TOWER_WIDTH - 5 * GameData.ZOOM, TOWER_HEIGHT - 5 * GameData.ZOOM);

				// draw eyes
				g.setColor(Color.WHITE);

				int whiteLeftEyePosX = attacker.getPos().x + 7 * GameData.ZOOM;
				int whiteRightEyePosX = attacker.getPos().x + 13 * GameData.ZOOM;

				int blackLeftEyePosX = attacker.getPos().x + 8 * GameData.ZOOM;
				int blackRightEyePosX = attacker.getPos().x + 14 * GameData.ZOOM;

				if(attacker.getVy() >=0) {
					if (attacker.getVx() > 0) {
						g.fillOval(whiteLeftEyePosX, attacker.getPos().y + 8 * GameData.ZOOM, 3 * GameData.ZOOM, 3 * GameData.ZOOM);
						g.fillOval(whiteRightEyePosX, attacker.getPos().y + 8 * GameData.ZOOM, 3 * GameData.ZOOM, 3 * GameData.ZOOM);
						g.setColor(Color.BLACK);
						g.fillOval(blackLeftEyePosX, attacker.getPos().y + 9 * GameData.ZOOM, 2 * GameData.ZOOM, 2 * GameData.ZOOM);
						g.fillOval(blackRightEyePosX, attacker.getPos().y + 9 * GameData.ZOOM, 2 * GameData.ZOOM, 2 * GameData.ZOOM);
					} else if (attacker.getVx() == 0) {
						g.fillOval(whiteLeftEyePosX - 3, attacker.getPos().y + 8 * GameData.ZOOM, 3 * GameData.ZOOM, 3 * GameData.ZOOM);
						g.fillOval(whiteRightEyePosX - 3, attacker.getPos().y + 8 * GameData.ZOOM, 3 * GameData.ZOOM, 3 * GameData.ZOOM);
						g.setColor(Color.BLACK);
						g.fillOval(blackLeftEyePosX - 4, attacker.getPos().y + 9 * GameData.ZOOM, 2 * GameData.ZOOM, 2 * GameData.ZOOM);
						g.fillOval(blackRightEyePosX - 4, attacker.getPos().y + 9 * GameData.ZOOM, 2 * GameData.ZOOM, 2 * GameData.ZOOM);
					} else {
						g.fillOval(whiteLeftEyePosX - 4, attacker.getPos().y + 8 * GameData.ZOOM, 3 * GameData.ZOOM, 3 * GameData.ZOOM);
						g.fillOval(whiteRightEyePosX - 4, attacker.getPos().y + 8 * GameData.ZOOM, 3 * GameData.ZOOM, 3 * GameData.ZOOM);
						g.setColor(Color.BLACK);
						g.fillOval(blackLeftEyePosX - 5, attacker.getPos().y + 9 * GameData.ZOOM, 2 * GameData.ZOOM, 2 * GameData.ZOOM);
						g.fillOval(blackRightEyePosX - 5, attacker.getPos().y + 9 * GameData.ZOOM, 2 * GameData.ZOOM, 2 * GameData.ZOOM);
					}
				}
			}
		}
	}
}
