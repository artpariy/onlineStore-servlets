package ru.pariy.entity;

/**
 * @brief Текущий класс описывает пользователя
 */
public class User{
    private String email;
    private String password;
    private String salt;

    private String firstName;
    private String lastName;
    private String role;

    public User(String email, String password, String uniqueSalt, String firstName, String lastName, String role) {
        this.email = email;
        this.password = password;
        this.salt = uniqueSalt;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public User(String email, String firstName, String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getSalt() {
        return salt;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getRole() {
        return role;
    }
}
