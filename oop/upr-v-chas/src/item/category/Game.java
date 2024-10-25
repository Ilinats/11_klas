package item.category;

import item.StoreItem;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Game implements StoreItem {
    private String title;
    private BigDecimal price;
    private LocalDateTime releaseDate;
    private String genre;
    private double rating;
    static int count = 0;

    public Game(String title, BigDecimal price, LocalDateTime releaseDate, String genre) {
        this.title = title;
        this.price = price;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.rating = 0;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
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
}
