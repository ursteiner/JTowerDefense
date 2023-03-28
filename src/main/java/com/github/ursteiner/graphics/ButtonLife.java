package com.github.ursteiner.graphics;

import com.github.ursteiner.model.GameData;
import com.github.ursteiner.model.Hint;

import java.awt.*;

public class ButtonLife extends AbstractButton {

    public ButtonLife(GameData gameData){
       super(gameData);
       this.position = new Point(360 * GameData.ZOOM, 350 * GameData.ZOOM);
       this.width = 10 * GameData.ZOOM;
       this.height = 20 * GameData.ZOOM;
       this.hint = new Hint("life " + gameData.getFails() + "/5", new Point(360 * GameData.ZOOM, 350 * GameData.ZOOM));
    }

    @Override
    public void execute() {
        // no action
    }

    @Override
    public void paintButton(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(getPosition().x, getPosition().y, 5 * GameData.ZOOM, 20 * GameData.ZOOM);
        g.setColor(Color.GREEN);
        g.fillRect(getPosition().x, getPosition().y + (4 * (5 - getGameData().getFails())), 5 * GameData.ZOOM, 20 * GameData.ZOOM - (4 * GameData.ZOOM * (5 * GameData.ZOOM - getGameData().getFails())));

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
        return false;
    }
}
