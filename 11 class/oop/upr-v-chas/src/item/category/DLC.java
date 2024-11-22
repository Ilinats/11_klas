package item.category;

import item.StoreItem;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DLC implements StoreItem {
    private String title;
    private BigDecimal price;
    private LocalDateTime releaseDate;
    private Game game;
    private double rating;
    static int count = 0;

    public DLC(String title, BigDecimal price, LocalDateTime releaseDate, Game game) {
        this.title = title;
        this.price = price;
        this.releaseDate = releaseDate;
        this.game = game;
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

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
