/*-
 * $Log:$
 * NOTE: Do not change Ascii / binary property.
 */

package org.cah.dctm.bof.tbo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.StringTokenizer;
import java.util.Vector;

import org.cah.dctm.bof.tbo.persistent.IPersistentObject;
import org.cah.dctm.bof.tbo.persistent.type.IType;

import com.documentum.fc.client.DfClient;
import com.documentum.fc.client.DfQuery;
import com.documentum.fc.client.DfServiceException;
import com.documentum.fc.client.IDfCollection;
import com.documentum.fc.client.IDfQuery;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.client.IDfSessionManager;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.DfLoginInfo;
import com.documentum.fc.common.IDfLoginInfo;


/**
 * <H4>Generate TBO interfaces and classes from existing Docbase types.</H4>
 * <DL>
 * <DT><B>Copyright :</B>
 * <DD>(c) 2005 Christopher Harper</DD>
 * </DT>
 * <DT><B>Created :</B>
 * <DD>26.3.2005 13:43:09.</DD>
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
 * href="http://www.gnu.org/licenses/gpl.html">GNU General Public License</a>
 * for more details.
 * </p>
 * 
 * @author Christopher Harper account: HARPEC
 * @version 3.0.0
 * @since 2.0.0
 */
public class GenerateTBOs
{

	/**
	 * Classes parameter.
	 * 
	 * @since 2.0.0
	 */
	public static final String[]		CLASSES						= new String[] {
		"-cl", "-classes"											};									//$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * Copyright parameter.
	 * 
	 * @since 2.0.0
	 */
	public static final String[]		COPYRIGHT					= new String[] {
		"-co", "-copyright"											};									//$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * Version parameter.
	 * 
	 * @since 2.0.0
	 */
	public static final String[]		DEBUG						= new String[] {
		"-db", "-debug"												};									//$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * Deep package hierarhcy parameter.
	 * 
	 * @since 2.0.0
	 */
	public static final String[]		DEEP						= new String[] {
		"-de", "-deep"												};									//$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * Docbase name parameter.
	 * 
	 * @since 2.0.0
	 */
	public static final String[]		DOCBASE						= new String[] {
		"-d", "-docbase"											};									//$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * User domain name parameter.
	 * 
	 * @since 2.0.0
	 */
	public static final String[]		DOMAIN						= new String[] {
		"-do", "-domain"											};									//$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * Target folder name parameter.
	 * 
	 * @since 2.0.0
	 */
	public static final String[]		FOLDER						= new String[] {
		"-f", "-folder"												};									//$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * Help parameter.
	 * 
	 * @since 2.0.0
	 */
	public static final String[]		HELP						= new String[] {
		"-he", "-help"												};									//$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * Inherit from Documentum / TBO parameter.
	 * 
	 * @since 2.0.0
	 */
	public static final String[]		INHERIT						= new String[] {
		"-in", "-inherit"											};									//$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * Create custom attribute methods.
	 * 
	 * @since 2.0.0
	 */

	public static final String[]		METHODS						= new String[] {
		"-me", "-methods"											};									//$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The platfor spesific new line character.
	 * 
	 * @since 2.0.0
	 */
	public static final String			NEWLINE						= System
																		.getProperty("line.separator"); //$NON-NLS-1$

	/**
	 * Target package name parameter.
	 * 
	 * @since 2.0.0
	 */
	public static final String[]		PACKAGE						= new String[] {
		"-a", "-package"											};									//$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * Password parameter.
	 * 
	 * @since 2.0.0
	 */
	public static final String[]		PASSWORD					= new String[] {
		"-p", "-password"											};									//$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * Copyright parameter.
	 * 
	 * @since 2.0.0
	 */
	public static final String[]		PREFIX						= new String[] {
		"-pr", "-prefix"											};									//$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * Words that can't be used while we create our package structure. Filenames
	 * have a prefix so they are OK.
	 * 
	 * @since 2.0.0
	 */
	public static final Vector<String>	RESERVED					= new Vector<String>(
																		Arrays
																			.asList(new String[] {
		"abstract", "do", "if", "package", "synchronized", //$NON-NLS-1$ //$NON-NLS-2$	//$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ 
		"boolean", "double", "implements", "private", "this", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ 
		"break", "else", "import", "protected", "throw", "byte", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
		"extends", "instanceof", "public", "throws", "case", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
		"false", "int", "return", "transient", "catch", "final", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
		"interface", "short", "true", "char", "finally", "long", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
		"static", "try", "class", "float", "native", "strictfp", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
		"void", "const", "for", "new", "super", "volatile", //$NON-NLS-1$ //$NON-NLS-2$	//$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
		"continue", "goto", "null", "switch", "while", "default", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
		"assert", "enum"													}));						//$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * Since parameter.
	 * 
	 * @since 2.0.0
	 */
	public static final String[]		SINCE						= new String[] {
		"-si", "-since"												};									//$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * Thread parameter.
	 * 
	 * @since 2.0.0
	 */
	public static final String[]		THREAD						= new String[] {
		"-th", "-thread"											};									//$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * Prefix of our thread names. Related to bug 84255.
	 * 
	 * @since 2.0.0
	 */
	public static final String			THREAD_NAME_PREFIX			= "bof_";							//$NON-NLS-1$

	/**
	 * Suffix of our thread names. Related to bug 84255.
	 * 
	 * @since 2.0.0
	 */
	public static final String			THREAD_NAME_SUFFIX			= "_tbo";							//$NON-NLS-1$

	/**
	 * User name parameter.
	 * 
	 * @since 2.0.0
	 */
	public static final String[]		USER						= new String[] {
		"-u", "-user"												};									//$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * Version parameter.
	 * 
	 * @since 2.0.0
	 */
	public static final String[]		VERSION						= new String[] {
		"-ve", "-version"											};									//$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * How many collections are in use.
	 * 
	 * @since 2.0
	 */
	private int							collectionsInUse			= 0;

	/**
	 * The copyright holder.
	 * 
	 * @since 2.0.0
	 */

	private String						copyright					= "Christopher 'Ride' Harper";		//$NON-NLS-1$ 

	/**
	 * The debug value.
	 * 
	 * @since 2.0.0
	 */
	private boolean						debug						= false;

	/**
	 * The deep packages option.
	 * 
	 * @since 2.0.0
	 */
	private boolean						deepPackages				= true;

	/**
	 * Docbase name
	 * 
	 * @since 2.0.0
	 */
	private String						docbase						= null;

	/**
	 * Domain.
	 * 
	 * @since 2.0.0
	 */
	private String						domain						= null;

	/**
	 * Target folder.
	 * 
	 * @since 2.0.0
	 */
	private File						folder						= new File(
																		System
																			.getProperty("user.dir"));	//$NON-NLS-1$ 

	/**
	 * Inherit from existing.
	 * 
	 * @since 2.0.0
	 */
	private boolean						inherit						= true;

	/**
	 * Should class files be created and DBOR registration class be created.
	 * 
	 * @since 2.0.0
	 */
	private boolean						isClass						= true;

	/**
	 * Should custom attributes get helper methods.
	 * 
	 * @since 2.0.0
	 */
	private boolean						isCustomAttributeMethods	= false;

	/**
	 * Should the program be run using threads.
	 * 
	 * @since 2.0.0
	 */
	private boolean						isThread					= false;

	/**
	 * Session manager.
	 * 
	 * @since 2.0.0
	 */

	private final IDfSessionManager		manager						= DfClient
																		.getLocalClientEx()
																		.newSessionManager();

	/**
	 * Users password.
	 * 
	 * @since 2.0.0
	 */
	private String						password					= null;

	/**
	 * The persistent interface name.
	 * 
	 * @since 2.0.0
	 */
	private String						persistentInterfaceName		= null;

	/**
	 * Root type node.
	 * 
	 * @since 2.0.0
	 */
	private TypeNode					persistentObject			= null;

	/**
	 * File prefix to use.
	 * 
	 * @since 2.0.0
	 */
	private String						prefix						= "CAH";							//$NON-NLS-1$ 

	/**
	 * Information about TBO's to register.
	 * 
	 * @since 2.0.0
	 */
	private Vector<String[]>			registerThese				= new Vector<String[]>();

	/**
	 * The since value to use. Default '1.0.0'.
	 * 
	 * @since 2.0.0
	 */
	private String						since						= "1.0.0";							//$NON-NLS-1$ 

	/**
	 * Target package.
	 * 
	 * @since 2.0.0
	 */
	private String						targetPackage				= "org.cah.bof.tbo";				//$NON-NLS-1$

	/**
	 * user name.
	 * 
	 * @since 2.0.0
	 */
	private String						userName					= null;

	/**
	 * Version number.
	 * 
	 * @since 2.0.0
	 */

	private String						version						= "1.0.0";							//$NON-NLS-1$

	/**
	 * Default constructor to enable the reading of default values.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 29.3.2005 14:55:17</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 */
	public GenerateTBOs()
	{

		super();
	}

	/**
	 * Start the actual work constructor.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.20059:29:26</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param arguments
	 *            TBO generator arguments.
	 */
	public GenerateTBOs(final String[] arguments)
	{

		Thread.currentThread().setName(
			GenerateTBOs.THREAD_NAME_PREFIX
				+ "main" + GenerateTBOs.THREAD_NAME_SUFFIX);//$NON-NLS-1$
		if(isDebug())
		{
			System.out.println(Thread.currentThread().getName() + ' '
				+ getClass().getName()
				+ ".GenerateTBOs(final String[] arguments) start");//$NON-NLS-1$
		}
		if(parseArguments(arguments))
		{
			populate();
		} else
		{
			printUsage();
		}
		if(isDebug())
		{
			System.out.println(Thread.currentThread().getName() + ' '
				+ getClass().getName()
				+ ".GenerateTBOs(final String[] arguments) end");//$NON-NLS-1$
		}
	}

	/**
	 * Launch the functionality.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.2005 13:43:09</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param arguments
	 * 
	 * <pre>
	 *     Required:
	 *      -d|-docbase		[docbase_name]		Docbase where to connect.
	 *      -f|-folder		[target_folder]		Root folder where to place the generated files.
	 *      -a|-package		[target_package]	Your package under which the TBO's and possible packages will be created.
	 *      -p|-password	[password]			The users password to use.
	 *      -u|-user		[user_name]			Connecting user name.
	 *     Optional:
	 *      -cl|-classes	[true|false]		Should TBO implementation class files be generated. Default is 'true'.
	 *      -co|-copyright	[copyright_value]	The copyright holder of the generated classes.
	 *      -db|-debug		[true|false]		Should debug info be printed. Default is 'false'.
	 *      -de|-deep		[true|false]		Should a package hierarchy be created. Default is 'true'.
	 *      -do|-domain		[users_domain]		The connecting users domain.
	 *      -he|-help							Print this message.
	 *      -in|-inherit	[true|false]		Inherit the generated classes from Existing Documentum / TBO classes. 
	 *      									Default is 'true'.
	 *      -me|-methods	[true|false]		Should custom attribute accessor method be created. Default is 'false'.
	 *      -pr|-prefix		[prefix_value]		Prefix to use in class and interface names.
	 *      -si|-since		[since_value]		The @since javadoc tag value. Default is '1.0.0'.
	 *      -th|-thread		[true|false]		Should threads be used while creating TBO's. Default is 'false'.
	 *      -ve|-version	[version_value]		The @version javadoc tag value. Default is '1.0.0'.
	 * </pre>
	 */
	public static void main(final String[] arguments)
	{

		new GenerateTBOs(arguments);
	}

	/**
	 * Convert a string to title case. dm_document > Document , title case >
	 * Title Case.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Mar 4, 2004 4:04:41 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param original
	 *            the string to convert.
	 * @param preserveWhiteSpace
	 *            should white space be removed.
	 * @return the title case string.
	 */
	public static String makeTitleCase(String original,
										final boolean preserveWhiteSpace)
	{

		if((original.startsWith(IPersistentObject.TYPE_PREFIX))
			|| (original.startsWith(IPersistentObject.TYPE_PREFIX_INTERNAL))
			|| (original
				.startsWith(IPersistentObject.ATTRIBUTE_PREFIX_APPLICATION))
			|| (original
				.startsWith(IPersistentObject.ATTRIBUTE_PREFIX_INTERNAL))
			|| (original
				.startsWith(IPersistentObject.ATTRIBUTE_PREFIX_READONLY))
			|| ((original.indexOf('_') < 3) && (original.indexOf('_') > -1)))
		{
			original = original.substring(original.indexOf('_') + 1);
		}
		if((original.endsWith(IPersistentObject.TABLE_PREFIX_REPEATING))
			|| (original.endsWith(IPersistentObject.TABLE_PREFIX_SINGLE)))
		{
			original = original.substring(0, original.lastIndexOf('_'));
		}
		char[] chars = original.trim().toCharArray();
		StringBuffer returnValue = new StringBuffer(chars.length);
		boolean wasSpace = true;

		for(int charIndex = 0; charIndex < chars.length; charIndex++)
		{
			char ch = chars[charIndex];
			if((Character.isWhitespace(ch)) || (ch == '_'))
			{
				if(!wasSpace)
				{
					if(preserveWhiteSpace)
					{
						returnValue.append(' ');
					}
					wasSpace = true;
				}
			} else if(wasSpace)
			{
				returnValue.append(Character.toUpperCase(ch));
				wasSpace = false;
			} else
			{
				returnValue.append(ch);
			}
		}
		return returnValue.toString();
	}

	/**
	 * Get the copyright holder.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.200514:02:50</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @return The copyright holder.
	 */
	public String getCopyright()
	{

		return this.copyright;
	}

	/**
	 * Get the root folder.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.2005 14:52:01</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @return the root folder.
	 */
	public File getFolder()
	{

		return this.folder;
	}

	/**
	 * Do we inherit from old TBO's.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.2005 14:51:44</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @return should generated classes inherit from old TBO's
	 */
	public boolean getInherit()
	{

		return this.inherit;
	}

	/**
	 * Get the default package name.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.2005 14:51:36</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @return Get the default package.
	 */
	public String getPackage()
	{

		return this.targetPackage;
	}

	/**
	 * Get the prefix.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.2005 16:41:04</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @return Returns the prefix
	 */

	public String getPrefix()
	{

		return this.prefix;
	}

	/**
	 * Get the since value.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.200512:48:55</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @return the since value.
	 */
	public String getSince()
	{

		return this.since;
	}

	/**
	 * Get the version number.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.200513:45:22</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @return The version number.
	 */
	public String getVersion()
	{

		return this.version;
	}

	/**
	 * Should class files be created.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.2005 10:37:04</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @return true if classes should be created.
	 */
	public boolean isClass()
	{

		return this.isClass;
	}

	/**
	 * Should methods be created for custom attributes.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 27.3.2005 13:11:14</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @return true if helper methods should be created for custom attributes.
	 */
	public boolean isCustomAttributeMethods()
	{

		return this.isCustomAttributeMethods;
	}

	/**
	 * Get the debug value.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.2005 15:57:34</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @return The debug value.
	 */
	public boolean isDebug()
	{

		return this.debug;
	}

	/**
	 * Get the deep packages option.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.200511:27:28</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @return The deep packages option.
	 */
	public boolean isDeepPackages()
	{

		return this.deepPackages;
	}

	/**
	 * Get the use threads.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.200516:28:07</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @return The use threads.
	 */
	public boolean isThread()
	{

		return this.isThread;
	}

	/**
	 * Add a new TBO to be registered.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 29.3.2005 10:43:36</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param register
	 *            information about the TBO to be registered. [0]=Fully
	 *            qualified TBO interface name.
	 *            [1]=[InterfaceName].[TypeNameVariable]. [2]=Fully qualified
	 *            TBO class name.
	 */
	protected void addRegistration(final String[] register)
	{

		getRegisterThese().addElement(register);
	}

	/**
	 * Get the persistent interface name.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 27.3.2005 20:51:18</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @return the interface name.
	 */
	protected String getPersistentInterfaceName()
	{

		return this.persistentInterfaceName;
	}

	/**
	 * Get the root type node.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.2005 15:30:37</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @return Returns the persistentObject
	 */

	protected TypeNode getPersistentObject()
	{

		return this.persistentObject;
	}

	/**
	 * Get the TBO's to register.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 29.3.2005 11:39:13</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @return the TBO's to register.
	 */
	protected Vector<String[]> getRegisterThese()
	{

		return this.registerThese;
	}

	/**
	 * Get a session
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.2005 17:30:11</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @return a docbase session.
	 * @throws InterruptedException
	 * @throws DfException
	 */
	protected synchronized IDfSession getSession() throws InterruptedException,
					DfException
	{

		try
		{
			if(isDebug())
			{
				System.out.println(Thread.currentThread().getName() + ' '
					+ getClass().getName() + ".getSession() start");//$NON-NLS-1$
			}
			while(getCollectionsInUse() > 9)
			{
				Thread.sleep(50);
			}
			add();
			return getManager().getSession(getDocbase());
		} finally
		{
			if(isDebug())
			{
				System.out.println(Thread.currentThread().getName() + ' '
					+ getClass().getName() + ".getSession() end");//$NON-NLS-1$
			}
		}
	}

	/**
	 * Set the persistent object interface name.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 27.3.2005 20:49:16</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param aPersistentInterfaceName
	 *            the interface name.
	 */
	protected void setPersistentInterfaceName(
												final String aPersistentInterfaceName)
	{

		this.persistentInterfaceName = aPersistentInterfaceName;
	}

	/**
	 * Set the session (release).
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.20059:37:42</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param aSession
	 *            a Docbase sesson.
	 */
	protected synchronized void setSession(final IDfSession aSession)
	{

		getManager().release(aSession);
		reduce();
	}

	/**
	 * Increase the amount of collections in use.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.20059:50:46</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 */
	private void add()
	{

		this.collectionsInUse++;
	}

	/**
	 * Get the amount of collections currently open.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.20059:50:50</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @return the amount of open collections.
	 */
	private int getCollectionsInUse()
	{

		return this.collectionsInUse;
	}

	/**
	 * Get the docbase name.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.2005 14:52:09</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @return the Docabse name.
	 */
	private String getDocbase()
	{

		return this.docbase;
	}

	/**
	 * Get the domain.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.2005 14:52:05</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @return the users domain
	 */
	private String getDomain()
	{

		return this.domain;
	}

	/**
	 * Get the session manager.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.2005 15:07:51</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @return the manager.
	 */
	private synchronized IDfSessionManager getManager()
	{

		return this.manager;
	}

	/**
	 * Get the password
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.2005 15:06:21</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @return the password.
	 */

	private String getPassword()
	{

		return this.password;
	}

	/**
	 * Get the user name.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.2005 14:51:28</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @return the user name.
	 */
	private String getUser()
	{

		return this.userName;
	}

	/**
	 * Check that the arguments are valid.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.2005 14:12:14</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param arguments
	 *            the program arguments.
	 * @return true if arguments are valid.
	 */
	private boolean parseArguments(final String[] arguments)
	{

		try
		{
			if(isDebug())
			{
				System.out.println(Thread.currentThread().getName() + ' '
					+ getClass().getName()
					+ ".parseArguments(final String[] arguments) start");//$NON-NLS-1$
			}
			for(int argumentIndex = 1; argumentIndex < arguments.length; argumentIndex++)
			{
				final String argument = arguments[argumentIndex - 1]
					.toLowerCase();
				final String value = arguments[argumentIndex];
				if((argument.equals(GenerateTBOs.CLASSES[0]))
					|| (argument.equals(GenerateTBOs.CLASSES[1])))
				{
					setIsClass(value);
				} else if((argument.equals(GenerateTBOs.COPYRIGHT[0]))
					|| (argument.equals(GenerateTBOs.COPYRIGHT[1])))
				{
					setCopyright(value);
				} else if((argument.equals(GenerateTBOs.DEBUG[0]))
					|| (argument.equals(GenerateTBOs.DEBUG[1])))
				{
					setDebug(value);
				} else if((argument.equals(GenerateTBOs.DEEP[0]))
					|| (argument.equals(GenerateTBOs.DEEP[1])))
				{
					setDeepPackages(value);
				} else if((argument.equals(GenerateTBOs.DOCBASE[0]))
					|| (argument.equals(GenerateTBOs.DOCBASE[1])))
				{
					setDocbase(value);
				} else if((argument.equals(GenerateTBOs.DOMAIN[0]))
					|| (argument.equals(GenerateTBOs.DOMAIN[1])))
				{
					setDomain(value);
				} else if((argument.equals(GenerateTBOs.FOLDER[0]))
					|| (argument.equals(GenerateTBOs.FOLDER[1])))
				{
					setFolder(value);
				} else if((argument.equals(GenerateTBOs.HELP[0]))
					|| (argument.equals(GenerateTBOs.HELP[1])))
				{
					printUsage();
					return false;
				} else if((argument.equals(GenerateTBOs.INHERIT[0]))
					|| (argument.equals(GenerateTBOs.INHERIT[1])))
				{
					setInherit(value);
				} else if((argument.equals(GenerateTBOs.METHODS[0]))
					|| (argument.equals(GenerateTBOs.METHODS[1])))
				{
					setIsCustomAttributeMethods(value);
				} else if((argument.equals(GenerateTBOs.PACKAGE[0]))
					|| (argument.equals(GenerateTBOs.PACKAGE[1])))
				{
					setPackage(value);
				} else if((argument.equals(GenerateTBOs.PASSWORD[0]))
					|| (argument.equals(GenerateTBOs.PASSWORD[1])))
				{
					setPassword(value);
				} else if((argument.equals(GenerateTBOs.PREFIX[0]))
					|| (argument.equals(GenerateTBOs.PREFIX[1])))
				{
					setPrefix(value);
				} else if((argument.equals(GenerateTBOs.SINCE[0]))
					|| (argument.equals(GenerateTBOs.SINCE[1])))
				{
					setSince(value);
				} else if((argument.equals(GenerateTBOs.THREAD[0]))
					|| (argument.equals(GenerateTBOs.THREAD[1])))
				{
					setIsThread(value);
				} else if((argument.equals(GenerateTBOs.USER[0]))
					|| (argument.equals(GenerateTBOs.USER[1])))
				{
					setUser(value);
				} else if((argument.equals(GenerateTBOs.VERSION[0]))
					|| (argument.equals(GenerateTBOs.VERSION[1])))
				{
					setVersion(value);
				}
			}
			if(testArguments())
			{
				return true;
			}
			return false;
		} finally
		{
			if(isDebug())
			{
				System.out.println(Thread.currentThread().getName() + ' '
					+ getClass().getName()
					+ ".parseArguments(final String[] arguments) end");//$NON-NLS-1$
			}
		}
	}

	/**
	 * Populate the type tree.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.2005 15:32:18</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 */
	private void populate()
	{

		if(isDebug())
		{

			System.out.println(Thread.currentThread().getName() + ' '
				+ getClass().getName() + ".populate() start");//$NON-NLS-1$
		}
		final ThreadGroup creationThreads = new ThreadGroup("root");//$NON-NLS-1$
		new TypeNode(this, null, null, creationThreads);
		/*
		 * Bug 84255 forces this.
		 */
		boolean keepRunning = true;
		while((creationThreads.activeCount() > 0) && keepRunning)
		{
			keepRunning = false;
			synchronized(creationThreads)
			{
				try
				{
					creationThreads.wait(5000);
					Thread[] list = new Thread[creationThreads.activeCount()];
					creationThreads.enumerate(list);
					for(final Thread thread: list)
					{
						if(thread != null)
						{
							final String threadName = thread.getName();
							if((threadName
								.startsWith(GenerateTBOs.THREAD_NAME_PREFIX))
								|| (threadName
									.endsWith((GenerateTBOs.THREAD_NAME_SUFFIX))))
							{
								keepRunning = true;
							}
						}
					}
				} catch(final InterruptedException iex)
				{
					System.out.println(Thread.currentThread().getName()
						+ " Wait interupted. "//$NON-NLS-1$
						+ iex.getMessage().trim());
				}
			}
		}
		writeRegisterClass();
		if(isDebug())
		{
			System.out.println(Thread.currentThread().getName() + ' '
				+ getClass().getName() + ".populate() end");//$NON-NLS-1$
		}
	}

	/**
	 * If invalid parameters are used this will be printed.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.2005 14:20:13</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 */
	private void printUsage()
	{

		System.out.println("Documentum BOF / TBO class & interface generator.");//$NON-NLS-1$
		System.out.println();
		System.out.println("Copyright :(c) 2005 Christopher Harper");//$NON-NLS-1$
		System.out.println();
		System.out
			.println("This program is free software; you can redistribute it and/or modify it under the terms");//$NON-NLS-1$
		System.out
			.println("of the GNU General Public License as published by the Free Software Foundation; either");//$NON-NLS-1$
		System.out
			.println("version 2 of the License, or (at your option) any later version.");//$NON-NLS-1$
		System.out.println();
		System.out
			.println("This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;");//$NON-NLS-1$
		System.out
			.println("without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.");//$NON-NLS-1$
		System.out
			.println("See the http://www.gnu.org/licenses/gpl.html for more details.");//$NON-NLS-1$
		System.out.println();
		System.out
			.println("Usage: java org.cah.dctm.bof.tbo.GenerateTBOs arguments");//$NON-NLS-1$
		System.out.println();
		System.out.println("Where required arguments are:");//$NON-NLS-1$
		System.out
			.println("\t-d|-docbase\t<docbase_name>\t\tDocbase where to connect.");//$NON-NLS-1$
		System.out
			.println("\t-f|-folder\t<target_folder>\t\tRoot folder where to place the generated files.");//$NON-NLS-1$
		System.out
			.println("\t-a|-package\t<target_package>\tYour package under which the TBO's and possible ");//$NON-NLS-1$
		System.out.println("\t\t\t\t\t\tpackages will be created.");//$NON-NLS-1$
		System.out
			.println("\t-p|-password\t<password>\t\tThe users password to use.");//$NON-NLS-1$
		System.out.println("\t-u|-user\t<user_name>\t\tConnecting user name.");//$NON-NLS-1$
		System.out.println("and optional arguments are:");//$NON-NLS-1$
		System.out
			.println("\t-cl|-classes\t<true|false>\t\tShould TBO implementation class files be generated. ");//$NON-NLS-1$
		System.out.println("\t\t\t\t\t\tDefault is 'true'.");//$NON-NLS-1$
		System.out
			.println("\t-co|-copyright\t<copyright_value>\tThe copyright holder of the generated classes.");//$NON-NLS-1$
		System.out
			.println("\t-db|-debug\t<true|false>\t\tShould debug info be printed. Default is 'false'.");//$NON-NLS-1$
		System.out
			.println("\t-de|-deep\t<true|false>\t\tShould a package hierarchy be created. Default is 'true'.");//$NON-NLS-1$
		System.out
			.println("\t-do|-domain\t<users_domain>\t\tThe connecting users domain.");//$NON-NLS-1$
		System.out.println("\t-he|-help\t\t\t\tPrint this message.");//$NON-NLS-1$
		System.out
			.println("\t-in|-inherit\t<true|false>\t\tInherit the generated classes from Existing");//$NON-NLS-1$
		System.out
			.println("\t\t\t\t\t\tDocumentum / TBO classes. Default is 'true'.");//$NON-NLS-1$
		System.out
			.println("\t-me|-methods\t<true|false>\t\tShould custom attribute accessor method be created.");//$NON-NLS-1$
		System.out.println("\t\t\t\t\t\tDefault is 'false'.");//$NON-NLS-1$
		System.out
			.println("\t-pr|-prefix\t<prefix_value>\t\tPrefix to use in class and interface names.");//$NON-NLS-1$
		System.out
			.println("\t-si|-since\t<since_value>\t\tThe @since javadoc tag value. Default is '1.0.0'.");//$NON-NLS-1$
		System.out
			.println("\t-th|-thread\t<true|false>\t\tShould threads be used while creating TBO's. Default is");//$NON-NLS-1$
		System.out.println("\t\t\t\t\t\t'false'.");//$NON-NLS-1$
		System.out
			.println("\t-ve|-version\t<version_value>\t\tThe @version javadoc tag value. Default is '1.0.0'.");//$NON-NLS-1$
		System.out.println();
		System.out.println("NOTE: java must be version 1.5 or greater.");//$NON-NLS-1$
	}

	/**
	 * Reduce the amount of collections in use.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.20059:52:22</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 */
	private void reduce()
	{

		this.collectionsInUse--;
	}

	/**
	 * Set the copyright holder.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.200514:02:55</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param aCopyright
	 *            The copyright holder.
	 */
	private void setCopyright(final String aCopyright)
	{

		this.copyright = aCopyright;
	}

	/**
	 * Set the debug value.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.2005 15:57:16</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param aDebug
	 *            The debug value.
	 */
	private void setDebug(final String aDebug)
	{

		this.debug = Boolean.getBoolean(aDebug);
	}

	/**
	 * Set the deep packages option.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.200511:26:58</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param aDeepPackages
	 *            The deep packages option.
	 */
	private void setDeepPackages(final String aDeepPackages)
	{

		this.deepPackages = Boolean.getBoolean(aDeepPackages);
	}

	/**
	 * Set the docbase name.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.2005 14:51:20</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param aDocbase
	 *            the docbase name.
	 */
	private void setDocbase(final String aDocbase)
	{

		this.docbase = aDocbase;
	}

	/**
	 * Set the domain.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.2005 14:51:14</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param aDomain
	 *            the domain.
	 */
	private void setDomain(final String aDomain)
	{

		this.domain = aDomain;
	}

	/**
	 * Set the folder name.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.2005 14:51:07</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param aFolder
	 *            the folder.
	 */
	private void setFolder(final String aFolder)
	{

		this.folder = new File(aFolder);
	}

	/**
	 * Set shold inheritance be used.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.2005 14:51:00</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param anInherit
	 *            the inheritance swich.
	 */
	private void setInherit(final String anInherit)
	{

		this.inherit = Boolean.getBoolean(anInherit);
	}

	/**
	 * Set the is class value.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.2005 10:36:04</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param anIsClass
	 *            The is class value.
	 */
	private void setIsClass(final String anIsClass)
	{

		this.isClass = Boolean.getBoolean(anIsClass);
	}

	/**
	 * Set should custom attributes have helper methos.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 27.3.2005 13:10:52</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param anIsCustomAttributeMethods
	 *            new create custom attribute methods value.
	 */
	private void setIsCustomAttributeMethods(
												final String anIsCustomAttributeMethods)
	{

		this.isCustomAttributeMethods = Boolean
			.getBoolean(anIsCustomAttributeMethods);
	}

	/**
	 * Set the use threads.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.200516:28:03</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param anIsThread
	 *            The use threads.
	 */
	private void setIsThread(final String anIsThread)
	{

		this.isThread = Boolean.getBoolean(anIsThread);
	}

	/**
	 * Set the root package.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.2005 14:50:47</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param aPackage
	 *            root package name.
	 */
	private void setPackage(final String aPackage)
	{

		this.targetPackage = aPackage;
	}

	/**
	 * Set the password.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.2005 15:06:45</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param aPassword
	 *            new password.
	 */

	private void setPassword(final String aPassword)
	{

		this.password = aPassword;
	}

	/**
	 * Set the prefix.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.2005 16:41:04</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param aPrefix
	 *            The prefix to set
	 */

	private void setPrefix(final String aPrefix)
	{

		if((aPrefix != null) && (!aPrefix.equals(""))) //$NON-NLS-1$
		{
			this.prefix = aPrefix;
		}
	}

	/**
	 * Set the since value.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.200512:47:18</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param aSince
	 *            the since.
	 */
	private void setSince(final String aSince)
	{

		this.since = aSince;
	}

	/**
	 * Set the user name.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.2005 14:50:38</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param anUser
	 *            the user.
	 */

	private void setUser(final String anUser)
	{

		this.userName = anUser;
	}

	/**
	 * Set the version number.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.200513:44:47</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param aVersion
	 *            The version number.
	 */
	private void setVersion(final String aVersion)
	{

		this.version = aVersion;
	}

	/**
	 * Check if the given information will work.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.2005 14:43:34</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @return true if the arguments were valid.
	 */
	private boolean testArguments()
	{

		if(isDebug())
		{
			System.out.println(Thread.currentThread().getName() + ' '
				+ getClass().getName() + ".testArguments() start"); //$NON-NLS-1$
		}
		final File temp = new File(getFolder(), String.valueOf(System
			.currentTimeMillis())
			+ ".tmp"); //$NON-NLS-1$
		IDfSession session = null;
		IDfCollection results = null;
		try
		{
			if(temp.createNewFile())
			{
				File packageFolder = temp.getParentFile();
				temp.delete();
				final StringTokenizer folderNames = new StringTokenizer(
					getPackage(), ".", false); //$NON-NLS-1$
				final StringBuffer defaultPackage = new StringBuffer(
					getPackage().length() + 10);
				while(folderNames.hasMoreTokens())
				{
					String folderName = folderNames.nextToken().toLowerCase();
					if(GenerateTBOs.RESERVED.contains(folderName))
					{
						folderName = folderName + getPrefix().toLowerCase();
					}
					packageFolder = new File(packageFolder, folderName);
					if((!packageFolder.exists()) && (!packageFolder.mkdirs()))
					{
						System.out
							.println("Failed to create package folder '" + packageFolder.getAbsolutePath() + "'."); //$NON-NLS-1$  //$NON-NLS-2$
						return false;
					}
					defaultPackage.append(folderName);
					if(folderNames.hasMoreTokens())
					{
						defaultPackage.append('.');
					}
				}
				IDfLoginInfo login = new DfLoginInfo();
				login.setDomain(getDomain());
				login.setPassword(getPassword());
				login.setUser(getUser());
				getManager().setIdentity(getDocbase(), login);
				getManager().authenticate(getDocbase());
				final StringBuffer typeQuery = new StringBuffer(
					"select distinct ").append(IType.NAME).append(" from ") //$NON-NLS-1$ //$NON-NLS-2$
					.append(IType.TYPE_DM_TYPE);
				final IDfQuery query = new DfQuery();
				query.setDQL(typeQuery.toString());
				session = getManager().getSession(getDocbase());
				results = query.execute(session, IDfQuery.DF_EXECREAD_QUERY);
				while(results.next())
				{
					try
					{
						session.getObjectByQualification(results
							.getString(IType.NAME));
					} catch(DfException dex)
					{
						System.out.println(dex.getMessage());
						dex.printStackTrace(System.err);
					}
				}
				return true;
			}
		} catch(final DfServiceException swallow)
		{
			System.out
				.println("Failed to open session into '" + getDocbase() + "' with user '" + getUser() + "'. " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					+ swallow.getMessage());
		} catch(final IOException swallow)
		{
			System.out
				.println("Failed to create file '" + temp.getAbsolutePath() + "' into '" //$NON-NLS-1$ //$NON-NLS-2$
					+ getFolder().getAbsolutePath()
					+ "'. " + swallow.getMessage()); //$NON-NLS-1$
		} catch(final NullPointerException swallow)
		{
			System.out
				.println("Failed to test given parameters." + swallow.getMessage()); //$NON-NLS-1$
		} catch(final DfException swallow)
		{
			System.out
				.println("Failed to run type query." + swallow.getMessage()); //$NON-NLS-1$
		} finally
		{
			if((results != null)
				&& (results.getState() != IDfCollection.DF_CLOSED_STATE))
			{
				try
				{
					results.close();
				} catch(final DfException swallow)
				{
					System.out
						.println("Failed to close collection." + swallow.getMessage()); //$NON-NLS-1$
				}
			}
			if(session != null)
			{
				getManager().release(session);
			}
		}
		if(isDebug())
		{
			System.out.println(Thread.currentThread().getName() + ' '
				+ getClass().getName() + ".testArguments() end"); //$NON-NLS-1$
		}
		return false;
	}

	/**
	 * Write the Register.java file.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 29.3.2005 11:37:23</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 */
	private void writeRegisterClass()
	{

		PrintWriter writer = null;
		final File target = new File(getFolder().getAbsolutePath()
			+ File.separatorChar
			+ getPackage().replace('.', File.separatorChar)
			+ File.separatorChar + "Register.java"); //$NON-NLS-1$
		try
		{
			writer = new PrintWriter(new BufferedWriter(new FileWriter(target)));
			System.out
				.println(Thread.currentThread().getName()
					+ ' '
					+ getClass().getName()
					+ ".writeRegisterClass() Writing class " + target.getAbsolutePath() + "."); //$NON-NLS-1$  //$NON-NLS-2$
			writer.println("/*-"); //$NON-NLS-1$
			writer.println(" * $Log:$"); //$NON-NLS-1$
			writer.println(" *"); //$NON-NLS-1$
			writer.println(" */"); //$NON-NLS-1$
			writer.println();
			writer.print("package "); //$NON-NLS-1$
			writer.print(getPackage());
			writer.print(';');
			writer.println();
			for(final String[] register: getRegisterThese())
			{
				writer.print("import "); //$NON-NLS-1$
				writer.print(register[0]);
				writer.print(';');
				writer.println();
				writer.print("import "); //$NON-NLS-1$
				writer.print(register[2]);
				writer.print(';');
				writer.println();
			}
			writer.println("import com.documentum.fc.client.DfClient;"); //$NON-NLS-1$
			writer.println("import com.documentum.fc.client.DfDborEntry;"); //$NON-NLS-1$
			writer.println("import com.documentum.fc.client.IDfDbor;"); //$NON-NLS-1$
			writer.println("import com.documentum.fc.client.IDfDborEntry;"); //$NON-NLS-1$
			writer.println("import com.documentum.fc.common.DfException;"); //$NON-NLS-1$
			final Calendar creationTime = Calendar.getInstance();
			writer.println("/**"); //$NON-NLS-1$
			writer.println(" * <H4>TBO registration class.</H4>"); //$NON-NLS-1$
			writer
				.println(" * <DL><DT><B>Description:</B> <DD>Register all the created TBO classes. Usage:<pre>"); //$NON-NLS-1$
			writer
				.print(" * java -cp \"[your_path]\\dfc.jar;[your_path]\\tbo.classes\" "); //$NON-NLS-1$
			writer.print(getPackage());
			writer.print('.');
			writer.print("Register"); //$NON-NLS-1$
			writer.println();
			writer
				.println(" * </pre>NOTE: java must be version 1.5 or greater.</DD></DT>"); //$NON-NLS-1$
			writer.print(" * <DT><B>Copyright:</B><DD>(c) "); //$NON-NLS-1$
			writer.print(creationTime.get(Calendar.YEAR));
			writer.print(' ');
			writer.print(getCopyright());
			writer.print("</DD></DT>"); //$NON-NLS-1$
			writer.println();
			writer.print(" * <DT><B>Created:</B> <DD>"); //$NON-NLS-1$
			writer.print(creationTime.get(Calendar.DAY_OF_MONTH));
			writer.print('.');
			writer.print(creationTime.get(Calendar.MONTH));
			writer.print('.');
			writer.print(creationTime.get(Calendar.YEAR));
			writer.print(' ');
			writer.print(creationTime.get(Calendar.HOUR_OF_DAY));
			writer.print(':');
			writer.print(creationTime.get(Calendar.MINUTE));
			writer.print(':');
			writer.print(creationTime.get(Calendar.SECOND));
			writer.print('.');
			writer.print("</DD></DT></DL>"); //$NON-NLS-1$
			writer.println();
			writer.print(" * @author "); //$NON-NLS-1$
			writer.print(getClass().getName());
			writer.println();
			writer.print(" * @version "); //$NON-NLS-1$
			writer.print(getVersion());
			writer.println();
			writer.print(" * @since "); //$NON-NLS-1$
			writer.print(getSince());
			writer.println();
			writer.println(" */"); //$NON-NLS-1$
			writer.println("public class Register"); //$NON-NLS-1$
			writer.println("\textends"); //$NON-NLS-1$
			writer.println("\t\tObject"); //$NON-NLS-1$
			writer.println("{"); //$NON-NLS-1$

			writer.println("\t/**"); //$NON-NLS-1$
			writer.println("\t * Entry point for the class registration."); //$NON-NLS-1$
			writer.print("\t * <DL><DT><B>Created:</B> <DD>"); //$NON-NLS-1$
			writer.print(creationTime.get(Calendar.DAY_OF_MONTH));
			writer.print('.');
			writer.print(creationTime.get(Calendar.MONTH));
			writer.print('.');
			writer.print(creationTime.get(Calendar.YEAR));
			writer.print(' ');
			writer.print(creationTime.get(Calendar.HOUR_OF_DAY));
			writer.print(':');
			writer.print(creationTime.get(Calendar.MINUTE));
			writer.print(':');
			writer.print(creationTime.get(Calendar.SECOND));
			writer.print('.');
			writer.print("</DD></DT>"); //$NON-NLS-1$
			writer.println();
			writer.print("\t * <DT><B>Author:</B> <DD>"); //$NON-NLS-1$
			writer.print(getClass().getName());
			writer.print("</DD></DT></DL>"); //$NON-NLS-1$
			writer.println();
			writer.println("\t * @param arguments"); //$NON-NLS-1$
			writer.println("\t * \t none what so ever."); //$NON-NLS-1$
			writer.print("\t * @since "); //$NON-NLS-1$
			writer.print(getSince());
			writer.println();
			writer.println("\t */"); //$NON-NLS-1$
			writer
				.println("\tpublic static void main(final String[] arguments)"); //$NON-NLS-1$
			writer.println("\t{"); //$NON-NLS-1$
			final StringBuffer typeNameBuffer = new StringBuffer(
				"\t\tfinal String[] typeNames = new String[] {"); //$NON-NLS-1$
			final StringBuffer tboNameBuffer = new StringBuffer(
				"\t\tfinal String[] tboNames = new String[] {"); //$NON-NLS-1$
			for(int registerIndex = 0; registerIndex < getRegisterThese()
				.size(); registerIndex++)
			{
				if(registerIndex > 0)
				{
					typeNameBuffer.append(',').append(GenerateTBOs.NEWLINE)
						.append("\t\t\t"); //$NON-NLS-1$
					tboNameBuffer.append(',').append(GenerateTBOs.NEWLINE)
						.append("\t\t\t"); //$NON-NLS-1$
				}
				typeNameBuffer.append(getRegisterThese().elementAt(
					registerIndex)[1]);
				String className = getRegisterThese().elementAt(registerIndex)[2];
				className = className.substring(className.lastIndexOf('.') + 1);
				tboNameBuffer.append(className).append(".class.getName()"); //$NON-NLS-1$
			}
			typeNameBuffer.append("};").append(GenerateTBOs.NEWLINE); //$NON-NLS-1$
			tboNameBuffer.append("};").append(GenerateTBOs.NEWLINE); //$NON-NLS-1$
			writer.println(typeNameBuffer.toString());
			writer.println(tboNameBuffer.toString());
			writer.println("\t\ttry"); //$NON-NLS-1$
			writer.println("\t\t{"); //$NON-NLS-1$
			writer
				.println("\t\t\tfinal IDfDbor dbor = DfClient.getLocalClient().getDbor();"); //$NON-NLS-1$
			writer
				.println("\t\t\tfor(int tboIndex = 0; tboIndex < tboNames.length; tboIndex++)"); //$NON-NLS-1$
			writer.println("\t\t\t{"); //$NON-NLS-1$
			writer.println("\t\t\t\ttry"); //$NON-NLS-1$
			writer.println("\t\t\t\t{"); //$NON-NLS-1$
			writer
				.println("\t\t\t\t\tfinal String lookup = dbor.lookupObject(typeNames[tboIndex]);"); //$NON-NLS-1$
			writer.println("\t\t\t\t\tSystem.out.print(typeNames[tboIndex]);"); //$NON-NLS-1$
			writer
				.println("\t\t\t\t\tSystem.out.print(\" registered with lookup \");"); //$NON-NLS-1$
			writer.println("\t\t\t\t\tSystem.out.print(lookup);"); //$NON-NLS-1$
			writer.println("\t\t\t\t\tSystem.out.println('.');"); //$NON-NLS-1$
			writer.println("\t\t\t\t} catch (final DfException swallow)"); //$NON-NLS-1$
			writer.println("\t\t\t\t{"); //$NON-NLS-1$
			writer
				.println("\t\t\t\t\tfinal IDfDborEntry entry = new DfDborEntry();"); //$NON-NLS-1$
			writer.println("\t\t\t\t\tentry.setName(typeNames[tboIndex]);"); //$NON-NLS-1$
			writer.println("\t\t\t\t\tentry.setServiceBased(false);"); //$NON-NLS-1$
			writer.println("\t\t\t\t\tentry.setJavaClass(tboNames[tboIndex]);"); //$NON-NLS-1$
			writer.print("\t\t\t\t\tentry.setVersion(\""); //$NON-NLS-1$
			writer.print(getVersion());
			writer.print("\");"); //$NON-NLS-1$
			writer.println();
			writer.println("\t\t\t\t\tdbor.register(entry);"); //$NON-NLS-1$
			writer.println("\t\t\t\t\tSystem.out.print(typeNames[tboIndex]);"); //$NON-NLS-1$
			writer
				.println("\t\t\t\t\tSystem.out.print(\" registered with implementation \");"); //$NON-NLS-1$
			writer.println("\t\t\t\t\tSystem.out.print(tboNames[tboIndex]);"); //$NON-NLS-1$
			writer.print("\t\t\t\t\tSystem.out.print(\" and version "); //$NON-NLS-1$
			writer.print(getVersion());
			writer.print(" as a TBO. \");"); //$NON-NLS-1$
			writer.println();
			writer.println("\t\t\t\t}"); //$NON-NLS-1$
			writer.println("\t\t\t}"); //$NON-NLS-1$
			writer.println("\t\t} catch (final DfException dex)"); //$NON-NLS-1$
			writer.println("\t\t{"); //$NON-NLS-1$
			writer.println("\t\t\tSystem.out.println(dex.getMessage());"); //$NON-NLS-1$
			writer.println("\t\t\tdex.printStackTrace(System.err);"); //$NON-NLS-1$
			writer.println("\t\t}"); //$NON-NLS-1$
			writer.println("\t}"); //$NON-NLS-1$
			writer.println("}"); //$NON-NLS-1$
		} catch(final IOException ioex)
		{
			System.out.println(ioex.getMessage());
		} finally
		{
			if(writer != null)
			{
				writer.flush();
				writer.close();
			}
		}
	}
}
