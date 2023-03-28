package com.github.ursteiner.graphics;

import com.github.ursteiner.model.GameData;

import java.awt.*;

public class ButtonBloodOption extends AbstractButton {

    public ButtonBloodOption(GameData gameData){
        super(gameData);
        this.position = new Point(360, 85);
        this.width = 105;
        this.height = 15;
    }
    @Override
    public void execute() {
        getGameData().setBloodMode(!getGameData().isBloodMode());
    }

    @Override
    public void paintButton(Graphics g) {
        if(isButtonVisible()) {
            TowerDefenseGraphics.paintMenuButton(g, getPosition(), "Blood is " + (getGameData().isBloodMode() ? "on" : "off"), isMouseOver());
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
