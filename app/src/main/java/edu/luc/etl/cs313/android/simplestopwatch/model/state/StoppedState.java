package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.R;

class StoppedState implements StopwatchState {

    private final StopwatchSMStateView sm;

    public StoppedState(final StopwatchSMStateView sm) {
        this.sm = sm;
    }

    @Override
    public void onButtonClick() {
        sm.toWaitingState();
    }

    @Override
    public void onTick() {
        // do nothing
    }

    @Override
    public int getStateId() {
        return R.string.STOPPED;
    }

    @Override
    public int getButtonId() {
        return R.string.BUTTON_ADD_SECOND;
    }
}
