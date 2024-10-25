package item.filter;

import item.StoreItem;

import java.time.LocalDateTime;

public class ReleaseDateItemFilter implements ItemFilter{
    LocalDateTime lowerBound;
    LocalDateTime upperBound;

    public ReleaseDateItemFilter(LocalDateTime lowerBound, LocalDateTime upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    @Override
    public boolean matches(StoreItem item) {
        if(item.getReleaseDate().isAfter(lowerBound) && item.getReleaseDate().isBefore(upperBound))
            return true;
        
        return false;
    }
}
