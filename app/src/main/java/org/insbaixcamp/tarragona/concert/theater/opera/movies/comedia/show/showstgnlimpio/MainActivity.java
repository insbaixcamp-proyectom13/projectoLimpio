package org.insbaixcamp.tarragona.concert.theater.opera.movies.comedia.show.showstgnlimpio;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Menu;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import org.insbaixcamp.tarragona.concert.theater.opera.movies.comedia.show.showstgnlimpio.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private NavController navController;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        findViewById(R.id.text_home).setVisibility(View.GONE);
        findViewById(R.id.pbHome).setVisibility(View.VISIBLE);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Si viene de menu principal y quiere iniciar sesion
                if(getIntent().getStringExtra("button").equals("login")) {
                    loadHome();
                    navController.navigate(R.id.nav_slideshow);

                //Si viene de menu principal y quiere registrar-se
                } else if (getIntent().getStringExtra("button").equals("registre")) {
                    loadHome();
                    navController.navigate(R.id.nav_gallery);

                } else if (getIntent().getStringExtra("button").equals("omitir")){
                    loadHome();
                }

            }
        }, 2000);



    }

    public void loadHome() {
        setSupportActionBar(binding.appBarMain.toolbar);
        findViewById(R.id.pbHome).setVisibility(View.GONE);
        findViewById(R.id.text_home).setVisibility(View.VISIBLE);

        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        lockNavigationDrawer();
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home)
                .setOpenableLayout(drawer)
                .build();

        NavigationUI.setupActionBarWithNavController(MainActivity.this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void lockNavigationDrawer() {
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

}