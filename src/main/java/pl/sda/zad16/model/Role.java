package pl.sda.zad16.model;

import javax.persistence.*;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String name;

    public Role(){}

    public Role(String name) {
        this.name = name;
    }
}
