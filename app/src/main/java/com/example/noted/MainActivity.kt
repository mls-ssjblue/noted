package com.example.noted

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
//        if (supportActionBar != null) {
//            supportActionBar!!.setDisplayHomeAsUpEnabled(false)
//
//            supportActionBar!!.setHomeButtonEnabled(false)
//        }


//        val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment()

        val navController = findNavController(R.id.nav_host_fragment)
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            navController.navigate(R.id.action_HomeFragment_to_CreateNoteFragment)
            it.visibility = View.GONE
            val backButton = findViewById<Toolbar>(R.id.toolbar).navigationIcon
            backButton?.setVisible(true, false)
        }


    }

    private fun navigateToAddNoteScreen(view: View) {
//        findViewById<ViewPager2>(R.id.view_pager).
//                currentItem = 1

//        val navController = findNavController(R.layout.fragment_first)
//        view.findNavController().navigate(R.layout.create_note_fragment)

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