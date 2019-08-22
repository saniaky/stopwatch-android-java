package edu.luc.etl.cs313.android.simplestopwatch.model.state;

/**
 * The restricted view states have of their surrounding state machine.
 * This is a client-specific interface in Peter Coad's terminology.
 *
 * @author laufer
 */
interface StopwatchSMStateView {

    int getTime();

    // transitions
    void toRunningState();
    void toStoppedState();
    void toWaitingState();
    void toAlarmState();

    // actions
    void actionInit();
    void actionReset();
    void actionInc();
    void actionDec();
    void actionStartAlarm();
    void actionStopAlarm();

}
