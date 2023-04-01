package com.github.ursteiner.graphics;

import com.github.ursteiner.model.GameData;
import com.github.ursteiner.model.Hint;

import java.awt.*;

public class ButtonLife extends AbstractButton {

    public ButtonLife(GameData gameData){
       super(gameData);
       this.position = new Point(360 * GameData.ZOOM, 350 * GameData.ZOOM);
       this.width = 5 * GameData.ZOOM;
       this.height = 20 * GameData.ZOOM;
       this.hint = new Hint("life " + gameData.getFails() + "/5", new Point(360 * GameData.ZOOM, 350 * GameData.ZOOM));
    }

    @Override
    public void execute() {
        // no action
    }

    @Override
    public void paintButton(Graphics g) {
        if(isButtonVisible()) {
            int healthHeight = (height / 5) * getGameData().getFails();

            g.setColor(Color.RED);
            g.fillRect(getPosition().x, getPosition().y, width, height);
            g.setColor(Color.GREEN);
            g.fillRect(getPosition().x, getPosition().y + height - healthHeight, width, healthHeight);

            if (isMouseOver()) {
                TowerDefenseGraphics.paintHint(g, getHint());
            }
        }
    }

    @Override
    public boolean isButtonEnabled() {
        return false;
    }

    @Override
    public boolean isButtonVisible() {
        return !getGameData().isInMenu();
    }
}
