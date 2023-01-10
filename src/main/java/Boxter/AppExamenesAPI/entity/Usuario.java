package Boxter.AppExamenesAPI.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usuarios")
@Data
public class Usuario implements UserDetails, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "campo requerido")
    private String username;
    private String password;
    private String nombre;
    private String apellido;

    @Email(message = "Debe ingresar un correo valido")
    @Column(nullable = false, unique = true)
    private String email;
    private String telefono;
    private boolean enabled = true;
    private String perfil;
    private String foto;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "usuario")
    @JsonIgnore
    private Set<UsuarioRol> usuarioRoles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Authority> autoridades = new HashSet<>();
        this.usuarioRoles.forEach(usuarioRol -> {
            autoridades.add(new Authority(usuarioRol.getRol().getRolNombre()));
        });

        return autoridades;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
