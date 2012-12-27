package awl.frontsolutions.services.atos;

import java.util.Arrays;
import java.util.Hashtable;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.apache.tapestry5.internal.TapestryInternalUtils;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.ioc.internal.util.InternalUtils;
import org.apache.tapestry5.services.ApplicationStateManager;
import org.apache.tapestry5.services.RequestGlobals;

import awl.frontsolutions.entities.DASUser;
import awl.frontsolutions.internal.ComponentConstants;
import awl.frontsolutions.internal.LogsUtils;
import awl.frontsolutions.services.AtosService;
import awl.frontsolutions.services.Authentification;
import awl.frontsolutions.services.MailService;

public class AtosAuthentificationImpl implements Authentification {

	private ApplicationStateManager manager;
	private AtosService atos;
	private RequestGlobals request;
	private MailService mail;

	private String friends;
	private String componentFolder;

	public AtosAuthentificationImpl(
			@Symbol("kawwa2.friends") String friends,
			ApplicationStateManager manager,
			@Symbol(value = ComponentConstants.FILE_INDEXER_ROOT) String componentFolder,
			AtosService atos, RequestGlobals request, MailService mail) {

		super();

		this.friends = friends;
		this.manager = manager;
		this.componentFolder = componentFolder;
		this.atos = atos;
		this.request = request;
		this.mail = mail;
	}

	@Override
	public String authentificate(String login, String password) {

		if (checkFriends(login, password))
			return null;

		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL,
				"aoLdapKey=AAA212300,ou=people,dc=atosorigin,dc=com");
		env.put(Context.SECURITY_CREDENTIALS, "@ll0verTh&W0rld");
		env.put(Context.INITIAL_CONTEXT_FACTORY,
				"com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldaps://ldap.atosorigin.com:636");

		try {
			// Context with the functional account
			DirContext ctx = new InitialDirContext(env);

			// Set up the search controls
			SearchControls ctls = new SearchControls();
			ctls.setSearchScope(SearchControls.ONELEVEL_SCOPE); // Search object
																// only

			NamingEnumeration<SearchResult> answer = ctx.search(
					"ou=people,dc=atosorigin,dc=com", "(uid={0})",
					new String[] { login }, ctls);

			SearchResult searchResult = null;
			if (answer.hasMoreElements()) {
				searchResult = answer.nextElement();
				if (!answer.hasMoreElements()) {
					System.out.println("1 result ["
							+ searchResult.getNameInNamespace() + "]");

					ctx.close();
					env.put(Context.SECURITY_PRINCIPAL,
							searchResult.getNameInNamespace());
					env.put(Context.SECURITY_CREDENTIALS, password);
					try {
						// Context with the user's login account
						ctx = new InitialDirContext(env);
						manager.set(DASUser.class, new DASUser(login, false));
						atos.addIpAdress(request.getHTTPServletRequest()
								.getRemoteAddr());

						logDas(login);
						// TODO What you want. This ctx can be used with user's
						// login authentification...

					} catch (AuthenticationException e) {
						return "wrong login or password...";

					}
				}
			} else
				return "wrong login or password...";
		} catch (NamingException e) {
			return "wrong login or password";
		}

		return null;
	}

	private Boolean checkFriends(String das, String password) {
		if (Arrays.asList(TapestryInternalUtils.splitAtCommas(friends))
				.contains(das)) {

			if (das.equalsIgnoreCase(password)) {

				manager.set(DASUser.class, new DASUser(das, true));
				logDas(das);
				return true;
			}
		}
		return false;
	}

	private void logDas(String das) {
		if (InternalUtils.isBlank(request.getRequest().getHeader("DNT"))) {
			LogsUtils.log(LogsUtils.getFile("das", componentFolder), das);
			mail.sendMailToDev("Connection", das);
		}
	}
}
