package com.woka.android.iduty.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.facebook.login.widget.LoginButton;
import com.woka.android.iduty.R;
import com.woka.android.iduty.data.UserAndPassLogin;

public class SignUpFragment extends Fragment implements FragmentInteractorInterface {

    private EditText etEmailSignUp;
    private EditText etPasswordSignUp;
    private EditText etPasswordSignUpRepeat;
    private Button btnSignUp;
    private ProgressBar pbEmailAndPassSignUp;

    private FragmentInteractorInterface iInteractor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        iInteractor = this;

        initWidgets(view);

        addListeners();

        return view;
    }

    private void initWidgets(View view) {
        etEmailSignUp = (EditText) view.findViewById(R.id.etEmailSignUp);
        etPasswordSignUp = (EditText) view.findViewById(R.id.etPasswordSignUp);
        etPasswordSignUpRepeat = (EditText) view.findViewById(R.id.etPasswordSignUpRepeat);

        btnSignUp = (Button) view.findViewById(R.id.btnSignUp);

        pbEmailAndPassSignUp = (ProgressBar) view.findViewById(R.id.pbEmailAndPassSignUp);
    }

    private void addListeners() {
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmailSignUp.getText().toString();
                String password = etPasswordSignUp.getText().toString();
                String passwordRepeat = etPasswordSignUpRepeat.getText().toString();
                if("".equals(email) && "".equals(password)) {
                    Toast.makeText(getActivity(), "Empty fields", Toast.LENGTH_SHORT).show();
                } else if(!password.equals(passwordRepeat)){
                    Toast.makeText(getActivity(), "Passwords not equals", Toast.LENGTH_SHORT).show();
                } else {
                    UserAndPassLogin login = new UserAndPassLogin(iInteractor, email, password);
                    login.signUp();
                }
            }
        });
    }

    @Override
    public Activity getActivityInstance() {
        return getActivity();
    }

    @Override
    public LoginButton getLoginButton() {
        return null;
    }
}
