package ru.ratanov.mobile.producer.detacher

import ru.ratanov.mobile.producer.Producer

class ProducerDetacher<T>(
    private val producer: Producer<T>,
    private val listener: (T) -> Unit
) {

    fun detach() = producer.detach(listener)

}