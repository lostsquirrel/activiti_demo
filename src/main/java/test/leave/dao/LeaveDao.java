package test.leave.dao;

import org.springframework.stereotype.Component;

/**
 * 请假实体管理接口
 *
 * @author HenryYan
 */
@Component
public interface LeaveDao {

	Leave findOne(Long id);

	void save(Leave entity);
}
