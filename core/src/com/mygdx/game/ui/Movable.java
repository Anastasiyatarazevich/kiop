package com.mygdx.game.ui;

public interface Movable {
    public void move(int timeStep);

    public interface OnEndAnimation {
        public void onEnd();
    }
}
