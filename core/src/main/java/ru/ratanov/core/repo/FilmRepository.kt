package ru.ratanov.core.repo

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import ru.ratanov.core.model.TopFilm
import ru.ratanov.core.urls.Endpoints.TOP
import java.net.URL

object FilmRepository {

    fun getTopFilms(): List<TopFilm>? {
        return GsonBuilder().create()
            .fromJson<List<TopFilm>>(URL(TOP).readText(), object : TypeToken<List<TopFilm>>() {}.type)
    }

}