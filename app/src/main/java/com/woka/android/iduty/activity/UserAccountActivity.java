package com.woka.android.iduty.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.woka.android.iduty.IDuty;
import com.woka.android.iduty.R;
import com.woka.android.iduty.dialog.AccountDialog;
import com.woka.android.iduty.dialog.ContactDialog;
import com.woka.android.iduty.dialog.PersonalInfoDialog;
import com.woka.android.iduty.entity.User;

public class UserAccountActivity extends AppCompatActivity {

    private LinearLayout llPersonalInfo, llContact, llAccount;
    private TextView tvLastName, tvFirstName, tvBirthAndAge, tvId, tvAddress, tvTel, tvCel, tvEmail;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account);

        user = IDuty.APPLICATION.getUser();

        initWidget();

        setValues();

        addListener();
    }

    private void initWidget() {
        llPersonalInfo = (LinearLayout) findViewById(R.id.llPersonalInfo);
        llContact = (LinearLayout) findViewById(R.id.llContact);
        llAccount = (LinearLayout) findViewById(R.id.llAccount);
        tvLastName = (TextView) findViewById(R.id.tvLastName);
        tvFirstName = (TextView) findViewById(R.id.tvFirstName);
        tvBirthAndAge = (TextView) findViewById(R.id.tvBirthAndAge);
        tvId = (TextView) findViewById(R.id.tvId);
        tvAddress = (TextView) findViewById(R.id.tvAddress);
        tvTel = (TextView) findViewById(R.id.tvTel);
        tvCel = (TextView) findViewById(R.id.tvCel);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
    }

    private void setValues() {
        if (user.getFirstName() != null) {
            tvFirstName.setText(user.getFirstName());
        }
        if (user.getLastName() != null) {
            tvLastName.setText(user.getLastName());
        }
        if (user.getBirth() != null || user.getAge() != 0) {
            tvBirthAndAge.setText(user.getBirth() + " - " + Integer.toString(user.getAge()) + " años");
        }
        if (user.getId() != 0) {
            tvId.setText(Integer.toString(user.getId()));
        }
        if (user.getAddress() != null) {
            tvAddress.setText(user.getAddress());
        }
        if (user.getTel() != 0) {
            tvTel.setText(Integer.toString(user.getTel()));
        }
        if (user.getCel() != 0) {
            tvCel.setText(Integer.toString(user.getCel()));
        }
        if (user.getEmail() != null) {
            tvEmail.setText(user.getEmail());
        }
    }

    private void addListener() {
        llPersonalInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPersonalInfoDialog(view);
            }
        });
        llContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showContactDialog(view);
            }
        });
        llAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAccountDialog(view);
            }
        });
    }

    private void showPersonalInfoDialog(View v){
        PersonalInfoDialog myDialog = new PersonalInfoDialog();
        myDialog.show(getFragmentManager(), "Dialog");
    }

    private void showContactDialog(View v){
        ContactDialog myDialog = new ContactDialog();
        myDialog.show(getFragmentManager(), "Dialog");
    }

    private void showAccountDialog(View v){
        AccountDialog myDialog = new AccountDialog();
        myDialog.show(getFragmentManager(), "Dialog");
    }
}