package com.immiapp.immiapp.events;

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

import java.util.List;

public class EventsMain extends Fragment {

    DatabaseHandler db;
    List<Event> events;
    private ListView list;
    View view;
    final static String ARG_POSITION = "position";
    int mCurrentPosition = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(savedInstanceState != null)
            mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);

        view = inflater.inflate(R.layout.activity_events, container, false);
        list = (ListView) view.findViewById(R.id.listView1);

        db = new DatabaseHandler(view.getContext());

        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                Event event = (Event)parent.getItemAtPosition(position);
                bundle.putInt(db.KEY_ROWID_EVENTS, event.getID());
                Intent intent = new Intent(view.getContext(), EventActivityShow.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onResume(){
        super.onResume();
        getAllEvents();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        MenuItem searchItem = menu.findItem(R.id.action_search);
        //SearchView mSearchView = (SearchView)searchItem.getActionView();
        //mSearchView.setQueryHint("Search...");
        //mSearchView.setOnQueryTextListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.action_add:
                //showHideEditText();
                Log.e("Menu add events", "click");
                Intent intent = new Intent(view.getContext(), EventActivityAdd.class);
                startActivity(intent);
                return true;
            case R.id.action_search:
                Log.e("Menu search events", "click");
                return true;
            default:
                break;
        }
        return false;
    }

    private void getAllEvents() {
        events = db.getAllEvents();
        if (events.isEmpty()) Log.d("Array of Events", "Empty");
        final EventsAdapter adapter = new EventsAdapter(view.getContext(), R.layout.list_events, events);
        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

        list.setAdapter(adapter);

    }

}
