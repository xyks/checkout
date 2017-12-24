package com.hixyks.checkout.web_app.service;

import java.util.List;

import com.hixyks.checkout.web_app.vo.CategoryVO;

public interface CategoryService {

    void add(CategoryVO vo);

    List<CategoryVO> findAllWithTreeLevel();

    List<CategoryVO> findAll();


}
