package ru.punko.spingcourse.Project2Boot.models;

import com.opencsv.bean.CsvBindByPosition;

public class Person {

    @CsvBindByPosition(position = 0)
    private int id;

    @CsvBindByPosition(position = 1)
    private int randomId;

    @CsvBindByPosition(position = 2)
    private String fullName;

    @CsvBindByPosition(position = 3)
    private String address;

    @CsvBindByPosition(position = 4)
    private String phone;

    public Person(int id, int randomId, String fullName, String address, String phone) {
        this.id = id;
        this.randomId = randomId;
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
    }

    public Person() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRandomId() {
        return randomId;
    }

    public void setRandomId(int randomId) {
        this.randomId = randomId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", randomId=" + randomId +
                ", fullName='" + fullName + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
