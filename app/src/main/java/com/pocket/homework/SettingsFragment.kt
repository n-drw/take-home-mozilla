package com.pocket.homework

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.pocket.homework.databinding.SettingsScreenBinding
import com.pocket.homework.internal.Item
import kotlinx.android.synthetic.main.fragment_list.*
import java.util.*

class SettingsFragment : Fragment() {
    private lateinit var dataBinding: SettingsScreenBinding
    val listViewModel by viewModels<ListViewModel>()
    private val settings: AppSettings by lazy {
        AppSettings(PreferenceManager.getDefaultSharedPreferences(requireContext()))
    }

    private val listAdapter: MyListAdapter by lazy {
        MyListAdapter(context, settings.getItemOrder())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = SettingsScreenBinding.inflate(inflater)
        return dataBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        listViewModel.getItems()
        dataBinding?.viewModel = listViewModel
        listViewModel.selectedOrder.observe(viewLifecycleOwner) { order ->
            var sortedList = listViewModel.sortItems(order)
//            listAdapter.updateItems(order, sortedList)
            updateItems(order, sortedList)

        }
        listViewModel.getItems()
    }

    private fun updateItems(sortOrder: ItemSortOrder, sortedList: List<Item>?) {
        settings.saveItemOrder(sortOrder)
        listAdapter.updateItems(sortedList)
    }

//        listViewModel.selectedOrder.observe(viewLifecycleOwner) { order ->
//            when (order) {
//                ItemSortOrder.DATE_DESC -> {
//                    println(order.type.toString())
//                    updateItemOrder(order)
////                    orderedItems = listViewModel.items.value?.sortedByDescending { Date(it.publishedAt) }!!
//                    orderedItems?.sortedByDescending { Date(it.publishedAt) }
//                    listAdapter.updateItemsFilters(order)
//                    listAdapter.updateItems(orderedItems)
//                }
//                ItemSortOrder.DATE_ASC -> {
//                    updateItemOrder(order)
////                    orderedItems = listViewModel.items.value!!.sortedBy { Date(it.publishedAt) }
//                    orderedItems?.sortedBy { Date(it.publishedAt) }
//                    listAdapter.updateItems(orderedItems)
//                    listAdapter.updateItemsFilters(order)
//                }
//                ItemSortOrder.TITLE_ASC -> {
//                    updateItemOrder(order)
////                    orderedItems = listViewModel.items.value!!.sortedBy { it.title }
//                    orderedItems?.sortedBy { Date(it.publishedAt) }
//                    listAdapter.updateItems(orderedItems)
//                    listAdapter.updateItemsFilters(order)
//                }
//                ItemSortOrder.TITLE_DESC -> {
//                    updateItemOrder(order)
////                    orderedItems = listViewModel.items.value!!.sortedBy { it.title }
//                    orderedItems?.sortedByDescending { Date(it.publishedAt) }
//                    listAdapter.updateItems(orderedItems)
//                    listAdapter.updateItemsFilters(order)
//                }
//            }
//        }
//    }

//    fun updateItemOrder(sortOrder: ItemSortOrder) {
//        listAdapter.updateItemsFilters(sortOrder)
//    }
}
