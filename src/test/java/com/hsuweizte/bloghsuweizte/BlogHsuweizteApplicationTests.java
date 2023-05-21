package com.hsuweizte.bloghsuweizte;

import com.hsuweizte.bloghsuweizte.po.Tag;
import com.hsuweizte.bloghsuweizte.service.BlogService;
import com.hsuweizte.bloghsuweizte.service.TagService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@SpringBootTest
class BlogHsuweizteApplicationTests {
    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;

    @Test
    void contextLoads() {


    }

    @Test
    @GetMapping("/tags/{id}")
    public String tags(@PageableDefault(size = 5, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                       @PathVariable Long id, Model model) {
        List<Tag> tags = tagService.listTagTop();
        if (id == -1) {
            id = tags.get(0).getId();
        }
        model.addAttribute("tags", tags);
        model.addAttribute("page", blogService.listBlog(id,pageable));
        model.addAttribute("activeTagId", id);
        return "tags";
    }

}
