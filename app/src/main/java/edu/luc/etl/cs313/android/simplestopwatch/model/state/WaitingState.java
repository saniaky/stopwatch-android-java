package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import java.util.Timer;
import java.util.TimerTask;

import edu.luc.etl.cs313.android.simplestopwatch.R;

import static edu.luc.etl.cs313.android.simplestopwatch.common.Constants.WAITING_TIME;

class WaitingState implements StopwatchState {

    private final StopwatchStateMachine sm;
    private Timer timer;
    private boolean hasExistingTimer;

    public WaitingState(final StopwatchStateMachine stopwatchStateMachine) {
        this.sm = stopwatchStateMachine;
        this.hasExistingTimer = false;
    }

    @Override
    public int getStateId() {
        return R.string.WAITING;
    }

    @Override
    public int getButtonId() {
        return R.string.BUTTON_ADD_SECOND;
    }

    @Override
    public void onButtonClick() {
        if (hasExistingTimer) {
            timer.cancel();
            timer.purge();
            hasExistingTimer = false;
        } else {
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    sm.toRunningState();
                }
            }, WAITING_TIME);
            hasExistingTimer = true;
        }
        sm.actionInc();
    }

    @Override
    public void onTick() {
        // do nothing
    }

}
