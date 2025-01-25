package com.CCMe.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.CCMe.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    User findByEmail(String email);
    List<User> findByIsContractor(boolean isContractor);
    @Query(value = "select * from user u where u.is_contractor = true and u.first_name like %:query%", nativeQuery=true)
    List<User> findAllByisContractorTrueAndfirstNameLike(@Param("query") String query);
    @Query(value = "select us.user_id from user_skills us where us.skills_id in :ids", nativeQuery = true)
    List<User> findUsersBySkill(@Param("ids")List<Long> skillIds);
    @Query(value = "select u.email from skill s join user_skills us on s.id=us.skills_id join user u on u.id=us.user_id where name in :skillNames", nativeQuery = true)
    List<String> getUsersInSkill(@Param("skillNames") List<String> skillNames);
}
