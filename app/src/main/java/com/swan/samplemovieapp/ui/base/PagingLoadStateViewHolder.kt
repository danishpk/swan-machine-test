package com.swan.samplemovieapp.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.swan.samplemovieapp.databinding.PagingLoadStateFooterViewItemBinding

class PagingLoadStateViewHolder(
    private val itemBinding: PagingLoadStateFooterViewItemBinding,
    retry: () -> Unit,
) : RecyclerView.ViewHolder(itemBinding.root) {

    init {
        itemBinding.btnRetry.setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            itemBinding.tvErrorMessage.text = loadState.error.localizedMessage
        }

        itemBinding.apply {
            pbLoading.isVisible = loadState is LoadState.Loading
            btnRetry.isVisible = loadState !is LoadState.Loading
            tvErrorMessage.isVisible = loadState !is LoadState.Loading
        }
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): PagingLoadStateViewHolder {
            val itemBinding =
                PagingLoadStateFooterViewItemBinding.inflate(LayoutInflater.from(parent.context),
                    parent,
                    false)
            return PagingLoadStateViewHolder(itemBinding, retry)
        }
    }
}