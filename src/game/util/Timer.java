package game.util;

import javafx.animation.AnimationTimer;

public abstract class Timer extends AnimationTimer {
    private long cycleDuration;
    public Timer(long cycleDuration){
       setCycleDuration(cycleDuration);
    }

    public void setCycleDuration(long cycleDuration){
        this.cycleDuration = cycleDuration * 1_000_000;
    }

    private long lastTime = 0;
    @Override
    public void handle(long currentTime) {
        if(currentTime - lastTime > cycleDuration){
            handle();
            lastTime = currentTime;
        }
    }

    public abstract void handle();
}
