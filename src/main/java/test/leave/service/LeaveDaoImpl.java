package test.leave.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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

	private JdbcTemplate jdbcTemplate;

	@Override
	public Leave findOne(Long id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM oa_leave WHERE ID = ?";
		List<Leave> entitys = jdbcTemplate.query(sql, new Object[] { id }, new RowMapper<Leave>() {

			@Override
			public Leave mapRow(ResultSet arg0, int arg1) throws SQLException {
				Leave entity = new Leave();
				System.out.println(arg0);
				
				return entity;
			}

		});

		return entitys.get(0);
	}

	@Override
	public void save(final Leave entity) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				String INSERT_SQL = "INSERT INTO `activiti`.`oa_leave` (" +
						"	`ID`," +
						"	`PROCESS_INSTANCE_ID`," +
						"	`USER_ID`," +
						"	`START_TIME`," +
						"	`END_TIME`," +
						"	`LEAVE_TYPE`," +
						"	`REASON`," +
						"	`APPLY_TIME`," +
						"	`REALITY_START_TIME`," +
						"	`REALITY_END_TIME`" +
						")" +
						"VALUES(?,?,?,?,?,?,?,?,?,?);" ;
				java.sql.PreparedStatement ps = connection.prepareStatement(INSERT_SQL , new String[] { 
						
				});
				ps.setLong(1, entity.getId());
				ps.setString(2, entity.getProcessInstance().getId());
				ps.setString(3, entity.getUserId());
				;
				Date xz = d2d(entity.getStartTime());
				ps.setDate(4, xz);
				ps.setDate(5, d2d(entity.getEndTime()));
				;
				;
				ps.setString(6, entity.getLeaveType());
				ps.setString(7, entity.getReason());
				;
				;
				ps.setDate(8, d2d(entity.getApplyTime()));
				ps.setDate(9, d2d(entity.getRealityStartTime()));
				ps.setDate(10, d2d(entity.getRealityEndTime()));
			
				return ps;
			}

			private Date d2d(java.util.Date s) {
				Date xz = new Date(s.getTime());
				return xz;
			}
		}, keyHolder);
	}

}
