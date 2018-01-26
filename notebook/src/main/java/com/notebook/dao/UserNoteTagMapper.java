package com.notebook.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.notebook.config.mybatis.plus.SuperMapper;
import com.notebook.entities.UserNotetag;
  

/**
 * 
 * @author 2ing
 * @createTime 2018年1月8日
 * @remarks 记事本标签DAO
 */

public interface UserNoteTagMapper extends SuperMapper<UserNotetag>{
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018-01-26
	 * @remarks 根据pageModel和condition获取标签NoteTag数据
	 */
	List<UserNotetag> selectNoteTagByPageAndCondition(RowBounds rowBounds, @Param("ew") Wrapper<UserNotetag> wrapper) throws Exception;
}
