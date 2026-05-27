package edu.unicauca.dsantiago135.concesionaria.ui.model;

public enum UserRole {
    MANAGER("Gerente"),
    SELLER("Vendedor");

    private final String displayName;

    UserRole(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public boolean isManager() {
        return this == MANAGER;
    }
}
