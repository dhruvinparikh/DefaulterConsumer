/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package defaulterconsumer;

/**
 *
 * @author imsil
 */
public class Consumer {

    long consumerID = 0;
    String consumerFirst;
    String consumerLast;
    String consumerAddress;
    double billAmount;
    int dueDateYYYY;
    int dueDateMM;
    int dueDateDD;

    public Consumer() {
    }
    
    Consumer(long consumerID, String consumerFirst, String consumerLast, String consumerAddress, double billAmount, int dueDateYYYY, int dueDateMM, int dueDateDD) {
        this.consumerID = consumerID;
        this.consumerFirst = consumerFirst;
        this.consumerLast = consumerLast;
        this.consumerAddress = consumerAddress;
        this.billAmount = billAmount;
        this.dueDateYYYY = dueDateYYYY;
        this.dueDateMM = dueDateMM;
        this.dueDateDD = dueDateDD;
    }

    public String toString() {
        return String.format("%12d", consumerID)
                + String.format("%4s", "|")
                + String.format("%3s"," ")
                + String.format("%-15s", consumerFirst)
                + String.format("%s", "|")
                + String.format("%3s"," ")
                + String.format("%-12s", consumerLast)
                + String.format("%s", "|")
                + String.format("%3s"," ")
                + String.format("%-13s", consumerAddress)
                + String.format("%s", "|")
                + String.format("%6s"," ")
                + String.format("%-14.2f", billAmount)
                + String.format("%s", "|")
                + String.format("%3s"," ")
                + String.format("%d/%02d/%02d", dueDateYYYY, dueDateMM, dueDateDD);

    }

    public void setConsumerID(long consumerID) {
        this.consumerID = consumerID;
    }

    public long getConsumerID() {
        return consumerID;
    }

    public void setConsumerFirst(String consumerFirst) {
        this.consumerFirst = consumerFirst;
    }

    public String getConsumerFirst() {
        return consumerFirst;
    }

    public void setConsumerLast(String consumerLast) {
        this.consumerLast = consumerLast;
    }

    public String getConsumerLast() {
        return consumerLast;
    }

    public void setConsumerAddress(String consumerAddress) {
        this.consumerAddress = consumerAddress;
    }

    public String getConsumerAddress() {
        return consumerAddress;
    }

    public void setBillAmount(double billAmount) {
        this.billAmount = billAmount;
    }

    public double getBillAmount() {
        return billAmount;
    }

    public void setDueDateYYYY(int dueDateYYYY) {
        this.dueDateYYYY = dueDateYYYY;
    }

    public int getDueDateYYYY() {
        return dueDateYYYY;
    }

    public int getDueDateMM() {
        return dueDateMM;
    }

    public void setDueDateDD(int dueDateDD) {
        this.dueDateDD = dueDateDD;
    }

    public void setDueDateMM(int dueDateMM) {
        this.dueDateMM = dueDateMM;
    }

    public int getDueDateDD() {
        return dueDateDD;
    }
}
