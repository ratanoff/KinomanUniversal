package ru.ratanov.mobile.producer

open class DataProducer<T>(
    initialData: T
) : CallOnAttachProducer<T>() {

    protected var data: T = initialData
        set(value) {
            field = value
            call(value)
        }

    protected fun call() = call(data)

    override fun getDataForAttachedListener(listener: (T) -> Unit) = data

}