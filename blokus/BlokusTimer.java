package blokus;
import java.util.Timer;
import java.util.TimerTask;

public class BlokusTimer {
    private Timer turnTime;

    public Timer getTurnTime() {
        return turnTime;
    }

    public BlokusTimer(TimerTask taskToDo) {
        this.turnTime = new Timer();
        this.turnTime.schedule(taskToDo, 0, 1000);
    }

    // stopTimer terminates the timer
    public void stopTimer(){
        this.turnTime.cancel();
    }


}