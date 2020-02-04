package com.siigo.www.work_list.UI;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.view.View;
import org.hamcrest.Matcher;
public class CustomActions {
    public static ViewAction waitFor(final long delay) {
        return new ViewAction() {
            @Override public Matcher<View> getConstraints() {
                return ViewMatchers.isRoot();
            }
            @Override public String getDescription() {
                return "wait for " + delay + "milliseconds";
            }
            @Override public void perform(UiController uiController, View view) {
                uiController.loopMainThreadForAtLeast(delay);
            }
        };
    }
}
