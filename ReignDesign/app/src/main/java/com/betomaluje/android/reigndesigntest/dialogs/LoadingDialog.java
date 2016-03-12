package com.betomaluje.android.reigndesigntest.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.view.WindowManager;

import com.betomaluje.android.reigndesigntest.R;

/**
 * Created by betomaluje on 3/12/16.
 */
public class LoadingDialog extends Dialog {

    public LoadingDialog(Activity activity) {
        super(activity, R.style.Theme_FullScreenDialog);

        setCanceledOnTouchOutside(true);
        setCancelable(true);
        setContentView(R.layout.dialog_progress);

        final Window window = getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#CCF8F8F8")));
    }
}