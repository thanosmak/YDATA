package com.example.user.ydata.model;

public class Bill {

    private String barcode;
    private String issuedDate;
    private String paymentDueDate;
    private String remainingAmount;
    private String chargedConsumption;
    private String liableFullName;
    private String billingAddress;
    private String kodYdr;
    private String watermeterNumber;
    private String watermeterPin;
    private UsagePeriod usagePeriod;

    // Cunstructor
    public Bill() {}

    // Cunstructor with params
    public Bill(String barcode,
                String issuedDate,
                String paymentDueDate,
                String remainingAmount,
                String chargedConsumption,
                String liableFullName,
                String billingAddress,
                String kodYdr,
                String watermeterNumber,
                String watermeterPin,
                UsagePeriod usagePeriod) {
        this.barcode = barcode;
        this.issuedDate = issuedDate;
        this.paymentDueDate = paymentDueDate;
        this.remainingAmount = remainingAmount;
        this.chargedConsumption = chargedConsumption;
        this.barcode = liableFullName;
        this.issuedDate = billingAddress;
        this.paymentDueDate = kodYdr;
        this.remainingAmount = watermeterNumber;
        this.chargedConsumption = watermeterPin;
        this.usagePeriod = usagePeriod;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(String issuedDate) {
        this.issuedDate = issuedDate;
    }

    public String getPaymentDueDate() {
        return paymentDueDate;
    }

    public void setPaymentDueDate(String paymentDueDate) {
        this.paymentDueDate = paymentDueDate;
    }

    public String getRemainingAmount() {
        return remainingAmount;
    }

    public void setRemainingAmount(String remainingAmount) {
        this.remainingAmount = remainingAmount;
    }

    public String getChargedConsumption() {
        return chargedConsumption;
    }

    public void setChargedConsumption(String chargedConsumption) {
        this.chargedConsumption = chargedConsumption;
    }

    public UsagePeriod getUsagePeriod() {
        return usagePeriod;
    }

    public void setUsagePeriod(UsagePeriod usagePeriod) {
        this.usagePeriod = usagePeriod;
    }

    public String getLiableFullName() {
        return liableFullName;
    }

    public void setLiableFullName(String liableFullName) {
        this.liableFullName = liableFullName;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getKodYdr() {
        return kodYdr;
    }

    public void setKodYdr(String kodYdr) {
        this.kodYdr = kodYdr;
    }

    public String getWatermeterNumber() {
        return watermeterNumber;
    }

    public void setWatermeterNumber(String watermeterNumber) {
        this.watermeterNumber = watermeterNumber;
    }

    public String getWatermeterPin() {
        return watermeterPin;
    }

    public void setWatermeterPin(String watermeterPin) {
        this.watermeterPin = watermeterPin;
    }

    // UsagePeriod class with getters and setters
    public static class UsagePeriod {
        private String from;
        private String to;

        public UsagePeriod() {
        }

        public UsagePeriod(String from, String to) {
            this.from = from;
            this.to = to;
        }

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }
    }
}
