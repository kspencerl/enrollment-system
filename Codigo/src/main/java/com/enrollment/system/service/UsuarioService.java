package com.enrollment.system.service;

import com.enrollment.system.dto.LoginResponse;
import com.enrollment.system.models.Usuario;
import com.enrollment.system.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public LoginResponse autenticarUsuario(String email, String senha) {
        Usuario usuario = usuarioRepository.findByEmail(email);

        if (usuario == null || !usuario.getSenha().equals(senha)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Acesso negado: Credenciais Invalidas");
        }

        return new LoginResponse("Acesso Liberado", usuario.getTipoUsuario());
    }
}
