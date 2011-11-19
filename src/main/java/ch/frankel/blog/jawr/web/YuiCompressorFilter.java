package ch.frankel.blog.jawr.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import ch.frankel.blog.jawr.web.BasicErrorReporter;

import com.yahoo.platform.yui.compressor.JavaScriptCompressor;

/**
 * Filter that minifies JavaScript files. Can be used with CSS files with less
 * configuration.
 * 
 * @author Nicolas Fränkel
 * @since 17 mars 2009
 */
public class YuiCompressorFilter implements Filter {

    /** Configuration object. */
    private FilterConfig config;

    /** Number of kept linebreaks. */
    private int linebreak;

    /** Munge. */
    private boolean munge;

    /** Verbose. */
    private boolean verbose;

    /** Should all semicolons be kept. */
    private boolean preserveAllSemiColons;

    /** Should optimization be disabled. */
    private boolean disableOptimizations;

    /**
     * This implementation does nothing.
     * 
     * @see javax.servlet.Filter#destroy()
     */
    @Override
    public void destroy() {
    }

    /**
     * Compress JS and CSS output.
     * 
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
     *      javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
	    ServletException {

	StringWriter stringWriter = new StringWriter();

	final PrintWriter printWriter = new PrintWriter(stringWriter);

	HttpServletRequest httpRequest = (HttpServletRequest) request;

	String path = httpRequest.getServletPath();

	InputStream stream = config.getServletContext().getResourceAsStream(path);

	Reader source = new InputStreamReader(stream);

	stream.close();

	JavaScriptCompressor compressor = new JavaScriptCompressor(source, new BasicErrorReporter());

	compressor.compress(printWriter, linebreak, munge, verbose, preserveAllSemiColons, disableOptimizations);

	response.getWriter().write(stringWriter.getBuffer().toString());

	printWriter.close();
    }

    /**
     * Reads filter parameters.
     * 
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    @Override
    public void init(FilterConfig config) throws ServletException {

	this.config = config;

	String linebreakString = config.getInitParameter("linebreak");
	String mungeString = config.getInitParameter("munge");
	String verboseString = config.getInitParameter("verbose");
	String preserveAllSemiColonsString = config.getInitParameter("preserveAllSemiColons");
	String disableOptimizationsString = config.getInitParameter("disableOptimizations");

	munge = Boolean.parseBoolean(mungeString);
	verbose = Boolean.parseBoolean(verboseString);
	preserveAllSemiColons = Boolean.parseBoolean(preserveAllSemiColonsString);
	disableOptimizations = Boolean.parseBoolean(disableOptimizationsString);

	try {

	    if (linebreakString != null) {

		linebreak = Integer.parseInt(linebreakString);
	    }

	} catch (NumberFormatException e) {

	    throw new ServletException(e);
	}
    }
}
