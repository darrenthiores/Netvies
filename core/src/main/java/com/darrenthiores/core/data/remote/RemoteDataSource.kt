package com.darrenthiores.core.data.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.darrenthiores.core.data.paging.MoviesPagingSource
import com.darrenthiores.core.data.remote.ktor.MovieService
import com.darrenthiores.core.model.data.MovieResponse
import kotlinx.coroutines.flow.Flow

const val LOAD_SIZE = 20
class RemoteDataSource(
    private val service: MovieService
) {
    fun getMovies(): Flow<PagingData<MovieResponse>> =
        Pager(
            config = PagingConfig(
                pageSize = LOAD_SIZE,
                enablePlaceholders = true
            ),
            pagingSourceFactory = {
                MoviesPagingSource(service)
            }
        ).flow
}