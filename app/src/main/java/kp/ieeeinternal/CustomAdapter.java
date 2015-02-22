package kp.ieeeinternal;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Krishna Kalubandi on 22-02-2015.
 */
public class CustomAdapter extends BaseAdapter {
    private final Activity context;
    List<AnnouncementData> aData;
    public CustomAdapter(Activity context) {
        this.context = context;
        aData = getAnnouncementData();
    }

    @Override
    public int getCount() {
        return aData.size();
    }

    @Override
    public Object getItem(int position) {
        return aData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.announcement_listview, parent, false);
        }
        TextView personOne = (TextView) convertView.findViewById(R.id.tvPersonOne);
        Log.d("Textview" , personOne.toString());
        TextView personTwo = (TextView) convertView.findViewById(R.id.tvPersonTwo);
        TextView venue = (TextView) convertView.findViewById(R.id.tvVenue);
        TextView time = (TextView) convertView.findViewById(R.id.tvTime);
        AnnouncementData current = aData.get(position);
        personOne.setText(current.getPersonOne());
        personTwo.setText(current.getPersonTwo());
        venue.setText(current.getVenue());
        time.setText(current.getTime());
        return convertView;
       }
    public List<AnnouncementData> getAnnouncementData() {
        List<AnnouncementData> announcementDataCollection = new ArrayList<AnnouncementData>();
        for(int i = 0; i < 5; i++) {
            AnnouncementData announcementData = new AnnouncementData();
            announcementData.setPersonOne("First Person " + i);
            announcementData.setPersonTwo("Second Person " + i);
            announcementData.setVenue("SJT");
            announcementData.setTime("10-11PM");
            announcementDataCollection.add(announcementData);
        }
        return announcementDataCollection;
    }
}
