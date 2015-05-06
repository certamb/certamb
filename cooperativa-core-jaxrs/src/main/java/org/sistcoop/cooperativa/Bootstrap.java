package org.sistcoop.cooperativa;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.wordnik.swagger.jaxrs.config.BeanConfig;

public class Bootstrap extends HttpServlet {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	

	@Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setTitle("Cooperativa restfull services");
        beanConfig.setDescription("Cooperativa restfull services para el sistema SistCoop");
        beanConfig.setTermsOfServiceUrl("http://www.apache.org/licenses/LICENSE-2.0.html");
        beanConfig.setContact("sistcoop@gmail.com");
        beanConfig.setLicense("Apache Licence 2.0");
        beanConfig.setLicenseUrl("http://www.apache.org/licenses/LICENSE-2.0.txt");
        beanConfig.setVersion("1.0.0.Final");
        beanConfig.setBasePath("http://localhost:8080/cooperativa/rest");
        beanConfig.setResourcePackage("org.sistcoop.cooperativa");
        beanConfig.setScan(true);
    }
    
}
