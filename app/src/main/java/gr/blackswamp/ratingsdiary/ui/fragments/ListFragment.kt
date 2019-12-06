package gr.blackswamp.ratingsdiary.ui.fragments

import gr.blackswamp.core.ui.CoreFragment
import gr.blackswamp.ratingsdiary.R
import gr.blackswamp.ratingsdiary.vm.ListViewModel
import gr.blackswamp.ratingsdiary.vm.MainViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel


class ListFragment : CoreFragment<ListViewModel>() {
    companion object {
        const val TAG = "ListFragment"
        fun newInstance() = ListFragment()
    }

    override val vm: ListViewModel by sharedViewModel<MainViewModel>()
    override val layoutId: Int = R.layout.fragment_list


}
