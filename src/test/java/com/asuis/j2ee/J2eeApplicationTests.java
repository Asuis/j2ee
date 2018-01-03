package com.asuis.j2ee;

import com.asuis.j2ee.dao.RoleDao;
import com.asuis.j2ee.dao.RouterDao;
import com.asuis.j2ee.dao.UserDao;
import com.asuis.j2ee.dao.UserMapper;
import com.asuis.j2ee.domain.Routes;
import com.asuis.j2ee.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private RouterDao routerDao;
	@Test
	public void contextLoads() {
		// 保存字符串
		stringRedisTemplate.opsForValue().set("aaa", "111");
		Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
	}
	@Test
	public void redisTest() {
		User user = new User(111L,"hello","avatar");
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
	@Test
	public void userDao() {
		com.asuis.j2ee.model.User user = userDao.getUserByUsername("Asuis");
		System.out.println(user.toString());
	}
	@Test
	public void roleDao() {
		List<String> auths = roleDao.getAuthsByUserId(1L);
		System.out.println(auths.toString());
		List<GrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority("ROLE_MANAGER"));
		List<com.asuis.j2ee.model.Routes> routes = roleDao.getRoutesByRoleName(roles);
	}
	@Test
	public void routerDao() {
		List<Routes> routes = routerDao.findRouterByPid(0);
		System.out.println(routes);
	}
}
