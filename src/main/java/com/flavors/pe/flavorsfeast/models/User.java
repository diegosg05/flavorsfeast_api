package com.flavors.pe.flavorsfeast.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User implements UserDetails {
    @Column(name = "id_user")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUser;

    @Column(name = "full_name_user")
    private String fullNameUser;

    @Column(name = "email_user")
    private String emailUser;

    @Column(name = "password_user")
    private String passwordUser;

    @Column(name = "phone_user")
    private String phoneUser;

    @Column(name = "date_creation_user")
    private LocalDate dateCreationUser;

    @Column(name = "role_user")
    @Enumerated(EnumType.STRING)
    private Role roleUser;

    @OneToMany(mappedBy = "user")
    private List<Purchase> purchases;

    @OneToMany(mappedBy = "user")
    private List<Reservation> reservations;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(roleUser.name()));
    }

    @Override
    public String getPassword() {
        return passwordUser;
    }

    @Override
    public String getUsername() {
        return emailUser;
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

    @Override
    public boolean isEnabled() {
        return true;
    }
}
