class FullTimeEmployee extends Employee {
    private final double annualSalary;

    public FullTimeEmployee(String name, int id, double annualSalary) {
        super(name, id);
        this.annualSalary = annualSalary;
    }

    @Override
    public double calculateMonthlyPay() {
        return annualSalary / 12;
    }

    @Override
    public String getRole() {
        return "FullTime";
    }
}