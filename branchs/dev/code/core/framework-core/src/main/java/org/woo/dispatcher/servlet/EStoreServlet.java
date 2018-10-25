package org.woo.dispatcher.servlet;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class EStoreServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = -4026309882246848932L;

	@Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.initRootPath(config);
    }

    public void initRootPath(ServletConfig config) {
        String routePath = config.getInitParameter("root_path");
        String[] kvSet = routePath.split(";");
        for (String kv : kvSet) {
            String[] route = kv.split(";");
        }
    }
}
