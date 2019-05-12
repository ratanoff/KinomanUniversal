package ru.ratanov.mobile.view.main.bottomsheet

import ru.ratanov.core.model.Param
import ru.ratanov.mobile.producer.DataProducer

class ParamsProducer(value: Param) : DataProducer<Param>(value) {

    fun setValue(value: Param) {
        data = value
    }

    fun getValue() = data

}