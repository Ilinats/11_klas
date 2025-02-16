class AdminUser implements User {
    @Override
    public String getRole() {
        return "Admin";
    }
}