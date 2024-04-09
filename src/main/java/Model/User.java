package Model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "users", schema = "irish_home_listings")
public class User {
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "firstName")
    private String firstName;
    @Basic
    @Column(name = "lastName")
    private String lastName;
    @Basic
    @Column(name = "phone")
    private String phone;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "userName")
    private String userName;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "active")
    private int active;
    @Basic
    @Column(name = "userType")
    private String userType;
}
