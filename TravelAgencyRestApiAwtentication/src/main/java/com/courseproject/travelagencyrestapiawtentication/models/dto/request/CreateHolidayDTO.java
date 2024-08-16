package com.courseproject.travelagencyrestapiawtentication.models.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;

public class CreateHolidayDTO {
    @NotNull
    private long location;
    @NotNull
    @Size(min = 5, max = 20)
    private String title;
    @NotNull
    private Date startDate;
    @NotNull
    @Size(min = 1)
    private int duration;
    @NotNull
    private String price;
    @NotNull
    @Size(min = 1)
    private int freeSlots;

    public CreateHolidayDTO() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getFreeSlots() {
        return freeSlots;
    }

    public void setFreeSlots(int freeSlots) {
        this.freeSlots = freeSlots;
    }

    public long getLocation() {
        return location;
    }

    public void setLocation(Integer location) {
        this.location = location;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("  \"location\": ").append(location).append(",\n");
        sb.append("  \"title\": \"").append(title).append("\",\n");
        sb.append("  \"startDate\": \"").append(startDate).append("\",\n");
        sb.append("  \"duration\": ").append(duration).append(",\n");
        sb.append("  \"price\": \"").append(price).append("\",\n");
        sb.append("  \"freeSlots\": ").append(freeSlots).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
