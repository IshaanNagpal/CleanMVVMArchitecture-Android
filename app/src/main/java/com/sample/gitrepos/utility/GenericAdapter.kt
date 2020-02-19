package com.sample.gitrepos.utility

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.ListAdapter
import com.sample.gitrepos.BR
import java.util.*


open class GenericAdapter<T>(private var list: List<T>) : ListAdapter<T, GenericViewHolder<*>>(
    AsyncDifferConfig.Builder<T>(GenericDiffCallback())
        .build()) {

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return (list[position] as ListItemModel).layoutId()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder<*> {
        val bind = DataBindingUtil.bind<ViewDataBinding>(LayoutInflater.from(parent.context).inflate(viewType, parent, false))
            ?: throw IllegalArgumentException()
        return GenericViewHolder(bind)
    }

    override fun onBindViewHolder(holder: GenericViewHolder<*>, position: Int) {
        val model = list[position]
        holder.getBinding().setVariable(BR.listItemModel, model)
        holder.getBinding().executePendingBindings()
    }

    // This is for swipe to delete button
    private fun removeAt(position: Int) {
        (list as ArrayList<T>).removeAt(position)
        notifyItemRemoved(position)
    }

    fun removeLast() {
        (list as ArrayList<T>).removeAt(list.size - 1)
        notifyItemRemoved(list.size - 1)
    }

    fun removeItem(itemToRemove: T) {
        val positionOfItemToRemove = list.indexOf(itemToRemove)
        removeAt(positionOfItemToRemove)
    }

    fun insertItem(newListItem: T) {
        val listSize = list.size
        (list as ArrayList<T>).add(list.size, newListItem)
        notifyItemInserted(listSize)
    }

    fun updateItem(updatedItem: T, updateIndex: Int) {
        (list as ArrayList<T>)[updateIndex] = updatedItem
        notifyItemChanged(updateIndex)
    }

    fun updateItemsAt(updatedItem: List<T>, updateIndex: Int) {
        notifyItemRangeRemoved(updateIndex, list.size - 1)
        list = (list as ArrayList<T>).replaceItems(updateIndex, updatedItem)
        notifyItemRangeInserted(updateIndex, updatedItem.size)
    }

    fun updateItems(updatedItem: List<T>?) {
        list = updatedItem ?: mutableListOf()
        notifyDataSetChanged()
    }

    private fun MutableList<T>.replaceItems(startIndex: Int, list: List<T>): MutableList<T> {
        val changeList = this.dropLast(size - 1).toMutableList()
        changeList.addAll(startIndex, list)
        return changeList
    }
}