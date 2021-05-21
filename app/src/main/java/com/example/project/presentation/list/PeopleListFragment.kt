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
    private lateinit var buttonPrevious: Button
    private lateinit var buttonNext: Button
    private val adapter = ElementAdapter(listOf(), ::onClickedPeople)
    private val viewModel: PeopleListViewModel by viewModels()

    private lateinit var pagePrev: String
    private lateinit var pageNext: String

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_people_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonPrevious = view.findViewById(R.id.button_previous)
        buttonNext = view.findViewById(R.id.button_next)

        recyclerView = view.findViewById(R.id.people_recyclerview)
        loader = view.findViewById(R.id.people_list_loader)
        textViewError = view.findViewById(R.id.people_list_error)

        adapter.listener
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@PeopleListFragment.adapter
        }

        val page = arguments?.getString("page") ?: "1"

        viewModel.callApi(page)
        viewModel.peopleList.observe(viewLifecycleOwner, Observer { elementListModel ->

            when(elementListModel) {
                is ElementListLoader -> {
                    loader.isVisible = true
                    textViewError.isVisible = false

                    buttonPrevious.isVisible = false
                    buttonNext.isVisible = false
                }

                ElementListError -> {
                    loader.isVisible = false
                    textViewError.isVisible = true

                    buttonPrevious.isVisible = false
                    buttonNext.isVisible = false
                }

                is ElementListSuccess -> {
                    loader.isVisible = false
                    textViewError.isVisible = false

                    showList(elementListModel.elementList)

                    if(elementListModel.previous != null) {
                        buttonPrevious.isVisible = true
                        pagePrev = elementListModel.previous.substring(39, 40)
                    } else {
                        buttonPrevious.isVisible = false
                        pagePrev = "1"
                    }

                    if(elementListModel.next != null) {
                        buttonNext.isVisible = true
                        pageNext = elementListModel.next.substring(39, 40)
                    } else {
                        buttonNext.isVisible = false
                        pageNext = "1"
                    }
                }
            }
        })

        view.findViewById<Button>(R.id.button_menu).setOnClickListener {
            findNavController().navigate(R.id.action_PeopleListFragment_to_MenuFragment)
        }

        view.findViewById<Button>(R.id.button_next).setOnClickListener {
            findNavController().navigate(R.id.action_refresh_PeopleListFragment, bundleOf(
                    "page" to pageNext
            ))
        }

        view.findViewById<Button>(R.id.button_previous).setOnClickListener {
            findNavController().navigate(R.id.action_refresh_PeopleListFragment, bundleOf(
                    "page" to pagePrev
            ))
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