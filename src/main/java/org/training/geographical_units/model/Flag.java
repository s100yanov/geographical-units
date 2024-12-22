package org.training.geographical_units.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Flag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Long size;
    private String path;
    @OneToOne
    @JoinColumn(name = "country")
    @JsonBackReference
    private Country country;

    public Flag(String name, long size, String path, Country country) {
        this.name = name;
        this.size = size;
        this.path = path;
        this.country = country;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
