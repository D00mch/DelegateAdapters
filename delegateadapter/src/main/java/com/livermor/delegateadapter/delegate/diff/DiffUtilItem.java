package com.livermor.delegateadapter.delegate.diff;

/**
 * @author dumchev on 16.11.17.
 */
public interface DiffUtilItem {

    /**
     * {@link DiffUtilCallback} uses this method to distinguish between items
     *
     * @return item's unique id that won't be changed
     */
    Object id();

    /**
     * {@link DiffUtilCallback} uses this method to know, that item has changed
     *
     * @return data that represents visual part of the item
     */
    Object content();
}
