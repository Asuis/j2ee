package com.asuis.j2ee;

import com.asuis.j2ee.dao.UserMapper;
import com.asuis.j2ee.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class J2eeApplicationTests {
	@Autowired
	StringRedisTemplate stringRedisTemplate;
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	@Autowired
	private UserMapper mapper;
	@Test
	public void contextLoads() {
		// 保存字符串
		stringRedisTemplate.opsForValue().set("aaa", "111");
		Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
	}
	@Test
	public void redisTest() {
		User user = new User("hello","seeyou","avatar");
		redisTemplate.opsForValue().set("user",user,80, TimeUnit.SECONDS);
		boolean exists=redisTemplate.hasKey("user");
		if(exists){
			System.out.println("exists is true");
		}else{
			System.out.println("exists is false");
		}
	}
	@Test
	public void userMapper(){
		com.asuis.j2ee.model.User user = new com.asuis.j2ee.model.User();
		user.setAge((byte) 19);
		user.setIsAccountNonExpired("1");
		user.setUsername("Asuis");
		user.setPhone("15988440973");
		user.setIsEnabled("1");
		user.setCreateDate(new Date(System.currentTimeMillis()));
		mapper.insert(user);
	}
}
