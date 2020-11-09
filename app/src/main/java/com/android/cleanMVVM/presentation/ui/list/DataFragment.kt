package com.android.cleanMVVM.presentation.ui.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.cleanMVVM.common.State
import com.android.cleanMVVM.data.entities.UserData
import com.android.cleanMVVM.databinding.DataFragmentBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DataFragment : Fragment(), DataAdapter.ItemListener {

    private lateinit var binding: DataFragmentBinding
    private val viewModel: DataViewModel by viewModels()
    private lateinit var adapter: DataAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataFragmentBinding.inflate(inflater, container, false)
        with(binding, {
            swipeRefresh.setOnRefreshListener {
                adapter.notifyDataSetChanged()
                swipeRefresh.isRefreshing = false
            }

            search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    adapter.filter.filter(newText)
                    return false
                }

            })
        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        adapter = DataAdapter(this)
        binding.usersRv.layoutManager = LinearLayoutManager(requireContext())
        binding.usersRv.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.data.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                State.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) adapter.setItems(ArrayList(it.data))
                }
                State.Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }

                State.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        })
    }

    override fun onClickedItem(userData: UserData) {
        showDialog(userData)
    }

    private fun showDialog(userData: UserData) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setTitle(" ID  : " + userData.id)
        builder.setMessage(" TITLE  : " + userData.title)
        builder.setPositiveButton("OK", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}
