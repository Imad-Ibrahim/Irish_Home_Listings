package Model;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "properties", schema = "irish_home_listings")
public class Property {
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "street")
    private String street;
    @Basic
    @Column(name = "city")
    private String city;
    @Basic
    @Column(name = "listingNum")
    private Integer listingNum;
    @Basic
    @Column(name = "styleId")
    private Integer styleId;
    @Basic
    @Column(name = "typeId")
    private Integer typeId;
    @Basic
    @Column(name = "bedrooms")
    private Integer bedrooms;
    @Basic
    @Column(name = "bathrooms")
    private Integer bathrooms;
    @Basic
    @Column(name = "squarefeet")
    private Integer squarefeet;
    @Basic
    @Column(name = "berRating")
    private String berRating;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "lotsize")
    private String lotsize;
    @Basic
    @Column(name = "garagesize")
    private Integer garagesize;
    @Basic
    @Column(name = "garageId")
    private Integer garageId;
    @Basic
    @Column(name = "agentId")
    private Integer agentId;
    @Basic
    @Column(name = "photo")
    private String photo;
    @Basic
    @Column(name = "price")
    private Double price;
    @Basic
    @Column(name = "dateAdded")
    private Date dateAdded;
    @Basic
    @Column(name = "archived")
    private Integer archived;
    @Basic
    @Column(name = "vendorId")
    private Integer vendorId;

/*    public Property(String street, String city, Integer listingNum, Integer styleId, Integer typeId, Integer bedrooms,
                    Integer bathrooms, Integer squarefeet, String berRating, String description, String lotsize, Integer garagesize,
                    Integer garageId, Integer agentId, String photo, Double price, Date dateAdded, Integer archived, Integer vendorId) {
        this.street = street;
        this.city = city;
        this.listingNum = listingNum;
        this.styleId = styleId;
        this.typeId = typeId;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.squarefeet = squarefeet;
        this.berRating = berRating;
        this.description = description;
        this.lotsize = lotsize;
        this.garagesize = garagesize;
        this.garageId = garageId;
        this.agentId = agentId;
        this.photo = photo;
        this.price = price;
        this.dateAdded = dateAdded;
        this.archived = archived;
        this.vendorId = vendorId;
    }*/
}
