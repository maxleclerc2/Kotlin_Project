package com.example.project.presentation.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project.R
import com.example.project.presentation.detail.Film

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class FilmListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var loader: ProgressBar
    private lateinit var textViewError: TextView
    private val adapter = FilmAdapter(listOf(), ::onClickedFilm)
    private val viewModel: FilmListViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_film_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.film_recyclerview)
        loader = view.findViewById(R.id.film_list_loader)
        textViewError = view.findViewById(R.id.film_list_error)

        adapter.listener
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@FilmListFragment.adapter
        }

        viewModel.filmList.observe(viewLifecycleOwner, Observer { filmListModel ->
            loader.isVisible = filmListModel is FilmListLoader
            textViewError.isVisible = filmListModel is FilmListError

            if(filmListModel is FilmListSuccess) {
                showList(filmListModel.filmList)
            }
        })

        view.findViewById<Button>(R.id.button_menu).setOnClickListener {
            findNavController().navigate(R.id.action_FilmListFragment_to_MenuFragment)
        }
    }



    private fun showList(filmList: List<Film>) {
        adapter.updateList(filmList)
    }

    private fun onClickedFilm(uid: String) {
        findNavController().navigate(R.id.action_FilmListFragment_to_FilmDetailFragment, bundleOf(
            "uid" to uid
        ))
    }
}