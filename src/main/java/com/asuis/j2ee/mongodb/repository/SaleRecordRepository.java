package com.asuis.j2ee.mongodb.repository;

import com.asuis.j2ee.mongodb.domain.ActionRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 15988440973
 */
@Repository
public interface SaleRecordRepository extends MongoRepository<ActionRecord,String> {
}
