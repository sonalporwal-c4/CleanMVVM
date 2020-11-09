package com.android.cleanMVVM.presentation.ui.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.widget.Filter
import android.widget.Filterable
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.cleanMVVM.data.entities.UserData
import com.android.cleanMVVM.databinding.ItemDataBinding
import java.util.*
import kotlin.collections.ArrayList

class DataAdapter(private val listener: ItemListener) : RecyclerView.Adapter<DataViewHolder>(), Filterable {

    interface ItemListener {
        fun onClickedItem(data: UserData)
    }

    private var items = ArrayList<UserData>()

    private val dataList = ArrayList<UserData>()

    fun setItems(items: ArrayList<UserData>) {
        this.items.clear()
        this.items.addAll(items)
        this.dataList.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding: ItemDataBinding = ItemDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) = holder.bind(items[position])


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                val filterResults = FilterResults()
                if(charSearch.isNotEmpty()) {
                    val resultList = ArrayList<UserData>()
                    for (row in dataList) {
                        if (row.title.toLowerCase(Locale.ROOT).contains(charSearch.toLowerCase(Locale.ROOT))) {
                            resultList.add(row)
                        }
                    }
                    filterResults.values = resultList
                }else {
                    filterResults.values = dataList
                }
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                items = results?.values as ArrayList<UserData>
                notifyDataSetChanged()
            }

        }
    }

}

class DataViewHolder(private val itemBinding: ItemDataBinding, private val listener: DataAdapter.ItemListener) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var userData: UserData

    init {
        itemBinding.root.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: UserData) {
        this.userData = item
        itemBinding.id.text = item.id.toString()
        itemBinding.title.text = item.title
        itemBinding.body.text = item.body
    }

    override fun onClick(v: View?) {
        listener.onClickedItem(userData)
    }
}

