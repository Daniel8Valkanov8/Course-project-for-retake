package com.courseproject.travelagencyrestapiawtentication.models.dto.request;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateLocationDTO{
    @NotNull
    @Size(min = 3)
    private String number;
    @NotNull
    @Size(min = 3)
    private String country;
    @NotNull
    @Size(min = 3)
    private String city;
    @NotNull
    @Size(min = 3)
    private String street;


    public CreateLocationDTO(String number, String country, String city, String street) {
        this.number = number;
        this.country = country;
        this.city = city;
        this.street = street;

    }

    public CreateLocationDTO() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
