package org.zimo.app.qydj.storage.mybatis.SQLBuilder;

import org.apache.ibatis.jdbc.SQL;
import org.zimo.app.qydj.storage.mybatis.Table;

public class MemUserSQLBuilder {

	public String insert(){
		return new SQL(){
			{
				INSERT_INTO(Table.MEM_USER.mark());
				VALUES("mobile", "#{mobile}");
				VALUES("sex", "#{sex}");
				VALUES("nickName", "#{nick_name}");
				VALUES("password", "#{password}");
				VALUES("birthday", "#{birthday}");
				VALUES("created",  "#{created}");
				VALUES("updated",  "#{updated}");
			}
		}.toString();
	}
	
	public String getByMobile(){
		return new SQL(){
			{
				SELECT("*");
				FROM(Table.MEM_USER.mark());
				WHERE("mobile=#{mobile}");
			}
		}.toString();
	}
}
