package com.hsuweizte.bloghsuweizte.dao;

import com.hsuweizte.bloghsuweizte.po.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {

    Tag findByName(String name);

    @Query("select t from Tag t")
    List<Tag> findTop(Pageable pageable);


    @Query("SELECT t FROM Tag t WHERE t.id IN :ids")
    List<Tag> findByIdsIn(List<Long> ids);

}
