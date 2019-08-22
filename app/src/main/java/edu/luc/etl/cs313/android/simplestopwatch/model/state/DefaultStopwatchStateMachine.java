package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import android.content.Context;

import edu.luc.etl.cs313.android.simplestopwatch.common.StopwatchUIUpdateListener;
import edu.luc.etl.cs313.android.simplestopwatch.model.alarm.AlarmService;
import edu.luc.etl.cs313.android.simplestopwatch.model.alarm.SimpleAlarmService;
import edu.luc.etl.cs313.android.simplestopwatch.model.time.TimeModel;

/**
 * An implementation of the state machine for the stopwatch.
 *
 * @author laufer
 */
public class DefaultStopwatchStateMachine implements StopwatchStateMachine {

    private final TimeModel timeModel;

    // known states
    private final StopwatchState STOPPED = new StoppedState(this);
    private final StopwatchState RUNNING = new RunningState(this);
    private final StopwatchState WAITING = new WaitingState(this);
    private final StopwatchState ALARM = new AlarmState(this);

    private StopwatchState state;
    private StopwatchUIUpdateListener uiUpdateListener;
    private AlarmService alarmService;

    public DefaultStopwatchStateMachine(final TimeModel timeModel, Context applicationContext) {
        this.timeModel = timeModel;
        this.alarmService = new SimpleAlarmService(applicationContext);
    }

    private void setState(final StopwatchState state) {
        this.state = state;
        uiUpdateListener.updateState(state.getStateId(), state.getButtonId());
    }

    private void updateUI() {
        uiUpdateListener.updateTime(timeModel.getTime());
    }

    @Override
    public void setUIUpdateListener(final StopwatchUIUpdateListener uiUpdateListener) {
        this.uiUpdateListener = uiUpdateListener;
    }
    // forward event uiUpdateListener methods to the current state
    // these must be synchronized because events can come from the
    // UI thread or the timer thread

    @Override
    public synchronized void onButtonClick() {
        state.onButtonClick();
    }

    @Override
    public int getTime() {
        return timeModel.getTime();
    }

    // transitions

    @Override
    public void toRunningState() {
        setState(RUNNING);
        timeModel.start();
    }

    @Override
    public void toStoppedState() {
        setState(STOPPED);
        timeModel.stop();
        timeModel.resetTime();
        updateUI();
    }

    @Override
    public void toWaitingState() {
        setState(WAITING);
    }

    @Override
    public void toAlarmState() {
        setState(ALARM);
    }

    // actions

    @Override
    public void actionInit() {
        toStoppedState();
        actionReset();
    }

    @Override
    public void actionReset() {
        timeModel.resetTime();
        updateUI();
    }

    @Override
    public void actionInc() {
        timeModel.incTime();
        updateUI();
    }

    @Override
    public void actionDec() {
        timeModel.decTime();
        updateUI();
    }

    @Override
    public void actionStartAlarm() {
        alarmService.startAlarm();
    }

    @Override
    public void actionStopAlarm() {
        alarmService.stopAlarm();
    }

    @Override
    public void onTick() {
        state.onTick();
    }

}
