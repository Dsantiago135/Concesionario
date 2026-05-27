package edu.unicauca.dsantiago135.concesionaria.ui.service;

import java.util.List;
import java.util.Optional;

import edu.unicauca.dsantiago135.concesionaria.ui.model.AppUser;
import edu.unicauca.dsantiago135.concesionaria.ui.model.UserRole;

/**
 * Usuarios de prueba para la aplicación de escritorio.
 * <p>
 * Gerente: usuario {@code gerente} / contraseña {@code manager123}<br>
 * Vendedor: usuario {@code vendedor} / contraseña {@code seller123}
 * </p>
 */
public class AuthService {

    public static final AppUser DEMO_MANAGER = new AppUser(
            "gerente", "manager123", UserRole.MANAGER, 1, "Ana Gerente");

    public static final AppUser DEMO_SELLER = new AppUser(
            "vendedor", "seller123", UserRole.SELLER, 2, "Luis Vendedor");

    private static final List<AppUser> USERS = List.of(DEMO_MANAGER, DEMO_SELLER);

    public Optional<AppUser> authenticate(String username, String password) {
        if (username == null || password == null) {
            return Optional.empty();
        }
        String user = username.trim().toLowerCase();
        return USERS.stream()
                .filter(u -> u.username().equalsIgnoreCase(user) && u.password().equals(password))
                .findFirst();
    }

    public List<AppUser> getDemoUsers() {
        return USERS;
    }
}
