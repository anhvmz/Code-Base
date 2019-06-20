package com.heligate.codebase.base;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import com.heligate.codebase.R;

/**
 * Created by EG SonNguyen on 11/13/2017.
 * Eastgate Software
 */
public class LoadingDialogFragment extends DialogFragment {

    private static final String TAG = LoadingDialogFragment.class.getSimpleName();

    public static void show(FragmentManager fragmentManager) {
        LoadingDialogFragment loadingDialogFragment = (LoadingDialogFragment) fragmentManager.findFragmentByTag(TAG);
        if (loadingDialogFragment == null) {
            loadingDialogFragment = new LoadingDialogFragment();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.add(loadingDialogFragment, TAG);
            ft.commitAllowingStateLoss();
        }
    }

    public static void dismiss(FragmentManager fragmentManager) {
        try {
            LoadingDialogFragment loadingDialogFragment = (LoadingDialogFragment) fragmentManager.findFragmentByTag(TAG);
            if (loadingDialogFragment != null) {
                loadingDialogFragment.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setCancelable(false);
        return inflater.inflate(R.layout.dialog_loading, container, false);
    }
}
