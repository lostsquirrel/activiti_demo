package test.leave.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import test.leave.dao.Leave;
import test.leave.dao.LeaveDao;

/**
 * 请假实体管理
 *
 * @author HenryYan
 */
@Component
@Transactional(readOnly = true)
public class LeaveManager {

	private LeaveDao leaveDao;

	public Leave getLeave(Long id) {
		return leaveDao.findOne(id);
	}

	@Transactional(readOnly = false)
	public Long saveLeave(Leave entity) {
		if (entity.getId() == null) {
			entity.setApplyTime(new Date());
		}
		Long id = leaveDao.save(entity);
		entity.setId(id);
		
		return id;
	}
	@Transactional(readOnly = false)
	public void addProcessInstance(Leave entity) {
		leaveDao.addProcessInstanceId(entity.getId(), entity.getProcessInstanceId());
	}

	@Autowired
	public void setLeaveDao(LeaveDao leaveDao) {
		this.leaveDao = leaveDao;
	}

}
