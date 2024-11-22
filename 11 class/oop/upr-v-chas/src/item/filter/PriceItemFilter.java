package item.filter;

import item.StoreItem;

import java.math.BigDecimal;

public class PriceItemFilter implements ItemFilter{
    BigDecimal lowerBound;
    BigDecimal upperBound;

    public PriceItemFilter(BigDecimal lowerBound, BigDecimal upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    @Override
    public boolean matches(StoreItem item) {
        int lower = item.getPrice().compareTo(lowerBound);
        int higher = item.getPrice().compareTo(upperBound);

        if(lower == -1 && higher == 1)
            return true;

        return false;
    }
}
