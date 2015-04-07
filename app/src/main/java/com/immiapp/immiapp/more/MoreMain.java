package com.immiapp.immiapp.more;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.immiapp.immiapp.Database.DatabaseHandler;
import com.immiapp.immiapp.R;
import com.immiapp.immiapp.events.EventActivityAdd;


public class MoreMain extends Fragment {


    View view;
    final static String ARG_POSITION = "position";
    int mCurrentPosition = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(savedInstanceState != null)
            mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);

        view = inflater.inflate(R.layout.fragment_more, container, false);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setHasOptionsMenu(true);
    }



    @Override
    public void onResume(){
        super.onResume();
    }

}
