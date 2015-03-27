/*-
 * $Log: DQLKeywordScanner.java,v $
 * Revision 1.11  2005/12/04 22:17:21  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 * Revision 1.10  2005/12/04 20:28:23  madcook
 * Version 3.0.0 work started.
 *
 * Revision 1.9  2005/11/21 14:53:38  madcook
 * Old 1.4 style code removed.
 *
 * Revision 1.8  2005/11/21 13:04:32  madcook
 * Plugin version 3.0.0 work started and made code to comply with all java 5 
 * settings.
 *
 * Revision 1.7  2005/03/25 09:21:17  harpechr
 * Version 2.0.0 code that has implemented the new java 1.5 features.
 *
 * Revision 1.6  2005/02/10 11:33:38  harpechr
 * Safety commit!
 *
 * Revision 1.5  2005/02/09 14:01:35  harpechr
 * Version 1.0.5 work started.
 *
 * Revision 1.4  2005/01/24 12:34:55  harpechr
 * Version 1.0.4 work started.
 *
 * Revision 1.3  2005/01/11 14:02:02  harpechr
 * Changed version number from 1.0.2 to 1.0.3.
 *
 * Revision 1.2  2005/01/09 10:46:56  harpechr
 * Renamed classes that were abstract by prefixing the class name with 
 * 'Abstract'.
 *
 * Revision 1.1  2005/01/07 12:37:49  harpechr
 * Version 1.0.2 code. First CVS commit!
 *
 */

package org.cah.eclipse.plugins.dctm.dql.editors.dql;

import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;

import org.cah.dctm.bof.tbo.persistent.IPersistentObject;
import org.cah.dctm.bof.tbo.persistent.sysobject.registered.IRegistered;
import org.cah.dctm.bof.tbo.persistent.type.IType;
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
import org.eclipse.jface.text.rules.WhitespaceRule;
import org.eclipse.swt.SWT;

import com.documentum.fc.client.DfQuery;
import com.documentum.fc.client.IDfCollection;
import com.documentum.fc.client.IDfQuery;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.common.DfException;


/**
 * <H4>DQL keyword scanner that detects DQL statement starts, keywords, tables,
 * columns, types and attributes.</H4>
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
public class DQLKeywordScanner
								extends
									RuleBasedScanner
{

	/**
	 * Type attributes and table columns combined.
	 * 
	 * @since 1.0
	 */
	public static final Vector<String>	ALL_ATTRIBUTES		= new Vector<String>();

	/**
	 * Docbase types.
	 * 
	 * @since 1.0
	 */
	public static final Vector<String>	DOCBASE_TYPES		= new Vector<String>();

	/**
	 * DQL reserverd words.
	 * 
	 * @since 1.0
	 */
	public static final Vector<String>	KEYWORDS			= new Vector<String>(
																Arrays
																	.asList(new String[] {
		"BOOL", //$NON-NLS-1$
		"CONTENT_ID", "DOCBASIC", "ACL BOOLEAN", "COUNT", "DOCUMENT", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ 
		"ADD", "BROWSE", "DOUBLE", "ADD_FTINDEX", "BUSINESS", "CURRENT", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$  
		"ADDRESS", "BY", "DATE", "DROP_FTINDEX", "ALL", "CABINET", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ 
		"DATEADD", "ELSE", "ALLOW", "CACHING", "DATEDIFF", "ELSEIF", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
		"DATEFLOOR", "ENABLE", "AND", "CHARACTER", "DATETOSTRING", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ 
		"ENFORCE", "ANY", "CHARACTERS", "DAY", "ESCAPE", "APPEND", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
		"CHAR", "DEFAULT", "ESTIMATE", "APPLICATION", "CHECK", "EXEC", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
		"AS", "COMMENT", "DELETED", "EXECUTE", "ASC", "DEPENDENCY", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
		"EXISTS", "ASSEMBLIES", "COMPLETE", "DEPTH", "FALSE", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ 
		"ASSEMBLY", "COMPONENTS", "DESC", "FIRST", "ASSISTANCE", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ 
		"COMPOSITE", "DESCEND", "FLOAT", "ATTR", "COMPUTED", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$  
		"DISABLE", "FOLDER", "AVG", "CONTAIN_ID", "DISPLAY", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
		"FTINDEX", "CONTAINS", "DISTINCT", "FUNCTION", "BETWEEN", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
		"CONTENT_FORMAT", "DM_SESSION_DD_LOCALE", "GROUP", "LINK", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ 
		"OF", "REPORT", "FOR", "LOWER", "ON", "FOREIGN", "MAPPING", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$
		"ONLY", "SCORE", "FROM", "MAX", "OR", "SEARCH", "FT_OPTIMIZER", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$
		"MCONTENTID", "ORDER", "HAVING", "MEMBERS", "OWNER", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ 
		"SEPARATOR", "HITS", "MFILE_URL", "PAGE_NO", "SERVER", "ID", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ 
		"MHITS", "PARENT", "SET", "IF", "MIN", "PATH", "SETFILE", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$
		"IN", "MODIFY", "PERMIT", "SMALLINT", "MONTH", "POLICY", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ 
		"SOME", "INT", "MOVE", "POSITION", "STATE", "INTEGER", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ 
		"MSCORE", "PRIMARY", "STORAGE", "INTERNAL", "NODE", "PRIVATE", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ 
		"STRING", "INTO", "NODESORT", "PRIVILEGES", "SUBSTR", "IS", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ 
		"NONE", "PUBLIC", "SUBSTRING", "ISCURRENT", "NOT", "QRY", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ 
		"SUM", "ISPUBLIC", "NOTE", "RDBMS", "SUMMARY", "ISREPLICA", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
		"NOW", "READ", "SUPERTYPE", "KEY", "NULL", "REFERENCES", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
		"SUPERUSER", "LANGUAGE", "NULLDATE", "SYNONYM", "LAST", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ 
		"NULLINT", "RELATE", "SYSADMIN", "LATEST", "NULLSTRING", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ 
		"REMOVE", "SYSOBJ_ID", "LIST", "OBJECT", "REPEATING", "SYSTEM", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ 
		"LIKE", "OBJECTS", "REPLACEIF", "TABLE", "TAG", "TRUE", "USER", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$
		"WHERE", "TEXT", "TRUNCATE", "USING", "WITH", "TIME", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ 
		"DOCBASE_TYPE", "VALUE", "WITHIN", "TO", "UNION", "VALUES", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ 
		"WITHOUT", "TODAY", "UNIQUE", "VERSION", "WORLD", "TOMORROW", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
		"UNLINK", "VERITY", "WRITE", "TOPIC", "VIOLATION", "YEAR", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ 
		"TRAN", "WEEK", "YESTERDAY", "TRANSACTION", "UPPER", "GO", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ 
		"DM_DBO", "SQL_DEF_RESULT_SET", "FORCE_ORDER", "PUBLISH", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ 
		"RETURN_TOP", "OPTIMIZE_TOP", "FETCH_ALL_RESULTS"			}));			//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ 

	/**
	 * Registered tables in the Docbase.
	 * 
	 * @since 1.0
	 */
	public static final Vector<String>	REGISTERED_TABLES	= new Vector<String>();

	/**
	 * Column to return from the value assistance queries 'value_name'.
	 * 
	 * @since 1.0.5
	 */
	static public final String			RETURN_COLUMN		= "value_name";		//$NON-NLS-1$ 

	/**
	 * Statement start words.
	 * 
	 * @since 1.0
	 */
	public static final Vector<String>	STARTWORDS			= new Vector<String>(
																Arrays
																	.asList(new String[] {
		"ABORT", //$NON-NLS-1$ 
		"ALTER", "BEGIN", "CHANGE", "COMMIT", "CREATE", "DELETE", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$  
		"DROP", "GRANT", "INSERT", "REGISTER", "REVOKE", "SELECT", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ 
		"UNREGISTER", "UPDATE"										}));			//$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * Registered table columns.
	 * 
	 * @since 1.0
	 */
	public static final Vector<String>	TABLE_COLUMNS		= new Vector<String>();

	/**
	 * Types attributes.
	 * 
	 * @since 1.0
	 */
	public static final Vector<String>	TYPE_ATTRIBUTES		= new Vector<String>();

	/**
	 * Types and tables combined.
	 * 
	 * @since 1.0
	 */
	public static final Vector<String>	TYPES_AND_TABLES	= new Vector<String>();

	/**
	 * Sort static arrays.
	 * 
	 * @since 1.0
	 */
	static
	{
		Collections.sort(DQLKeywordScanner.STARTWORDS);
		Collections.sort(DQLKeywordScanner.KEYWORDS);
	}

	/**
	 * Sole constructor that creates the colouring rules.
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
	public DQLKeywordScanner(final DQLColorProvider provider)
	{

		final IToken string = new Token(new TextAttribute(provider
			.getColor(DQLColorProvider.STRING)));
		final IToken singleLine = new Token(new TextAttribute(provider
			.getColor(DQLColorProvider.SINGLE_LINE_COMMENT)));
		final IToken multiLine = new Token(new TextAttribute(provider
			.getColor(DQLColorProvider.MULTI_LINE_COMMENT)));
		final IToken typeDef = new Token(new TextAttribute(provider
			.getColor(DQLColorProvider.DEFAULT)));
		final IToken docbaseType = new Token(new TextAttribute(provider
			.getColor(DQLColorProvider.DOCBASE_TYPE), provider
			.getColor(DQLColorProvider.BACKGROUND), SWT.BOLD));
		final IToken registeredTable = new Token(new TextAttribute(provider
			.getColor(DQLColorProvider.REGISTERED_TABLE), provider
			.getColor(DQLColorProvider.BACKGROUND), SWT.BOLD));
		final IToken attribute = new Token(new TextAttribute(provider
			.getColor(DQLColorProvider.ATTRIBUTE), provider
			.getColor(DQLColorProvider.BACKGROUND), SWT.BOLD));
		final IToken column = new Token(new TextAttribute(provider
			.getColor(DQLColorProvider.COLUMN), provider
			.getColor(DQLColorProvider.BACKGROUND), SWT.BOLD));
		final IToken keyword = new Token(new TextAttribute(provider
			.getColor(DQLColorProvider.KEYWORD), provider
			.getColor(DQLColorProvider.BACKGROUND), SWT.BOLD));
		final IToken startWord = new Token(new TextAttribute(provider
			.getColor(DQLColorProvider.STARTWORD), provider
			.getColor(DQLColorProvider.BACKGROUND), SWT.BOLD));

		final Vector<IRule> colouringRules = new Vector<IRule>();
		/* Add rule for single and double quotes. */
		colouringRules.add(new SingleLineRule("\"", "\"", string, '\'')); //$NON-NLS-1$ //$NON-NLS-2$
		colouringRules.add(new SingleLineRule("'", "'", string, '\''));//$NON-NLS-1$ //$NON-NLS-2$
		colouringRules.add(new EndOfLineRule("//", singleLine, '\\'));//$NON-NLS-1$ 
		colouringRules.add(new EndOfLineRule("#", singleLine, '\\'));//$NON-NLS-1$
		colouringRules.add(new EndOfLineRule("--", singleLine, '\\'));//$NON-NLS-1$ 
		colouringRules.add(new MultiLineRule("/*", "*/", multiLine, '\\'));//$NON-NLS-1$ //$NON-NLS-2$
		/* Add generic whitespace rule. */
		colouringRules.add(new WhitespaceRule(new DQLWhitespaceDetector()));
		/* Add word rule for keywords, TYPES, and constants. */
		final DQLWordRule typeRule = new DQLWordRule(typeDef);

		addKeyWord(typeRule, DQLKeywordScanner.DOCBASE_TYPES, docbaseType);
		addKeyWord(typeRule, DQLKeywordScanner.REGISTERED_TABLES,
			registeredTable);
		addKeyWord(typeRule, DQLKeywordScanner.TYPE_ATTRIBUTES, attribute);
		addKeyWord(typeRule, DQLKeywordScanner.TABLE_COLUMNS, column);
		addKeyWord(typeRule, DQLKeywordScanner.KEYWORDS, keyword);
		addKeyWord(typeRule, DQLKeywordScanner.STARTWORDS, startWord);
		colouringRules.add(typeRule);

		final IRule[] result = new IRule[colouringRules.size()];
		colouringRules.copyInto(result);
		setRules(result);
	}

	/**
	 * Populate the type, table information when a connection is established.
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
			StringBuffer valueQueryBuilder = new StringBuffer(
				"select distinct ").append(IType.NAME).append(" as ") //$NON-NLS-1$ //$NON-NLS-2$
				.append(DQLKeywordScanner.RETURN_COLUMN)
				.append(" from ").append(IType.TYPE_DM_TYPE) //$NON-NLS-1$
				.append(" union select distinct ").append(IRegistered.TABLE_NAME).append(" as ") //$NON-NLS-1$ //$NON-NLS-2$
				.append(DQLKeywordScanner.RETURN_COLUMN)
				.append(" from ").append(IRegistered.TYPE_DM_REGISTERED) //$NON-NLS-1$
				.append(" order by 1"); //$NON-NLS-1$
			populate(connection, valueQueryBuilder.toString(),
				DQLKeywordScanner.TYPES_AND_TABLES);
			valueQueryBuilder = new StringBuffer("select distinct ").append(IType.NAME).append(" as ") //$NON-NLS-1$ //$NON-NLS-2$
				.append(DQLKeywordScanner.RETURN_COLUMN)
				.append(" from ").append(IType.TYPE_DM_TYPE) //$NON-NLS-1$
				.append(" order by 1"); //$NON-NLS-1$
			populate(connection, valueQueryBuilder.toString(),
				DQLKeywordScanner.DOCBASE_TYPES);
			valueQueryBuilder = new StringBuffer("select distinct ").append(IRegistered.TABLE_NAME).append(" as ") //$NON-NLS-1$ //$NON-NLS-2$
				.append(DQLKeywordScanner.RETURN_COLUMN)
				.append(" from ").append(IRegistered.TYPE_DM_REGISTERED) //$NON-NLS-1$
				.append(" order by 1"); //$NON-NLS-1$
			populate(connection, valueQueryBuilder.toString(),
				DQLKeywordScanner.REGISTERED_TABLES);
			valueQueryBuilder = new StringBuffer("select distinct ").append(IType.ATTR_NAME).append(" as ") //$NON-NLS-1$ //$NON-NLS-2$
				.append(DQLKeywordScanner.RETURN_COLUMN)
				.append(" from ").append(IType.TYPE_DM_TYPE) //$NON-NLS-1$
				.append(" union select distinct ").append(IRegistered.COLUMN_NAME).append(" as ") //$NON-NLS-1$ //$NON-NLS-2$
				.append(DQLKeywordScanner.RETURN_COLUMN)
				.append(" from ").append(IRegistered.TYPE_DM_REGISTERED) //$NON-NLS-1$
				.append(" order by 1"); //$NON-NLS-1$
			populate(connection, valueQueryBuilder.toString(),
				DQLKeywordScanner.ALL_ATTRIBUTES);
			DQLKeywordScanner.ALL_ATTRIBUTES.addElement(IPersistentObject.STAR);
			DQLKeywordScanner.ALL_ATTRIBUTES
				.addElement(IPersistentObject.R_OBJECT_ID);
			valueQueryBuilder = new StringBuffer("select distinct ").append(IType.ATTR_NAME).append(" as ") //$NON-NLS-1$ //$NON-NLS-2$
				.append(DQLKeywordScanner.RETURN_COLUMN)
				.append(" from ").append(IType.TYPE_DM_TYPE) //$NON-NLS-1$
				.append(" order by 1"); //$NON-NLS-1$
			populate(connection, valueQueryBuilder.toString(),
				DQLKeywordScanner.TYPE_ATTRIBUTES);
			DQLKeywordScanner.TYPE_ATTRIBUTES
				.addElement(IPersistentObject.STAR);
			DQLKeywordScanner.TYPE_ATTRIBUTES
				.addElement(IPersistentObject.R_OBJECT_ID);
			valueQueryBuilder = new StringBuffer("select distinct ").append(IRegistered.COLUMN_NAME).append(" as ") //$NON-NLS-1$ //$NON-NLS-2$
				.append(DQLKeywordScanner.RETURN_COLUMN)
				.append(" from ").append(IRegistered.TYPE_DM_REGISTERED) //$NON-NLS-1$
				.append(" order by 1"); //$NON-NLS-1$
			populate(connection, valueQueryBuilder.toString(),
				DQLKeywordScanner.TABLE_COLUMNS);
			DQLKeywordScanner.TABLE_COLUMNS
				.addElement(IPersistentObject.R_OBJECT_ID);
		} catch(final DfException dex)
		{
			MessageView.getInstance().addMessage(dex);
		} finally
		{
			ConnectionView.getInstance().getSelectedConnection()
				.releaceConnection(connection);
		}
	}

	/**
	 * Populate a single storage.
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
	 *            a valid Docbase session.
	 * @param valueQuery
	 *            the query returning data.
	 * @param values
	 *            the target container.
	 * @throws DfException
	 *             if the query can't be run.
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
			MessageView.getInstance().addMessage(
				connection.getLoginInfo().getDomain() + '\\'
					+ connection.getLoginUserName() + '@'
					+ connection.getDocbaseName() + ' ' + query.getDQL());
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
					MessageView
						.getInstance()
						.addMessage(
							connection.getLoginInfo().getDomain()
								+ '\\'
								+ connection.getLoginUserName()
								+ '@'
								+ connection.getDocbaseName()
								+ " Failure closing collection.\n" + swallow.getMessage()); //$NON-NLS-1$
				}
			}
		}
	}

	/**
	 * Add keywords to the DQL rule.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 27.2.2005 16:29:35</DD>
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
	protected void addKeyWord(final DQLWordRule typeRule,
								final Vector<String> keyWords,
								final IToken keywordToken)
	{

		for(final String keyWord: keyWords)
		{
			typeRule.addKeyword(keyWord, keywordToken);
		}
	}
}
