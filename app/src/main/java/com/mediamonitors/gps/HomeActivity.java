package com.mediamonitors.gps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;












public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



                drawerLayout = findViewById(R.id.drawer);
                toolbar = findViewById(R.id.toolbar);
                navigationView = findViewById(R.id.navigationView);
               setSupportActionBar(toolbar);
              // getSupportActionBar(). setDefaultDisplayHomeAsUpEnabled(true) ;

                //getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setDisplayShowTitleEnabled(false);
                toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawerOpen,R.string.drawerClose);
                drawerLayout.addDrawerListener(toggle);
                toggle.syncState();
                navigationView.setNavigationItemSelectedListener(this);






            }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.user:

                Toast.makeText(HomeActivity.this, "Account Selected", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));

                break;
            case R.id.message:
                Toast.makeText(HomeActivity.this, "Message Selected", Toast.LENGTH_SHORT).show();
                 startActivity(new Intent(getApplicationContext(), Message.class));

                break;
            case R.id.complains:
                Toast.makeText(HomeActivity.this, "Complains Selected", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), MachineActivity.class));

                break;
            case R.id.logout:
                Toast.makeText(HomeActivity.this, "Logout Selected", Toast.LENGTH_SHORT).show();
                //firebaseAuth.signOut();
                // finish();
                break;
            case R.id.location:
                Toast.makeText(HomeActivity.this, "Location Selected", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), MapsActivity.class));

                break;


        }
        return false;


    }}