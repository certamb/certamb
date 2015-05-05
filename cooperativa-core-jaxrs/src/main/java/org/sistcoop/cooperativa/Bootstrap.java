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
        beanConfig.setTermsOfServiceUrl("http://localhost:8080/tems");
        beanConfig.setContact("sistcoop@gmail.com");
        beanConfig.setLicense("Apache Licence");
        beanConfig.setLicenseUrl("licenceurl");
        beanConfig.setVersion("1.0.0");
        beanConfig.setBasePath("http://localhost:8080/api");
        beanConfig.setResourcePackage("org.sistcoop.cooperativa.services.resources.admin");
        beanConfig.setScan(true);
    }
    
}
