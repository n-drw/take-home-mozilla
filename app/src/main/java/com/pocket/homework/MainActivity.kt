package com.pocket.homework

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.pocket.homework.databinding.MainScreenBinding
import com.pocket.homework.internal.Item
import com.pocket.homework.internal.ItemRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * The visual representation of a person's saved [Item]s as a simple ListFragment.
 * 
 * TODO: Add a Settings screen launched from the Settings menu option,
 * that allows configuring sort order of the list by:
 * * newest published,
 * * oldest published,
 * * by title alphabetically,
 * * and maybe some other sorting options that seem useful, but we've missed?
 */
class MainActivity : BaseActivity() {
//    private val itemRepository = ItemRepository()
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_screen)

        navController = Navigation.findNavController(this, R.id.fragment)
        NavigationUI.setupActionBarWithNavController(this, navController)

//        val adapter = MyListAdapter()
//
//        MainScreenBinding.inflate(LayoutInflater.from(this)).apply {
//            setContentView(root)
//            list.adapter = adapter
//
//            swipeRefresh.setOnRefreshListener {
//                lifecycleScope.launch {
//                    val items = withContext(Dispatchers.IO) {
//                        itemRepository.fetch()
//                    }
//                    adapter.updateItems(items)
//                }
//            }
//        }
//
//        lifecycleScope.launch {
//            val items = withContext(Dispatchers.IO) {
//                itemRepository.fetch()
//            }
//            adapter.updateItems(items)
//        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }



}
