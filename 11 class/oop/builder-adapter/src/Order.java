class Order {
    private final String mainDish;
    private final String drink;
    private final String dessert;
    private final String notes;

    private Order(Builder builder) {
        this.mainDish = builder.mainDish;
        this.drink = builder.drink;
        this.dessert = builder.dessert;
        this.notes = builder.notes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Поръчка: " + mainDish);
        if (drink != null) sb.append(", ").append(drink);
        if (dessert != null) sb.append(", ").append(dessert);
        if (notes != null) sb.append(" (Бележки: ").append(notes).append(")");
        return sb.toString();
    }

    public static class Builder {
        private String mainDish;
        private String drink;
        private String dessert;
        private String notes;

        public Builder setMainDish(String mainDish) {
            this.mainDish = mainDish;
            return this;
        }

        public Builder setDrink(String drink) {
            this.drink = drink;
            return this;
        }

        public Builder setDessert(String dessert) {
            this.dessert = dessert;
            return this;
        }

        public Builder setNotes(String notes) {
            this.notes = notes;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}