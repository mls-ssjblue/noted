package com.example.noted

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.noted.adapters.NotesAdapter
import com.example.noted.databinding.HomeFragmentBinding
import com.example.noted.viewmodels.NotesViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: NotesViewModel by viewModels ()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = HomeFragmentBinding.inflate(inflater, container, false)
        context ?: return binding.root

        val adapter = NotesAdapter()
        binding.noteList.adapter = adapter
//        adapter.submitList(viewModel.notes.value)

        subscribeUi(adapter)

        setHasOptionsMenu(true)
        return binding.root
    }

    private fun subscribeUi(adapter: NotesAdapter) {
        viewModel.notes.observe(viewLifecycleOwner) { notes ->
            adapter.submitList(notes)
        }
    }


    override fun onResume() {
        super.onResume()
        activity?.findViewById<FloatingActionButton>(R.id.fab)?.visibility = View.VISIBLE
        val backButton = activity?.findViewById<Toolbar>(R.id.toolbar)?.navigationIcon
        backButton?.setVisible(true, false)
        val supportActionBar = (activity as AppCompatActivity?)!!.supportActionBar!!
        supportActionBar.setHomeButtonEnabled(false)
        supportActionBar.setDisplayHomeAsUpEnabled(false)
    }

    override fun onStop() {
        super.onStop()
        val supportActionBar = (activity as AppCompatActivity?)!!.supportActionBar!!
        supportActionBar.setHomeButtonEnabled(true)
        supportActionBar.setDisplayHomeAsUpEnabled(true)

    }

}