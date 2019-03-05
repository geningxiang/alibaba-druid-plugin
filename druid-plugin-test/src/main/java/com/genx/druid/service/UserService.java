package com.genx.druid.service;

import com.genx.druid.entitiy.UserEntity;
import com.genx.druid.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author: genx
 * @date: 2019/3/5 23:30
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Iterable<UserEntity> findAll() {
        return userRepository.findAll(Sort.by("id"));
    }

    /**
     * 查询对象
     **/
    public Optional<UserEntity> getModel(long id) {
        return userRepository.findById(id);
    }

    /**
     * 添加对象
     **/
    public void save(UserEntity model) {
        userRepository.save(model);
    }
}
