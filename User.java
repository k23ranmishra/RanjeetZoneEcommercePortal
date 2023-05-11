package rkm.ecom;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;


@Entity
@Table(name = "users")
public class User implements UserDetails  {
    @SequenceGenerator(
            name = "users_sequence",
            sequenceName = "users_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "users_sequence"
    )
    private int id;

    @NotNull(message = "First Name cannot be empty")
    @Column(name = "first_name")
    private String firstName;

    @NotNull(message = "Last Name cannot be empty")
    @Column(name = "last_name")
    private String lastName;
    
    @NotNull(message = "Address Line1  cannot be empty")
    @Column(name = "addressLine1")
    private String addressLine1;
    
    @Column(name = "addressLine2")
    private String addressLine2;
    

    @NotNull(message = "zipCode  cannot be empty")
    @Column(name = "zipCode")
    private String zipCode;
    
    @NotNull(message = "city  cannot be empty")
    @Column(name = "city")
    private String city;
    
    @NotNull(message = "state  cannot be empty")
    @Column(name = "state")
    private String state;
    
    @NotNull(message = "country  cannot be empty")
    @Column(name = "country")
    private String country;
    

    @NotNull(message = "Email cannot be empty")
    @Email(message = "Please enter a valid email address")
    @Column(name = "email", unique = true)
    private String email;

    @NotNull(message = "Password cannot be empty")
    @Length(min = 7, message = "Password should be atleast 7 characters long")
    @Column(name = "password")
    private String password;

    @Column(name = "mobile", unique = true)
    @Length(min = 10, message = "Password should be atleast 10 number long")
    private String mobile;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "locked")
    private Boolean locked = false;

    @Column(name = "enabled")
    private Boolean enabled = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public Role getRole() { return role; }

    public void setRole(rkm.ecom.Role role) {
        this.role = role;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getFirstName() { return firstName; }
    public String getAddressLine1() { return addressLine1; }
    public String getAddressLine2() { return addressLine2; }
    public String getZipCode() { return zipCode; }
    public String getCity() { return city; }
    public String getState() { return state; }
    public String getCountry() { return country; }

    public void setFirstName(String firstName) { this.firstName = firstName;}
    public void setAddressLine1(String addressLine1) { this.addressLine1 = addressLine1;}
    public void setAddressLine2(String addressLine2) { this.addressLine2 = addressLine2;}
    public void setZipCode(String zipCode) { this.zipCode = zipCode;}
    public void setCity(String city) { this.city = city;}
    public void setState(String state) { this.state = state;}
    public void setCountry(String country) { this.country = country;}
    

    public String getMobile() { return mobile; }

    public void setMobile(String mobile) { this.mobile = mobile; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }
}