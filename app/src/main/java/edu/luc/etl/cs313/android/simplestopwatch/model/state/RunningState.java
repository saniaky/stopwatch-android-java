package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.R;

class RunningState implements StopwatchState {

    private final StopwatchSMStateView sm;

    public RunningState(final StopwatchSMStateView sm) {
        this.sm = sm;
    }

    @Override
    public void onButtonClick() {
        //  If the timer is running and the button is pressed,
        //  the timer stops and the time is reset to zero. (The button acts as a cancel button.)
        sm.toStoppedState();
    }

    @Override
    public void onTick() {
        if (sm.getTime() == 0) {
            //  If the timer is running and the time reaches zero by itself
            //  (without the button being pressed), then the timer stops
            //  and the alarm starts beeping continually and indefinitely.
            sm.toAlarmState();
        } else {
            sm.actionDec();
        }
    }

    @Override
    public int getStateId() {
        return R.string.RUNNING;
    }

    @Override
    public int getButtonId() {
        return R.string.BUTTON_STOP_TIMER;
    }

}
