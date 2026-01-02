package top.potat.spring.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@ComponentScan(basePackages = {
        "top.potat.spring.admin",
        "top.potat.spring.db.service"
})
public class ScaffoldAdminApplication {
    public static void main(String[] args) {
        //计算启动时间
        long startTime = System.currentTimeMillis();
        SpringApplication.run(ScaffoldAdminApplication.class,args);
        long endTime = System.currentTimeMillis();
        System.out.println("启动时间: " + (endTime - startTime) + "ms");
        System.out.println("启动成功");
        System.out.println("      ___          _____          ___                       ___     \n" +
                "     /  /\\        /  /::\\        /__/\\        ___          /__/\\    \n" +
                "    /  /::\\      /  /:/\\:\\      |  |::\\      /  /\\         \\  \\:\\   \n" +
                "   /  /:/\\:\\    /  /:/  \\:\\     |  |:|:\\    /  /:/          \\  \\:\\  \n" +
                "  /  /:/~/::\\  /__/:/ \\__\\:|  __|__|:|\\:\\  /__/::\\      _____\\__\\:\\ \n" +
                " /__/:/ /:/\\:\\ \\  \\:\\ /  /:/ /__/::::| \\:\\ \\__\\/\\:\\__  /__/::::::::\\\n" +
                " \\  \\:\\/:/__\\/  \\  \\:\\  /:/  \\  \\:\\~~\\__\\/    \\  \\:\\/\\ \\  \\:\\~~\\~~\\/\n" +
                "  \\  \\::/        \\  \\:\\/:/    \\  \\:\\           \\__\\::/  \\  \\:\\  ~~~ \n" +
                "   \\  \\:\\         \\  \\::/      \\  \\:\\          /__/:/    \\  \\:\\     \n" +
                "    \\  \\:\\         \\__\\/        \\  \\:\\         \\__\\/      \\  \\:\\    \n" +
                "     \\__\\/                       \\__\\/                     \\__\\/    ");
    }
}
