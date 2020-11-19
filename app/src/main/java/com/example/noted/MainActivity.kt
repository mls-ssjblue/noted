package com.example.noted

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment()

        val navController = findNavController(R.id.nav_host_fragment)
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            navController.navigate(R.id.action_FirstFragment_to_SecondFragment)
            it.visibility = View.GONE
        }


    }

    private fun navigateToAddNoteScreen(view: View) {
//        findViewById<ViewPager2>(R.id.view_pager).
//                currentItem = 1

//        val navController = findNavController(R.layout.fragment_first)
//        view.findNavController().navigate(R.layout.fragment_second)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}

/*

purple.setOnClickListener(new OnClickListener() {
    @Override
    public void onClick(View v) {
        Fragment fragment = new tasks();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
});


 */