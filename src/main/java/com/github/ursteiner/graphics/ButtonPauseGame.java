package com.github.ursteiner.graphics;

import com.github.ursteiner.model.GameData;
import com.github.ursteiner.model.Hint;

import java.awt.*;

public class ButtonPauseGame extends AbstractButton {

    public ButtonPauseGame(GameData gameData) {
        super(gameData);
        this.position = new Point(430 * GameData.ZOOM, 350 * GameData.ZOOM);
        this.hint = new Hint("Pause", this.position);
    }

    @Override
    public void execute() {
        getGameData().setPause(true);
        getGameData().setInMenu(true);
    }

    @Override
    public void paintButton(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(getPosition().x, getPosition().y, getWidth(), getWidth());

        if (isButtonEnabled()) {
            g.setColor(Color.WHITE);
        } else {
            g.setColor(Color.GRAY);
        }

        g.fillRect(getPosition().x + 3 * GameData.ZOOM, getPosition().y + 2 * GameData.ZOOM, 5 * GameData.ZOOM, + getHeight() - 4 * GameData.ZOOM);
        g.fillRect(getPosition().x + 12 * GameData.ZOOM, getPosition().y + 2 * GameData.ZOOM, 5 * GameData.ZOOM, + getHeight() - 4 * GameData.ZOOM);

        if(isMouseOver()) {
            TowerDefenseGraphics.paintHint(g, getHint());
        }
    }

    @Override
    public boolean isButtonEnabled() {
        return getGameData().isPause();
    }

    @Override
    public boolean isButtonVisible() {
        return false;
    }
}
