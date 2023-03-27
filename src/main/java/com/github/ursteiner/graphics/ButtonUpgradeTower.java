package com.github.ursteiner.graphics;

import com.github.ursteiner.TowerDefHelper;
import com.github.ursteiner.model.GameData;

import java.awt.*;

public class ButtonUpgradeTower extends Button {

    public ButtonUpgradeTower(GameData gameData) {
        super(gameData);
        this.position =  new Point(370, 350);
    }

    @Override
    public void execute() {
        if (getGameData().getSelectedTower() != -1) {
            if (getGameData().getMoney() >= 100 && TowerDefHelper.canUpgrade(getGameData().getBuildTowers().get(getGameData().getSelectedTower()), getGameData())) {
                getGameData().getBuildTowers().get(getGameData().getSelectedTower()).upgrade();
                getGameData().setMoney(getGameData().getMoney() - 100);
                //repaint();
            } else if (getGameData().getMoney() >= 250 && TowerDefHelper.canUpgrade(getGameData().getBuildTowers().get(getGameData().getSelectedTower()), getGameData())) {
                getGameData().getBuildTowers().get(getGameData().getSelectedTower()).upgrade();
                getGameData().setMoney(getGameData().getMoney() - 300);
                //repaint();
            }
        }
    }

    @Override
    public void paintButton(Graphics g) {

        g.setColor(Color.BLACK);
        g.fillRect(position.x, position.y, getWidth(), getHeight());

        if (isButtonEnabled()) {
            g.setColor(Color.WHITE);
        } else {
            g.setColor(Color.GRAY);
        }

        g.fillRect(position.x + 1, position.y + 8, 18, 4);
        g.fillRect(position.x + 8, position.y + 1, 4, 18);
    }

    @Override
    public boolean isButtonEnabled() {
        return getGameData().getSelectedTower() != -1 && TowerDefHelper.canUpgrade(getGameData().getBuildTowers().get(getGameData().getSelectedTower()), getGameData());
    }

    @Override
    public boolean isButtonVisible() {
        return false;
    }
}
