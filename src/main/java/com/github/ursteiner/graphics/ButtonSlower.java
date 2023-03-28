package com.github.ursteiner.graphics;

import com.github.ursteiner.model.GameData;
import com.github.ursteiner.model.Hint;

import java.awt.*;

public class ButtonSlower extends AbstractButton {

    public ButtonSlower(GameData gameData) {
        super(gameData);
        this.position = new Point(460, 360);
    }

    @Override
    public void execute() {
        if(isButtonVisible()) {
            if (getGameData().getSpeed() <= GameData.MAX_SPEED - 5) {
                getGameData().setSpeed(getGameData().getSpeed() + 5);
            }
        }
    }

    @Override
    public void paintButton(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(getPosition().x, getPosition().y, 10, 10);

        g.setColor(Color.WHITE);

        g.fillRect(getPosition().x + 1, getPosition().y + 4, 8, 2);
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
