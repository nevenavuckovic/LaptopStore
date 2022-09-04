package rs.ac.ni.pmf.webproject.laptopstore.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "korisnici", uniqueConstraints = {@UniqueConstraint(columnNames = {"korisnicko_ime", "eadresa"})})
public class UserEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "korisnicko_ime")
    String username;
    @Column(name = "eadresa")
    String email;
    @Column(name = "lozinka")
    String password;

    @Column(name = "uloga_id")
    Integer roleId;

    @Column(name = "aktivan")
    Boolean active;

    @Column(name = "token")
    String token;

    public UserEntity() {
    }

    @PrePersist
    @PreUpdate
    private void prepare(){
        if(active == null){
            active = false;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
