package com.example.web.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.example.web.model.Member;

@Mapper
public interface MemberRepository {

	@Insert("insert into member(name, active) values(#{name}, #{active})")
	@SelectKey(statement = "select LAST_INSERT_ID()", before = false, keyProperty = "id", resultType = Integer.class)
	public int insert(Member member);

	@Update("update member set name = #{name}, active = #{active} where id = #{id}")
	public int update(Member member);

	@Delete("delete from member where id = #{id}")
	public int deleteById(int id);

	@Select("select count(*) from member")
	public int count();

	@Select("select * from member order by id asc")
	/*생략하면 @ResultType(Member.class) 설정을 사용한다.*/
//	@ResultType(Member.class)
	/*member-mapper.xml에 정의한 리절트맵을 사용할 수 있다.*/
//	@ResultMap("memberResultMap")
	/*@Results(id = "memberResults") 설정을 사용할 수 있다.*/
//	@ResultMap("memberResults")
	public List<Member> selectAll();

	@Select("select * from member where id = #{id}")
	@Results(id = "memberResults", value = { 
			@Result(property = "id", column = "id"),
			@Result(property = "name", column = "name"), 
			@Result(property = "active", column = "active") })
	public Member selectById(int id);
	
//	@Select("select * from member order by id asc limit #{arg0}, #{arg1}")
//	@Select("select * from member order by id asc limit #{param1}, #{param2}")
	@Select("select * from member order by id asc limit #{first}, #{second}")
	public List<Member> selectByLimit(@Param("first") int skip, @Param("second") int limit);
	
//	@Select(value = "{ CALL proc_multiply(#{in}, #{out, mode=OUT, jdbcType=INTEGER, javaType=int}) }")
//	@Options(statementType = StatementType.CALLABLE)
	public void multiply(Map<String, Object> param);
	
	public Member selectFromProcById(int id);
}
