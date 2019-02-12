package ru.ratanov.core.repo

import com.fasterxml.jackson.databind.ObjectMapper
import org.jsoup.Jsoup

object TrailerRepository {

    fun getTrailer(filmUrl: String): String {
        val kpUrl = FilmRepository.getKpUrl(filmUrl)
        val videoPageUrl = Jsoup.connect(kpUrl).get().select("meta[name=twitter:player:stream]").attr("content")
        val frameUrl = Jsoup.connect(videoPageUrl).get().select("iframe").attr("src")

        val jsonScript = Jsoup.connect(frameUrl).get().getElementsByTag("script").last().data()
        val objectMapper = ObjectMapper()
        val rootNode = objectMapper.readTree(jsonScript)
        val urlNode = rootNode.findPath("url").asText()

        val dataParams = Jsoup.connect(urlNode).get().select("div.embed").attr("data-params")
        val trailerUrl = objectMapper.readTree(dataParams).findPath("mp4").findPath("videoUrl").asText()

        return trailerUrl
    }

}