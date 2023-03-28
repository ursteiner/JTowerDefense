package com.github.ursteiner.graphics;

import com.github.ursteiner.model.GameData;
import com.github.ursteiner.model.Hint;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
@Getter
@Setter
public abstract class AbstractButton {

    public int width = 20;
    public int height = 20;
    public Point position;
    public Hint hint;

    private GameData gameData;
    private boolean mouseOver;

    public AbstractButton(GameData gameData){
        this.gameData = gameData;
    }

    public abstract void execute();
    public abstract void paintButton(Graphics g);
    public abstract boolean isButtonEnabled();
    public abstract boolean isButtonVisible();
}
