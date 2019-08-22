package edu.luc.etl.cs313.android.simplestopwatch.model;

import android.content.Context;

import edu.luc.etl.cs313.android.simplestopwatch.common.StopwatchUIUpdateListener;
import edu.luc.etl.cs313.android.simplestopwatch.model.state.DefaultStopwatchStateMachine;
import edu.luc.etl.cs313.android.simplestopwatch.model.state.StopwatchStateMachine;
import edu.luc.etl.cs313.android.simplestopwatch.model.time.DefaultTimeModel;

/**
 * An implementation of the model facade.
 *
 * @author laufer
 */
public class ConcreteStopwatchModelFacade implements StopwatchModelFacade {

    private StopwatchStateMachine stateMachine;

    public ConcreteStopwatchModelFacade(Context applicationContext) {
        DefaultTimeModel timeModel = new DefaultTimeModel();
        stateMachine = new DefaultStopwatchStateMachine(timeModel, applicationContext);
        timeModel.setListener(stateMachine);
    }

    @Override
    public void onStart() {
        stateMachine.actionInit();
    }

    @Override
    public void setUIUpdateListener(final StopwatchUIUpdateListener listener) {
        stateMachine.setUIUpdateListener(listener);
    }

    @Override
    public void onButtonClick() {
        stateMachine.onButtonClick();
    }

}
