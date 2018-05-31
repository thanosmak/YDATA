package com.example.user.ydata.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by makar on 17/5/2018.
 */

public class Waterconnection implements Serializable {

    private String deya;
    private String pin;
    private String status;
    private String owner;
    private String address;
    private Watermeter watermeter;
    private List<Indications> indicationsList;

    // Cunstructor
    public Waterconnection() {
    }

    // Cunstructor with params
    public Waterconnection(String deya, String pin, String status, String owner, String address, Watermeter watermeter, List<Indications> indicationsList) {
        this.deya = deya;
        this.pin = pin;
        this.status = status;
        this.owner = owner;
        this.address = address;
        this.watermeter = watermeter;
        this.indicationsList = indicationsList;
    }

    // Getter functions
    public String getDeya() {
        return deya;
    }

    public String getPin() {
        return pin;
    }

    public String getStatus() {
        return status;
    }

    public String getOwner() {
        return owner;
    }

    public String getAddress() {
        return address;
    }

    public Watermeter getWatermeter() {
        return watermeter;
    }

    public List<Indications> getIndicationsList() {
        return indicationsList;
    }

    // Setter functions
    public void setDeya(String deya) {
        this.deya = deya;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setWatermeter(Watermeter watermeter) {
        this.watermeter = watermeter;
    }

    public void setIndicationsList(List<Indications> indicationsList) {
        this.indicationsList = indicationsList;
    }

    // Watermeter class with getters and setters
    public static class Watermeter implements Serializable {
        private String watermeterPin;
        private String watermeterNumber;
        private String consumerCode;
        private String status;
        private String fullNameOnBill;
        private String rrdto;

        public String getWatermeterPin() {
            return watermeterPin;
        }

        public void setWatermeterPin(String watermeterPin) {
            this.watermeterPin = watermeterPin;
        }

        public String getWatermeterNumber() {
            return watermeterNumber;
        }

        public void setWatermeterNumber(String watermeterNumber) {
            this.watermeterNumber = watermeterNumber;
        }

        public String getConsumerCode() {
            return consumerCode;
        }

        public void setConsumerCode(String consumerCode) {
            this.consumerCode = consumerCode;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getFullNameOnBill() {
            return fullNameOnBill;
        }

        public void setFullNameOnBill(String fullNameOnBill) {
            this.fullNameOnBill = fullNameOnBill;
        }

        public String getRrdto() {
            return rrdto;
        }

        public void setRrdto(String rrdto) {
            this.rrdto = rrdto;
        }
    }

    // Indications class with getters and setters
    public static class Indications implements Serializable {
        private String usage;
        private String newIndication;
        private String newIndicationDate;
        private String cubicDifference;
        private String cubicCharged;
        private String comments;

        public String getUsage() {
            return usage;
        }

        public void setUsage(String usage) {
            this.usage = usage;
        }

        public String getNewIndication() {
            return newIndication;
        }

        public void setNewIndication(String newIndication) {
            this.newIndication = newIndication;
        }

        public String getNewIndicationDate() {
            return newIndicationDate;
        }

        public void setNewIndicationDate(String newIndicationDate) {
            this.newIndicationDate = newIndicationDate;
        }

        public String getCubicDifference() {
            return cubicDifference;
        }

        public void setCubicDifference(String cubicDifference) {
            this.cubicDifference = cubicDifference;
        }

        public String getCubicCharged() {
            return cubicCharged;
        }

        public void setCubicCharged(String cubicCharged) {
            this.cubicCharged = cubicCharged;
        }

        public String getComments() {
            return comments;
        }

        public void setComments(String comments) {
            this.comments = comments;
        }
    }


}
