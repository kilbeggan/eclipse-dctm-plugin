/*
 * $Log: DQLParser.java,v $
 * Revision 1.8  2005/12/04 22:17:21  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 * Revision 1.7 2005/12/04 20:28:23 madcook Version
 * 3.0.0 work started. Revision 1.6 2005/11/21 13:04:32 madcook Plugin version
 * 3.0.0 work started and made code to comply with all java 5 settings. Revision
 * 1.5 2005/03/25 09:21:16 harpechr Version 2.0.0 code that has implemented the
 * new java 1.5 features. Revision 1.4 2005/02/09 14:01:36 harpechr Version
 * 1.0.5 work started. Revision 1.3 2005/01/24 12:34:55 harpechr Version 1.0.4
 * work started. Revision 1.2 2005/01/11 14:02:13 harpechr Changed version
 * number from 1.0.2 to 1.0.3. Revision 1.1 2005/01/07 12:37:49 harpechr Version
 * 1.0.2 code. First CVS commit!
 */

package org.cah.eclipse.plugins.dctm.dql.editors.dql;

import java.util.ArrayList;


/**
 * <H4>DQL statement parser.</H4>
 * <DL>
 * <DT><B>Copyright (c): </B>
 * <DD>Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>Nov 28, 2004 3:08:10 PM.</DD>
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
public class DQLParser
{

	/**
	 * Test the parser.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 3:18:55 PM</DD>
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

		// parseDQL("select * from table where column = \"po\\\"opy\"; --don't
		// include this" + '\n'+ "/* another
		// monkey;");
		String dqls = "select\n	*, eka\nfrom\n    table\nwhere\n	column = 'O''brien'\ngo\n" //$NON-NLS-1$
			+ "/*\n * Juu ei.\n */\nselect\n	*, toka\nfrom\n	table\nwhere\n	column = 'O''brien';\n" //$NON-NLS-1$
			+ "//Ulli mulli\nselect\n    *, kolkki\nfrom\n	table\nwhere\n	column = 'O''brien';\n" //$NON-NLS-1$
			+ "--Möhköfantti\nselect\n	*, nelkki\nfrom\n	table\nwhere\n	column = 'O''brien';\n" //$NON-NLS-1$
			+ "# pahkapaa\nselect\n	*, viides\nfrom\n	\"table\"\nwhere\n	column = 'O''br\\\\i\\*juu*\\en';\n"; //$NON-NLS-1$
		System.out.println(dqls);
		String[] prepared = prepareDQL(dqls);
		for(int index = 0; index < prepared.length; index++)
		{
			System.out.println(prepared[index]);
		}

	}

	/**
	 * Gather valid DQL statements.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 5:57:48 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param statementData
	 *            the original buffer.
	 * @return comment stripped dql statements.
	 */
	public static String[] prepareDQL(final String statementData)
	{

		StringBuffer dqlBuilder = new StringBuffer();
		final ArrayList<String> preparedStatements = new ArrayList<String>();
		final char[] dataCharacters = statementData.toCharArray();
		boolean notInString = true;
		boolean notEscapeLocation = true;
		char previousCharacter = ' ';
		char nextCharacter = ' ';
		char currentCharacter = ' ';
		for(int characterIndex = 0; characterIndex < dataCharacters.length; characterIndex++)
		{
			if(characterIndex > 0)
			{
				previousCharacter = dataCharacters[characterIndex - 1];
			}
			if((characterIndex + 1) < dataCharacters.length)
			{
				nextCharacter = dataCharacters[characterIndex + 1];
			} else
			{
				nextCharacter = ' ';
			}
			currentCharacter = dataCharacters[characterIndex];
			if((notInString) && (currentCharacter == '/')
				&& (nextCharacter == '*'))
			{
				characterIndex = goToTheEndOfAComment(dataCharacters,
					"*/".toCharArray(), false, characterIndex);//$NON-NLS-1$
				continue;
			} else if((notInString) && (currentCharacter == '/')
				&& (nextCharacter == '/'))
			{
				characterIndex = goToTheEndOfAComment(dataCharacters, null,
					true, characterIndex);
				continue;
			} else if((notInString) && (currentCharacter == '#'))
			{
				characterIndex = goToTheEndOfAComment(dataCharacters, null,
					true, characterIndex);
				continue;
			} else if((notInString) && (currentCharacter == '-')
				&& (nextCharacter == '-'))
			{
				characterIndex = goToTheEndOfAComment(dataCharacters, null,
					true, characterIndex);
				continue;
			}
			if(((previousCharacter == '\'') && (currentCharacter == '\''))
				|| ((currentCharacter == '\'') && (nextCharacter == '\'')))
			{
				notEscapeLocation = false;
			} else
			{
				notEscapeLocation = true;
			}
			if(((notEscapeLocation) && (currentCharacter == '\''))
				|| (currentCharacter == '"'))
			{
				notInString = !notInString;
			}
			final String lastTwo = new String(new char[] {currentCharacter,
				nextCharacter});
			if((notInString)
				&& ((currentCharacter == ';') || (lastTwo
					.equalsIgnoreCase("go") && Character//$NON-NLS-1$
					.isWhitespace(previousCharacter))))
			{
				preparedStatements.add(dqlBuilder.toString().trim());
				dqlBuilder = new StringBuffer();
				if(lastTwo.equalsIgnoreCase("go"))//$NON-NLS-1$
				{
					characterIndex++;
				}
				continue;
			}
			if(!((notInString) && (Character.isWhitespace(currentCharacter)) && (Character
				.isWhitespace(nextCharacter))))
			{
				if(Character.isWhitespace(currentCharacter))
				{
					dqlBuilder.append(' ');
				} else
				{
					dqlBuilder.append(currentCharacter);
				}
			}
		}
		if(preparedStatements.size() == 0)
		{
			preparedStatements.add(dqlBuilder.toString().trim());
		}
		final String[] returnStatements = new String[preparedStatements.size()];
		preparedStatements.toArray(returnStatements);
		return returnStatements;
	}

	/**
	 * Consume a comment.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 5:32:09 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param dataCharacters
	 *            the whole string.
	 * @param endChars
	 *            comment endinc characters.
	 * @param findLineBreak
	 *            find any libe break character.
	 * @param characterIndex
	 *            current character index.
	 * @return the indexposition of that comment ending character.
	 */
	protected static int goToTheEndOfAComment(final char[] dataCharacters,
												final char[] endChars,
												final boolean findLineBreak,
												int characterIndex)
	{

		if(findLineBreak)
		{
			for(; characterIndex < dataCharacters.length; characterIndex++)
			{
				if((dataCharacters[characterIndex] == 13)
					|| (dataCharacters[characterIndex] == 10))
				{
					break;
				}
			}
		} else
		{
			final String valueToFid = new String(endChars);
			for(; characterIndex < dataCharacters.length; characterIndex++)
			{
				final String found = new String(dataCharacters, characterIndex,
					endChars.length);
				if(valueToFid.equals(found))
				{
					characterIndex = characterIndex + endChars.length - 1;
					break;
				}
			}

		}
		return characterIndex;
	}
}
