package lect01_2_oopano.whyano.backend;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import beans.student.Student;

/**
 * @overview
 *
 * @author Duc Minh Le (ducmle)
 *
 * @version
 */
@Controller
@RequestMapping("/hello")
public class CustomerCtrl {

  /**
   * @effects 
   *  using the basic technique 
   */
  @RequestMapping(method = RequestMethod.GET)
  public String printHello(ModelMap model, HttpServletRequest req) {
    // get the student id from the request
    String id = req.getParameter("id");
    
    // look up student bean by id
    ApplicationContext cxt = new ClassPathXmlApplicationContext("web/SpringWeb.xml");
    CustomerEntity cust = (CustomerEntity) cxt.getBean("id"+id);
    
    // pass Student to the view for rendering
    model.addAttribute("message", "HCL-Java: Hello Spring MVC Framework!");
    model.addAttribute("customer", cust);

    // returns the view's name for rendering the model
    return "customer";
  }
}
