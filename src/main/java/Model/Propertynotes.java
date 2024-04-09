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
public class Propertynotes {
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "propertyId")
    private Integer propertyId;
    @Basic
    @Column(name = "userId")
    private Integer userId;
    @Basic
    @Column(name = "note")
    private String note;
}
