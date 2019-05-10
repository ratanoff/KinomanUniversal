package ru.ratanov.mobile.producer

abstract class CallOnAttachProducer<T> : Producer<T>() {

    override fun onAttached(listener: (T) -> Unit) {
        super.onAttached(listener)
        val dataForAttachedListener = getDataForAttachedListener(listener)
        listener.invoke(dataForAttachedListener)
    }

    protected abstract fun getDataForAttachedListener(listener: (T) -> Unit): T

}