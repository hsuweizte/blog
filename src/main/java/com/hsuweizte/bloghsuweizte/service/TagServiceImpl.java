package com.hsuweizte.bloghsuweizte.service;

import com.hsuweizte.bloghsuweizte.NotFoundException;
import com.hsuweizte.bloghsuweizte.dao.TagRepository;
import com.hsuweizte.bloghsuweizte.po.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Transactional
    @Override
    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Transactional
    @Override
    public Tag getTag(Long id) {
        return tagRepository.getReferenceById(id);
    }

    @Override
    public Tag getTagByName(String name) {
        return tagRepository.findByName(name);
    }

    @Transactional
    @Override
    public Page<Tag> listTag(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    @Override
    public List<Tag> listTag() {
        return tagRepository.findAll();
    }


    @Override
    public List<Tag> listTagTop() {
        List<Tag> all = tagRepository.findAll();
        for(Tag t :all){
            t.setBlogsize(t.getBlogs().size());
        }
        tagRepository.saveAll(all);
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "blogsize"));
        return tagRepository.findTop(pageable);
    }


    @Override
    public List<Tag> listTag(String ids) { //1,2,3

        return tagRepository.findByIdsIn(convertToList(ids));
    }

    private List<Long> convertToList(String ids) {
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null) {
            String[] idarray = ids.split(",");
            for (int i = 0; i < idarray.length; i++) {
                list.add(new Long(idarray[i]));
            }
        }
        return list;
    }


    @Transactional
    @Override
    public Tag updateTag(Long id, Tag tag) {
        Tag t = tagRepository.getReferenceById(id);
        if (t == null) {
            throw new NotFoundException("不存在該標籤");
        }
        BeanUtils.copyProperties(tag, t);
        return tagRepository.save(t);
    }


    @Transactional
    @Override
    public void deleteTag(Long id) {
        tagRepository.delete(tagRepository.getReferenceById(id));
    }
}
