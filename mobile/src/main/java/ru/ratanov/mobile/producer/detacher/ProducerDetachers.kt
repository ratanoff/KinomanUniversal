package ru.ratanov.mobile.producer.detacher

import java.util.*

class ProducerDetachers {

    private val finishers = LinkedList<ProducerDetacher<*>>()

    fun addDetacher(detacher: ProducerDetacher<*>) {
        finishers.add(detacher)
    }

    fun detachAllAndClear() = synchronized(this) {
        finishers.forEach(ProducerDetacher<*>::detach)
        finishers.clear()
    }

}