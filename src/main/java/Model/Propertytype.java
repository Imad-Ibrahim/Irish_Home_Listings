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
@Table(name = "propertytypes", schema = "irish_home_listings")
public class Propertytype {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "typeId")
    private int typeId;
    @Basic
    @Column(name = "pType")
    private String pType;
}
