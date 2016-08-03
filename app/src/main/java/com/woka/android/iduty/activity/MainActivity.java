package com.woka.android.iduty.activity;

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

import com.facebook.login.LoginManager;
import com.woka.android.iduty.IDuty;
import com.woka.android.iduty.R;
import com.woka.android.iduty.data.FirebaseLoginManager;
import com.woka.android.iduty.entity.User;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private Intent intent;
    private View view;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        user = IDuty.APPLICATION.getUser();

        initWidgets();
    }

    private void initWidgets(){
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.nav_header_main, null);

        try {
            ((TextView) findViewById(R.id.tvUserName)).setText(user.getFirstName() + ", " + user.getSecondName());
            ((TextView) findViewById(R.id.tvUserEmail)).setText(user.getEmail());
        } catch (Exception e) {
            ((TextView) view.findViewById(R.id.tvUserName)).setText(user.getFirstName() + ", " + user.getSecondName());
            ((TextView) view.findViewById(R.id.tvUserEmail)).setText(user.getEmail());
        }
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
//            intent = new Intent(this, UserAccountActivity.class);
//            startActivity(intent);
        } else if (id == R.id.icTurn) {
//            intent = new Intent(this, TurnsActivity.class);
//            startActivity(intent);
        } else if (id == R.id.icHistorial) {
//            intent = new Intent(this, HistoryActivity.class);
//            startActivity(intent);
        } else if (id == R.id.icSignOut) {
            FirebaseLoginManager firebaseLoginManager = new FirebaseLoginManager();
            firebaseLoginManager.getAuth().signOut();
            LoginManager.getInstance().logOut();
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
}
