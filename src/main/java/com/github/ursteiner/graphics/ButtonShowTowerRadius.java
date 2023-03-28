package com.github.ursteiner.graphics;

import com.github.ursteiner.model.GameData;
import com.github.ursteiner.model.Hint;

import java.awt.*;

public class ButtonShowTowerRadius extends AbstractButton {

    public ButtonShowTowerRadius(GameData gameData) {
        super(gameData);
        this.position = new Point(400 * GameData.ZOOM, 350 * GameData.ZOOM);
        this.hint = new Hint("Radius", this.position);
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

        g.fillOval(getPosition().x + 1 * GameData.ZOOM, getPosition().y + 1 * GameData.ZOOM, 10 * GameData.ZOOM, 10 * GameData.ZOOM);
        g.fillOval(getPosition().x + 10 * GameData.ZOOM, getPosition().y + 5 * GameData.ZOOM, 10 * GameData.ZOOM, 10 * GameData.ZOOM);
        g.fillOval(getPosition().x + 1 * GameData.ZOOM, getPosition().y + 9 * GameData.ZOOM, 10 * GameData.ZOOM, 10 * GameData.ZOOM);

        if(isMouseOver()){
            TowerDefenseGraphics.paintHint(g, getHint());
        }
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
