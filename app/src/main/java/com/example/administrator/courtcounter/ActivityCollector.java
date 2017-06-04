package com.example.administrator.courtcounter;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/4.
 */



/* Try to manage all of the activity
* Because I just want to create a big App,not just a demo.
* In this app,we just create a login activity,if you were not signed in.
* You will not get access into this app mainActivity*/
public class ActivityCollector {
    public static List<Activity> activities = new ArrayList<>();
    public  static void addActivity (Activity activity) {
        activities.add(activity);

    }
    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }
    public static void finishAll() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}
