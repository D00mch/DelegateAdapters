package com.livermor.delegateadapter.delegate.diff

/**
 * kotlin data classes should implement this interface,
 * or make sure that your equals() and hashCode() are implemented,
 * or override content() fun.
 */
interface KDiffUtilItem : DiffUtilItem {
    val id: Any

    override fun id(): Any = id
    override fun content(): Any = this
}