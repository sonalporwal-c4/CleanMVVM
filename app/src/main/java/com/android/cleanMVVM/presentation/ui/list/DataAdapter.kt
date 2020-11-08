package com.android.cleanMVVM.presentation.ui.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.cleanMVVM.data.entities.UserData
import com.android.cleanMVVM.databinding.ItemDataBinding

class DataAdapter(private val listener: ItemListener) : RecyclerView.Adapter<DataViewHolder>() {

    interface ItemListener {
        fun onClickedItem(itemId: Int)
    }

    private val items = ArrayList<UserData>()

    fun setItems(items: ArrayList<UserData>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding: ItemDataBinding = ItemDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) = holder.bind(items[position])
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
        listener.onClickedItem(userData.id)
    }
}

