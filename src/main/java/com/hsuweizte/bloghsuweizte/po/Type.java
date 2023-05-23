package com.hsuweizte.bloghsuweizte.po;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_type")
public class Type {

    @Id
    @GeneratedValue
    private Long id;
    @NotBlank(message = "分類不得為空")
    private String name;

    @OneToMany(mappedBy = "type", cascade = ALL)
    private List<Blog> blogs = new ArrayList<>();

    private int blogsize;
}
