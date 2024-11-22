package item.category;

import item.StoreItem;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class GameBundle implements StoreItem {
    private String title;
    private BigDecimal price;
    private LocalDateTime releaseDate;
    private Game[] games;
    private double rating;
    static int count = 0;

    public GameBundle(String title, BigDecimal price, LocalDateTime releaseDate, Game[] games) {
        this.title = title;
        this.price = price;
        this.releaseDate = releaseDate;
        this.games = games;
        this.rating = 0;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public double getRating() {
        return rating;
    }

    @Override
    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public void setReleaseDate(LocalDateTime releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public void rate(double rating) {
        this.rating += rating;
        count ++;
        this.rating /= count;
    }

    public Game[] getGames() {
        return games;
    }

    public void setGames(Game[] games) {
        this.games = games;
    }
}
