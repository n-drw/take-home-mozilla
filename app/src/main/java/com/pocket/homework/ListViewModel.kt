package com.pocket.homework

import android.app.Application
import android.os.Build
import android.widget.ListAdapter
import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.annotation.RequiresApi
import androidx.lifecycle.*
import com.pocket.homework.internal.Item
import com.pocket.homework.internal.ItemRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

class ListViewModel(application: Application) : BaseViewModel(application) {
    val items = MutableLiveData<List<Item>>()
    var sortedList = mutableListOf<Item>()
//    private var args = listOf("ascending", "descending", "newest", "oldest")


    private val itemRepository = ItemRepository()

//    private val selectedDateOrder = MutableLiveData<String?>(null)
//    private val selectedTitleOrder = MutableLiveData<String?>(null)

    val selectedOrder: MutableLiveData<ItemSortOrder> = MutableLiveData()
//    var sortedList = mutableListOf<Item>()

    val loading = MutableLiveData<Boolean>()

//    fun getFilteredItems() = args.filter { it == selectedOrder.value?.type }

    private fun itemsRetrieved(itemsList: List<Item>) {
//        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm")
//        sortedItems = itemsList.sortedByDescending { Date(it.publishedAt) }

//        sortedList.value = itemsList
//        sortedList = itemsList
        items.value = itemsList
        loading.value = false
    }



    fun sortItems(order: ItemSortOrder): List<Item>? {
        var itemsSorted = emptyList<Item>()
        if (order != null) {
            when (order) {
                ItemSortOrder.TITLE_DESC -> {
//                    sortedList = if (settings.datesOrder == "sortDateDesc") {
//                    sortedList.value = items.value?.sortedByDescending { it.title }!!
                itemsSorted = items.value?.sortedByDescending { it.title }!!
                sortedList = itemsSorted.toMutableList()
//
                }
                ItemSortOrder.TITLE_ASC -> {
//                    sortedList.value = items.value?.sortedBy { it.title }!!
//                    itemsSorted = sortedList.value!!
                    itemsSorted = items.value?.sortedBy { it.title }!!
                    sortedList = itemsSorted.toMutableList()
                }
                ItemSortOrder.DATE_DESC -> {
//                    sortedList.value = items.value!!.sortedByDescending { Date(it.publishedAt) }
//                    itemsSorted = sortedList.value!!
                    itemsSorted = items.value!!.sortedByDescending { Date(it.publishedAt) }
                    sortedList = itemsSorted.toMutableList()
                }
                ItemSortOrder.DATE_ASC -> {
//                    sortedList.value = items.value!!.sortedBy { Date(it.publishedAt) }
//                    itemsSorted = sortedList.value!!
                    itemsSorted = items.value!!.sortedBy { Date(it.publishedAt) }
                    sortedList = itemsSorted.toMutableList()
                }
                else -> null
            }
        }
        return itemsSorted
    }

    fun getItems() {
        viewModelScope.launch {
            if (sortedList.isNotEmpty()) {
                itemsRetrieved(sortedList)
                Toast.makeText(getApplication(), "getItems() Sorted? \uD83D\uDEA8", Toast.LENGTH_SHORT).show()
            } else {
                val items = itemRepository.fetch()
                itemsRetrieved(items)
                Toast.makeText(getApplication(), "getItems() onLaunch \uD83D\uDEA8", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

//    fun getSortedItems() = items.value.filter {  }


//    val titlesSorted = settings.observe()
//        .filter { it == AppSettings.TITLE_ORDER_KEY}
//        .map { settings.titlesOrder }
//        .distinctUntilChanged()
//        .onEach { it ->
//            if (selectedOrder.value != it) {
//                selectedOrder.postValue(it)
//            }
//        }.asLiveData(viewModelScope.coroutineContext)

//    val titlesSorted = _titlesSorted.asLiveData(viewModelScope.coroutineContext)

//    val datesSorted = settings.observe()
//        .filter { it == AppSettings.DATE_ORDER_KEY }
//        .map { settings.datesOrder }
//        .distinctUntilChanged()
//        .onEach { it ->
//            if (selectedOrder.value != it) {
//                selectedOrder.postValue(it)
//            }
//        }.asLiveData(viewModelScope.coroutineContext)

//    val datesSorted = _datesSorted.asLiveData(viewModelScope.coroutineContext)

//    init {
//        launch {
//            selectedDateOrder.value = datesSorted.value
//            selectedTitleOrder.value = datesSorted.value
//        }
//    }

//    private fun sortItems(order: String?) : List<Item> {
//        var sortedList: List<Item> = emptyList()
//        if(order != null) {
//            when (order) {
////                settings.datesOrder -> {
////                    sortedList = if (settings.datesOrder == "newest") {
//                        items.value?.sortedByDescending { Date(it.publishedAt) }!!
//                    } else {
//                        items.value?.sortedBy { Date(it.publishedAt) }!!
//                    }
//                }
//                settings.titlesOrder -> {
//                    sortedList = if (settings.titlesOrder == "descending") {
//                        items.value?.sortedByDescending { it.title }!!
//                    } else {
//                        items.value?.sortedBy { it.title }!!
//                    }
//                }
// }
//        }
//        return sortedList
//    }
