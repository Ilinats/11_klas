package item.filter;

import item.StoreItem;

public class RatingItemFilter implements ItemFilter{
    double rating;

    public RatingItemFilter(double rating) {
        this.rating = rating;
    }

    @Override
    public boolean matches(StoreItem item) {
        if(item.getRating() >= rating)
            return true;

        return false;
    }
}
