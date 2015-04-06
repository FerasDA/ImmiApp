package com.immiapp.immiapp.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.immiapp.immiapp.R;

/**
 * Created by Feras on 3/8/2015.
 */

public class TextFieldsFragment extends Fragment {
    private TextView mRegularTextView;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_signup, container, false);

        return v;
    }
}
