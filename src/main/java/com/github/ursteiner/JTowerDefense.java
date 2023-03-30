package com.github.ursteiner;

import com.github.ursteiner.graphics.*;
import com.github.ursteiner.model.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class JTowerDefense extends JPanel implements Runnable, MouseListener, MouseMotionListener, KeyListener {

    private static final long serialVersionUID = 5661273172996533040L;

    public static final String VERSION = "1.10";
    private final Point MOUSE_OFFSET = new Point(3, 25);
    private final Point roundedMousePos = new Point();
    private Hint hint;
    private final GameData gameData = new GameData();
    private final GameMap gameMap = new GameMap();
    private final ButtonUpgradeTower upgradeTowerButton = new ButtonUpgradeTower(gameData);
    private final ButtonShowTowerRadius showTowerRadiusButton = new ButtonShowTowerRadius(gameData);
    private final ButtonOpenMenu openMenuButton = new ButtonOpenMenu(gameData);
    private final ButtonPauseGame pauseGameButton = new ButtonPauseGame(gameData);
    private final ButtonAboutGame aboutGameButton = new ButtonAboutGame(gameData);
    private final ButtonExitGame exitGameButton = new ButtonExitGame(gameData);
    private final ButtonReturn returnButton = new ButtonReturn(gameData);
    private final ButtonNewGame newGameButton = new ButtonNewGame(gameData);
    private final ButtonBloodOption bloodOptionButton = new ButtonBloodOption(gameData);
    private final ButtonFaster fasterButton = new ButtonFaster(gameData);
    private final ButtonSlower slowerButton = new ButtonSlower(gameData);
    private final ButtonLife lifeButton = new ButtonLife(gameData);
    private final List<AbstractButton> buttonList = List.of(upgradeTowerButton, showTowerRadiusButton, openMenuButton, pauseGameButton, aboutGameButton, exitGameButton, returnButton, newGameButton, bloodOptionButton, fasterButton, slowerButton, lifeButton);
    private final TowerDefenseGraphics graphicsDriver = new TowerDefenseGraphics();

    public JTowerDefense() {
        gameData.initGame();

        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        graphicsDriver.paintGameField(g, gameMap, gameData);
        graphicsDriver.paintAttackers(g, gameData.getAttackers());

        // paint build towers
        int tIndex = 0;
        for (Tower tower : gameData.getBuildTowers()) {
            // draw radius of selected tower
            if (tIndex == gameData.getSelectedTower() || gameData.isShowRadius()) {
                graphicsDriver.paintTowerRadius(g, tower);
            }
            tIndex++;

            graphicsDriver.paintTower(g, tower, gameData, true, false);
        }

        if (!gameData.isInMenu()) {

            // paint available towers
            int index = 0;
            for (Tower tower : gameData.getAvailableTowers()) {
                if (index == gameData.getSelectedBuildTower()) {
                    graphicsDriver.paintTowerSelection(g, tower);
                }
                graphicsDriver.paintTower(g, tower, gameData, false, true);
                index++;
            }

            // info about buildable tower
            if(hint != null){
                TowerDefenseGraphics.paintHint(g, hint);
            }

            // Paint GUI Buttons
            upgradeTowerButton.paintButton(g);
            showTowerRadiusButton.paintButton(g);
            pauseGameButton.paintButton(g);
            fasterButton.paintButton(g);
            slowerButton.paintButton(g);
            lifeButton.paintButton(g);

            graphicsDriver.paintSpeedBar(g, new Point(460 * GameData.ZOOM, 350 * GameData.ZOOM), gameData.getSpeed(), GameData.MIN_SPEED, GameData.MAX_SPEED);


            // paint Tower to build
            if (gameData.getSelectedBuildTower() != -1) {
                Tower tmp = new Tower(roundedMousePos, gameData.getAvailableTowers().get(gameData.getSelectedBuildTower()).getRadius(), 0, gameData.getAvailableTowers().get(gameData.getSelectedBuildTower()).getType());
                graphicsDriver.paintTower(g, tmp, gameData, true, false);
                if (!gameData.getAvailableTowers().get(gameData.getSelectedBuildTower()).getType().equals(Type.DETECTOR)) {
                    graphicsDriver.paintTowerRadius(g, tmp);
                }
            }
        } else {
            openMenuButton.paintButton(g);
            newGameButton.paintButton(g);
            returnButton.paintButton(g);
            bloodOptionButton.paintButton(g);
            aboutGameButton.paintButton(g);
            exitGameButton.paintButton(g);

        }

        if (gameData.isActiveGameRunning() && !gameData.isInMenu()) {
            // paint status
            graphicsDriver.paintStatus(g, gameData, gameData.getKillList().size());
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("TowerDefense " + VERSION);
        JTowerDefense td = new JTowerDefense();
        frame.setSize(1000, 860);

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (d.width - frame.getSize().width) / 2;
        int y = (d.height - frame.getSize().height) / 2;
        frame.setLocation(x, y);
        frame.addMouseMotionListener(td);
        frame.addKeyListener(td);

        frame.setIconImage(new ImageIcon(TowerDefenseGraphics.class.getResource("td.png")).getImage());
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

        int roundMx = mx - (mx % Tower.TOWER_WIDTH);
        int roundMy = my - (my % Tower.TOWER_WIDTH);
        Point mousePoint = new Point(mx, my);

        if (MouseEvent.BUTTON1 == e.getButton()) {
            if (gameData.isActiveGameRunning()) {
                int index = 0;
                boolean towerSelected = false;
                // was a tower selected in build menu
                for (Tower tower : gameData.getAvailableTowers()) {
                    if (TowerDefHelper.checkCollision(mousePoint, tower.getPos(), Tower.TOWER_WIDTH, Tower.TOWER_HEIGHT)) {
                        gameData.setSelectedBuildTower(index);
                        towerSelected = true;
                        break;
                    }
                    index++;
                }

                Tower towerToBuild = null;
                if (gameData.getSelectedBuildTower() != -1) {
                    towerToBuild = gameData.getAvailableTowers().get(gameData.getSelectedBuildTower());
                }

                // build tower
                if (TowerDefHelper.isPositionFree(new Point(roundMx, roundMy), gameData, gameMap) && gameData.getSelectedBuildTower() != -1 && !towerSelected && gameData.getMoney() >= towerToBuild.getType().getCost() && gameData.getSelectedTower() == -1 && roundMy < 340) {
                    gameData.getBuildTowers().add(new Tower(new Point(roundMx, roundMy), 50 * GameData.ZOOM, 0, towerToBuild.getType()));
                    repaint();
                    gameData.setMoney(gameData.getMoney() - towerToBuild.getType().getCost());
                    gameData.setSelectedTower(gameData.getBuildTowers().size() - 1);
                    gameData.setSelectedBuildTower(-1);
                    gameData.setSelectedTower(-1);
                }


                setSelectedTower(mousePoint);
            }

            for (AbstractButton btn : buttonList) {
                if (TowerDefHelper.checkCollision(mousePoint, btn.getPosition(), btn.getWidth(), btn.getHeight())) {
                    btn.execute();
                }
            }

        } else if (MouseEvent.BUTTON3 == e.getButton() && !gameData.isGameOver() && !gameData.isPause()) {

            gameData.setSelectedBuildTower(-1);

            gameData.setSelectedTower(-1);
            setSelectedTower(mousePoint);

            // remove tower
            if (gameData.getSelectedTower() != -1) {
                if (gameData.getBuildTowers().get(gameData.getSelectedTower()).getLevel() == 1) {
                    gameData.setMoney(gameData.getMoney() + 50);
                } else if (gameData.getBuildTowers().get(gameData.getSelectedTower()).getLevel() == 2) {
                    gameData.setMoney(gameData.getMoney() + 130);
                } else if (gameData.getBuildTowers().get(gameData.getSelectedTower()).getLevel() == 3) {
                    gameData.setMoney(gameData.getMoney() + 200);
                }
                gameData.getBuildTowers().remove(gameData.getSelectedTower());
                gameData.setSelectedTower(-1);
                repaint();
            }
        }
    }

    private void setSelectedTower(Point mousePoint) {
        int index = 0;
        for (Tower tower : gameData.getBuildTowers()) {
            if (TowerDefHelper.checkCollision(mousePoint, tower.getPos(), Tower.TOWER_WIDTH, Tower.TOWER_HEIGHT)) {
                gameData.setSelectedTower(index);
                break;
            }
            index++;
        }
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
        for (Attacker attacker : gameData.getAttackers()) {
            if (!attacker.isDead()) {
                allDead = false;
                break;
            }
        }
        return allDead;
    }

    private boolean allAttackersMarchedThrough() {
        boolean allThrough = true;
        for (Attacker attacker : gameData.getAttackers()) {
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

            if (!gameData.isGameOver() && !gameData.isPause()) {
                //animation
                gameData.countTmpMoney();
                gameData.countTmpScore();

                if (allAttackersDead()) {
                    gameData.addAttackersForLevel();
                }

                if (gameData.getFails() <= 0) {
                    gameData.setGameOver(false);
                    gameData.setInMenu(true);
                    gameData.setTmpScore(gameData.getScore());
                }

                if (allAttackersMarchedThrough()) {
                    gameData.addAttackersForLevel();
                }

                for (Blood b : gameData.getKillList()) {
                    b.dryBlood();
                }

                moveAttackers();
                handleTowers();
            }

            repaint();

            try {
                Thread.sleep(gameData.MAX_SPEED + 1 - gameData.getSpeed());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void moveAttackers(){
        for (Attacker attacker : gameData.getAttackers()) {
            if (attacker.isInvisible()) {
                attacker.setDetected(false);
            }

            if (attacker.getWaypointIndex() >= gameMap.getWaypoints().size() && !attacker.isMarchedThrough()) {
                attacker.setMarchedThrough(true);

                if (gameData.isGameStarted()) {
                    gameData.setFails(gameData.getFails() - 1);
                }

            } else if (!attacker.isDead() && attacker.getWaypointIndex() < gameMap.getWaypoints().size()) {
                Point targetPoint = gameMap.getWaypoints().get(attacker.getWaypointIndex());

                if (attacker.getPos().y < targetPoint.y) {
                    attacker.getPos().y += 1 * GameData.ZOOM;
                    attacker.setVx(0);
                    attacker.setVy(1 * GameData.ZOOM);
                } else if (attacker.getPos().y > targetPoint.y) {
                    attacker.getPos().y -= 1 * GameData.ZOOM;
                    attacker.setVx(0);
                    attacker.setVy(-1 * GameData.ZOOM);
                } else if (attacker.getPos().x < targetPoint.x) {
                    attacker.getPos().x += 1 * GameData.ZOOM;
                    attacker.setVx(1 * GameData.ZOOM);
                    attacker.setVy(0);
                } else if (attacker.getPos().x > targetPoint.x) {
                    attacker.getPos().x -= 1 * GameData.ZOOM;
                    attacker.setVx(-1 * GameData.ZOOM);
                    attacker.setVy(0);
                }

                if (attacker.getPos().x == targetPoint.x && attacker.getPos().y == targetPoint.y) {
                    attacker.setWaypointIndex(attacker.getWaypointIndex() + 1);
                }
            }
        }

        // detect attackers using detector towers
        for (Tower tower : gameData.getBuildTowers()) {
            for (Attacker attacker : gameData.getAttackers()) {
                if (Type.DETECTOR.equals(tower.getType()) && TowerDefHelper.getDistance(tower.getPos(), attacker.getPos()) < tower.getRadius()) {
                    // Detector tower has detected an attacker
                    attacker.setDetected(true);
                }
            }
        }
    }

    private void handleTowers(){
        // shoot towers
        for (Tower tower : gameData.getBuildTowers()) {

            handleTowerShots(tower);

            // upgrade counter
            if (tower.isInUpgrade()) {
                tower.doUpgrade();
            }

            if (tower.getCanonColor() > 10) {
                tower.setCanonColor(tower.getCanonColor() - 10);
            }

            boolean towerAnimated = false;

            for (Attacker attacker : gameData.getAttackers()) {

                if (!attacker.isDead() && attacker.isDetected() && TowerDefHelper.getDistance(tower.getPos(), attacker.getPos()) < tower.getRadius() && !tower.isInUpgrade()) {

                    if (tower.getType().equals(attacker.getType()) || tower.getType().equals(Type.MIXED)) {
                        // move tower canon
                        tower.setCanonEndpoint(TowerDefHelper.rotateCannon(attacker.getPos(), tower.getPos(), tower.getCanonLength()));
                        tower.setLastAttackerPos(new Point(attacker.getPos().x, attacker.getPos().y));
                        towerAnimated = true;

                        if (tower.shoot()) {
                            attacker.hit(tower.getStrength());
                            tower.setCanonLength(1);
                            gameData.setScore(gameData.getScore() + tower.getStrength());
                            if (attacker.isDead()) {
                                gameData.setMoney(gameData.getMoney() + 45);
                                gameData.getKillList().add(new Blood(attacker.getPos()));
                            }
                            tower.setShot(new Shot(attacker.getPos(), gameData.isBloodMode()));

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
        int roundMx = x - (x % Tower.TOWER_WIDTH);
        int roundMy = y - (y % Tower.TOWER_WIDTH);
        roundedMousePos.x = roundMx;
        roundedMousePos.y = roundMy;

        Point mousePosition = new Point(x, y);

        for (AbstractButton btn : buttonList) {
            btn.setMouseOver(TowerDefHelper.checkCollision(mousePosition, btn.getPosition(), btn.getWidth(), btn.getHeight()));
        }


        for (Tower tower : gameData.getAvailableTowers()) {
            if (TowerDefHelper.checkCollision(mousePosition, tower.getPos(), Tower.TOWER_WIDTH, Tower.TOWER_HEIGHT)) {

                Type type = tower.getType();
                hint = new Hint(type.getCost() + "$ " + type.getDescription(), tower.getPos());
                break;
            }
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        switch (keyCode) {
            case KeyEvent.VK_SUBTRACT:
                if (gameData.getSpeed() <= GameData.MAX_SPEED - 5) {
                    gameData.setSpeed(gameData.getSpeed() + 5);
                }
                break;
            case KeyEvent.VK_ADD:
                if (gameData.getSpeed() > GameData.MIN_SPEED) {
                    gameData.setSpeed(gameData.getSpeed() - 5);
                }
                break;
            case KeyEvent.VK_ESCAPE:
                gameData.setPause(true);
                gameData.setInMenu(true);
                break;
            case KeyEvent.VK_1:
                gameData.setSelectedTower(-1);
                gameData.setSelectedBuildTower(0);
                break;
            case KeyEvent.VK_2:
                gameData.setSelectedTower(-1);
                gameData.setSelectedBuildTower(1);
                break;
            case KeyEvent.VK_3:
                gameData.setSelectedTower(-1);
                gameData.setSelectedBuildTower(2);
                break;
            case KeyEvent.VK_4:
                gameData.setSelectedTower(-1);
                gameData.setSelectedBuildTower(3);
                break;
            case KeyEvent.VK_U:
                if (gameData.getSelectedTower() > -1) {
                    gameData.getBuildTowers().get(gameData.getSelectedTower()).doUpgrade();
                }
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
