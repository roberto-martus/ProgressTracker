package com.cleancoder.base.android.util;

import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by Leonid on 16.12.2014.
 */
public class ViewUtils {

    public static int[] getRelativeLayoutRules(View view) {
        return ((RelativeLayout.LayoutParams) view.getLayoutParams()).getRules();
    }

    public static boolean hasRules(View view, int... rules) {
        int[] viewRules = getRelativeLayoutRules(view);
        for (int rule : rules) {
            if (viewRules[rule] == 0) {
                return false;
            }
        }
        return true;
    }

}
