package item.filter;

import item.StoreItem;

public interface ItemFilter {

    /**
     * Checks if the given store item matches the filter.
     *
     * @param item the store item to be checked
     * @return true if the store item matches the filter, false otherwise
     */
    boolean matches(StoreItem item);
}
