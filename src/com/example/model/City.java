package com.example.model;

public class City {
    private int idCity;
    private String name;
    private int touristNumber;
    private String description;

    public City(int idCity, String name, int touristNumber, String description) {
        this.idCity = idCity;
        this.name = name;
        this.touristNumber = touristNumber;
        this.description = description;
    }

    public City() {
    }

    public int getIdCity() {
        return idCity;
    }

    public void setIdCity(int idCity) {
        this.idCity = idCity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTouristNumber() {
        return touristNumber;
    }

    public void setTouristNumber(int touristNumber) {
        this.touristNumber = touristNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


  @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        City city = (City) o;
        return idCity == city.idCity &&
                name.equals(city.name) &&
                touristNumber == city.touristNumber  &&
                description.equals(city.description);
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result +idCity;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result +touristNumber;
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        return result;
    }
}


