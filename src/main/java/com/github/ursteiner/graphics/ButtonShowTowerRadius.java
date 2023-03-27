package com.github.ursteiner.graphics;

import com.github.ursteiner.model.GameData;

import java.awt.*;

public class ButtonShowTowerRadius extends Button{

    public ButtonShowTowerRadius(GameData gameData) {
        super(gameData);
        this.position = new Point(400, 350);
    }

    @Override
    public void execute() {
        getGameData().setShowRadius(!getGameData().isShowRadius());
    }

    @Override
    public void paintButton(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(getPosition().x, getPosition().y, getWidth(), getHeight());

        if (isButtonEnabled()) {
            g.setColor(Color.WHITE);
        } else {
            g.setColor(Color.GRAY);
        }

        g.fillOval(getPosition().x + 1, getPosition().y + 1, 10, 10);
        g.fillOval(getPosition().x + 10, getPosition().y + 5, 10, 10);
        g.fillOval(getPosition().x + 1, getPosition().y + 9, 10, 10);
    }

    @Override
    public boolean isButtonEnabled() {
        return getGameData().isShowRadius();
    }

    @Override
    public boolean isButtonVisible() {
        return false;
    }
}
