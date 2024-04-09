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
@Table(name = "garagetypes", schema = "irish_home_listings")
public class Garagetype {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "garageId")
    private int garageId;
    @Basic
    @Column(name = "gType")
    private String gType;
}
