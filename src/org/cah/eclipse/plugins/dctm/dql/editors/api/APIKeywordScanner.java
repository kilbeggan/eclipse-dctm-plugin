/*-
 * $Log: APIKeywordScanner.java,v $
 * Revision 1.11  2006/01/12 06:15:06  madcook
 * Moved getpath from the apiexec Vector to the apiget collection.
 *
 * Revision 1.10  2005/12/04 22:17:21  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 * Revision 1.9  2005/12/04 20:27:46  madcook
 * Version 3.0.0 work started.
 *
 * Revision 1.8  2005/11/21 14:53:38  madcook
 * Old 1.4 style code removed.
 *
 * Revision 1.7  2005/11/21 13:04:32  madcook
 * Plugin version 3.0.0 work started and made code to comply with all java 5 
 * settings.
 *
 * Revision 1.6  2005/03/25 09:21:17  harpechr
 * Version 2.0.0 code that has implemented the new java 1.5 features.
 *
 * Revision 1.5  2005/02/09 14:01:40  harpechr
 * Version 1.0.5 work started.
 *
 * Revision 1.4  2005/01/24 12:34:57  harpechr
 * Version 1.0.4 work started.
 *
 * Revision 1.3  2005/01/11 14:02:18  harpechr
 * Changed version number from 1.0.2 to 1.0.3.
 *
 * Revision 1.2  2005/01/11 13:47:23  harpechr
 * Changed hard coded attribute names and type names to references from the bof 
 * structure (org.cah.dctm.bof).
 *
 * Revision 1.1  2005/01/07 12:37:50  harpechr
 * Version 1.0.2 code. First CVS commit!
 *
 */

package org.cah.eclipse.plugins.dctm.dql.editors.api;

import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;

import org.cah.dctm.bof.tbo.persistent.type.IType;
import org.cah.eclipse.plugins.dctm.dql.editors.dql.DQLKeywordScanner;
import org.cah.eclipse.plugins.dctm.dql.views.ConnectionView;
import org.cah.eclipse.plugins.dctm.dql.views.MessageView;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.swt.SWT;

import com.documentum.fc.client.DfQuery;
import com.documentum.fc.client.IDfCollection;
import com.documentum.fc.client.IDfQuery;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.common.DfException;


/**
 * <H4>Keyword, type, attribute scanner.</H4>
 * <DL>
 * <DT><B>Copyright (c): </B>
 * <DD>Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>Nov 28, 2004 2:20:22 PM.</DD>
 * </DT>
 * </DL>
 * <p>
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 * </p>
 * <p>
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the <a
 * href="http://www.gnu.org/licenses/gpl.html">GNU General Public License </a>
 * for more details.
 * </p>
 * 
 * @author Christopher Harper account : HARPECHR
 * @version 3.0.0
 * @since 1.0
 */
public class APIKeywordScanner
								extends
									RuleBasedScanner
{

	/**
	 * API exec keywords.
	 * 
	 * @since 1.0
	 */
	public static final Vector<String>	APIEXEC				= new Vector<String>(
																Arrays
																	.asList(new String[] {
		"ABORT", //$NON-NLS-1$
		"ACQUIRE", "ADDACTIVITY", "ADDLINK", "ADDNOTE", "ADDPACKAGEINFO", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ 
		"ADDPORT", "ADDROUTECASE", "ANYEVENTS", "APPENDFILE", "ASSEMBLE", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ 
		"ASSUME", "ATTACH", "AUDIT", "BEGINTRAN", "BINDFILE", "CHANGEPASSWORD", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ 
		"CLOSE", "COMMIT", "COMPLETE", "DELEGATE", "DEMOTE", "DEQUEUE", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
		"DESTROY", "DISASSEMBLE", "DISCONNECT", "EXECQUERY", "EXECSQL", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
		"EXECUTE", "FETCH", "FLUSH", "FLUSHCACHE", "FREEZE", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
		"GRANT", "HALT", "INSERTFILE", "INSTALL", "INVALIDATE", "KILL", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
		"LINK", "LOCK", "MARK", "MOUNT", "MOVESTATE", "NEXT", "PAUSE", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ 
		"PROMOTE", "PRUNE", "PUBLISH_DD", "PURGELOCAL", "REFRESH", "REINIT", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ 
		"REMOVE", "REMOVEACTIVITY", "REMOVECONTENT", "REMOVELINK", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ 
		"REMOVENOTE", "REMOVEPACKAGE", "REMOVEPACKAGEINFO", "REMOVEPART", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ 
		"REMOVEPORT", "REMOVERENDITION", "REMOVEROUTECASE", "REMOVESTATE", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ 
		"RESET", "RESTART", "RESUME", "REVERT", "REVOKE", "SAVE", "SEEK", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$
		"SETBATCHHINT", "SETDOC", "SETFILE", "SETOUTPUT", "SETPATH", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
		"SETPERFORMERS", "SETSUPERVISOR", "SHUTDOWN", "SIGNOFF", "SUSPEND", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ 
		"TRACE", "TRUNCATE", "UNAUDIT", "UNFREEZE", "UNINSTALL", "UNLINK", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ 
		"UNLOCK", "UNMARK", "UNPRINT", "UNREGISTER", "UPDATEPART", "USEACL", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
		"VALIDATE"													}));			//$NON-NLS-1$

	/**
	 * Api get keywords.
	 * 
	 * @since 1.0
	 */
	public static final Vector<String>	APIGET				= new Vector<String>(
																Arrays
																	.asList(new String[] {
		"ADDPACKAGE", "APPENDPART", "APPENDSTATE", "APPLY", "ARCHIVE", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$  
		"BRANCH", "CACHEQUERY", "CHECKIN", "CHECKINAPP", "CHECKOUT", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$  
		"CONNECT", "COUNT", "CREATE", "DATATYPE", "DEREFERENCE", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
		"DESCRIBE", "DUMP", "DUMPCONNECTION", "GET", "GETCONNECTION", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ 
		"GETCONTENT", "GETDOCBASEMAP", "GETDOCBROKERMAP", "GETEVENTS", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ 
		"GETFILE", "GETLASTCOLL", "GETLOGIN", "GETMESSAGE", "GETPATH", "GETSERVERMAP", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$   
		"ID", "INSERTPART", "INSERTSTATE", "ISCACHED", "LISTCONNECTION", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ 
		"LISTMESSAGE", "LOCATE", "LPQ", "OFFSET", "PRINT", "QUERY", "QUEUE", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ 
		"READQUERY", "REPEAT", "REPEATING", "RESOLVEALIAS", "RESTORE", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$  
		"RETRIEVE", "SAVEASNEW", "TYPE", "VALUES", "VDMPATH", "VDMPATHDQL"}));		//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$

	/**
	 * Api set keywords.
	 * 
	 * @since 1.0
	 */
	public static final Vector<String>	APISET				= new Vector<String>(
																Arrays
																	.asList(new String[] {
		"APPEND", //$NON-NLS-1$
		"APPENDCONTENT", "INSERT", "INSERTCONTENT", "REGISTER", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ 
		"SET", "SETCONTENT"											}));			//$NON-NLS-1$ //$NON-NLS-2$ 

	/**
	 * Type attributes.
	 * 
	 * @since 1.0
	 */
	public static final Vector<String>	ATTRIBUTES			= new Vector<String>();

	/**
	 * All API keywords combined.
	 * 
	 * @since 1.0
	 */
	public static final Vector<String>	COMBINED_KEYWORDS	= new Vector<String>(
																Arrays
																	.asList(new String[] {
		"ABORT", //$NON-NLS-1$ 
		"ACQUIRE", "ADDACTIVITY", "ADDLINK", "ADDNOTE", "ADDPACKAGEINFO", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ 
		"ADDPORT", "ADDROUTECASE", "ANYEVENTS", "APPENDFILE", "ASSEMBLE", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ 
		"ASSUME", "ATTACH", "AUDIT", "BEGINTRAN", "BINDFILE", "CHANGEPASSWORD", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$  
		"CLOSE", "COMMIT", "COMPLETE", "DELEGATE", "DEMOTE", "DEQUEUE", "DESTROY", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ 
		"DISASSEMBLE", "DISCONNECT", "EXECQUERY", "EXECSQL", "EXECUTE", "FETCH", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$  
		"FLUSH", "FLUSHCACHE", "FREEZE", "GETPATH", "GRANT", "HALT", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$  
		"INSERTFILE", "INSTALL", "INVALIDATE", "KILL", "LINK", "LOCK", "MARK", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ 
		"MOUNT", "MOVESTATE", "NEXT", "PAUSE", "PROMOTE", "PRUNE", "PUBLISH_DD", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ 
		"PURGELOCAL", "REFRESH", "REINIT", "REMOVE", "REMOVEACTIVITY", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ 
		"REMOVECONTENT", "REMOVELINK", "REMOVENOTE", "REMOVEPACKAGE", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$  
		"REMOVEPACKAGEINFO", "REMOVEPART", "REMOVEPORT", "REMOVERENDITION", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$  
		"REMOVEROUTECASE", "REMOVESTATE", "RESET", "RESTART", "RESUME", "REVERT", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ 
		"REVOKE", "SAVE", "SEEK", "SETBATCHHINT", "SETDOC", "SETFILE", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
		"SETOUTPUT", "SETPATH", "SETPERFORMERS", "SETSUPERVISOR", "SHUTDOWN", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ 
		"SIGNOFF", "SUSPEND", "TRACE", "TRUNCATE", "UNAUDIT", "UNFREEZE", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
		"UNINSTALL", "UNLINK", "UNLOCK", "UNMARK", "UNPRINT", "UNREGISTER", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
		"UPDATEPART", "USEACL", "VALIDATE", "ADDPACKAGE", "APPENDPART", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ 
		"APPENDSTATE", "APPLY", "ARCHIVE", "BRANCH", "CACHEQUERY", "CHECKIN", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ 
		"CHECKINAPP", "CHECKOUT", "CONNECT", "COUNT", "CREATE", "DATATYPE", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ 
		"DEREFERENCE", "DESCRIBE", "DUMP", "DUMPCONNECTION", "GET", "GETCONNECTION", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
		"GETCONTENT", "GETDOCBASEMAP", "GETDOCBROKERMAP", "GETEVENTS", "GETFILE", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ 
		"GETLASTCOLL", "GETLOGIN", "GETMESSAGE", "GETSERVERMAP", "ID", "INSERTPART", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ 
		"INSERTSTATE", "ISCACHED", "LISTCONNECTION", "LISTMESSAGE", "LOCATE", "LPQ", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ 
		"OFFSET", "PRINT", "QUERY", "QUEUE", "READQUERY", "REPEAT", "REPEATING", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$
		"RESOLVEALIAS", "RESTORE", "RETRIEVE", "SAVEASNEW", "TYPE", "VALUES", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ 
		"VDMPATH", "VDMPATHDQL", "APPEND", "APPENDCONTENT", "INSERT", "INSERTCONTENT", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ 
		"REGISTER", "SET", "SETCONTENT"								}));			//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

	/**
	 * Docbase types.
	 * 
	 * @since 1.0
	 */
	public static final Vector<String>	TYPES				= new Vector<String>();

	/**
	 * Sort static arrays.
	 * 
	 * @since 1.0
	 */
	static
	{
		Collections.sort(APIKeywordScanner.COMBINED_KEYWORDS);
		Collections.sort(APIKeywordScanner.APIEXEC);
		Collections.sort(APIKeywordScanner.APIGET);
		Collections.sort(APIKeywordScanner.APISET);
	}

	/**
	 * Sole constructor.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 2:20:45 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param provider
	 *            colour provider.
	 */
	public APIKeywordScanner(final APIColorProvider provider)
	{

		final IToken string = new Token(new TextAttribute(provider
			.getColor(APIColorProvider.STRING)));
		final IToken singleLine = new Token(new TextAttribute(provider
			.getColor(APIColorProvider.SINGLE_LINE_COMMENT)));
		final IToken multiLine = new Token(new TextAttribute(provider
			.getColor(APIColorProvider.MULTI_LINE_COMMENT)));
		final IToken typeDef = new Token(new TextAttribute(provider
			.getColor(APIColorProvider.DEFAULT)));
		final IToken type = new Token(new TextAttribute(provider
			.getColor(APIColorProvider.TYPE), provider
			.getColor(APIColorProvider.BACKGROUND), SWT.BOLD));
		final IToken attribute = new Token(new TextAttribute(provider
			.getColor(APIColorProvider.ATTRIBUTE), provider
			.getColor(APIColorProvider.BACKGROUND), SWT.BOLD));
		final IToken set = new Token(new TextAttribute(provider
			.getColor(APIColorProvider.APISET), provider
			.getColor(APIColorProvider.BACKGROUND), SWT.BOLD));
		final IToken exec = new Token(new TextAttribute(provider
			.getColor(APIColorProvider.APIEXEC), provider
			.getColor(APIColorProvider.BACKGROUND), SWT.BOLD));
		final IToken get = new Token(new TextAttribute(provider
			.getColor(APIColorProvider.APIGET), provider
			.getColor(APIColorProvider.BACKGROUND), SWT.BOLD));

		final Vector<IRule> rules = new Vector<IRule>();
		/* Add rule for single and double quotes. */
		rules.add(new SingleLineRule("\"", "\"", string, '\'')); //$NON-NLS-1$ //$NON-NLS-2$
		rules.add(new SingleLineRule("'", "'", string, '\'')); //$NON-NLS-1$ //$NON-NLS-2$
		rules.add(new EndOfLineRule("//", singleLine, '\\')); //$NON-NLS-1$
		rules.add(new EndOfLineRule("#", singleLine, '\\')); //$NON-NLS-1$ 
		rules.add(new EndOfLineRule("--", singleLine, '\\')); //$NON-NLS-1$ 
		rules.add(new MultiLineRule("/*", "*/", multiLine, '\\')); //$NON-NLS-1$ //$NON-NLS-2$
		/* Add word rule for keywords, TYPES, and constants. */
		final APIWordRule typeRule = new APIWordRule(typeDef);

		addKeyWord(typeRule, APIKeywordScanner.APIEXEC, exec);
		addKeyWord(typeRule, APIKeywordScanner.APISET, set);
		addKeyWord(typeRule, APIKeywordScanner.APIGET, get);
		addKeyWord(typeRule, APIKeywordScanner.ATTRIBUTES, attribute);
		addKeyWord(typeRule, APIKeywordScanner.TYPES, type);

		rules.add(typeRule);

		final IRule[] result = new IRule[rules.size()];
		rules.copyInto(result);
		setRules(result);
	}

	/**
	 * Populate the keywords and types when a Docbase session is opened.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 29, 2004 12:36:11 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 */
	public static void populateTypesAndAttributes()
	{

		IDfSession connection = null;
		try
		{
			connection = ConnectionView.getInstance().getSelectedConnection()
				.getSession();
			StringBuffer valueQuery = new StringBuffer("select distinct ").append(IType.NAME).append(" as ") //$NON-NLS-1$ //$NON-NLS-2$
				.append(DQLKeywordScanner.RETURN_COLUMN)
				.append(" from ").append(IType.TYPE_DM_TYPE) //$NON-NLS-1$ 
				.append(" order by 1"); //$NON-NLS-1$ 
			populate(connection, valueQuery.toString(), APIKeywordScanner.TYPES);
			Collections.sort(APIKeywordScanner.TYPES);
			valueQuery = new StringBuffer("select distinct ").append(IType.ATTR_NAME).append(" as ") //$NON-NLS-1$ //$NON-NLS-2$
				.append(DQLKeywordScanner.RETURN_COLUMN)
				.append(" from ").append(IType.TYPE_DM_TYPE) //$NON-NLS-1$ 
				.append(" order by 1"); //$NON-NLS-1$
			populate(connection, valueQuery.toString(),
				APIKeywordScanner.ATTRIBUTES);
			Collections.sort(APIKeywordScanner.ATTRIBUTES);
		} catch(DfException dex)
		{
			MessageView.getInstance();
		} finally
		{
			ConnectionView.getInstance().getSelectedConnection()
				.releaceConnection(connection);
		}
	}

	/**
	 * Do population for either types or attributes..
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 29, 2004 12:36:03 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param connection
	 *            A Docbase session.
	 * @param valueQuery
	 *            the query returning values.
	 * @param values
	 *            the target container.
	 * @throws DfException
	 *             if the query fails.
	 */
	protected static void populate(final IDfSession connection,
									final String valueQuery,
									final Vector<String> values) throws DfException
	{

		final IDfQuery query = new DfQuery();
		query.setDQL(valueQuery);
		IDfCollection results = null;
		try
		{
			results = query.execute(connection, IDfQuery.DF_EXECREAD_QUERY);
			MessageView.getInstance().addMessage(query.getDQL());
			values.clear();
			while(results.next())
			{
				final String value = results
					.getString(DQLKeywordScanner.RETURN_COLUMN);
				if((value != null) && (value.trim().length() > 0))
				{
					values.addElement(value.trim());
				}
			}
			Collections.sort(values);
		} finally
		{
			if(results != null)
			{
				try
				{
					results.close();
				} catch(DfException swallow)
				{
					MessageView.getInstance().addMessage(
						"Failure closing collection.\n" + swallow.getMessage()); //$NON-NLS-1$
				}
			}
		}
	}

	/**
	 * Add keywords to the API rule.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 27.2.2005 16:31:35</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param typeRule
	 *            rule to add to.
	 * @param keyWords
	 *            the keywords to add.
	 * @param keywordToken
	 *            the colour token to use.
	 */
	protected void addKeyWord(final APIWordRule typeRule,
								final Vector<String> keyWords,
								final IToken keywordToken)
	{

		for(final String keyWord: keyWords)
		{
			typeRule.addKeyword(keyWord, keywordToken);
		}
	}
}
