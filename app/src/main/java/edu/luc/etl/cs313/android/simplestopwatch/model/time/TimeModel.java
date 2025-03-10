package edu.luc.etl.cs313.android.simplestopwatch.model.time;

/**
 * The passive data model of the stopwatch.
 * It does not emit any events.
 *
 * @author laufer
 */
public interface TimeModel {

    void start();

    void stop();

    void resetTime();

    void incTime();

    void decTime();

    int getTime();

}
