package com.siigo.www.work_list.UI;


import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.siigo.www.work_list.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static com.siigo.www.work_list.UI.CustomActions.waitFor;


@LargeTest
@RunWith(AndroidJUnit4.class)
public class CreateListTest {

    @Rule
    public ActivityTestRule<ListOfListsActivity> mActivityTestRule = new ActivityTestRule<>(ListOfListsActivity.class);

    @Test
    public void createListTest() {
        ViewInteraction floatingActionButton = onView(withId(R.id.add_list_fab));

        floatingActionButton.perform(click());

        ViewInteraction appCompatEditText = onView(withId(R.id.list_name_edit_text));

        appCompatEditText.perform(replaceText("list1"), closeSoftKeyboard());

        ViewInteraction createButton = onView(withId(R.id.create_button));

        createButton.perform(click());

        DataInteraction item = onData(hasToString(startsWith("list1"))).inAdapterView(withId(R.id.list_of_list_list_view)).atPosition(0);
        item.perform(click());

        ViewInteraction addTaskFab = onView(withId(R.id.add_task_fab));

        addTaskFab.perform(click());


        ViewInteraction taskNameEditText = onView(withId(R.id.task_name_edit_text));
        taskNameEditText.perform(replaceText("task1"), closeSoftKeyboard());

        ViewInteraction taskDescriptionEditText = onView(withId(R.id.task_description_edit_text));
        taskDescriptionEditText.perform(replaceText("description1"), closeSoftKeyboard());

        ViewInteraction taskHourEditText = onView(withId(R.id.task_hour_edit_text));
        taskHourEditText.perform(replaceText("12:30"), closeSoftKeyboard());

        ViewInteraction taskDateEditText = onView(withId(R.id.task_date_edit_text));
        taskDateEditText.perform(replaceText("22/01/2020"), closeSoftKeyboard());


        ViewInteraction createTaskFab = onView(withId(R.id.create_task_fab));
        createTaskFab.perform(click());

        DataInteraction task_item = onData(hasToString(startsWith("task1"))).inAdapterView(withId(R.id.task_list_list_view)).atPosition(0);


        onView(isRoot()).perform(waitFor(500));
        task_item.perform(click());

        ViewInteraction taskNameEditText2 = onView(withId(R.id.task_name_edit_text));
        ViewInteraction taskDescriptionEditText2 = onView(withId(R.id.task_description_edit_text));
        ViewInteraction taskHourEditText2 = onView(withId(R.id.task_hour_edit_text));
        ViewInteraction taskDateEditText2 = onView(withId(R.id.task_date_edit_text));


        taskDateEditText2.check(matches(withText("22/01/2020")));
        taskDescriptionEditText2.check(matches(withText("description1")));
        taskHourEditText2.check(matches(withText("12:30")));
        taskNameEditText2.check(matches(withText("task1")));

        taskNameEditText2.perform(replaceText("edited task"));
        taskDescriptionEditText2.perform(replaceText("edited description"));
        taskHourEditText2.perform(replaceText("15:30"));
        taskDateEditText2.perform(replaceText("22/01/1999"));

        ViewInteraction createTaskFab2 = onView(withId(R.id.create_task_fab));
        createTaskFab2.perform(click());
        DataInteraction task_item2 = onData(hasToString(startsWith("edited task"))).inAdapterView(withId(R.id.task_list_list_view)).atPosition(0);
        onView(isRoot()).perform(waitFor(500));
        task_item2.perform(click());

        ViewInteraction taskNameEditText3 = onView(withId(R.id.task_name_edit_text));
        ViewInteraction taskDescriptionEditText3 = onView(withId(R.id.task_description_edit_text));
        ViewInteraction taskHourEditText3 = onView(withId(R.id.task_hour_edit_text));
        ViewInteraction taskDateEditText3 = onView(withId(R.id.task_date_edit_text));

        taskDateEditText3.check(matches(withText("22/01/1999")));
        taskDescriptionEditText3.check(matches(withText("edited description")));
        taskHourEditText3.check(matches(withText("15:30")));
        taskNameEditText3.check(matches(withText("edited task")));










    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
