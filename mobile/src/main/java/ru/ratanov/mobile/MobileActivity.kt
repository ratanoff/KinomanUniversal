package ru.ratanov.mobile

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.lapism.searchview.SearchAdapter
import com.lapism.searchview.SearchItem
import com.lapism.searchview.SearchView
import com.lapism.searchview.SearchView.OnOpenCloseListener
import kotlinx.android.synthetic.main.activity_mobile.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import ru.ratanov.core.repo.FilmRepository


class MobileActivity : AppCompatActivity(), NavHost {
    override fun getNavController(): NavController {
        return Navigation.findNavController(this, R.id.my_nav_host_fragment)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.my_nav_host_fragment).navigateUp()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mobile)

        setupSearchView()

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
//        finishAffinity()
//        System.exit(0)
    }


    private fun setupSearchView() {
        with(searchview) {
            version = SearchView.VERSION_MENU_ITEM
            setVersionMargins(SearchView.VERSION_MARGINS_MENU_ITEM)
            setTheme(SearchView.THEME_LIGHT)
            setOnOpenCloseListener(object : OnOpenCloseListener {
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
                val suggestion = FilmRepository.getTopFilms()?.map { SearchItem(it.title) }
                uiThread {
                    adapter = SearchAdapter(this@MobileActivity, suggestion)
                }
            }

        }
    }


}
