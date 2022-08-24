package com.abn.assessment.mycookbook.repository;

import com.abn.assessment.mycookbook.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
}
