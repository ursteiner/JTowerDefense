package com.github.ursteiner.graphics;

import com.github.ursteiner.model.GameData;
import java.awt.*;

public class ButtonSlower extends AbstractButton {

    public ButtonSlower(GameData gameData) {
        super(gameData);
        this.position = new Point(460 * GameData.ZOOM, 360 * GameData.ZOOM);
        this.width = 10 * GameData.ZOOM;
        this.height = 9 * GameData.ZOOM;
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
        g.fillRect(getPosition().x, getPosition().y, width, height);

        g.setColor(Color.WHITE);
        g.fillRect(getPosition().x + 1 * GameData.ZOOM, getPosition().y + 4 * GameData.ZOOM, 8 * GameData.ZOOM, 2 * GameData.ZOOM);
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
