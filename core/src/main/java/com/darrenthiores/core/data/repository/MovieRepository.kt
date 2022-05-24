package com.darrenthiores.core.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.darrenthiores.core.data.remote.RemoteDataSource
import com.darrenthiores.core.model.domain.MovieDomain
import com.darrenthiores.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository(
    private val remoteDataSource: RemoteDataSource
): IMovieRepository {
    override fun getMovies(): Flow<PagingData<MovieDomain>> =
        remoteDataSource.getMovies().map { pagingData ->
            pagingData.map {
                DataMapper.mapResponsesToDomain(it)
            }
        }
}