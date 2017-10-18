package me.wuhao.https;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by everseeker on 2017/10/12.
 */
@RestController
public class MyHttpServlet extends HttpServlet {

    @RequestMapping(value = "/http", method = RequestMethod.GET)
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @RequestMapping(value = "/http", method = RequestMethod.POST)
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 获取基本信息: http协议版本，客户ip
         * remote ip address是直接和服务器三次握手的ip地址，如果客户端直连，就是客户端主机ip地址；如果使用代理，就是代理地址
         */
        System.out.println("protocol: " + req.getProtocol());
        System.out.println("remote ip address: " + req.getRemoteAddr());
        /**
         * 获取请求头：x-forwarded-for, user-agent
         * curl http://127.0.0.1:8080 -H 'X-Forwarded-For: 1.2.3.4'
         * x-forwarded-for ip地址可以随意被伪造，比如以上curl语句
         */
        System.out.println("x-forwarded-for: " + req.getHeader("x-forwarded-for"));
        System.out.println("user-agent: " + req.getHeader("user-agent"));
        /**
         * 获取参数信息: curl http://127.0.0.1:8080 -H 'X-Forwarded-For: 1.2.3.4' -d 'name=abc&password=123'
         */
        Map<String, String[]> paramMap = req.getParameterMap();
        for (Map.Entry<String, String[]> entry: paramMap.entrySet()) {
            String paramName = entry.getKey();
            System.out.print(paramName + ": ");
            StringBuffer paramValues = new StringBuffer();
            for (String paramValue: entry.getValue()) {
                paramValues.append(paramValue + ", ");
            }
            paramValues.deleteCharAt(paramValues.length()-2);
            System.out.print(paramValues.toString() + "\n");
        }

        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().println("user-agent: " + req.getHeader("user-agent"));
        resp.flushBuffer();
    }
}
