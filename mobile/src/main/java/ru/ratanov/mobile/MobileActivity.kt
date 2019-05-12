package ru.ratanov.mobile

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_mobile.*
import kotlinx.android.synthetic.main.fragment_tabs.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import ru.ratanov.core.model.Filter
import ru.ratanov.core.repo.FilmRepository
import ru.ratanov.mobile.view.main.bottomsheet.FilterFragment
import ru.ratanov.search.SearchAdapter
import ru.ratanov.search.SearchItem
import ru.ratanov.search.SearchView


class MobileActivity : AppCompatActivity(), NavHost {

    private lateinit var filters: List<Filter>

    override fun getNavController(): NavController {
        return Navigation.findNavController(this, R.id.my_nav_host_fragment)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.my_nav_host_fragment).navigateUp()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mobile)

        setSupportActionBar(bottomAppBar)
        setupSearchView()
        initFilters()

//        NavigationUI.setupWithNavController(toolbar, navController)

        fab.setOnClickListener {
            if (searchview.isSearchOpen) {
                searchview.close(true)
            } else {
                searchview.open(true)
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()

        //todo Check if start screen -> exit
        if (navController.currentDestination?.id == navController.graph.startDestination) {
            toast("Нажмите ещё раз для выхода")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bottomappbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_filter -> {
                val page = tabLayout.selectedTabPosition
                val filterFragment = FilterFragment.newInstance(filters, page)
                filterFragment.show(supportFragmentManager, filterFragment.tag)
            }

        }
        return super.onOptionsItemSelected(item)
    }


    private fun setupSearchView() {
        with(searchview) {
            version = SearchView.VERSION_MENU_ITEM
            setVersionMargins(SearchView.VERSION_MARGINS_MENU_ITEM)
            setTheme(SearchView.THEME_LIGHT)
            setOnOpenCloseListener(object : SearchView.OnOpenCloseListener {
                override fun onOpen(): Boolean {
                    fab.hide()
                    return true
                }

                override fun onClose(): Boolean {
                    fab.show()
                    return true
                }

            })

            doAsync {
                val suggestion = FilmRepository.getTopFilms()?.map { SearchItem(it.title, it.posterUrl) }
                uiThread {
                    adapter = SearchAdapter(this@MobileActivity, suggestion)
                }
            }

        }
    }

    private fun initFilters() {
        doAsync {
            filters = FilmRepository.getFilters()
        }
    }

}
