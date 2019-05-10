package ru.ratanov.mobile.view.main.bottomsheet

import ru.ratanov.mobile.producer.DataProducer

class FilterProducer(value: String) : DataProducer<String>(value) {

    fun setValue(value: String) {
        data = value
    }

    fun getValue() = data

}