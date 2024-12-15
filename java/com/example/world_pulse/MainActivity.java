package com.example.world_pulse;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.world_pulse.databinding.ActivityMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    // Binding instance for view binding
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize view binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Find views
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        FloatingActionButton fab = findViewById(R.id.fab_button);

        // Setup toolbar
        setSupportActionBar(toolbar);

        // Setup ActionBarDrawerToggle for navigation drawer
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.open_nav,
                R.string.close_nav
        );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Load HomeFragment initially if no previous state exists
        if (savedInstanceState == null) {
            replaceFragment(new HomeFragment());
        }

        // Customize BottomNavigationView
        binding.bottomNavView.setBackground(null);
        binding.bottomNavView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment;

            if (item.getItemId() == R.id.popular) {
                selectedFragment = new PopularFragment();
            } else if (item.getItemId() == R.id.latest) {
                selectedFragment = new LatestFragment();
            } else if (item.getItemId() == R.id.video) {
                selectedFragment = new VideoFragment();
            } else if (item.getItemId() == R.id.saved) {
                selectedFragment = new SavedFragment();
            } else {
                selectedFragment = new HomeFragment();
            }

            replaceFragment(selectedFragment);
            return true;
        });

        // Setup FloatingActionButton click listener
        fab.setOnClickListener(view -> {
            replaceFragment(new HomeFragment());
            // Optionally reset NavigationView state
            navigationView.getMenu().setGroupCheckable(0, true, false);
        });
    }

    // Helper method to replace fragments
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}
