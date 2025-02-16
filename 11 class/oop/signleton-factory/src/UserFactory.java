class UserFactory {
    public static User createUser(String type) {
        return switch (type.toLowerCase()) {
            case "admin" -> new AdminUser();
            case "regular" -> new RegularUser();
            case "guest" -> new Guest();
            default -> throw new IllegalArgumentException("Unknown user type: " + type);
        };
    }
}