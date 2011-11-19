package ch.frankel.blog.jawr.web;

import org.mozilla.javascript.ErrorReporter;
import org.mozilla.javascript.EvaluatorException;

/**
 * TODO Décrire les responsabilités de la classe.
 * 
 * @author Nicolas Fränkel
 * @since 17 mars 2009
 */
public class BasicErrorReporter implements ErrorReporter {

    /**
     * @see org.mozilla.javascript.ErrorReporter#error(java.lang.String,
     *      java.lang.String, int, java.lang.String, int)
     */
    @Override
    public void error(String aArg0, String aArg1, int aArg2, String aArg3, int aArg4) {
	// TODO Auto-generated method stub

    }

    /**
     * @see org.mozilla.javascript.ErrorReporter#runtimeError(java.lang.String,
     *      java.lang.String, int, java.lang.String, int)
     */
    @Override
    public EvaluatorException runtimeError(String aArg0, String aArg1, int aArg2, String aArg3, int aArg4) {
	// TODO Auto-generated method stub
	return null;
    }

    /**
     * @see org.mozilla.javascript.ErrorReporter#warning(java.lang.String,
     *      java.lang.String, int, java.lang.String, int)
     */
    @Override
    public void warning(String aArg0, String aArg1, int aArg2, String aArg3, int aArg4) {
	// TODO Auto-generated method stub

    }
}
