package ru.ratanov.core.repo

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import ru.ratanov.core.model.Film
import ru.ratanov.core.model.Filter
import ru.ratanov.core.model.TopFilm
import ru.ratanov.core.urls.Endpoints.FILM
import ru.ratanov.core.urls.Endpoints.FILTERS
import ru.ratanov.core.urls.Endpoints.KP_URL
import ru.ratanov.core.urls.Endpoints.POSTER
import ru.ratanov.core.urls.Endpoints.TOP
import ru.ratanov.core.urls.Endpoints.TRAILER
import java.net.URL

object FilmRepository {

    fun getTopFilms(): List<TopFilm>? {
        return GsonBuilder().create()
            .fromJson<List<TopFilm>>(URL(TOP).readText(), object : TypeToken<List<TopFilm>>() {}.type)
    }

    fun getFilters(): List<Filter> = GsonBuilder().create()
        .fromJson<List<Filter>>(URL(FILTERS).readText(), object : TypeToken<List<Filter>>() {}.type)

    fun getFilm(filmUrl: String) = GsonBuilder().create().fromJson(URL("$FILM?filmUrl=$filmUrl").readText(), Film::class.java)

    fun getPoster(filmUrl: String): String = URL("$POSTER?filmUrl=$filmUrl").readText()

    fun getKpUrl(filmUrl: String): String = URL("$KP_URL?filmUrl=$filmUrl").readText()

    fun getTrailer(filmUrl: String): String = URL("$TRAILER?filmUrl=$filmUrl").readText()
}