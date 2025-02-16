class Guest implements User {
    @Override
    public String getRole() {
        return "Guest";
    }
}