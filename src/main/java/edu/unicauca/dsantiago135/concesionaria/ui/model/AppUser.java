package edu.unicauca.dsantiago135.concesionaria.ui.model;

public record AppUser(
        String username,
        String password,
        UserRole role,
        int employeeId,
        String displayName) {
}
