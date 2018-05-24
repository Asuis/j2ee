package com.asuis.j2ee.mongodb.repository;

import com.asuis.j2ee.mongodb.domain.NoticeMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 15988440973
 */
@Repository
public interface NoticeMessageRepository extends MongoRepository<NoticeMessage,String> {
    public Page<NoticeMessage> findByTo(String to, Pageable pageable);
    public Page<NoticeMessage> findByFrom(String from, Pageable pageable);
}
