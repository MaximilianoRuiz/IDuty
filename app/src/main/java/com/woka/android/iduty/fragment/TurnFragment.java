package com.woka.android.iduty.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.woka.android.iduty.IDuty;
import com.woka.android.iduty.R;
import com.woka.android.iduty.entity.Turn;

public class TurnFragment extends Fragment {

    private TextView tvClinic, tvSpeciality, tvSpecialist;
    private RadioGroup radioGroup;

    public TurnFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_turn, container, false);

        initWidgets(view);
        setListeners();
        setValues();

        return view;
    }

    private void initWidgets(View view) {
        radioGroup = (RadioGroup) view.findViewById(R.id.rbGroup);
        tvClinic = (TextView) view.findViewById(R.id.tvClinic);
        tvSpeciality = (TextView) view.findViewById(R.id.tvSpeciality);
        tvSpecialist = (TextView) view.findViewById(R.id.tvSpecialist);
    }

    private void setListeners() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId) {
                    case R.id.rbMorning:
                        IDuty.APPLICATION.getTurn().setHour("Mañana");
                        break;
                    case R.id.rbAfterNoon:
                        IDuty.APPLICATION.getTurn().setHour("Tarde");
                        break;
                    default:
                        Toast.makeText(getActivity(), "Selecciona un Horario",
                                Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    private void setValues() {
        Turn turn = IDuty.APPLICATION.getTurn();
        tvClinic.setText(turn.getClinicName());
        tvSpeciality.setText(turn.getSpecialityName());
        tvSpecialist.setText(turn.getSpecialistName());
    }
}
