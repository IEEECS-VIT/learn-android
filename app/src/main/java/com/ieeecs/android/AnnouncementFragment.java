package com.ieeecs.android;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;



/**
 * Created by Krishna Kalubandi on 22-02-2015.
 */
public class AnnouncementFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.announcement_fragment,container,false);
        ListView mCustomListView = (ListView) rootView.findViewById(R.id.announcementListView);
        mCustomListView.setAdapter(new CustomAdapter(getActivity()));
        return  rootView;
    }
}
