package com.woka.android.iduty.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.woka.android.iduty.IDuty;
import com.woka.android.iduty.R;
import com.woka.android.iduty.adapter.TurnListAdapter;
import com.woka.android.iduty.entity.Clinic;
import com.woka.android.iduty.entity.Turn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TurnsActivity extends AppCompatActivity {

    private ListView lvTurns;
    private TurnListAdapter turnListAdapter;

    private List<Turn> turns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turns);

        initWidgets();

        readEntity();
    }

    private void initWidgets() {
        lvTurns = (ListView) findViewById(R.id.lvTurns);
    }

    private void readEntity() {
        String userUid = IDuty.APPLICATION.getUser().getUid();
        IDuty.APPLICATION.getFirebaseLoginManager().getReference("turns").child(userUid)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        turns = new ArrayList<Turn>();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Turn turn = snapshot.getValue(Turn.class);
                            turns.add(turn);
                        }
                        turnListAdapter = new TurnListAdapter(getApplication(), turns);

                        lvTurns.setAdapter(turnListAdapter);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

}
