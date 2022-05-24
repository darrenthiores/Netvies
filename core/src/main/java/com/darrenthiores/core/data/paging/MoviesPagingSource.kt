package com.darrenthiores.core.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.darrenthiores.core.data.remote.ktor.MovieService
import com.darrenthiores.core.model.data.MovieResponse

class MoviesPagingSource(
    private val service: MovieService
): PagingSource<Int, MovieResponse>() {

    override fun getRefreshKey(state: PagingState<Int, MovieResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieResponse> {
        val page = params.key ?: 1
        return try {
            val response = service.getMovies(page)
            val movies = response.results

            val nextKey = if(movies.isEmpty()) {
                null
            } else {
                page.plus(1) //+ (params.loadSize / LOAD_SIZE)
            }

            LoadResult.Page(
                data = movies,
                prevKey = if(page==1) null else page,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}