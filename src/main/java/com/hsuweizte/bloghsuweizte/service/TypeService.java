package com.hsuweizte.bloghsuweizte.service;
import com.hsuweizte.bloghsuweizte.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TypeService {

    Type saveType(Type type);

    Type getType(Long id);

    Type getTypeByName(String name);

    Page<Type> listType(Pageable pageable);

    List<Type> listType();

    List<Type> listTypeTop();

    Type updateType(Long id,Type type);

    void deleteType(Long id);
}
