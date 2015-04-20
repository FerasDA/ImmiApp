package com.immiapp.immiapp.events;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.immiapp.immiapp.Database.DatabaseHandler;
import com.immiapp.immiapp.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EventsAdapter extends ArrayAdapter<Event> {
    int resource;
    Context context;
    DatabaseHandler db;
    List<Event> eventList;
    @SuppressLint("UseSparseArrays")
    public HashMap<Integer, Boolean> mSelection = new HashMap<Integer, Boolean>() ;

    public EventsAdapter(Context context, int resource, List<Event> events) {
        super(context, resource, events);
        this.context = context;
        this.resource = resource;
        this.eventList = events;
        this.db = new DatabaseHandler(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        LayoutInflater inflater;
        ViewHolder viewHolder;
        Event event = getItem(position);

        //Inflate the view
        if(convertView==null){
            inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resource, null);

            viewHolder = new ViewHolder();
            viewHolder.title = (TextView)convertView.findViewById(R.id.textViewEventName);
            viewHolder.date = (TextView)convertView.findViewById(R.id.textViewEventDate);
            viewHolder.time = (TextView)convertView.findViewById(R.id.textViewEventTime);
            convertView.setTag(viewHolder);
        } else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.title.setText(event.getTitle());
        viewHolder.date.setText(event.getDate());
        viewHolder.time.setText(event.getTime());

        if (position % 2 == 1) {
            convertView.setBackgroundColor(Color.parseColor("#5FFFCE"));
        } else {
            convertView.setBackgroundColor(Color.parseColor("#FFBBFFDB"));
        }

        return convertView;
    }

	/*@Override
	public Filter getFilter(){

	    if(filter == null){
	        filter = new PizzaFilter();
	    }
	    return filter;
	}*/


    public void removeEvents(){

        Iterator iterator = mSelection.entrySet().iterator();
        List<Integer> indexs = new ArrayList<Integer>();
        while(iterator.hasNext()){
            Map.Entry<Integer, Boolean> e = (Map.Entry<Integer, Boolean>)iterator.next();
            indexs.add(e.getKey().intValue());
        }

        Collections.sort(indexs, Collections.reverseOrder());
        for (int i : indexs) {
            Event event = eventList.get(i);
            db.deleteEvent(event);
            eventList.remove(i);
        }
        clearSelection();
        notifyDataSetChanged();
    }

    public void setNewSlection(int position, boolean value){
        mSelection.put(position, value);
        notifyDataSetChanged();
    }

    public boolean isPositionCheked(int position){
        Boolean result = mSelection.get(position);
        return result == null ? false : result;
    }

    public Set<Integer> getCurrenCheckedPosition() {
        return mSelection.keySet();
    }

    public void removeSelection(int position){
        mSelection.remove(position);
        notifyDataSetChanged();
    }

    public void clearSelection(){
        mSelection = new HashMap<Integer, Boolean>();
        notifyDataSetChanged();
    }

    static class ViewHolder{
        protected TextView title;
        protected TextView date;
        protected TextView time;
    }
}
