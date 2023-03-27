package com.github.ursteiner.graphics;

import com.github.ursteiner.model.GameData;

import java.awt.*;

public class ButtonNewGame extends Button{

    public ButtonNewGame(GameData gameData){
        super(gameData);
        this.position = new Point(360, 45);
        this.width = 105;
        this.height = 15;
    }
    @Override
    public void execute() {
        getGameData().setInMenu(false);
        getGameData().initGame();
        getGameData().setGameStarted(true);
    }

    @Override
    public void paintButton(Graphics g) {
        if(isButtonVisible()) {
            TowerDefenseGraphics.paintMenuButton(g, getPosition(), "New Game", isMouseOver());
        }
    }

    @Override
    public boolean isButtonEnabled() {
        return false;
    }

    @Override
    public boolean isButtonVisible() {
        return getGameData().isInMenu();
    }
}