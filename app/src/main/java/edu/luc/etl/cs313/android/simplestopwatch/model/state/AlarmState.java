package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.R;

class AlarmState implements StopwatchState {

    private final StopwatchSMStateView sm;

    public AlarmState(StopwatchSMStateView sm) {
        this.sm = sm;
    }

    @Override
    public int getStateId() {
        return R.string.ALARM;
    }

    @Override
    public int getButtonId() {
        return R.string.BUTTON_STOP_ALARM;
    }

    @Override
    public void onButtonClick() {
        //  If the alarm is sounding and the button is pressed,
        //  the alarm stops sounding; the timer is now stopped
        //  and the (remaining) time is zero. (The button acts as a stop button.)
        sm.actionStopAlarm();
        sm.toStoppedState();
    }

    @Override
    public void onTick() {
        sm.actionStartAlarm();
    }

}
