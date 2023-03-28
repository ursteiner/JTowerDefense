package com.github.ursteiner.graphics;

import com.github.ursteiner.model.GameData;
import com.github.ursteiner.model.Hint;

import java.awt.*;

public class ButtonFaster extends AbstractButton{

    public ButtonFaster(GameData gameData) {
        super(gameData);
        this.position = new Point(460 * GameData.ZOOM, 350 * GameData.ZOOM);
        this.hint = new Hint("speed", new Point(getPosition().x - 5 * GameData.ZOOM, getPosition().y));
    }

    @Override
    public void execute() {
        if(isButtonVisible()) {
            if (getGameData().getSpeed() > GameData.MIN_SPEED) {
                getGameData().setSpeed(getGameData().getSpeed() - 5);
            }
        }
    }

    @Override
    public void paintButton(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(getPosition().x, getPosition().y, 10 * GameData.ZOOM, 9 * GameData.ZOOM);

        g.setColor(Color.WHITE);

        g.fillRect(getPosition().x + 1 * GameData.ZOOM, getPosition().y + 4 * GameData.ZOOM, 8 * GameData.ZOOM, 2 * GameData.ZOOM);
        g.fillRect(getPosition().x + 4 * GameData.ZOOM, getPosition().y + 1 * GameData.ZOOM, 2 * GameData.ZOOM, 8 * GameData.ZOOM);

        if(isMouseOver()) {
            TowerDefenseGraphics.paintHint(g, getHint());
        }
    }

    @Override
    public boolean isButtonEnabled() {
        return false;
    }

    @Override
    public boolean isButtonVisible() {
        return getGameData().isActiveGameRunning();
    }
}
