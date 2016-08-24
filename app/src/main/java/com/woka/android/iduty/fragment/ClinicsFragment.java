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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.woka.android.iduty.IDuty;
import com.woka.android.iduty.R;
import com.woka.android.iduty.activity.FragmentCoordinatorInterface;
import com.woka.android.iduty.entity.Clinic;
import com.woka.android.iduty.entity.EntityInterface;

import java.util.ArrayList;
import java.util.List;

public class ClinicsFragment extends Fragment {

    GridView gridview;
    FragmentCoordinatorInterface anInterface;

    private List<EntityInterface> clinics;

    public ClinicsFragment() {
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
                anInterface.changeFragment(Integer.toString(position), 1);

                IDuty.APPLICATION.createTurn();
                IDuty.APPLICATION.getTurn().setClinic((Clinic) clinics.get(position));
                Toast.makeText(getActivity(), "" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });

        readEntity();

        return view;
    }

    private void readEntity() {
        IDuty.APPLICATION.getFirebaseLoginManager().getReference("clinics")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        clinics = new ArrayList<>();
                        for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Clinic clinic = snapshot.getValue(Clinic.class);
                            ArrayList list = (ArrayList) snapshot.child("specialities").getValue();
                            clinic.setSpecialitieList(list);
                            clinics.add(clinic);
                        }
                        gridview.setAdapter(new ImageAdapter(getActivity(), clinics));
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    class ImageAdapter extends BaseAdapter {

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
            textView.setText(entityList.get(position).getEntityName());

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
