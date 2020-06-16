import com.czq.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class test {
    @Test
    public void test0() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService=(UserService)context.getBean("UserService");
        System.out.println("user = " + userService.queryUserByUsername("qiang","qiang"));
        System.out.println(userService.queryPassword("qiang","qiang"));
        System.out.println("user = " + userService.queryUser("qiang"));
    }
}
