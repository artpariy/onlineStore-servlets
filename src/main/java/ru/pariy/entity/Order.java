package ru.pariy.entity;

/**
 * @brief Текущий класс описывает заказ
 */
public class Order {
    private long id;
    private final String phoneNumber;
    private final String address;
    private String userLogin;

    public Order(String phoneNumber, String address) {
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public Order(String phoneNumber, String address, String userLogin) {
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.userLogin = userLogin;
    }

    public long getId() {
        return id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getUserLogin() {
        return userLogin;
    }

}
