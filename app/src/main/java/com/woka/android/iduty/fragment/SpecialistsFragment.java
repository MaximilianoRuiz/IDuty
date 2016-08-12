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

import com.woka.android.iduty.R;
import com.woka.android.iduty.activity.FragmentCoordinatorInterface;

import java.util.ArrayList;
import java.util.List;

public class SpecialistsFragment extends Fragment {

    GridView gridview;

    FragmentCoordinatorInterface anInterface;

    public SpecialistsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_clinics, container, false);

        anInterface = (FragmentCoordinatorInterface) getArguments().getSerializable("ACTIVITY");
        List<String> specialists = new ArrayList<>();
        specialists.add("Especialista1");
        specialists.add("Especialista2");
        specialists.add("Especialista3");
        specialists.add("Especialista4");
        specialists.add("Especialista5");

        gridview = (GridView) view.findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(getActivity(), specialists));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
//                anInterface.changeFragment(Integer.toString(position), 1);
                Toast.makeText(getActivity(), "" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    public class ImageAdapter extends BaseAdapter {

        private Context context;
        private List<String> clinics;

        public ImageAdapter(Context context, List<String> clinics) {
            this.context = context;
            this.clinics = clinics;
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view =  inflater.inflate(R.layout.adapter_grid_item, null);

            TextView textView = (TextView) view.findViewById(R.id.tvClinic);
            textView.setText(clinics.get(position));

            return view;
        }

        @Override
        public int getCount() {
            return clinics.size();
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
