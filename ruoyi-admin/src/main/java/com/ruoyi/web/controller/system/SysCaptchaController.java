package com.ruoyi.web.controller.system;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.utils.TzCodeUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 登录页面的图片验证码（支持算术形式）
 * 被@PostConstruct修饰的方法会在服务器加载Servlet的时候运行，并且只会被服务器执行一次。
 * PostConstruct在构造函数之后执行，init（）方法之前执行。PreDestroy（）方法在destroy（）方法知性之后执行
 *
 * 另外，spring中Constructor、@Autowired、@PostConstruct的顺序
 *
 * 其实从依赖注入的字面意思就可以知道，要将对象p注入到对象a，那么首先就必须得生成对象a和对象p，
 * 才能执行注入。所以，如果一个类A中有个成员变量p被@Autowried注解，那么@Autowired注入是发生在A的构造方法执行完之后的。
 * 如果想在生成对象时完成某些初始化操作，而偏偏这些初始化操作又依赖于依赖注入，那么久无法在构造函数中实现。
 * 为此，可以使用@PostConstruct注解一个方法来完成初始化，@PostConstruct注解的方法将会在依赖注入完成后被自动调用。
 *
 * Constructor >> @Autowired >> @PostConstruct
 *
 *
 * 简单理解：
 * @Autowired 根据类型注入，
 * @Resource 默认根据名字注入，其次按照类型搜索
 * @Autowired @Qualifie("userService") 两个结合起来可以根据名字和类型注入
 *
 * @Resource的作用相当于@Autowired，只不过@Autowired按byType自动注入，
 * 而@Resource默认按 byName自动注入罢了。@Resource有两个属性是比较重要的，
 * 分是name和type，Spring将@Resource注解的name属性解析为bean的名字，而type属性则解析为bean的类型。
 * @author ruoyi
 */
@Controller
@RequestMapping("/captcha")
@ConditionalOnProperty(prefix = "shiro.user", name = "captchaEnabled", havingValue = "true")
public class SysCaptchaController extends BaseController
{
    @PostConstruct
    public void init(){
        System.out.println("=============SysCaptchaController INIT=====================");
    }
    @Resource(name = "captchaProducer")
    private Producer captchaProducer;

    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;

    /**
     * 验证码生成
     */
    @GetMapping(value = "/captchaImage")
    public ModelAndView getKaptchaImage(HttpServletRequest request, HttpServletResponse response)
    {
        ServletOutputStream out = null;
        try
        {
            HttpSession session = request.getSession();
            response.setDateHeader("Expires", 0);
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            response.addHeader("Cache-Control", "post-check=0, pre-check=0");
            response.setHeader("Pragma", "no-cache");
            response.setContentType("image/jpeg");

            String type = request.getParameter("type");
            String capStr = null;
            String code = null;
            BufferedImage bi = null;
            if ("math".equals(type))
            {
                String capText = captchaProducerMath.createText();
                capStr = capText.substring(0, capText.lastIndexOf("@"));
                code = capText.substring(capText.lastIndexOf("@") + 1);
                bi = captchaProducerMath.createImage(capStr);
            }
            else if ("char".equals(type))
            {
                //capStr = code = captchaProducer.createText();
                //bi = captchaProducer.createImage(capStr);
                TzCodeUtil util=new TzCodeUtil();
                capStr = code =util.createCode();
                bi=util.getBuffImg();
            }
            session.setAttribute(Constants.KAPTCHA_SESSION_KEY, code);
            out = response.getOutputStream();
            ImageIO.write(bi, "jpg", out);
            out.flush();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (out != null)
                {
                    out.close();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return null;
    }
}