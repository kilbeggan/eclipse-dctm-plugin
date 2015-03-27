/*-
 * $Log: DQLWordRule.java,v $
 * Revision 1.7  2005/12/04 22:17:21  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 * Revision 1.6  2005/12/04 20:28:23  madcook
 * Version 3.0.0 work started.
 *
 * Revision 1.5  2005/03/25 09:21:16  harpechr
 * Version 2.0.0 code that has implemented the new java 1.5 features.
 *
 * Revision 1.4  2005/02/09 14:01:35  harpechr
 * Version 1.0.5 work started.
 *
 * Revision 1.3  2005/01/24 12:34:55  harpechr
 * Version 1.0.4 work started.
 *
 * Revision 1.2  2005/01/11 14:02:02  harpechr
 * Changed version number from 1.0.2 to 1.0.3.
 *
 * Revision 1.1  2005/01/07 12:37:49  harpechr
 * Version 1.0.2 code. First CVS commit!
 *
 */

package org.cah.eclipse.plugins.dctm.dql.editors.dql;

import java.util.HashMap;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;


/**
 * <H4>Text scanning rule.</H4>
 * <DL>
 * <DT><B>Copyright (c): </B>
 * <DD>Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>Nov 28, 2004 2:47:05 PM.</DD>
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
public class DQLWordRule
						implements
							IRule
{

	/**
	 * Key word collection.
	 * 
	 * @since 1.0
	 */
	private HashMap<String, IToken>	keywords	= new HashMap<String, IToken>();

	/**
	 * Rule token.
	 * 
	 * @since 1.0
	 */
	private IToken					token;

	/**
	 * Sole constructor.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 6:21:43 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aToken
	 *            the default token
	 */
	public DQLWordRule(final IToken aToken)
	{

		setToken(aToken);
	}

	/**
	 * Add a key word to this rule.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 6:21:47 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aKeyWord
	 *            the key word.
	 * @param aToken
	 *            the keyword token.
	 */
	public void addKeyword(final String aKeyWord, final IToken aToken)
	{

		if((aKeyWord != null) && (aToken != null))
		{
			getKeywords().put(aKeyWord.toUpperCase(), aToken);
		}
	}

	/**
	 * Evaluates the rule by examining the characters available from the
	 * provided character scanner. The token returned by this rule returns
	 * <code>true</code> when calling <code>isUndefined</code>, if the text
	 * the rule investigated does not match the rule's requirements.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 6:22:42 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param characterScanner
	 *            the character scanner to be used by this rule
	 * @return the token computed by the rule
	 * @see org.eclipse.jface.text.rules.IRule#evaluate(org.eclipse.jface.text.rules.ICharacterScanner)
	 */
	public IToken evaluate(final ICharacterScanner characterScanner)
	{

		char character = (char) characterScanner.read();
		if(Character.isLetter(character))
		{
			StringBuffer value = new StringBuffer();
			do
			{
				value.append(character);
				character = (char) characterScanner.read();
			} while((Character.isLetterOrDigit(character))
				|| (character == '_'));
			characterScanner.unread();
			final IToken returnValue = getKeywords().get(
				value.toString().toUpperCase());
			if(returnValue != null)
			{
				return returnValue;
			}
			return getToken();
		}
		characterScanner.unread();
		return Token.UNDEFINED;

	}

	/**
	 * Get the keywords.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 6:21:05 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return Returns the keywords
	 */
	protected HashMap<String, IToken> getKeywords()
	{

		return this.keywords;
	}

	/**
	 * Get the default token.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 6:21:05 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return Returns the token
	 */
	protected IToken getToken()
	{

		return this.token;
	}

	/**
	 * Set the key words.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 6:21:05 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param keywords
	 *            The keywords to set
	 */
	protected void setKeywords(final HashMap<String, IToken> keywords)
	{

		this.keywords = keywords;
	}

	/**
	 * Set the default token.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 6:21:05 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param token
	 *            The token to set
	 */
	protected void setToken(final IToken token)
	{

		this.token = token;
	}

}
