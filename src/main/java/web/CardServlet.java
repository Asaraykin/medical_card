package web;

import model.Patient;
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

import static org.slf4j.LoggerFactory.getLogger;

public class CardServlet extends HttpServlet {

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
        int  patientId = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("patient", patientRestController.get(patientId));
        request.getRequestDispatcher("/patientCard.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       request.getRequestDispatcher("/patientCard.jsp").forward(request,response);
    }
}
