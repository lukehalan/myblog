package com.lukehalan.myblog.services.impl;


import com.lukehalan.myblog.entities.PostEntity;
import com.lukehalan.myblog.entities.UserEntity;
import com.lukehalan.myblog.repositories.IPostRepository;
import com.lukehalan.myblog.services.IPostService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


import java.util.Optional;

@Service
public class PostServiceImp implements IPostService {

    private final IPostRepository iPostRepository;

    @Autowired
    public PostServiceImp(IPostRepository iPostRepository) {
        this.iPostRepository = iPostRepository;
    }


    @Override
    public PostEntity save(PostEntity post) {
        return iPostRepository.saveAndFlush(post);
    }

    @Override
    public void delete(PostEntity post) {
        iPostRepository.delete(post);
    }

    @Override
    public Optional<PostEntity> findId(Long id) {
        return iPostRepository.findById(id);
    }

    @Override
    public Page<PostEntity> findAllOrderedByDatePageable(int page) {
        return iPostRepository.findAllByOrderByCreateDateDesc(new PageRequest(reducePageByOne(page), 5));
    }

    @Override
    public Page<PostEntity> findByUserOrderedByDatePageable(UserEntity user, int page) {
        return iPostRepository.findByUserOrderByCreateDateDesc(user, new PageRequest(reducePageByOne(page), 5));
    }

    private int reducePageByOne(int page){
        return (page < 1) ? 0 : page - 1;
    }
}

