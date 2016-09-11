package com.woka.android.iduty.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.woka.android.iduty.IDuty;
import com.woka.android.iduty.R;
import com.woka.android.iduty.activity.FragmentCoordinatorInterface;
import com.woka.android.iduty.entity.Clinic;
import com.woka.android.iduty.entity.EntityInterface;
import com.woka.android.iduty.entity.Speciality;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SpecialtiesFragment extends Fragment {

    GridView gridview;
    FragmentCoordinatorInterface anInterface;

    private List<EntityInterface> specialities;
    private HashMap<String, Speciality> specialityHashMap;

    public SpecialtiesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_clinics, container, false);
        anInterface = IDuty.APPLICATION.getCoordinatorInterface();

        gridview = (GridView) view.findViewById(R.id.gridview);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                anInterface.changeFragment(Integer.toString(position), 2);
                IDuty.APPLICATION.getTurn().setSpecialityId(specialities.get(position).getId());
                IDuty.APPLICATION.getTurn().setSpecialityName(specialities.get(position).getName());
                Toast.makeText(getActivity(), "" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });

        readEntity();

        return view;
    }

    private void readEntity() {
        specialityHashMap = IDuty.APPLICATION.getMyClinic().getSpecialitieList();

        specialities = new ArrayList<>();

        for(Map.Entry<String, Speciality> e : specialityHashMap.entrySet()) {
            specialities.add(e.getValue());
        }
        gridview.setAdapter(new ImageAdapter(getActivity(), specialities));
    }

    public class ImageAdapter extends BaseAdapter {

        private Context context;
        private List<EntityInterface> entityList;

        public ImageAdapter(Context context, List<EntityInterface> entityList) {
            this.context = context;
            this.entityList = entityList;
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view =  inflater.inflate(R.layout.adapter_grid_item, null);

            TextView textView = (TextView) view.findViewById(R.id.tvClinic);
            textView.setText(entityList.get(position).getName());

            return view;
        }

        @Override
        public int getCount() {
            return entityList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

    }
}
