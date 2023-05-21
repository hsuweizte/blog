package com.hsuweizte.bloghsuweizte.service;

import com.hsuweizte.bloghsuweizte.NotFoundException;
import com.hsuweizte.bloghsuweizte.dao.TypeRepository;
import com.hsuweizte.bloghsuweizte.po.Type;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by limi on 2017/10/16.
 */
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository typeRepository;

    @Transactional
    @Override
    public Type saveType(Type type) {
        return typeRepository.save(type);
    }

    @Transactional
    @Override
    public Type getType(Long id) {
        return typeRepository.getReferenceById(id);
    }

    @Override
    public Type getTypeByName(String name) {
        return typeRepository.findByName(name);
    }

    @Transactional
    @Override
    public Page<Type> listType(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }

    @Override
    public List<Type> listType() {
        return typeRepository.findAll();
    }


    @Transactional
    @Override
    public List<Type> listTypeTop() {
        List<Type> all = typeRepository.findAll();
        for (Type t : all) {
            t.setBlogsize(t.getBlogs().size());
        }
        typeRepository.saveAll(all);
        Pageable pageable = PageRequest.of(0, 6, Sort.by(Sort.Direction.DESC, "blogsize"));
        return typeRepository.findTop(pageable);
    }


    @Transactional
    @Override
    public Type updateType(Long id, Type type) {
        Type t = typeRepository.getReferenceById(id);
        if (t == null) {
            throw new NotFoundException("不存在該組別");
        }
        BeanUtils.copyProperties(type, t);
        return typeRepository.save(t);
    }

    @Transactional
    @Override
    public void deleteType(Long id) {
        typeRepository.delete(typeRepository.getReferenceById(id));
    }
}
