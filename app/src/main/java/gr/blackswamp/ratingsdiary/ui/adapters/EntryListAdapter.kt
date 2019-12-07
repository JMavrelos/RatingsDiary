package gr.blackswamp.ratingsdiary.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import gr.blackswamp.core.util.asString
import gr.blackswamp.core.util.toDateString
import gr.blackswamp.ratingsdiary.R
import gr.blackswamp.ratingsdiary.ui.model.EntryVO
import java.util.*

class EntryListAdapter() : RecyclerView.Adapter<EntryListAdapter.EntryItemViewHolder>() {
    companion object {
        private val LABEL_UUID = UUID(0L, 0L)
        const val LABEL = 0
        const val ENTRY = 1
    }

    private var listener: ((UUID) -> Unit)? = null
    private val entries = mutableListOf<EntryVO>()

    override fun getItemViewType(position: Int): Int =
        if (entries[position].id == LABEL_UUID) LABEL else ENTRY

    fun setItems(items: List<EntryVO>) {
        entries.clear()
        entries.addAll(items)
    }

    fun setSelectionListener(listener: (UUID) -> Unit) {
        this.listener = listener
    }

    private fun itemClicked(position: Int) {
        val id = entries[position].id
        if (id != LABEL_UUID)
            listener?.invoke(id)
    }

    override fun getItemCount(): Int = entries.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntryItemViewHolder {
        return if (viewType == LABEL) {
            LabelViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_label, parent, false))
        } else {
            EntryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_entry, parent, false))
        }
    }

    override fun onBindViewHolder(holder: EntryItemViewHolder, position: Int) {
        holder.update(entries[position])
    }


    abstract class EntryItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun update(entry: EntryVO)
    }

    class LabelViewHolder(view: View) : EntryItemViewHolder(view) {
        private val label = view as TextView

        override fun update(entry: EntryVO) {
            label.text = entry.date.toDateString()
        }
    }

    inner class EntryViewHolder(view: View) : EntryItemViewHolder(view) {
        private val date: TextView = view.findViewById(R.id.date)
        private val rating: TextView = view.findViewById(R.id.rating)
        private val name: TextView = view.findViewById(R.id.name)

        init {
            view.setOnClickListener { itemClicked(adapterPosition) }
        }

        @SuppressLint("SetTextI18n")
        override fun update(entry: EntryVO) {
            date.text = entry.date.toDateString() + "\n" + entry.date.asString("HH:mm")
            rating.text = "${entry.rating}/10"
            name.text = entry.description
        }
    }
}