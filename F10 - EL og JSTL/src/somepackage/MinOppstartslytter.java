package somepackage;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MinOppstartslytter implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce)  { 
        sce.getServletContext().setAttribute("felles", 35);
    }
    
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

	
}
