package com.woka.android.iduty.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.woka.android.iduty.R;
import com.woka.android.iduty.entity.Turn;

import java.util.List;

public class TurnListAdapter extends ArrayAdapter<Turn> {
    private Context context;
    private List<Turn> list;

    private TextView tvClinic, tvSpeciality, tvSpecialist;

    public TurnListAdapter(Context context, List<Turn> list) {
        super(context, R.layout.adapter_turn_row, android.R.id.text1, list);
        this.context = context;
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.adapter_turn_row, parent, false);

        initWidget(row);
        setValues(list.get(position));

        return row;
    }

    private void initWidget(View row) {
        tvClinic = (TextView) row.findViewById(R.id.tvClinic);
        tvSpeciality = (TextView) row.findViewById(R.id.tvSpeciality);
        tvSpecialist = (TextView) row.findViewById(R.id.tvSpecialist);
    }

    private void setValues(Turn turn) {
        tvClinic.setText(turn.getClinicName());
        tvSpeciality.setText(turn.getSpecialityName());
        tvSpecialist.setText(turn.getSpecialistName());
    }
}
