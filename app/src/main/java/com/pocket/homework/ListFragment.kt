package com.pocket.homework

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.pocket.homework.databinding.FragmentListBinding
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {
    private lateinit var viewModel: ListViewModel
    private var dataBinding: FragmentListBinding? = null

    private val appSettings: AppSettings by lazy {
        AppSettings(PreferenceManager.getDefaultSharedPreferences(requireContext()))
    }

    private val listAdapter: MyListAdapter by lazy {
        MyListAdapter(context, appSettings.getItemOrder())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        dataBinding = FragmentListBinding.inflate(inflater, container, false)
        return dataBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ListViewModel::class.java]

        dataBinding?.apply {
            itemsList.layoutManager = LinearLayoutManager(requireContext())
            itemsList.adapter = listAdapter
        }

        with(dataBinding?.swipeRefresh) {
            this?.setOnRefreshListener {
                itemsList.visibility = View.GONE
                loadingView.visibility = View.VISIBLE
                viewModel.getItems()
                swipeRefresh.isRefreshing = false
            }
        }
        observeViewModel()
        viewModel.getItems()
    }

    private fun observeViewModel() {
        viewModel.items.observe(viewLifecycleOwner, Observer { items ->
            items?.let {
                itemsList.visibility = View.VISIBLE
                    listAdapter.updateItems(items)
                }
            }
        )

        viewModel.loading.observe(viewLifecycleOwner, Observer { isLoading ->
            isLoading?.let {
                loadingView.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    itemsList.visibility = View.GONE
                }
            }
        })
//        viewModel.getFilteredItems()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.actionSettings -> {
                view?.let {
                    Navigation.findNavController(it)
                        .navigate(ListFragmentDirections.actionSettings())
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        dataBinding = null
    }

}