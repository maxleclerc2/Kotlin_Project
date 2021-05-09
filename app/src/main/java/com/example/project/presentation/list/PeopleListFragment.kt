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

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class PeopleListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var loader: ProgressBar
    private lateinit var textViewError: TextView
    private val adapter = ElementAdapter(listOf(), ::onClickedPeople)
    private val viewModel: PeopleListViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_people_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.people_recyclerview)
        loader = view.findViewById(R.id.people_list_loader)
        textViewError = view.findViewById(R.id.people_list_error)

        adapter.listener
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@PeopleListFragment.adapter
        }

        viewModel.peopleList.observe(viewLifecycleOwner, Observer { elementListModel ->
            loader.isVisible = elementListModel is ElementListLoader
            textViewError.isVisible = elementListModel is ElementListError

            if(elementListModel is ElementListSuccess) {
                showList(elementListModel.elementList)
            }
        })

        view.findViewById<Button>(R.id.button_menu).setOnClickListener {
            findNavController().navigate(R.id.action_PeopleListFragment_to_MenuFragment)
        }
    }

    private fun showList(elementList: List<Element>) {
        adapter.updateList(elementList)
    }

    private fun onClickedPeople(uid: String) {
        findNavController().navigate(R.id.action_PeopleListFragment_to_PeopleDetailFragment, bundleOf(
                "uid" to uid
        ))
    }
}