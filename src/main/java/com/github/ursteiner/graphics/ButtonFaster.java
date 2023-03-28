package com.github.ursteiner.graphics;

import com.github.ursteiner.model.GameData;
import com.github.ursteiner.model.Hint;

import java.awt.*;

public class ButtonFaster extends AbstractButton{

    public ButtonFaster(GameData gameData) {
        super(gameData);
        this.position = new Point(460, 350);
        this.hint = new Hint("speed", new Point(getPosition().x - 5, getPosition().y));
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
        g.fillRect(getPosition().x, getPosition().y, 10, 9);

        g.setColor(Color.WHITE);

        g.fillRect(getPosition().x + 1, getPosition().y + 4, 8, 2);
        g.fillRect(getPosition().x + 4, getPosition().y + 1, 2, 8);

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
