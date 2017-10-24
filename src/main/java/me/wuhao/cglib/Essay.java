package me.wuhao.cglib;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by everseeker on 16/7/26.
 */
public class Essay implements Serializable {
    private String title;
    private String context;
    private long pickupTimestamp;

    public Essay() {}

    public Essay(String title, String context) {
        this.title = title;
        this.context = context;
        pickupTimestamp = new Date().getTime();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public long getPickupTimestamp() {
        return pickupTimestamp;
    }

    public void setPickupTimestamp(long pickupTimestamp) {
        this.pickupTimestamp = pickupTimestamp;
    }

    public void printEssay() {
        System.out.println("The essay is [title=" + title + ", context=" +context + ", pickupTimestamp=" + pickupTimestamp + "]");
    }

    @Override
    public String toString() {
        return "[title=" + title + ", context=" +context + ", pickupTimestamp=" + pickupTimestamp + "]";
    }
}
