package com.example.base.config;

import com.example.base.dao.UsuarioRepository;
import com.example.base.model.Papel;
import com.example.base.model.Usuario;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@AllArgsConstructor
public class AdminSeeder {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner popularBancoDeDados() {
        return args -> {
            if (usuarioRepository.findByEmail("admin@empresa.com").isEmpty()) {

                Usuario admin = new Usuario();
                admin.setNome("Administrador Master");
                admin.setEmail("admin@empresa.com");
                admin.setSenha(passwordEncoder.encode("admin123"));
                admin.setPapel(Papel.ADMIN);

                usuarioRepository.save(admin);
            }
        };
    }
}