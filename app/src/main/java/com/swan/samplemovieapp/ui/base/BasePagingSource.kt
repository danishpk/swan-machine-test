package com.swan.samplemovieapp.ui.base

import androidx.paging.PagingSource
import com.swan.samplemovieapp.data.models.response.PaginatedResponse
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

abstract class BasePagingSource<T : Any> : PagingSource<Int, T>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        val position = params.key ?: STARTING_PAGE_INDEX

        return try {
            val response = load(
                page = position,
                limit = params.loadSize
            )
            val items = response.results
            val hasNextPage = items.isNotEmpty() && items.size > 1
            LoadResult.Page(
                data = items,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (hasNextPage) position + 1 else null
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    abstract suspend fun load(page: Int, limit: Int): PaginatedResponse<T>
}