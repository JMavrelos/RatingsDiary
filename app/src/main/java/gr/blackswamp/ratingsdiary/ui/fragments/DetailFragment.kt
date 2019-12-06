package gr.blackswamp.ratingsdiary.ui.fragments

import gr.blackswamp.core.ui.CoreFragment
import gr.blackswamp.ratingsdiary.R
import gr.blackswamp.ratingsdiary.vm.DetailViewModel
import gr.blackswamp.ratingsdiary.vm.MainViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class DetailFragment : CoreFragment<DetailViewModel>() {
    companion object {
        const val TAG = "DetailFragment"
        fun newInstance() = DetailFragment()
    }

    override val vm: DetailViewModel by sharedViewModel<MainViewModel>()
    override val layoutId: Int = R.layout.fragment_details



}
