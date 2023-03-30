package com.github.ursteiner.model;

import lombok.Data;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class GameData {
    public static final int MIN_SPEED = 5;
    public static final int MAX_SPEED = 45;
    public static int ZOOM = 2;
    private List<Tower> buildTowers = new ArrayList<>();
    private int selectedTower;
    private int selectedBuildTower;
    private final List<Attacker> attackers = new ArrayList<>();
    private final List<Tower> availableTowers = new ArrayList<>();
    private List<Blood> killList = new ArrayList<>();
    private int level;
    private int attackersEnergy;
    private int money;
    private int tmpMoney;
    private int score;
    private int tmpScore;
    private int speed;
    private int fails;
    private boolean gameOver = false;
    private boolean pause = false;
    private boolean gameStarted = false;
    private boolean bloodMode = false;
    private boolean inMenu = true;
    private boolean showRadius = false;

    public void initAvailableTowers(){
        availableTowers.clear();

        availableTowers.add(new Tower(new Point(10 * GameData.ZOOM, 350 * GameData.ZOOM), 50 * GameData.ZOOM, 5, Type.BLUE));
        availableTowers.add(new Tower(new Point(35 * GameData.ZOOM, 350 * GameData.ZOOM), 50 * GameData.ZOOM, 5, Type.MIXED));
        availableTowers.add(new Tower(new Point(60 * GameData.ZOOM, 350 * GameData.ZOOM), 50 * GameData.ZOOM, 5, Type.BROWN));
        availableTowers.add(new Tower(new Point(85 * GameData.ZOOM, 350 * GameData.ZOOM), 50 * GameData.ZOOM, 5, Type.DETECTOR));
    }

    public void initGame(){
        getAttackers().clear();

        attackers.add(new Attacker(new Point(-20 * GameData.ZOOM, 40 * GameData.ZOOM), getAttackersEnergy(), Type.BLUE, false));
        attackers.add(new Attacker(new Point(-60 * GameData.ZOOM, 40 * GameData.ZOOM), getAttackersEnergy(), Type.BLUE, false));
        attackers.add(new Attacker(new Point(-100 * GameData.ZOOM, 40 * GameData.ZOOM), getAttackersEnergy(), Type.BLUE, false));

        getKillList().clear();
        setLevel(1);
        setAttackersEnergy(4);
        setMoney(150);
        setTmpMoney(0);
        setFails(5);
        setSpeed(5);
        setPause(false);
        setGameOver(false);
        setScore(0);
        getBuildTowers().clear();
        setShowRadius(false);

        initAvailableTowers();

        setSelectedBuildTower(-1);
        setSelectedTower(-1);
    }

    /**
     * Animates slowly counting score to new score
     */
    public void countTmpScore() {
        if (getTmpScore() < getScore()) {
            if (getScore() > getTmpScore() + 10) {
                setTmpScore(getTmpScore() + 5);
            } else {
                setTmpScore(getTmpScore() + 1);
            }
        }
    }

    public void countTmpMoney(){
        if (getTmpMoney() < getMoney()) {
            setTmpMoney(getTmpMoney() + 5);
        } else if (getTmpMoney() > getMoney()) {
            setTmpMoney(getTmpMoney() - 5);
        }
    }

    public void addAttackersForLevel() {
        // init new Round
        attackers.clear();
        setLevel(getLevel() + 1);

        if (getLevel() >= 20) {
            attackers.add(new Attacker(new Point(-20 * GameData.ZOOM, 40 * GameData.ZOOM), getAttackersEnergy(), Type.BROWN, true));
            attackers.add(new Attacker(new Point(-45 * GameData.ZOOM, 40 * GameData.ZOOM), getAttackersEnergy(), Type.BLUE, true));
            attackers.add(new Attacker(new Point(-70 * GameData.ZOOM, 40 * GameData.ZOOM), getAttackersEnergy(), Type.BROWN, true));
            attackers.add(new Attacker(new Point(-95 * GameData.ZOOM, 40 * GameData.ZOOM), getAttackersEnergy(), Type.BLUE, true));
            attackers.add(new Attacker(new Point(-120 * GameData.ZOOM, 40 * GameData.ZOOM), getAttackersEnergy(), Type.BROWN, true));
            attackers.add(new Attacker(new Point(-145 * GameData.ZOOM, 40 * GameData.ZOOM), getAttackersEnergy(), Type.BLUE, true));
            attackers.add(new Attacker(new Point(-170 * GameData.ZOOM, 40 * GameData.ZOOM), getAttackersEnergy(), Type.BROWN, true));
            attackers.add(new Attacker(new Point(-195 * GameData.ZOOM, 40 * GameData.ZOOM), getAttackersEnergy(), Type.BLUE, true));
            attackers.add(new Attacker(new Point(-120 * GameData.ZOOM, 40 * GameData.ZOOM), getAttackersEnergy(), Type.BROWN, true));
            setAttackersEnergy(getAttackersEnergy() + 25);
        } else if (getLevel() >= 15) {
            attackers.add(new Attacker(new Point(-20 * GameData.ZOOM, 40 * GameData.ZOOM), getAttackersEnergy(), Type.BROWN, true));
            attackers.add(new Attacker(new Point(-45 * GameData.ZOOM, 40 * GameData.ZOOM), getAttackersEnergy(), Type.BLUE, false));
            attackers.add(new Attacker(new Point(-70 * GameData.ZOOM, 40 * GameData.ZOOM), getAttackersEnergy(), Type.BROWN, false));
            attackers.add(new Attacker(new Point(-95 * GameData.ZOOM, 40 * GameData.ZOOM), getAttackersEnergy(), Type.BLUE, true));
            attackers.add(new Attacker(new Point(-120 * GameData.ZOOM, 40 * GameData.ZOOM), getAttackersEnergy(), Type.BROWN, false));
            attackers.add(new Attacker(new Point(-145 * GameData.ZOOM, 40 * GameData.ZOOM), getAttackersEnergy(), Type.BLUE, false));
            attackers.add(new Attacker(new Point(-170 * GameData.ZOOM, 40 * GameData.ZOOM), getAttackersEnergy(), Type.BROWN, true));
            setAttackersEnergy(getAttackersEnergy() + 20);
        } else if (getLevel() >= 10) {
            attackers.add(new Attacker(new Point(-20 * GameData.ZOOM, 40 * GameData.ZOOM), getAttackersEnergy(), Type.BROWN, false));
            attackers.add(new Attacker(new Point(-45 * GameData.ZOOM, 40 * GameData.ZOOM), getAttackersEnergy(), Type.BLUE, false));
            attackers.add(new Attacker(new Point(-70 * GameData.ZOOM, 40 * GameData.ZOOM), getAttackersEnergy(), Type.BROWN, false));
            attackers.add(new Attacker(new Point(-95 * GameData.ZOOM, 40 * GameData.ZOOM), getAttackersEnergy(), Type.BLUE, false));
            attackers.add(new Attacker(new Point(-120 * GameData.ZOOM, 40 * GameData.ZOOM), getAttackersEnergy(), Type.BROWN, false));
            setAttackersEnergy(getAttackersEnergy() + 15);
        } else if (getLevel() >= 5) {
            attackers.add(new Attacker(new Point(-20 * GameData.ZOOM, 40 * GameData.ZOOM), getAttackersEnergy(), Type.BLUE, false));
            attackers.add(new Attacker(new Point(-60 * GameData.ZOOM, 40 * GameData.ZOOM), getAttackersEnergy(), Type.BROWN, false));
            attackers.add(new Attacker(new Point(-100 * GameData.ZOOM, 40 * GameData.ZOOM), getAttackersEnergy(), Type.BLUE, false));
            setAttackersEnergy(getAttackersEnergy() + 10);
        } else {
            attackers.add(new Attacker(new Point(-20 * GameData.ZOOM, 40 * GameData.ZOOM), getAttackersEnergy(), Type.BLUE, false));
            attackers.add(new Attacker(new Point(-60 * GameData.ZOOM, 40 * GameData.ZOOM), getAttackersEnergy(), Type.BLUE, false));
            attackers.add(new Attacker(new Point(-100 * GameData.ZOOM, 40 * GameData.ZOOM), getAttackersEnergy(), Type.BLUE, false));
            setAttackersEnergy(getAttackersEnergy() + 5);
        }
    }

    public boolean isActiveGameRunning(){
        return !gameOver && !pause;
    }
}
