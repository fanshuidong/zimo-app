package org.zimo.app.qydj.storage.mybatis.mapper;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.zimo.app.qydj.storage.domain.MemUser;
import org.zimo.app.qydj.storage.mybatis.SQLBuilder.MemUserSQLBuilder;

public interface MemUserMapper {

	@InsertProvider(type = MemUserSQLBuilder.class, method = "insert")
	void insert(MemUser memUser);
	
	@SelectProvider(type = MemUserSQLBuilder.class, method = "getByMobile")
	MemUser getByMobile(String mobile);
}
