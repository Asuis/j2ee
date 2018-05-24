package com.asuis.j2ee.mongodb.repository;

import com.asuis.j2ee.mongodb.domain.RequestMessage;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * @author 15988440973
 */
public interface RequestMessageRepository extends MongoRepository<RequestMessage,String> {
    /**
     * dfdfdf
     * @param status 状态
     * @param pageable fdf
     * @return fdf*/
    public Page<RequestMessage> findByStatusEqualsOrderByRequestTime(String status, Pageable pageable);
    public RequestMessage findBy_id(String _id);
    @Query(value = "{'auditMessage.auditorId' : ?#{[0]}}",count = true)
    public List<RequestMessage> findByQuery(String auditorId);
    public Page<RequestMessage> findByStatus(String status, Pageable pageable);
    public Page<RequestMessage> findByApplicant(String applicant, Pageable pageable);
}
