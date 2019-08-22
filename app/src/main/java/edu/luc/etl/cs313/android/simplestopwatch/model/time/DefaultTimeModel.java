package edu.luc.etl.cs313.android.simplestopwatch.model.time;

import java.util.Timer;
import java.util.TimerTask;

import edu.luc.etl.cs313.android.simplestopwatch.model.clock.OnTickListener;

/**
 * An implementation of the stopwatch data model.
 */
public class DefaultTimeModel implements TimeModel {

    private Timer timer;
    private int runningTime;
    private OnTickListener listener;

    public DefaultTimeModel() {
        runningTime = 0;
    }

    public void setListener(OnTickListener listener) {
        this.listener = listener;
    }

    @Override
    public void start() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                listener.onTick();
            }
        }, 1000, 1000);
    }

    @Override
    public void stop() {
        if (timer != null) {
            timer.cancel();
        }
    }

    @Override
    public void resetTime() {
        runningTime = 0;
    }

    @Override
    public void incTime() {
        if (runningTime <= 99) {
            runningTime++;
        }
    }

    @Override
    public void decTime() {
        if (runningTime > 0) {
            runningTime--;
        }
    }

    @Override
    public int getTime() {
        return runningTime;
    }

}