package gr.blackswamp.ratingsdiary.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import gr.blackswamp.core.ui.CoreActivity
import gr.blackswamp.ratingsdiary.R
import gr.blackswamp.ratingsdiary.ui.fragments.DetailFragment
import gr.blackswamp.ratingsdiary.ui.fragments.ListFragment
import gr.blackswamp.ratingsdiary.vm.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * An activity representing a single Entry detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a [ListFragment].
 */
class MainActivity : CoreActivity<MainViewModel>() {
    override val vm: MainViewModel by viewModel()

    override val layoutId: Int = R.layout.activity_main

    private var tabletMode = false
    private lateinit var loading: FrameLayout


    override fun setUpBindings() {
        super.setUpBindings()
        tabletMode = (findViewById<View>(R.id.content) == null)
        loading = findViewById(R.id.loading)
    }

    override fun initView(state: Bundle?) {
        if (state == null) {
            if (tabletMode) {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.list, ListFragment.newInstance(), ListFragment.TAG)
                    .commit()
            } else {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.content, ListFragment.newInstance(), ListFragment.TAG)
                    .commit()
            }
        }
    }

    override fun setUpListeners() {
        super.setUpListeners()
    }

    override fun setUpObservers(vm: MainViewModel) {
        vm.error.observe(this, Observer {
            it?.let { msg ->
                showSnackBar(loading, msg, Snackbar.LENGTH_INDEFINITE)
            }
        })
        vm.loading.observe(this, Observer { loading.visibility = if (it == true) View.VISIBLE else View.GONE })
    }

}
