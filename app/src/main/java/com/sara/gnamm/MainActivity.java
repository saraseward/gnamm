package com.sara.gnamm;

import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.sara.gnamm.fragments.DetailFragment;
import com.sara.gnamm.fragments.LoginFragment;
import com.sara.gnamm.fragments.MainFragment;
import com.sara.gnamm.fragments.RegisterFragment;
import com.sara.gnamm.fragments.SplashFragment;

public class MainActivity extends AppCompatActivity implements
        SplashFragment.OnFragmentInteractionListener,
        LoginFragment.OnFragmentInteractionListener,
        RegisterFragment.OnFragmentInteractionListener,
        MainFragment.OnFragmentInteractionListener,
        DetailFragment.OnFragmentInteractionListener {

    private NavController navController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Toolbar
        setContentView(R.layout.activity_main);

        //Use NavController for navigationLibrary
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        //Example navigation between nav items
//        findViewById(R.id.fab).setOnClickListener(v -> navController.navigate(R.id.action_main_to_detail));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }
}
