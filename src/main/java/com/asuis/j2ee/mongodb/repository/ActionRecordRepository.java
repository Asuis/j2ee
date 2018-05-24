package com.asuis.j2ee.mongodb.repository;

import com.asuis.j2ee.mongodb.domain.ActionRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @author 15988440973
 */
@Repository
public interface ActionRecordRepository extends MongoRepository<ActionRecord,String> {
    /**
     * @param _id_date time
     * @return actionRecord
     */
//    public Page<ActionRecord> findBy_id_Date(Date _id_date,Pageable pageable);

    /**
     * @param action login
     * @param pageable fu
     * @return action
     */
    public Page<ActionRecord> findByActionEquals(String action, Pageable pageable);

    public Page<ActionRecord> findByActionNot(String action, Pageable pageable);
}
