package gr.blackswamp.ratingsdiary.ui.activities

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import gr.blackswamp.core.ui.CoreActivity
import gr.blackswamp.ratingsdiary.R
import gr.blackswamp.ratingsdiary.ui.adapters.EntryListAdapter
import gr.blackswamp.ratingsdiary.vm.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : CoreActivity<MainViewModel>() {
    override val vm: MainViewModel by viewModel()

    override val layoutId: Int = R.layout.activity_main

    private lateinit var loading: View
    private lateinit var toolbar: Toolbar
    private lateinit var list: RecyclerView
    private val adapter = EntryListAdapter()


    override fun setUpBindings() {
        loading = findViewById(R.id.loading)
        toolbar = findViewById(R.id.toolbar)
        list = findViewById(R.id.list)
    }

    override fun initView(state: Bundle?) {
        setSupportActionBar(toolbar)
        title = getString(R.string.app_name)
        list.adapter = adapter
    }

    override fun setUpObservers(vm: MainViewModel) {
        vm.error.observe(this, Observer {
            it?.let { msg ->
                showSnackBar(loading, msg, Snackbar.LENGTH_INDEFINITE)
            }
        })
        vm.loading.observe(this, Observer { loading.visibility = if (it == true) View.VISIBLE else View.GONE })
        vm.entries.observe(this, Observer { items -> items?.let { adapter.setItems(it) } })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }


}
