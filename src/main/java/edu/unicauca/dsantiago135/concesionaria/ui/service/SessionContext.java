package edu.unicauca.dsantiago135.concesionaria.ui.service;

import edu.unicauca.dsantiago135.concesionaria.ui.model.AppUser;

public final class SessionContext {

    private static AppUser currentUser;

    private SessionContext() {
    }

    public static void setCurrentUser(AppUser user) {
        currentUser = user;
    }

    public static AppUser getCurrentUser() {
        return currentUser;
    }

    public static boolean isManager() {
        return currentUser != null && currentUser.role().isManager();
    }

    public static void clear() {
        currentUser = null;
    }
}
