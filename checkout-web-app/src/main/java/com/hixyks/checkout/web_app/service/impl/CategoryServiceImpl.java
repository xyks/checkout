package com.hixyks.checkout.web_app.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hixyks.checkout.web_app.entity.Category;
import com.hixyks.checkout.web_app.enums.DefaultCategory;
import com.hixyks.checkout.web_app.repository.CategoryRepository;
import com.hixyks.checkout.web_app.service.CategoryService;
import com.hixyks.checkout.web_app.vo.CategoryVO;
/**
 * 
 * @author xyks@yahoo.com
 *
 */
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    CategoryRepository categoryRepository;
    
    @Override
    public void add(CategoryVO vo) {
/*        Optional<Category> one = categoryRepository.findByNameIgnoreCase(vo.getName());
        if (one.isPresent()) {
            throw new RuntimeException(String.format("Category name already exists: %s", vo.getName()));
        }else {
            Category parent = null;
            if (vo.getParentId() !=null) {
               parent = Optional.ofNullable(categoryRepository.findOne(vo.getParentId())).orElseThrow(RuntimeException::new);
            }
            
           // Category category = Category.builder().name(vo.getName()).parent(parent).build();
           // categoryRepository.save(category);
        }*/
        
    }
    
    //TODO optimize  
    //1. add cache 
    //2. get all, then iterate
    @Override
    public List<CategoryVO> findAllWithTreeLevel() {
        List<Category> tops = categoryRepository.findByParentIdIsNull();
        List<CategoryVO> result = new LinkedList<>();
        result.add(CategoryVO.builder().id(DefaultCategory.all.getId()).name(DefaultCategory.all.getName()).level(0).levelArray(new int[0]).build());
        result.add(CategoryVO.builder().id(DefaultCategory.noCategory.getId()).name(DefaultCategory.noCategory.getName()).level(1).levelArray(new int[1]).build());
        for (Category top : tops) {
            CategoryVO vo = CategoryVO.builder().id(top.getId()).name(top.getName()).level(1).levelArray(new int[1]).build();
            result.add(vo);
            appendChildren(top, result, 1);
        }
        return result;
    }
    
    private void appendChildren(Category category, List<CategoryVO> list,int  level) {
        for (Category child : category.getChildren()) {
            CategoryVO vo = CategoryVO.builder().id(child.getId()).name(child.getName()).level(level+1).levelArray(new int[level+1]).build();
            list.add(vo);
            appendChildren(child, list, level+1);
        }
        return;
    }

    @Override
    public List<CategoryVO> findAll() {
        return categoryRepository.findAll().stream().map(x-> { return CategoryVO.builder().id(x.getId()).name(x.getName()).build();}).collect(Collectors.toList());
    }
    
/*    private int getLevel(Category category, HashMap<Integer, Integer> temp) {
        if (category.getParent() == null) {
            return 0;
        }else if (temp.containsKey(category.getId())) {
            return temp.get(category.getId());
        }else{
            Category parent = category.getParent();
            int level = getLevel(parent, temp) +1 ;
            temp.put(category.getId(), level);
            return level;
        }
    }*/

}
