package com.github.ursteiner.graphics;

import com.github.ursteiner.model.GameData;

import java.awt.*;

public class ButtonExitGame extends Button{

    public ButtonExitGame(GameData gameData) {
        super(gameData);
        this.position = new Point(360, 125);
        this.width = 105;
        this.height = 15;
    }
    @Override
    public void execute() {
        System.exit(0);
    }

    @Override
    public void paintButton(Graphics g) {
        if(isButtonVisible()) {
            TowerDefenseGraphics.paintMenuButton(g, getPosition(), "Exit", isMouseOver());
        }
    }

    @Override
    public boolean isButtonEnabled() {
        return true;
    }

    @Override
    public boolean isButtonVisible() {
        return getGameData().isInMenu();
    }
}
