package store;

import item.StoreItem;
import item.filter.ItemFilter;

import java.math.BigDecimal;

public class GameStore implements StoreAPI {
    private StoreItem[] availableItems;

    public GameStore(StoreItem[] availableItems) {
        this.availableItems = availableItems;
    }

    @Override
    public StoreItem[] findItemByFilters(ItemFilter[] itemFilters) {
        return new StoreItem[0];
    }

    @Override
    public void applyDiscount(String promoCode) {
        BigDecimal a = BigDecimal.valueOf(0.4);
        for (int i = 0; i < availableItems.length; i++) {
            availableItems[i].setPrice(availableItems[i].getPrice().subtract(availableItems[i].getPrice().multiply(a)));
        }
    }

    @Override
    public boolean rateItem(StoreItem item, int rating) {
        return false;
    }
}
