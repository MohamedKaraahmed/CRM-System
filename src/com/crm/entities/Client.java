package com.crm.entities;

public class Client extends AbstractClient {
    private String industry;
    private String contactPerson;
    private double revenue;

    public Client(long id, String name, String industry, String contactPerson, double revenue) {
        super(id, name);
        setIndustry(industry);
        setContactPerson(contactPerson);
        setRevenue(revenue);
    }

    public String getIndustry() {
        return industry;
    }

    private void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    private void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public double getRevenue() {
        return revenue;
    }

    private void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + super.getId() +
                ", name='" + super.getName() + '\'' +
                ", industry='" + industry + '\'' +
                ", contactPerson='" + contactPerson + '\'' +
                ", revenue=" + revenue +
                '}';
    }
}
