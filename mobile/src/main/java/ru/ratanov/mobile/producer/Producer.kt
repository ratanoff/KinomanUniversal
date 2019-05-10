package ru.ratanov.mobile.producer

import ru.ratanov.mobile.producer.detacher.ProducerDetacher
import ru.ratanov.mobile.producer.detacher.ProducerDetachers
import java.util.concurrent.CopyOnWriteArraySet

abstract class Producer<T> {

    private val listeners = CopyOnWriteArraySet<(T) -> Unit>()

    fun attach(listener: (T) -> Unit): ProducerDetacher<T>  {
        val added = listeners.add(listener)
        if (added) {
            if (listeners.size == 1) {
                onFirstAttached()
            }
            onAttached(listener)
        }
        return ProducerDetacher(this, listener)
    }

    fun attach(detacher: ProducerDetachers, listener: (T) -> Unit) =
        detacher.addDetacher(attach(listener))

    fun detach(listener: (T) -> Unit)  {
        val removed = listeners.remove(listener)
        if (removed) {
            onDetached(listener)
            if (listeners.isEmpty()) {
                onLastDetached()
            }
        }
    }

    protected fun call(data: T)  { listeners.forEach { it.invoke(data) } }

    protected open fun onAttached(listener: (T) -> Unit) {}

    protected open fun onFirstAttached() {}

    protected open fun onDetached(listener: (T) -> Unit) {}

    protected open fun onLastDetached() {}

    /**
     * Additional methods
     */


}