package com.asuis.j2ee.controller;

import com.asuis.j2ee.dao.DepartmentDao;
import com.asuis.j2ee.dao.UserDao;
import com.asuis.j2ee.dto.Result;
import com.asuis.j2ee.form.AuditForm;
import com.asuis.j2ee.model.Department;
import com.asuis.j2ee.model.User;
import com.asuis.j2ee.mongodb.domain.AuditMessage;
import com.asuis.j2ee.service.AuditorService;
import com.asuis.j2ee.utils.IpMacUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author 15988440973
 */
@RestController
@RequestMapping("/auditor")
@PreAuthorize("hasRole('ROLE_AUDIT') or hasRole('ROLE_ADMIN')")
public class AuditorController {

    @Autowired
    private AuditorService auditorService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private DepartmentDao departmentDao;

    @RequestMapping("/request/query")
    public Result queryRequest() {
        return auditorService.getRequestNeedAudit(1,100);
    }
    @RequestMapping("/request/{requestId}/audit")
    public Result audit(HttpServletRequest request, @RequestAttribute("username") String username, @PathVariable("requestId") String requestId, @ModelAttribute("audit_form")AuditForm auditForm) {

        User user = userDao.getUserByUsername(username);

        AuditMessage auditMessage = new AuditMessage();
        auditMessage.setAuditor(username);
        auditMessage.setAuditorId(user.getUserId().toString());
        auditMessage.setAuditTime(new Date(System.currentTimeMillis()));
        auditMessage.setAuditorAvatar(user.getAvatar());
        auditMessage.setSuggest(auditForm.getSuggest());
        auditMessage.setResult(auditForm.isResult());

        Department department = departmentDao.getDepartmentByUserId(user.getUserId());

        String ip = null;
        try {
            ip = IpMacUtils.getIpAddr(request);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return auditorService.auditRequest(user,department,ip,auditMessage,requestId);
    }
    @RequestMapping("/request/me/audit/{status}")
    public Result getMyAudit(@RequestAttribute("username")String username,@PathVariable("status")String status) {
        User user = userDao.getUserByUsername(username);
        return auditorService.getRequestByStatusAndAuditor(user.getUserId().toString(),status,0,100);
    }
}
