/*-
 * $Log: APIParser.java,v $
 * Revision 1.12  2005/12/04 22:17:21  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 * Revision 1.11  2005/12/04 20:27:46  madcook
 * Version 3.0.0 work started.
 *
 * Revision 1.10  2005/11/22 13:27:07  madcook
 * Eclipse 3.2 compiler check warnings modified.
 *
 * Revision 1.9  2005/11/21 14:53:38  madcook
 * Old 1.4 style code removed.
 *
 * Revision 1.8  2005/11/21 13:04:32  madcook
 * Plugin version 3.0.0 work started and made code to comply with all java 5 
 * settings.
 *
 * Revision 1.7  2005/04/01 11:28:02  harpechr
 * Reference to a static string changed to an interface.
 *
 * Revision 1.6  2005/03/25 09:21:17  harpechr
 * Version 2.0.0 code that has implemented the new java 1.5 features.
 *
 * Revision 1.5  2005/02/09 14:01:39  harpechr
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

import java.util.StringTokenizer;
import java.util.Vector;

import org.cah.eclipse.plugins.dctm.dql.IDCTMPlugin;
import org.cah.eclipse.plugins.dctm.dql.views.MessageView;


/**
 * <H4>Class to gather valid API statements from the buffer and return them.</H4>
 * <DL>
 * <DT><B>Copyright (c): </B>
 * <DD>Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>Dec 3, 2004 9:27:17 AM.</DD>
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
public class APIParser
{

	/**
	 * Sole constructor.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 3, 2004 9:27:18 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 */
	public APIParser()
	{

		super();
	}

	/**
	 * Remove comments and return valid API statements.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 3, 2004 9:27:55 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param apiText
	 * @return prepared lines
	 */
	public static Vector<String[]> prepareAPI(final String apiText)
	{

		final StringTokenizer lines = new StringTokenizer(apiText,
			IDCTMPlugin.NEWLINE, false);
		final Vector<String[]> results = new Vector<String[]>(lines
			.countTokens());
		String nextLine = null;
		while((lines.hasMoreTokens()) || (nextLine != null))
		{
			String line = null;
			if(nextLine != null)
			{
				line = nextLine.trim();
				nextLine = null;
			} else
			{
				line = lines.nextToken().trim();
			}
			if(line.indexOf("--") > -1) //$NON-NLS-1$
			{
				line = line.substring(0, line.indexOf("--")).trim(); //$NON-NLS-1$
			}
			if(line.indexOf('#') > -1)
			{
				line = line.substring(0, line.indexOf('#')).trim();
			}
			if(line.indexOf("//") > -1) //$NON-NLS-1$
			{
				line = line.substring(0, line.indexOf("//")).trim(); //$NON-NLS-1$
			}
			if(line.indexOf("/*") > -1) //$NON-NLS-1$
			{
				if((line.indexOf("*/") < line.indexOf("/*")) && (lines.hasMoreTokens())) //$NON-NLS-1$ //$NON-NLS-2$
				{
					nextLine = lines.nextToken();
					while((!(nextLine.indexOf("*/") > -1)) && (lines.hasMoreTokens())) //$NON-NLS-1$
					{
						nextLine = lines.nextToken();
					}
					if(lines.hasMoreTokens())
					{
						nextLine = lines.nextToken();
					} else
					{
						nextLine = null;
					}
				}
				line = line.substring(0, line.indexOf("/*")).trim(); //$NON-NLS-1$
			}
			if(line.indexOf(',') > 0)
			{
				final StringTokenizer portions = new StringTokenizer(line,
					",", false); //$NON-NLS-1$
				String command = null;
				String upperCommand = null;
				if(portions.hasMoreTokens())
				{
					command = portions.nextToken().trim();
					upperCommand = command.toUpperCase();
				}
				if((upperCommand != null)
					&& ((APIKeywordScanner.APIEXEC.contains(upperCommand))
						|| (APIKeywordScanner.APISET.contains(upperCommand)) || (APIKeywordScanner.APIGET
						.contains(upperCommand))))
				{
					String session = null;
					if(portions.hasMoreTokens())
					{
						session = portions.nextToken().trim();
					}
					if((session != null) && (session.equals("c"))) //$NON-NLS-1$
					{
						final StringBuffer arguments = new StringBuffer();
						String value = null;
						int count = 0;
						while(portions.hasMoreTokens())
						{
							if(count++ > 0)
							{
								arguments.append(',');
							}
							arguments.append(portions.nextToken());
						}
						if((APIKeywordScanner.APISET.contains(upperCommand))
							&& ((lines.hasMoreTokens()) || (nextLine != null)))
						{
							if(nextLine != null)
							{
								value = nextLine;
								nextLine = null;
							} else
							{
								value = lines.nextToken();
							}
						}
						results.addElement(new String[] {command,
							arguments.toString(), value, upperCommand});
					} else
					{
						MessageView
							.getInstance()
							.addMessage(
								"Invalid API! Session must be 'c' instead of '" + session + "'."); //$NON-NLS-1$ //$NON-NLS-2$
					}
				} else
				{
					MessageView.getInstance().addMessage(
						"Invalid API! Unknown command '" + command + "'."); //$NON-NLS-1$ //$NON-NLS-2$
				}
			} else if(line.length() > 0)
			{
				MessageView.getInstance().addMessage(
					"Invalid API! Unknown line '" + line + "'."); //$NON-NLS-1$ //$NON-NLS-2$
			}
		}
		return results;
	}

	/**
	 * Test parser.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 3, 2004 10:22:14 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param arguments
	 *            nothing.
	 */
	public static void main(final String[] arguments)
	{

		String apiText = "create,c,dm_document\nlink,c,l,/Temp /*\n" //$NON-NLS-1$
			+ " * Document needs to be linked into Temp instead of home cabinet.\n */\nsave,c,l\n"; //$NON-NLS-1$
		final Vector<String[]> values = prepareAPI(apiText);
		for(String[] api: values)
		{
			System.out.println(api[0] + ',' + api[1] + ',' + api[2]);
		}
	}
}
