package com.github.ursteiner.graphics;

import com.github.ursteiner.model.GameData;

import java.awt.*;
import java.net.URL;

public class ButtonAboutGame extends AbstractButton {

    public ButtonAboutGame(GameData gameData) {
        super(gameData);
        this.position = new Point(360 * GameData.ZOOM, 105 * GameData.ZOOM);
        this.width = 105 * GameData.ZOOM;
        this.height = 15 * GameData.ZOOM;
    }

    @Override
    public void execute() {
        if(isButtonVisible()) {
            Desktop desktop = Desktop.getDesktop();

            if (desktop.isSupported(java.awt.Desktop.Action.BROWSE)) {
                URL url;
                try {
                    url = new URL("https://github.com/ursteiner/JTowerDefense");
                    desktop.browse(url.toURI());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @Override
    public void paintButton(Graphics g) {
        if(isButtonVisible()) {
            TowerDefenseGraphics.paintMenuButton(g, getPosition(), "About", isMouseOver());
        }
    }

    @Override
    public boolean isButtonEnabled() {
        return true;
    }

    @Override
    public boolean isButtonVisible() {
        return getGameData().isInMenu();
    }
}
