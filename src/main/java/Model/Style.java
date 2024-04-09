package Model;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "styles", schema = "irish_home_listings")
public class Style {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "styleId")
    private int styleId;
    @Basic
    @Column(name = "pStyle")
    private String pStyle;
}
