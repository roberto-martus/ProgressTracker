package com.robertomartus.progresstracker.ui;

import android.support.v4.app.Fragment;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.cleancoder.base.android.util.TaggedLogger;
import com.cleancoder.base.android.util.ViewUtils;
import com.cleancoder.base.common.util.ValueWrapper;
import com.robertomartus.progresstracker.ProgressableItemFragment;
import com.robertomartus.progresstracker.R;
import com.robertomartus.progresstracker.testhelpers.ProgressableItemFragmentTestHelperActivity;
import com.robotium.solo.Solo;

import junit.framework.Assert;

/**
 * Created by Leonid on 15.12.2014.
 */
public class ProgressableItemFragmentTest extends ActivityInstrumentationTestCase2<ProgressableItemFragmentTestHelperActivity> {

    private static final TaggedLogger logger = TaggedLogger.forClass(ProgressableItemFragmentTest.class);

    private static final java.lang.String FRAGMENT_TAG = ProgressableItemFragment.class.getName();

    private Fragment fragment;
    private Solo solo;

    public ProgressableItemFragmentTest() {
        super(ProgressableItemFragmentTestHelperActivity.class);
    }

    public void setUp() throws Exception {
        getActivity().setFragmentCallbacks(new ProgressableItemFragment.DefaultCallbacks());
        solo = new Solo(getInstrumentation(), getActivity());
        getActivity().getSupportFragmentManager().beginTransaction()
                .add(R.id.container, new ProgressableItemFragment(), FRAGMENT_TAG)
                .commit();
        solo.waitForFragmentByTag(FRAGMENT_TAG);
        fragment = getActivity().getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG);
    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }

    public void testAbleToFindFragment() {
        Assert.assertNotNull(fragment);
    }

    public void testFragmentIsVisible() throws Exception {
        Assert.assertTrue(fragment.isVisible());
    }

    public void testFragmentHasClickableView_Add() {
        View addView = fragment.getView().findViewById(R.id.add_button);
        Assert.assertNotNull(addView);
        Assert.assertTrue(addView.isClickable());
        Assert.assertTrue(addView.isEnabled());
        Assert.assertEquals(View.VISIBLE, addView.getVisibility());
    }

    public void testFragmentHasClickableView_Remove() {
        View removeView = fragment.getView().findViewById(R.id.remove_button);
        Assert.assertNotNull(removeView);
        Assert.assertTrue(removeView.isClickable());
        Assert.assertTrue(removeView.isEnabled());
        Assert.assertEquals(View.VISIBLE, removeView.getVisibility());
    }

    public void testClickableViewAddPlacedCorrectly() {
        View addButton = fragment.getView().findViewById(R.id.add_button);
        Assert.assertTrue(ViewUtils.hasRules(addButton,
                RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.ALIGN_PARENT_RIGHT));
        Assert.assertEquals(ViewGroup.LayoutParams.WRAP_CONTENT, addButton.getLayoutParams().width);
        Assert.assertEquals(ViewGroup.LayoutParams.WRAP_CONTENT, addButton.getLayoutParams().height);
    }


    public void testClickableViewRemovePlacedCorrectly() {
        View addButton = fragment.getView().findViewById(R.id.remove_button);
        Assert.assertTrue(ViewUtils.hasRules(addButton,
                RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.ALIGN_PARENT_LEFT));
        Assert.assertEquals(ViewGroup.LayoutParams.WRAP_CONTENT, addButton.getLayoutParams().width);
        Assert.assertEquals(ViewGroup.LayoutParams.WRAP_CONTENT, addButton.getLayoutParams().height);
    }

    @UiThreadTest
    public void testClickOnAddButton() {
        final View addButton = fragment.getView().findViewById(R.id.add_button);
        final ValueWrapper<Boolean> callbackInvoked = new ValueWrapper<>(false);
        final ValueWrapper<Boolean> buttonWasntEnabledWhileClickWasHandling = new ValueWrapper<>(false);
        getActivity().setFragmentCallbacks(new ProgressableItemFragment.DefaultCallbacks() {
            @Override
            public void onAddButtonClicked() {
                callbackInvoked.setValue(true);
                buttonWasntEnabledWhileClickWasHandling.setValue(addButton.isEnabled() == false);
            }
        });
        addButton.performClick();
        Assert.assertTrue(callbackInvoked.getValue());
        Assert.assertTrue(buttonWasntEnabledWhileClickWasHandling.getValue());
        Assert.assertTrue(addButton.isEnabled());
    }

    @UiThreadTest
    public void testClickOnRemoveButton() {
        final View removeButton = fragment.getView().findViewById(R.id.remove_button);
        final ValueWrapper<Boolean> callbackInvoked = new ValueWrapper<>(false);
        final ValueWrapper<Boolean> buttonWasntEnabledWhileClickWasHandling = new ValueWrapper<>(false);
        getActivity().setFragmentCallbacks(new ProgressableItemFragment.DefaultCallbacks() {
            @Override
            public void onRemoveButtonClicked() {
                callbackInvoked.setValue(true);
                buttonWasntEnabledWhileClickWasHandling.setValue(removeButton.isEnabled() == false);
            }
        });
        removeButton.performClick();
        Assert.assertTrue(callbackInvoked.getValue());
        Assert.assertTrue(buttonWasntEnabledWhileClickWasHandling.getValue());
        Assert.assertTrue(removeButton.isEnabled());
    }

}
