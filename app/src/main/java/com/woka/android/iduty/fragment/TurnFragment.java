package com.woka.android.iduty.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.woka.android.iduty.IDuty;
import com.woka.android.iduty.R;
import com.woka.android.iduty.entity.Turn;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TurnFragment extends Fragment {

    private TextView tvClinic, tvSpeciality, tvSpecialist;
    private RadioGroup radioGroup;
    private Button btnSendTurn;

    private Turn turn;

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
        btnSendTurn = (Button) view.findViewById(R.id.btnSendTurn);

        turn = IDuty.APPLICATION.getTurn();
    }

    private void setListeners() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId) {
                    case R.id.rbMorning:
                        turn.setHour("Mañana");
                        break;
                    case R.id.rbAfterNoon:
                        turn.setHour("Tarde");
                        break;
                    default:
                        Toast.makeText(getActivity(), "Selecciona un Horario",
                                Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        btnSendTurn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
                Date date = new Date();
                turn.setId(dateFormat.format(date));
                turn.setUserUid(IDuty.APPLICATION.getUser().getUid());
                turn.setUserName(IDuty.APPLICATION.getUser().getLastName());
                IDuty.APPLICATION.sendTurn(turn);
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
