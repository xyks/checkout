package com.hixyks.checkout.web_app.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hixyks.checkout.web_app.entity.Category;
import com.hixyks.checkout.web_app.entity.Question;
import com.hixyks.checkout.web_app.entity.User;
import com.hixyks.checkout.web_app.enums.DefaultCategory;
import com.hixyks.checkout.web_app.repository.CategoryRepository;
import com.hixyks.checkout.web_app.repository.FollowingRepository;
import com.hixyks.checkout.web_app.repository.QuestionId;
import com.hixyks.checkout.web_app.repository.QuestionRepository;
import com.hixyks.checkout.web_app.repository.TrainingRepository;
import com.hixyks.checkout.web_app.repository.UserRepository;
import com.hixyks.checkout.web_app.service.QuestionService;
import com.hixyks.checkout.web_app.vo.QuestionVO;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    TrainingRepository trainingRepository;
    
    @Autowired 
    FollowingRepository followingRepository;

    @Autowired
    UserRepository userRepository;

    private static Function<Question, QuestionVO> convertToVO = x -> QuestionVO.builder().id(x.getId())
            .title(x.getTitle()).answer(x.getAnswer()).categoryId(x.getCategory().orElseGet(Category::new).getId())
            .categoryName(x.getCategory().orElseGet(Category::new).getName()).build();

    /**
     * TODO optimize to domain driven
     */
    @Override
    public void add(QuestionVO vo) {
        Optional<Question> old = questionRepository.findByTitleIgnoreCase(vo.getTitle());
        if (old.isPresent()) {
            throw new RuntimeException("Question already exists.");
        }
        Category category = null;
        
        if (Optional.ofNullable(vo.getCategoryId()).isPresent() && vo.getCategoryId() > 0) {
            category = Optional.ofNullable(categoryRepository.findOne(vo.getCategoryId()))
                    .orElseThrow(RuntimeException::new);
        }
        User user = Optional.ofNullable(userRepository.findOne(vo.getCreateById())).orElseThrow(RuntimeException::new);
        Question question = Question.builder().title(vo.getTitle()).answer(vo.getAnswer()).category(category)
                .createBy(user).createDate(LocalDateTime.now()).build();
        questionRepository.save(question);
    }

    @Override
    public void deleteById(Integer id) {
        questionRepository.delete(id);

    }

    @Override
    public Optional<QuestionVO> findById(Integer id) {
        Optional<Question> one = Optional.ofNullable(questionRepository.findOne(id));
        return one.map(x -> {
            QuestionVO vo = QuestionVO.builder().id(x.getId()).title(x.getTitle()).answer(x.getAnswer())
                    .categoryName(x.getCategory().orElseGet(Category::new).getName())
                    .build();
            return vo;
        });
    }
    
    /**
     * TODO   Add more description fields
     */
    @Override
    public Optional<QuestionVO> getDetails(Integer id, Integer userId) {
        Optional<Question> one = Optional.ofNullable(questionRepository.findOne(id));
        boolean following = followingRepository.findByLinkQuestionIdAndLinkUserId(id, userId).isPresent();
        return one.map(x -> {
            QuestionVO vo = QuestionVO.builder().id(x.getId()).title(x.getTitle()).answer(x.getAnswer())
                    .categoryId(x.getCategory().orElseGet(Category::new).getId())
                    .categoryName(x.getCategory().orElseGet(Category::new).getName())
                    .createByName(x.getCreateBy().getName())
                    .modifyByName(x.getModifyBy().orElseGet(User::new).getName())
                    .createDate(x.getCreateDate())
                    .modifyDate(x.getModifyDate())
                    .following(following)
                    .build();
                    
            return vo;
        });
    }

    @Override
    public void update(QuestionVO vo) {
        
        Question old = Optional.ofNullable(questionRepository.findOne(vo.getId())).orElseThrow(RuntimeException::new);
        
        if (!old.getTitle().equals(vo.getTitle())) {
            Optional<Question> question = questionRepository.findByTitleIgnoreCase(vo.getTitle());
            if (question.isPresent()) {
                throw new RuntimeException("Question already exists.");
            }
        }
        
        User user = Optional.ofNullable(userRepository.findOne(vo.getModifyById())).orElseThrow(RuntimeException::new);
        Optional<Category> category = Optional.empty();
        if (Optional.ofNullable(vo.getCategoryId()).isPresent() ) {
            
            if (vo.getCategoryId() > 0) {
                category = Optional.of(categoryRepository.findOne(vo.getCategoryId()));
                if (!category.isPresent()) {
                    throw new RuntimeException("No category exists.");
                }
                old.setCategory(category.get());
            }else {
                old.setCategory(null);
            }
            
        }else {
            old.setCategory(null);
        }
        
        old.setModifyBy(user);
        old.setModifyDate(LocalDateTime.now());
        old.setTitle(vo.getTitle());
        old.setAnswer(vo.getAnswer());
        questionRepository.save(old);
    }

    @Override
    public List<QuestionVO> findPendingForTrainingQuestionsByCategory(Integer categoryId, Integer userId) {
        List<Question> questions = null;
        if (categoryId.equals(DefaultCategory.all.getId())) {
            questions = questionRepository.findPendingForTrainingQuestionsWithAllCategory(userId);
        }else if (categoryId.equals(DefaultCategory.noCategory.getId())) {
            questions = questionRepository.findPendingForTrainingQuestionsWithoutCategory(userId);
        }else {
            List<Category> categories = getCategoriesWithChildrenById(categoryId);
            List<Integer> categoryIds = categories.stream().map(Category::getId).collect(Collectors.toList());
            questions = questionRepository.findPendingForTrainingQuestions(categoryIds, userId);
        }
        
        List<QuestionVO> vos = questions.stream().map(convertToVO)
        .collect(Collectors.toList());
        
        List<Integer> ids = questions.stream().map(Question::getId).collect(Collectors.toList()); 
        List<QuestionId> followingIds = followingRepository.findByLinkQuestionIdIn(ids);
        Set<Integer> followingIdsSet = followingIds.stream().map(QuestionId::getId).collect(Collectors.toSet());
        
        vos.stream().forEach(x->{
            if (followingIdsSet.contains(x.getId())) {
                x.setFollowing(true);
            }
        });
         return vos;
    }

    private List<Category> getCategoriesWithChildrenById(Integer categoryId) {
        List<Category> result = new ArrayList<>();
            
        Optional<Category> category = Optional.ofNullable(categoryRepository.findOne(categoryId));
        getCategoriesWithChildren(category, result);
        
        return result;
    }

    private List<Category> getCategoriesWithChildren(Optional<Category> category, List<Category> result) {
        if (category.isPresent()) {
            result.add(category.get());
            if (category.get().getChildren().isEmpty()) {
                return result;
            } else {
                for (Category item : category.get().getChildren()) {
                    return getCategoriesWithChildren(Optional.ofNullable(item), result);
                }
            }
        }
        return result;
    }

    @Override
    public List<QuestionVO> search(String searchBy, String searchValue) {
        List<QuestionVO> result = new ArrayList<>();
        if ("id".equalsIgnoreCase(searchBy)) {
           result = Stream.of(questionRepository.findOne(Integer.valueOf(searchValue))).map(convertToVO).collect(Collectors.toList());
        }else if ("title".equalsIgnoreCase(searchBy)) {
             result = questionRepository.findByTitleContaining(searchValue).stream().map(convertToVO).collect(Collectors.toList());  
        }
        return result;
    }




}
