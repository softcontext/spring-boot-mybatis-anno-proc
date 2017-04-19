package com.example.web.repository;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.web.model.Member;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MemberRepositoryTest {
	@Autowired
	private MemberRepository memberRepository;

	@Transactional
	@Test
	public void testInsert() {
		Member member = new Member();
		member.setName("Kris");
		member.setActive(true);
		
		int affected = memberRepository.insert(member);
		System.out.println("affected = "+affected);
		System.out.println("id = "+member.getId());
	}

	@Transactional
	@Test
	public void testUpdate() {
		Member member = new Member();
		member.setName("Kris");
		member.setActive(true);
		
		int affected = memberRepository.insert(member);
		System.out.println("affected = "+affected);
		System.out.println("id = "+member.getId());
		
		member.setActive(false);
		
		affected = memberRepository.update(member);
		System.out.println("affected = "+affected);
		
		Member m = memberRepository.selectById(member.getId());
		System.out.println("active = " + m.isActive());
		assertEquals(member.isActive(), m.isActive());
	}

	@Test
	public void testDeleteById() {
		int oldCount = memberRepository.count();
		System.out.println("oldCount = "+oldCount);
		
		Member member = new Member();
		member.setName("Kris");
		member.setActive(true);
		
		int affected = memberRepository.insert(member);
		System.out.println("affected = "+affected);
		System.out.println("id = "+member.getId());
		
		affected = memberRepository.deleteById(member.getId());
		System.out.println("affected = "+affected);
		
		int presentCount = memberRepository.count();
		System.out.println("presentCount = "+presentCount);
		assertEquals(oldCount, presentCount);
	}

	@Test
	public void testCount() {
		int count = memberRepository.count();
		System.out.println(count);
	}

	@Test
	public void testSelectAll() {
		List<Member> members = memberRepository.selectAll();
		members.forEach(System.out::println);
	}

	@Test
	public void testSelectById() {
		int id = 1;
		Member member = memberRepository.selectById(id);
		System.out.println(member);
	}

	@Test
	public void testSelectByLimit() {
		int skip = 1;
		int limit = 2;
		List<Member> members = memberRepository.selectByLimit(skip, limit);
		members.forEach(System.out::println);
	}
	
	@Test
	public void testProcedure() {
		Map<String, Object> param = new HashMap<>();
		param.put("in", 10);

		memberRepository.multiply(param);
		/**
		 * 프로시져에서 OUT 타입 변수로 결과를 리턴할 경우 메소드의 결과로 아무것도 리턴되지 않는다.
		 * 대신 호출할때 넘겨준 파라메터에 결과를 담아서 준다.
		 */
		param.forEach((key, value) -> {
			System.out.println(key + ":" + value);
		});
	}
	
	@Test
	public void testSelectFromProcById() {
		int id = 1;
		Member member = memberRepository.selectFromProcById(id);
		System.out.println(member);
	}
}
