package com.github.ursteiner.graphics;

import com.github.ursteiner.model.GameData;

import java.awt.*;

public class ButtonOpenMenu extends AbstractButton {

    public ButtonOpenMenu(GameData gameData) {
        super(gameData);
        this.position = new Point(430 * GameData.ZOOM, 350 * GameData.ZOOM);
    }

    @Override
    public void execute() {
        getGameData().setPause(true);
        getGameData().setInMenu(true);
    }

    @Override
    public void paintButton(Graphics g) {
        g.setColor(new Color(0f, 0f, 0f, 0.75f));
        g.fillRect(355 * GameData.ZOOM, 40 * GameData.ZOOM, 115 * GameData.ZOOM, 110 * GameData.ZOOM);
        if (isButtonEnabled()) {
            g.setFont(TowerDefenseGraphics.FONT_GAME_OVER);
            g.setColor(new Color(1f, 1f, 1f, 0.5f));
            g.drawString("GAME OVER!", 40 * GameData.ZOOM, 320 * GameData.ZOOM);
        }
    }

    @Override
    public boolean isButtonEnabled() {
        return getGameData().isGameOver();
    }

    @Override
    public boolean isButtonVisible() {
        return false;
    }
}
