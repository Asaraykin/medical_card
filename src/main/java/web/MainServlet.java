package web;

import model.AbstractBaseEntity;
import model.User;
import org.slf4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import web.patient.PatientRestController;
import web.user.UserRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MainServlet extends HttpServlet {
    private static final Logger log = getLogger(MainServlet.class);
    private ConfigurableApplicationContext springContext;
    private UserRestController userRestController;
    private PatientRestController patientRestController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml");
        userRestController = springContext.getBean(UserRestController.class);
        patientRestController = springContext.getBean(PatientRestController.class);
    }

    @Override
    public void destroy() {
        springContext.close();
        super.destroy();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        SecurityUtil.setAuthUserId(userId);
        request.getRequestDispatcher("/userListForAdmin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        SecurityUtil.setAuthUserId(userId);
        User authorizedUser = userRestController.get(userId);

        if(authorizedUser.getRole().equals("doctor")){
            request.setAttribute("userList", patientRestController.getAll());
            request.getRequestDispatcher("/userList.jsp").forward(request,response);
        }
        else {

            if (authorizedUser.getRole().equals("admin")) {
                request.setAttribute("userList", userRestController.getAll());
                request.getRequestDispatcher("/userListForAdmin.jsp").forward(request,response);
            }
            if (authorizedUser.getRole().equals("patient")) {
                request.setAttribute("userList", Arrays.asList(patientRestController.get(userId)));
                request.getRequestDispatcher("/userList.jsp").forward(request,response);
            }

        }


    }
}
