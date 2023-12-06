package bg.softuni.mobilele.model.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "brands")
@NamedEntityGraph(
        name = "brandWithModels",
        attributeNodes = @NamedAttributeNode("models")
)
public class BrandEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

//    private LocalDateTime created;
//
//    private LocalDateTime modified;

    @OneToMany(
            mappedBy = "brand",
            targetEntity = ModelEntity.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
//    @Fetch(value = FetchMode.SUBSELECT)
    private List<ModelEntity> models;

    public BrandEntity() {
        this.models = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public BrandEntity setName(String name) {
        this.name = name;
        return this;
    }

    public List<ModelEntity> getModels() {
        return models;
    }

    public BrandEntity setModels(List<ModelEntity> models) {
        this.models = models;
        return this;
    }

    @Override
    public String toString() {
        return "BrandEntity{" +
                "name='" + name + '\'' +
                ", models=" + models +
                '}';
    }
}
