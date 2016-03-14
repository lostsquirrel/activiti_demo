package test.leave.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import test.leave.dao.Leave;
import test.leave.dao.LeaveDao;

@Component
public class LeaveDaoImpl implements LeaveDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Leave findOne(Long id) {
		String sql = "SELECT * FROM oa_leave WHERE ID = ?";
		List<Leave> entitys = jdbcTemplate.query(sql, new Object[] {id}, new RowMapper<Leave>() {

			@Override
			public Leave mapRow(ResultSet rs, int arg1) throws SQLException {
				Leave entity = new Leave();
				System.out.println(rs);
				;
				entity.setId(rs.getLong("ID"));
				java.util.Date endTime = new java.util.Date();
				endTime.setTime(rs.getDate("END_TIME").getTime());
				entity.setEndTime(endTime);
				String leaveType = rs.getString("LEAVE_TYPE");
				//				entity.setHistoricProcessInstance(historicProcessInstance);
				entity.setLeaveType(leaveType );
//				entity.setProcessDefinition(processDefinition);
				
				String processInstance = rs.getString("PROCESS_INSTANCE_ID");
				entity.setProcessInstanceId(processInstance  );
				java.util.Date realityEndTime = new java.util.Date();
				realityEndTime.setTime(rs.getDate("REALITY_END_TIME").getTime());
				entity.setRealityEndTime(realityEndTime );
				java.util.Date realityStartTime = new java.util.Date();
				realityStartTime.setTime(rs.getDate("REALITY_START_TIME").getTime());
				entity.setRealityStartTime(realityStartTime);
				String reason = rs.getString("REASON");
				entity.setReason(reason );
				java.util.Date startTime = new java.util.Date();
				startTime.setTime(rs.getDate("START_TIME").getTime());
				entity.setStartTime(startTime);
				Task task = null;
				entity.setTask(task);
				String userId = rs.getString("USER_ID");
				entity.setUserId(userId);
//				entity.setVariables(variables);
				entity.setApplyTime(new java.util.Date());
				return entity;
			}

		});
		Leave x = null;
		if (entitys.size() > 0) {
			x = entitys.get(0);
		}
		return x;
	}

	@Override
	public Long save(final Leave entity) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				String INSERT_SQL = "INSERT INTO `activiti`.`oa_leave` (" +
//						"	`ID`," +
//						"	`PROCESS_INSTANCE_ID`," +
						"	`USER_ID`," +
						"	`START_TIME`," +
						"	`END_TIME`," +
						"	`LEAVE_TYPE`," +
						"	`REASON`," +
						"	`APPLY_TIME`," +
						"	`REALITY_START_TIME`," +
						"	`REALITY_END_TIME`" +
						")" +
						"VALUES(?,?,?,?,?,?,?,?);" ;
				java.sql.PreparedStatement ps = connection.prepareStatement(INSERT_SQL , new String[] { 
					"id"	
				});
//				ps.setLong(1, entity.getId());
//				ps.setString(2, entity.getProcessInstance().getId());
				ps.setString(1, entity.getUserId());
				;
				Date xz = d2d(entity.getStartTime());
				ps.setDate(2, xz);
				ps.setDate(3, d2d(entity.getEndTime()));
				;
				;
				ps.setString(4, entity.getLeaveType());
				ps.setString(5, entity.getReason());
				;
				;
				ps.setDate(6, d2d(entity.getApplyTime()));
				ps.setDate(7, d2d(entity.getRealityStartTime()));
				ps.setDate(8, d2d(entity.getRealityEndTime()));
			
				return ps;
			}

			private Date d2d(java.util.Date s) {
				
				Date xz = null;
				if (s != null) {
					new Date(s.getTime());
				}
						
				return xz;
			}
		}, keyHolder);
		
		return keyHolder.getKey().longValue();
	}

	@Override
	public void addProcessInstanceId(Long id, String processInstanceId) {
		// TODO Auto-generated method stub
		String sql = "update oa_leave set PROCESS_INSTANCE_ID = ? WHERE ID = ?";
		jdbcTemplate.update(sql, new Object[]{processInstanceId, id});
	}

}
