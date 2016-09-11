package com.woka.android.iduty.activity;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.woka.android.iduty.IDuty;
import com.woka.android.iduty.R;
import com.woka.android.iduty.data.FirebaseLoginManager;
import com.woka.android.iduty.entity.Clinic;
import com.woka.android.iduty.entity.Specialist;
import com.woka.android.iduty.entity.Speciality;
import com.woka.android.iduty.entity.User;
import com.woka.android.iduty.fragment.ClinicsFragment;
import com.woka.android.iduty.fragment.SpecialistsFragment;
import com.woka.android.iduty.fragment.SpecialtiesFragment;
import com.woka.android.iduty.fragment.TurnFragment;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, FragmentCoordinatorInterface{

    public static final String ACTIVITY_KEY = "ACTIVITY";

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private View view;
    private Intent intent;
    private TextView tvUserDataInfo;
    private Fragment fragment;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        fragment = new ClinicsFragment();

        if (savedInstanceState == null) {
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, fragment)
                    .commit();
        }

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        user = IDuty.APPLICATION.getUser();

        initWidgets();

        addListeners();

        IDuty.APPLICATION.setCoordinatorInterface(this);
    }

    private void initWidgets(){
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.nav_header_main, null);

        tvUserDataInfo = (TextView) findViewById(R.id.tvUserDataInfo);

        if (user.isUserDataCompleted()) {
            tvUserDataInfo.setVisibility(View.GONE);
        }
    }

    private void addListeners() {
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("users/" + user.getUid());
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (user.getFirstName() != null || user.getLastName() != null) {
                    try {
                        ((TextView) findViewById(R.id.tvUserName)).setText(user.getFirstName() + ", " + user.getLastName());
                        ((TextView) findViewById(R.id.tvUserEmail)).setText(user.getEmail());
                    } catch (Exception e) {
                        ((TextView) view.findViewById(R.id.tvUserName)).setText(user.getFirstName() + ", " + user.getLastName());
                        ((TextView) view.findViewById(R.id.tvUserEmail)).setText(user.getEmail());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(getBaseContext(), "Problem with Firebase DataBase", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.icUserAccount) {
            intent = new Intent(this, UserAccountActivity.class);
            startActivity(intent);
        } else if (id == R.id.icTurn) {
            intent = new Intent(this, TurnsActivity.class);
            startActivity(intent);
        } else if (id == R.id.icHistorial) {
            intent = new Intent(this, HistoryActivity.class);
            startActivity(intent);
        } else if (id == R.id.icSignOut) {
            FirebaseLoginManager firebaseLoginManager = new FirebaseLoginManager();
            firebaseLoginManager.getAuth().signOut();
            LoginManager.getInstance().logOut();
            IDuty.APPLICATION.setIsMainActivityRunning(false);
            finish();
            Intent intent = new Intent(getBaseContext(), LoginActivity.class);
            startActivity(intent);
        } else if (id == R.id.icCloseSession) {
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void changeFragment(String id, int fragmentNum) {

        switch (fragmentNum) {
            case 0:
                fragment = new ClinicsFragment();
                break;
            case 1:
                fragment = new SpecialtiesFragment();
                break;
            case 2:
                fragment = new SpecialistsFragment();
                break;
            case 3:
                fragment = new TurnFragment();
                break;
        }

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit();
    }
}
