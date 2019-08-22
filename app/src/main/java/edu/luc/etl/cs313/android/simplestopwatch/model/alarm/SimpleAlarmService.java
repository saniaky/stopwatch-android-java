package edu.luc.etl.cs313.android.simplestopwatch.model.alarm;

import android.content.Context;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;

public class SimpleAlarmService implements AlarmService {

    private final Ringtone r;

    public SimpleAlarmService(Context applicationContext) {
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        r = RingtoneManager.getRingtone(applicationContext, notification);
    }

    @Override
    public void startAlarm() {
        r.play();
    }

    @Override
    public void stopAlarm() {
        r.stop();
    }

}
