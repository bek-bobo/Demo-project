package com.example.demo.group.entity;


import com.example.demo.user.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "`group`")
@Getter
@Setter
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GroupStatus groupStatus;

    @OneToMany(mappedBy = "group",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnore
    private List<User> users;
}
