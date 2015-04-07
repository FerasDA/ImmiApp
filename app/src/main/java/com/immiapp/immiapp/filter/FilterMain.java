package com.immiapp.immiapp.filter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.immiapp.immiapp.Database.DatabaseHandler;
import com.immiapp.immiapp.R;
import com.immiapp.immiapp.events.Event;
import com.immiapp.immiapp.events.EventActivityAdd;
import com.immiapp.immiapp.events.EventActivityShow;
import com.immiapp.immiapp.events.EventsAdapter;

import java.util.List;


public class FilterMain extends Fragment {

    DatabaseHandler db;
    //List<Filter> filter;
    //private ListView list;

    View view;
    final static String ARG_POSITION = "position";
    int mCurrentPosition = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(savedInstanceState != null)
            mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);

        view = inflater.inflate(R.layout.activity_preferences, container, false);
        /*************
         * save to the database later from here
         * ***********/
        //list = (ListView) view.findViewById(R.id.listView2);
/*
        db = new DatabaseHandler(view.getContext());

        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                //Filter filter = (Filter)parent.getItemAtPosition(position);
                //bundle.putInt(db.KEY_ROWID_EVENTS, filter.getID());
                Intent intent = new Intent(view.getContext(), FilterActivityShow.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
*/
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
