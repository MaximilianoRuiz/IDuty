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

public class ContactDialog extends DialogFragment {

    private EditText etAddress, etTel, etCel, etEmail;

    private User user;

    public ContactDialog() {
        user = IDuty.APPLICATION.getUser();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(R.layout.dialog_contact, null);

        initWidgets(view);

        setValues();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        builder.setTitle("Contacto");

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
        etAddress = (EditText) view.findViewById(R.id.etAddress);
        etTel = (EditText) view.findViewById(R.id.etTel);
        etCel = (EditText) view.findViewById(R.id.etCel);
        etEmail = (EditText) view.findViewById(R.id.etEmail);
    }

    private void setValues() {
        if (user.getAddress() != null) {
            etAddress.setText(user.getAddress());
        }
        if (user.getTel() != 0) {
            etTel.setText(Integer.toString(user.getTel()));
        }
        if (user.getCel() != 0) {
            etCel.setText(Integer.toString(user.getCel()));
        }
        if (user.getEmail() != null) {
            etEmail.setText(user.getEmail());
        }
    }

    private void modifyUserValues() {
        if (! "".equals(etAddress.getText().toString())) {
            user.setAddress(etAddress.getText().toString());
        }
        if (! "".equals(etEmail.getText().toString())) {
            user.setEmail(etEmail.getText().toString());
        }
        if (! "".equals(etTel.getText().toString())) {
            user.setTel(Integer.parseInt(etTel.getText().toString()));
        }
        if (! "".equals(etCel.getText().toString())) {
            user.setCel(Integer.parseInt(etCel.getText().toString()));
        }
    }
}
