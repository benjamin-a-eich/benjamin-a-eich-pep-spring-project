package com.example.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Message;

@Repository
public interface MessageRepository extends CrudRepository<Message, Integer> {

    // Used a custom query to get the number of rows modified.
    @Modifying
    @Query("DELETE FROM Message m WHERE m.messageId = :id")
    int deleteByIdAndReturnCount(@Param("id") int id);

    Collection<Message> findAllMessagesByPostedBy(Integer postedBy);
}
