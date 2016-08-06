package com.woka.android.iduty.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.woka.android.iduty.R;
import com.woka.android.iduty.adapter.TurnListAdapter;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private ListView lvHistories;
    private TurnListAdapter turnListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        initWidgets();
    }

    private void initWidgets() {
        lvHistories = (ListView) findViewById(R.id.lvHistories);

        List<String> list = new ArrayList<>();
        list.add("hola");
        list.add("hola");
        list.add("hola");
        list.add("hola");
        list.add("hola");
        turnListAdapter = new TurnListAdapter(this, list);

        lvHistories.setAdapter(turnListAdapter);
    }
}
