package item.filter;

import item.StoreItem;

import java.util.Locale;

public class TitleItemFilter implements ItemFilter {
    String title;
    boolean caseSensitive;

    public TitleItemFilter(String title, boolean caseSensitive) {
        this.title = title;
        this.caseSensitive = caseSensitive;
    }

    @Override
    public boolean matches(StoreItem item) {
        if(item.getTitle().matches(title) && caseSensitive)
            return true;
        else if(item.getTitle().toLowerCase(Locale.ROOT).matches(title) && !caseSensitive)
            return true;
        
        return false;
    }
}
