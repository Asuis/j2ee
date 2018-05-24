package com.asuis.j2ee.mongodb.repository;

import com.asuis.j2ee.mongodb.domain.AuditMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 15988440973
 */
@Repository
public interface AuditMessageRepository extends MongoRepository<AuditMessage,String> {
    public Page<AuditMessage> findByAuditorIdOrderByAuditTime(String auditorId, Pageable pageable);
}
