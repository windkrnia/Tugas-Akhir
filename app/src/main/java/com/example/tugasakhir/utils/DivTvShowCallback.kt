package com.project.proyek_akhir_mobile_programming.utils

import androidx.recyclerview.widget.DiffUtil
import com.project.proyek_akhir_mobile_programming.data.model.TvShowResponse

class DivTvShowCallback(private val oldItem: List<TvShowResponse>, private val newItem: List<TvShowResponse>): DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldItem.size

    override fun getNewListSize(): Int = newItem.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldItem[oldItemPosition].id == newItem[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldItem[oldItemPosition].id == newItem[newItemPosition].id
}