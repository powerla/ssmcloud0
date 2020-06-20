import com.czq.service.FileService;
import com.czq.service.FoldService;
import com.czq.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.util.Random;

public class test {
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    @Test
    public void test0() {
        UserService userService=(UserService)context.getBean("UserService");
        System.out.println("user = " + userService.queryUserByUsername("qiang","qiang"));
        System.out.println(userService.queryPassword("qiang","qiang"));
        System.out.println("user = " + userService.queryUser("qiang"));
    }
    @Test
    public void filetset()
    {
        FileService fileService=(FileService)context.getBean("FileService");
        for (com.czq.pojo.File file : fileService.queryfileAll()) {
            System.out.println(file);
        }
        fileService.deleteById(2);
    }
    @Test
    public void fold()
    {
        FoldService foldService =(FoldService) context.getBean("FoldService");
        int foldid=new Random().nextInt(100000);
        //十万以内的随机数作为id,减少重复
        String foldName="test1";
        String hdfsPath="F:\\load\\"+foldName;
        File dir = new File(hdfsPath);
        if (!dir.exists())
        {
            dir.mkdir();
            foldService.addFold(foldid,foldName,hdfsPath,0);
        }
    }
    @Test
    public void split()
    {
        String name="1.jpg";
        String[] name1=name.split("\\.");
        System.out.println("name1.length = " + name1.length);
        for (String s : name1) {
            System.out.println(s);
        }
    }
}
