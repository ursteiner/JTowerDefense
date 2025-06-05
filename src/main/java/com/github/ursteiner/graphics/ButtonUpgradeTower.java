package com.github.ursteiner.graphics;

import com.github.ursteiner.TowerDefHelper;
import com.github.ursteiner.model.GameData;
import com.github.ursteiner.model.Hint;

import java.awt.*;

public class ButtonUpgradeTower extends AbstractButton {

    public ButtonUpgradeTower(GameData gameData) {
        super(gameData);
        this.position =  new Point(370 * GameData.ZOOM, 350 * GameData.ZOOM);
        this.hint = new Hint("Upgrade ", this.position);
    }

    @Override
    public void execute() {
        if (getGameData().getSelectedTower() != null) {
            if (getGameData().getMoney() >= 100 && TowerDefHelper.canUpgrade(getGameData().getSelectedTower(), getGameData())) {
                getGameData().getSelectedTower().upgrade();
                getGameData().setMoney(getGameData().getMoney() - 100);
                //repaint();
            } else if (getGameData().getMoney() >= 250 && TowerDefHelper.canUpgrade(getGameData().getSelectedTower(), getGameData())) {
                getGameData().getSelectedTower().upgrade();
                getGameData().setMoney(getGameData().getMoney() - 300);
                //repaint();
            }
        }
    }

    @Override
    public void paintButton(Graphics g) {
        if(isButtonVisible()) {

            g.setColor(Color.BLACK);
            g.fillRect(position.x, position.y, getWidth(), getHeight());

            if (isButtonEnabled()) {
                g.setColor(Color.WHITE);
            } else {
                g.setColor(Color.GRAY);
            }

            g.fillRect(position.x + GameData.ZOOM, position.y + 8 * GameData.ZOOM, 18 * GameData.ZOOM, 4 * GameData.ZOOM);
            g.fillRect(position.x + 8 * GameData.ZOOM, position.y + GameData.ZOOM, 4 * GameData.ZOOM, 18 * GameData.ZOOM);

            if (isMouseOver()) {
                TowerDefenseGraphics.paintHint(g, getHint());
            }
        }
    }

    @Override
    public boolean isButtonEnabled() {
        return getGameData().getSelectedTower() != null && TowerDefHelper.canUpgrade(getGameData().getSelectedTower(), getGameData());
    }

    @Override
    public boolean isButtonVisible() {
        return !getGameData().isInMenu();
    }
}
