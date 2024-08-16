package com.courseproject.travelagencyrestapiawtentication.models.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateReservationDTO {
    @NotNull
    @Size(min = 5)
    private String contactName;
    @NotNull
    @Size(min = 10)
    private String phoneNumber;
    @NotNull
    private Long holiday;

    public CreateReservationDTO() {
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getHoliday() {
        return holiday;
    }

    public void setHoliday(Long holiday) {
        this.holiday = holiday;
    }
}
