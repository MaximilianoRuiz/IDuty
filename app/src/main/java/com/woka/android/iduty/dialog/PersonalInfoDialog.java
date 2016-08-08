package com.woka.android.iduty.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.woka.android.iduty.IDuty;
import com.woka.android.iduty.R;
import com.woka.android.iduty.activity.UserAccountActivity;
import com.woka.android.iduty.entity.User;

public class PersonalInfoDialog extends DialogFragment {

    private EditText etLastName, etFirstName, etId, etAge, etBirth;

    private User user;

    public PersonalInfoDialog() {
        user =IDuty.APPLICATION.getUser();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(R.layout.dialog_personal_info, null);

        initWidgets(view);

        setValues();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        builder.setTitle("Informacion Personal");

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "Cancelar", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                modifyUserValues();
                UserAccountActivity activity = (UserAccountActivity) getActivity();
                activity.setValues();
                IDuty.APPLICATION.getFirebaseLoginManager().updateUser(user);
                Toast.makeText(getActivity(), "Aceptar", Toast.LENGTH_SHORT).show();
            }
        });

        return builder.create();
    }

    private void initWidgets(View view) {
        etLastName = (EditText) view.findViewById(R.id.etLastName);
        etFirstName = (EditText) view.findViewById(R.id.etFirstName);
        etId = (EditText) view.findViewById(R.id.etId);
        etAge = (EditText) view.findViewById(R.id.etAge);
        etBirth = (EditText) view.findViewById(R.id.etBirth);
    }

    private void setValues() {
        if (user.getFirstName() != null) {
            etFirstName.setText(user.getFirstName());
        }
        if (user.getLastName() != null) {
            etLastName.setText(user.getLastName());
        }
        if (user.getId() != 0) {
            etId.setText(Integer.toString(user.getId()));
        }
        if (user.getAge() != 0) {
            etAge.setText(Integer.toString(user.getAge()));
        }
        if (user.getBirth() != null) {
            etBirth.setText(user.getBirth());
        }
    }

    private void modifyUserValues() {
        if (! "".equals(etFirstName.getText().toString())) {
            user.setFirstName(etFirstName.getText().toString());
        }
        if (! "".equals(etLastName.getText().toString())) {
            user.setLastName(etLastName.getText().toString());
        }
        if (! "".equals(etId.getText().toString())) {
            user.setId(Integer.parseInt(etId.getText().toString()));
        }
        if (! "".equals(etAge.getText().toString())) {
            user.setAge(Integer.parseInt(etAge.getText().toString()));
        }
        if (! "".equals(etBirth.getText().toString())) {
            user.setBirth(etBirth.getText().toString());
        }
    }
}
