package com.woka.android.iduty.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.woka.android.iduty.R;

import java.util.List;

public class TurnListAdapter extends ArrayAdapter<String> {
    Context context;
    List<String> list;

    public TurnListAdapter(Context context, List<String> list) {
        super(context, R.layout.adapter_turn_row, android.R.id.text1, list);
        this.context = context;
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.adapter_turn_row, parent, false);


        return row;
    }
}
