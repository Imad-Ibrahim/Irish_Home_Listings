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
@Table(name = "agents", schema = "irish_home_listings")
public class Agent {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "agentId")
    private int agentId;
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
    @Column(name = "fax")
    private String fax;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "username")
    private String username;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "userType")
    private String userType;
}
