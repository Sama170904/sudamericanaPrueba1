package com.example.crudrapido.service;

import com.example.crudrapido.dto.LoginRequestDTO;
import com.example.crudrapido.dto.TokenResponseDTO;
import com.example.crudrapido.entity.Administrador;
import com.example.crudrapido.entity.Token;
import com.example.crudrapido.repository.AdministradorRepository;
import com.example.crudrapido.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AdministradorRepository administradorRepository;
    private final TokenRepository tokenRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public TokenResponseDTO login(LoginRequestDTO request) {
        //Se verifica si la contraseña coincide con la base de datos
        // Si la contraseña está mal, esto lanza una excepción automáticamente y corta el proceso.
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        //Si pasamos la línea anterior, las credenciales son correctas. Buscamos al usuario.
        Administrador admin = administradorRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Administrador no encontrado"));

        // Fabricamos los tokens 
        String jwtToken = jwtService.generateToken(admin);
        String refreshToken = jwtService.generateRefreshToken(admin);

        //Inactivamos tokens viejos en la BD y guardamos el nuevo
        revokeAllUserTokens(admin);
        saveUserToken(admin, jwtToken);

        // Devolvemos el sobre DTO listo para ir al controlador
        return new TokenResponseDTO(jwtToken, refreshToken);
    }

    //se inserta en un objeto token el la informacion del token y se la guarda en la db
    private void saveUserToken(Administrador admin, String jwtToken) {
        Token token = new Token();
        token.setAdministrador(admin);
        token.setToken(jwtToken);
        token.setExpired(false);
        token.setRevoked(false);
        tokenRepository.save(token);
    }


    private void revokeAllUserTokens(Administrador admin) {
        //busca el admin
        var validUserTokens = tokenRepository.findAllValidTokensByUser(admin.getId());
        //si es su primera vez iniciando sesion solo retorna
        if (validUserTokens.isEmpty()) {
            return;
        }
        //si ha iniciado sesion antes y tiene tokens previos, todos los revoca con un bucle for each
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        //se gurdan los cambios en la db
        tokenRepository.saveAll(validUserTokens);
    }
}