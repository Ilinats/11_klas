public class Main {
    public static void main(String[] args) {
        Order order1 = new Order.Builder()
                .setMainDish("Пица Маргарита")
                .setDrink("Кока-Кола")
                .setDessert("Тирамису")
                .build();

        Order order2 = new Order.Builder()
                .setMainDish("Паста Карбонара")
                .setDrink("Минерална вода")
                .build();

        System.out.println(order1);
        System.out.println(order2);
    }
}