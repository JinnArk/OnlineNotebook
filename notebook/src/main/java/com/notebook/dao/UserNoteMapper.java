package com.notebook.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.notebook.config.mybatis.plus.SuperMapper;
import com.notebook.entities.UserNote;
  

/**
 * 
 * @author 2ing
 * @createTime 2018年1月8日
 * @remarks 记事本DAO
 */

public interface UserNoteMapper extends SuperMapper<UserNote>{
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018-01-26
	 * @remarks 根据pageModel和condition获取事件Note数据
	 */
	List<UserNote> selectNoteByPageAndCondition(RowBounds rowBounds, @Param("ew") Wrapper<UserNote> wrapper) throws Exception;
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月25日
	 * @remarks	更新事件Note状态，更新为state变量对于状态，根据事件noteIDs数组
	 */
	int updateNotesState(@Param("state") String state, @Param("noteIDs") List<String> noteIDs) throws Exception;
}
