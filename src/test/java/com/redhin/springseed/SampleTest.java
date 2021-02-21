package com.redhin.springseed;


import com.redhin.springseed.entity.McCard;
import com.redhin.springseed.mapper.McCardMapper;
import java.util.List;
import javax.annotation.Resource;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleTest {

  @Resource
  private McCardMapper mcCardMapper;

  @Test
  public void testSelect() {
    System.out.println(("----- selectAll method test ------"));
    List<McCard> userList = mcCardMapper.selectList(null);
    Assert.assertEquals(5, userList.size());
    userList.forEach(System.out::println);
  }

}
