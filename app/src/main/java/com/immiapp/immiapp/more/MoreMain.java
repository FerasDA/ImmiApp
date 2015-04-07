package com.immiapp.immiapp.more;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.immiapp.immiapp.Database.DatabaseHandler;
import com.immiapp.immiapp.R;
import com.immiapp.immiapp.events.EventActivityAdd;


public class MoreMain extends Fragment {


    View view;
    final static String ARG_POSITION = "position";
    int mCurrentPosition = -1;


    private Button aboutBtn;
    private Button contactUsBtn;
    private Button immiPointsBtn;
    private TextView btnDescriptionText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(savedInstanceState != null)
            mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);

        view = inflater.inflate(R.layout.fragment_more, container, false);

        aboutBtn = (Button)view.findViewById(R.id.aboutBtn);
        contactUsBtn = (Button)view.findViewById(R.id.contactUsBtn);
        immiPointsBtn = (Button)view.findViewById(R.id.immiPointsBtn);

        setupAboutBtn();
        setupContactUsBtn();
        setUpImmiPointsBtn();

        return view;
    }

    private void setupAboutBtn() {
        aboutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnDescriptionText.setText("Immi is an event planning and finding social media app. It's main focus is finding local events near you.");
            }
        });
    }

    private void setupContactUsBtn() {
        contactUsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnDescriptionText.setText("Send us an email at osuimmiapp@gmail.com");
            }
        });
    }

    private void setUpImmiPointsBtn() {
        immiPointsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnDescriptionText.setText("Immi points track your popularity in our app. Events, up-votes, attendance, and more can raise your score.");
            }
        });
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
