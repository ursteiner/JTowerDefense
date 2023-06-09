package com.github.ursteiner.graphics;

import com.github.ursteiner.model.GameData;

import java.awt.*;

public class ButtonBloodOption extends AbstractButton {

    public ButtonBloodOption(GameData gameData){
        super(gameData);
        this.position = new Point(360 * GameData.ZOOM, 85 * GameData.ZOOM);
        this.width = 105 * GameData.ZOOM;
        this.height = 15 * GameData.ZOOM;
    }
    @Override
    public void execute() {
        if(isButtonVisible()) {
            getGameData().setBloodMode(!getGameData().isBloodMode());
        }
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
