package com.darrenthiores.core.utils

import com.darrenthiores.core.model.data.MovieResponse
import com.darrenthiores.core.model.domain.MovieDomain
import com.darrenthiores.core.model.presenter.Movie

object DataMapper {

    fun mapResponsesToDomain(input: MovieResponse): MovieDomain =
        MovieDomain(
            input.id,
            input.title,
            input.poster_path,
            input.release_date,
            input.vote_average
        )

    fun mapDomainToPresenter(input: MovieDomain): Movie =
        Movie(
            input.id,
            input.title,
            input.poster,
            input.date,
            input.vote
        )

}