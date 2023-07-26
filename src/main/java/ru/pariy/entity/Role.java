package ru.pariy.entity;

/**
 * @brief Текущий класс описывает роли
 */
public enum Role {
    ADMIN ("admin"),
    CLIENT ("user");

    String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
