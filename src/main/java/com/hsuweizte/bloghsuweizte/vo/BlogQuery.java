package com.hsuweizte.bloghsuweizte.vo;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class BlogQuery {

    private String title;
    private Long typeId;
    private boolean recommend;

}
