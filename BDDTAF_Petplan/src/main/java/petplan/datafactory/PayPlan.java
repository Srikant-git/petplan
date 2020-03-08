package petplan.datafactory;

public enum PayPlan {
    PAY_IN_FULL_CHECK("Pay In Full Check"),
    PAY_IN_FULL_CREDIT_CARD("Annually"),
    FOUR_PAYMENTS_CREDIT_CARD("Quarterly"),
    TWELVE_PAYMENTS_CREDIT_CARD("Monthly");

    PayPlan(String description) {
        this.description = description;
    }

    private String description;

    public String getDescription(){
        return description;
    }
}