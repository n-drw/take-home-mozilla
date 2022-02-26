package com.pocket.homework

import android.widget.RadioGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.lifecycle.MutableLiveData

@BindingAdapter("itemFilter")
fun RadioGroup.setCheckedButton(sortOrder: MutableLiveData<ItemSortOrder>?) {
    val selectedId = when (sortOrder?.value) {
        ItemSortOrder.TITLE_ASC -> R.id.sortTitleAsc
        ItemSortOrder.TITLE_DESC -> R.id.sortTitleDesc
        ItemSortOrder.DATE_ASC -> R.id.sortDatesAsc
        ItemSortOrder.DATE_DESC -> R.id.sortDatesDesc
        else -> null
    }

    if (selectedId != null && selectedId != checkedRadioButtonId) {
        check(selectedId)
    }
}

@InverseBindingAdapter(attribute = "itemFilter")
fun RadioGroup.getCheckedButton(): ItemSortOrder? {
    return when (checkedRadioButtonId) {
        R.id.sortTitleAsc -> ItemSortOrder.TITLE_ASC
        R.id.sortTitleDesc -> ItemSortOrder.TITLE_DESC
        R.id.sortDatesAsc -> ItemSortOrder.DATE_ASC
        R.id.sortDatesDesc -> ItemSortOrder.DATE_DESC
        else -> null
    }
}

@BindingAdapter("app:itemFilterAttrChanged")
fun RadioGroup.setListeners(listener: InverseBindingListener?) {
    listener?.let {
        setOnCheckedChangeListener { radioGroup, id ->
            listener.onChange()
        }
    }
}