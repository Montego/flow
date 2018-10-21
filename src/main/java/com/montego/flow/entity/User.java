package com.montego.flow.entity;

import lombok.Getter;
import lombok.Setter;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Username can't be empty")
    private String username;
    @NotBlank(message = "Password can't be empty")
    private String password;
    @Email(message="Email is not correct")
    @NotBlank(message = "Email can't be empty")
    private String email;

    private String activationCode;

    private boolean active;

    @ManyToMany
    @JoinTable(
            name="user_subscriptions",
            joinColumns = {@JoinColumn(name = "channel_id")},
            inverseJoinColumns = {@JoinColumn(name="subscriber_id")}
    )
    //чтобы не возникало NullPointerException добавляем инициацию HashSet
    private Set<User> subscribers = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name="user_subscriptions",
            joinColumns = {@JoinColumn(name = "subscriber_id")},
            inverseJoinColumns = {@JoinColumn(name="channel_id")}
    )
    private Set<User> subscribtions = new HashSet<>();

    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Note> notes;

//    public boolean isAdmin(){
//        return roles.contains(Role.ADMIN);
//    };

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name="user_role",joinColumns = @JoinColumn(name="user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return getRoles();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return isActive();
//    }

    public User(){

    }
}