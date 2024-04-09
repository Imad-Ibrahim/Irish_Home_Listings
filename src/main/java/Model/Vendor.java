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
@Table(name = "vendors", schema = "irish_home_listings", catalog = "")
public class Vendor {
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "vendorId")
    private int vendorId;
    @Basic
    @Column(name = "firstName")
    private String firstName;
    @Basic
    @Column(name = "LastName")
    private String lastName;
    @Basic
    @Column(name = "street")
    private String street;
    @Basic
    @Column(name = "city")
    private String city;
    @Basic
    @Column(name = "phone")
    private String phone;
    @Basic
    @Column(name = "email")
    private String email;
}
