/*-
 * $Log:$
 * NOTE: Do not change the Ascii / binary property.
 */

package org.cah.dctm.bof.tbo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Vector;

import org.cah.dctm.bof.tbo.persistent.IPersistentObject;
import org.cah.dctm.bof.tbo.persistent.type.IType;

import com.documentum.fc.client.DfQuery;
import com.documentum.fc.client.IDfCollection;
import com.documentum.fc.client.IDfPersistentObject;
import com.documentum.fc.client.IDfQuery;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.IDfAttr;


/**
 * <H4>Representation of a single Docbase type.</H4>
 * <DL>
 * <DT><B>Copyright :</B>
 * <DD>(c) 2005 Christopher Harper</DD>
 * </DT>
 * <DT><B>Created :</B>
 * <DD>26.3.2005 15:25:46.</DD>
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
public class TypeNode
						implements
							Runnable
{

	/**
	 * Types attributes key=attribute name. value[0]=attribute type name,
	 * value[1]=attribute type number, value[2]=attribute length (if type is
	 * string), value[3]=attribute repeating.
	 * 
	 * @since 2.0.0
	 */
	private Hashtable<String, String[]>	attributes		= new Hashtable<String, String[]>();

	/**
	 * The class name.
	 * 
	 * @since 2.0.0
	 */
	private String						className		= null;

	/**
	 * Fully qualified extended class name.
	 * 
	 * @since 2.0.0
	 */
	private String						extendsFull;

	/**
	 * The extendec classes name.
	 * 
	 * @since 2.0.0
	 */
	private String						extendsName;

	/**
	 * The java package name.
	 * 
	 * @since 2.0.0
	 */
	private String						filePackage		= null;

	/**
	 * Collection of implemented interfaces.
	 * 
	 * @since 2.0.0
	 */
	private Vector<String>				implementsThese	= new Vector<String>();

	/**
	 * The interface name.
	 * 
	 * @since 2.0.0
	 */
	private String						interfaceName	= null;

	/**
	 * The root generator.
	 * 
	 * @since 2.0.0
	 */
	private GenerateTBOs				rootGenerator;

	/**
	 * The running thread.
	 * 
	 * @since 2.0.0
	 */
	private Thread						runner			= null;

	/**
	 * Group of threads started by this thread.
	 * 
	 * @since 2.0.0
	 */
	private ThreadGroup					subThreads		= null;

	/**
	 * The super type.
	 * 
	 * @since 2.0.0
	 */

	private TypeNode					superType		= null;

	/**
	 * The target folder.
	 * 
	 * @since 2.0.0
	 */
	private File						targetFolder	= null;

	/**
	 * Current types name.
	 * 
	 * @since 2.0.0
	 */
	private String						typeName		= null;

	/**
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.2005 15:27:36</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @param aRootGenerator
	 *            the container for session and launch information.
	 * @param aSuperType
	 *            super type.
	 * @param aTypeName
	 *            name of this type.
	 * @param threadGroup
	 *            thread group this thread belongs to.
	 * @since 2.0.0
	 */
	public TypeNode(final GenerateTBOs aRootGenerator,
					final TypeNode aSuperType, final String aTypeName,
					final ThreadGroup threadGroup)
	{

		super();
		setRootGenerator(aRootGenerator);
		if(getRootGenerator().isDebug())
		{
			System.out
				.println(Thread.currentThread().getName()
					+ ' '
					+ getClass().getName()
					+ ".TypeNode(final GenerateTBOs aRootGenerator, final TypeNode aSuperType, final String aTypeName, final ThreadGroup threadGroup) start"); //$NON-NLS-1$
		}
		setSuperType(aSuperType);
		setTypeName(aTypeName);
		setRunner(new Thread(threadGroup, this));
		getRunner().setName(
			GenerateTBOs.THREAD_NAME_PREFIX + getTypeName()
				+ GenerateTBOs.THREAD_NAME_SUFFIX);
		getRunner().setPriority(Thread.MIN_PRIORITY);
		if(getRootGenerator().isThread())
		{
			getRunner().start();
		} else
		{
			run();
			synchronized(this)
			{
				notifyAll();
			}
		}
		if(getRootGenerator().isDebug())
		{
			System.out
				.println(Thread.currentThread().getName()
					+ ' '
					+ getClass().getName()
					+ ".TypeNode(final GenerateTBOs aRootGenerator, final TypeNode aSuperType, final String aTypeName, final ThreadGroup threadGroup) end"); //$NON-NLS-1$
		}
	}

	/**
	 * Add an attribute.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.2005 15:45:11</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param attributeName
	 *            name of the attribte.
	 * @param attributeIdentifiers
	 *            value[0]=attribute type name, value[1]=attribute type number,
	 *            value[2]=attribute length (if type is string),
	 *            value[3]=attribute repeating.
	 */

	public void addAttribute(final String attributeName,
								final String[] attributeIdentifiers)
	{

		getAttributes().put(attributeName, attributeIdentifiers);
	}

	/**
	 * Get the attribute information.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.2005 15:42:55</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @return Returns the attributes
	 */
	public Hashtable<String, String[]> getAttributes()
	{

		return this.attributes;
	}

	/**
	 * Get the super type.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.2005 15:33:53</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @return Returns the superType
	 */
	public TypeNode getSuperType()
	{

		return this.superType;
	}

	/**
	 * Get the type name.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.2005 15:33:53</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @return Returns the typeName
	 */

	public String getTypeName()
	{

		return this.typeName;
	}

	/**
	 * Check if the attribute is already present.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.2005 16:21:53</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param attributeName
	 * @return true if the attribute is found in the type or its super types.
	 */
	public boolean hasAttribute(final String attributeName)
	{

		if((getSuperType() != null)
			&& (getSuperType().hasAttribute(attributeName))
			|| (getAttributes().containsKey(attributeName)))
		{
			return true;
		}
		return false;
	}

	/**
	 * Populate the type information.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.2005 16:03:15</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @see java.lang.Runnable#run()
	 */
	public void run()
	{

		if(getRootGenerator().isDebug())
		{
			System.out.println(Thread.currentThread().getName() + ' '
				+ getClass().getName() + ".run() start"); //$NON-NLS-1$
		}
		gatherInformation();
		/*
		 * Bug 84255 forces this.
		 */
		boolean keepRunning = true;
		while((getSubThreads().activeCount() > 0) && keepRunning)
		{
			synchronized(getSubThreads())
			{
				try
				{
					getSubThreads().wait(2500);
					Thread[] list = new Thread[getSubThreads().activeCount()];
					getSubThreads().enumerate(list);
					keepRunning = false;
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
						+ " Wait interupted. " //$NON-NLS-1$
						+ iex.getMessage().trim());
				}
			}
		}
		synchronized(this)
		{
			notifyAll();
		}
		synchronized(getRootGenerator())
		{
			getRootGenerator().notifyAll();
		}
		if(getRootGenerator().isDebug())
		{
			System.out.println(Thread.currentThread().getName() + ' '
				+ getClass().getName() + ".run() end"); //$NON-NLS-1$
		}
	}

	/**
	 * Add an implemented interface.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.200515:02:06</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param anInterfaceName
	 *            name of the new interface.
	 */
	protected void addIsImplementing(final String anInterfaceName)
	{

		this.implementsThese.addElement(anInterfaceName);
	}

	/**
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.20059:26:05</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 */
	protected void createFiles()
	{

		if(getRootGenerator().isDebug())
		{
			System.out.println(Thread.currentThread().getName() + ' '
				+ getClass().getName() + ".createFiles() start"); //$NON-NLS-1$
		}
		try
		{
			createFolder();
			final String fileName = GenerateTBOs.makeTitleCase(getTypeName(),
				false);
			setInterfaceName('I' + getRootGenerator().getPrefix() + fileName);
			writeInterface();
			if(getRootGenerator().isClass())
			{
				setClassName(getRootGenerator().getPrefix() + fileName);
				writeClass();
			}
		} catch(final IOException ioex)
		{

			System.out
				.println(Thread.currentThread()
					+ " Failure creating target folder. " + ioex.getMessage().trim()); //$NON-NLS-1$
		}
		if(getRootGenerator().isDebug())
		{
			System.out.println(Thread.currentThread().getName() + ' '
				+ getClass().getName() + ".createFiles() end"); //$NON-NLS-1$
		}
	}

	/**
	 * Create the target package folder.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.200511:36:19</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 */
	protected void createFolder()
	{

		if(getRootGenerator().isDebug())
		{
			System.out.println(Thread.currentThread().getName() + ' '
				+ getClass().getName() + ".createFolder() start"); //$NON-NLS-1$
		}
		if(getRootGenerator().isDeepPackages())
		{
			String packageName = GenerateTBOs.makeTitleCase(getTypeName(),
				false).toLowerCase();
			if(GenerateTBOs.RESERVED.contains(packageName))
			{
				packageName = packageName
					+ GenerateTBOs.makeTitleCase(
						getRootGenerator().getPrefix(), false).toLowerCase();
			}
			if(getSuperType() == null)
			{
				setPackage(getRootGenerator().getPackage() + '.' + packageName);

			} else
			{
				setPackage(getSuperType().getPackage() + '.' + packageName);

			}
		} else
		{
			setPackage(getRootGenerator().getPackage());
		}
		setTargetFolder(new File(getRootGenerator().getFolder()
			.getAbsolutePath()
			+ File.separatorChar
			+ getPackage().replace('.', File.separatorChar)));
		getTargetFolder().mkdirs();
		if(getRootGenerator().isDebug())
		{
			System.out.println(Thread.currentThread().getName() + ' '
				+ getClass().getName() + ".createFolder() end"); //$NON-NLS-1$
		}
	}

	/**
	 * Construct the type information.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.20058:55:21</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 */
	protected void gatherInformation()
	{

		if(getRootGenerator().isDebug())
		{
			System.out.println(Thread.currentThread().getName() + ' '
				+ getClass().getName() + ".gatherInformation() start"); //$NON-NLS-1$
		}
		IDfCollection results = null;
		IDfSession session = null;
		final IDfQuery query = new DfQuery();
		if(getTypeName() == null)
		{
			addAttribute(IPersistentObject.I_IS_REPLICA, new String[] {
				"Boolean", String.valueOf(IDfAttr.DM_BOOLEAN), //$NON-NLS-1$
				"", String.valueOf(false)}); //$NON-NLS-1$
			addAttribute(IPersistentObject.I_VSTAMP, new String[] {
				"Integer", String.valueOf(IDfAttr.DM_INTEGER), "", //$NON-NLS-1$ //$NON-NLS-2$
				String.valueOf(false)});
			addAttribute(IPersistentObject.R_OBJECT_ID, new String[] {
				"ID", String.valueOf(IDfAttr.DM_ID), "", //$NON-NLS-1$ //$NON-NLS-2$
				String.valueOf(false)});
		} else
		{
			final StringBuffer attributeDQL = new StringBuffer(
				"select distinct ").append(IType.ATTR_NAME) //$NON-NLS-1$
				.append(" , ").append(IType.ATTR_TYPE).append(" , ").append(IType.ATTR_LENGTH).append(" , ").append( //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					IType.ATTR_REPEATING)
				.append(" from ").append(IType.TYPE_DM_TYPE).append(" where ").append( //$NON-NLS-1$ //$NON-NLS-2$
					IType.NAME)
				.append(" = '").append(getTypeName()).append("' order by 1"); //$NON-NLS-1$ //$NON-NLS-2$
			query.setDQL(attributeDQL.toString());
			synchronized(getRootGenerator())
			{
				try
				{
					session = getRootGenerator().getSession();
					if(getRootGenerator().isDebug())
					{
						System.out.println(Thread.currentThread().getName()
							+ ' ' + getClass().getName()
							+ ".gatherInformation() " + query.getDQL()); //$NON-NLS-1$
					}
					results = query
						.execute(session, IDfQuery.DF_EXECREAD_QUERY);
					while(results.next())
					{
						Thread.yield();
						final String attributeName = results
							.getString(IType.ATTR_NAME);
						if(!hasAttribute(attributeName))
						{
							addAttribute(attributeName,
								getAttributeInfo(results));
						}
					}
				} catch(DfException dex)
				{
					if(getRootGenerator().isDebug())
					{
						System.out
							.println(Thread.currentThread().getName()
								+ ' '
								+ getClass().getName()
								+ ".gatherInformation() Failed to run query. " + dex.getMessage().trim()); //$NON-NLS-1$
					}
				} catch(InterruptedException iex)
				{
					System.out.println(Thread.currentThread().getName()
						+ " Thread interrupted. " //$NON-NLS-1$
						+ iex.getMessage().trim());
				} finally
				{
					getRootGenerator().setSession(session);
					try
					{
						results.close();
					} catch(DfException dex)
					{
						System.out.println(Thread.currentThread().getName()
							+ " Failed to close collection. " //$NON-NLS-1$
							+ dex.getMessage().trim());
					}
				}
				getRootGenerator().notifyAll();
			}
		}
		final StringBuffer typeDQL = new StringBuffer("select distinct ").append(IType.NAME).append(" from ").append( //$NON-NLS-1$ //$NON-NLS-2$
				IType.TYPE_DM_TYPE).append(" where ").append(IType.SUPER_NAME); //$NON-NLS-1$
		if(getTypeName() == null)
		{
			setTypeName("dm_persistent"); //$NON-NLS-1$
			typeDQL.append(" is nullstring"); //$NON-NLS-1$
		} else
		{
			typeDQL.append(" = '").append(getTypeName()).append('\''); //$NON-NLS-1$
		}
		typeDQL.append(" order by 1"); //$NON-NLS-1$
		query.setDQL(typeDQL.toString());
		final LinkedList<String> types = new LinkedList<String>();
		synchronized(getRootGenerator())
		{
			try
			{
				session = getRootGenerator().getSession();
				if(getRootGenerator().isDebug())
				{
					System.out.println(Thread.currentThread().getName() + ' '
						+ getClass().getName()
						+ ".gatherInformation() " + query.getDQL()); //$NON-NLS-1$
				}
				results = query.execute(session, IDfQuery.DF_EXECREAD_QUERY);
				while(results.next())
				{
					Thread.yield();
					types.add(results.getString(IType.NAME));
				}
			} catch(DfException dex)
			{
				System.out.println(Thread.currentThread().getName()
					+ " Failed to run query. " //$NON-NLS-1$
					+ dex.getMessage().trim());
			} catch(InterruptedException iex)
			{
				System.out.println(Thread.currentThread().getName()
					+ " Tread interrupted. " + iex.getMessage().trim()); //$NON-NLS-1$
			} finally
			{
				getRootGenerator().setSession(session);
				try
				{
					results.close();
				} catch(DfException dex)
				{
					System.out.println(Thread.currentThread().getName()
						+ " Failed to close collection. " //$NON-NLS-1$
						+ dex.getMessage().trim());
				}
			}
			getRootGenerator().notifyAll();
		}
		createFiles();
		setSubThreads(new ThreadGroup(GenerateTBOs.THREAD_NAME_PREFIX
			+ "group_" + getTypeName() //$NON-NLS-1$
			+ GenerateTBOs.THREAD_NAME_SUFFIX));
		for(final String type: types)
		{
			new TypeNode(getRootGenerator(), this, type, getSubThreads());
		}
		if(getRootGenerator().isDebug())
		{
			System.out.println(Thread.currentThread().getName() + ' '
				+ getClass().getName() + ".gatherInformation() end"); //$NON-NLS-1$
		}
	}

	/**
	 * Get attribute information.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.2005 16:46:11</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param results
	 * @return the attribute information.
	 * @throws DfException
	 */
	protected String[] getAttributeInfo(final IDfCollection results) throws DfException
	{

		final String[] attributeInfo = new String[4];
		final int type = results.getInt(IType.ATTR_TYPE);
		switch(type)
		{
			case IDfAttr.DM_BOOLEAN:
				attributeInfo[0] = "boolean"; //$NON-NLS-1$
				break;
			case IDfAttr.DM_DOUBLE:
				attributeInfo[0] = "double"; //$NON-NLS-1$
				break;
			case IDfAttr.DM_ID:
				attributeInfo[0] = "IDfId"; //$NON-NLS-1$
				break;
			case IDfAttr.DM_INTEGER:
				attributeInfo[0] = "int"; //$NON-NLS-1$
				break;
			case IDfAttr.DM_STRING:
				attributeInfo[0] = "String"; //$NON-NLS-1$
				break;
			case IDfAttr.DM_TIME:
				attributeInfo[0] = "IDfTime"; //$NON-NLS-1$
				break;
			default:
				attributeInfo[0] = "Undefined"; //$NON-NLS-1$
				break;
		}
		attributeInfo[1] = String.valueOf(type);
		attributeInfo[2] = results.getString(IType.ATTR_LENGTH);
		attributeInfo[3] = String.valueOf(results
			.getBoolean(IType.ATTR_REPEATING));
		return attributeInfo;

	}

	/**
	 * Get the class name.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.200511:52:18</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @return The class name.
	 */

	protected String getClassName()
	{

		return this.className;
	}

	/**
	 * Get the interface name.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.200511:52:06</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @return The interface name.
	 */
	protected String getInterfaceName()
	{

		return this.interfaceName;
	}

	/**
	 * Get this types target package.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.200511:39:58</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @return the target package.
	 */
	protected String getPackage()
	{

		return this.filePackage;
	}

	/**
	 * Get the runner.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.2005 17:14:26</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @return the running thread.
	 */
	protected Thread getRunner()
	{

		return this.runner;
	}

	/**
	 * Get the sub threads.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.20058:53:08</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @return threads started by this thread.
	 */
	protected ThreadGroup getSubThreads()
	{

		return this.subThreads;
	}

	/**
	 * Get the target folder.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.200511:43:14</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @return The target folder.
	 */
	protected File getTargetFolder()
	{

		return this.targetFolder;
	}

	/**
	 * Is the given class been extended by a super class.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.2005 10:05:40</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param testClassName
	 *            name of the test class.
	 * @return true if the given class has already been implemented.
	 */
	protected boolean isExtending(final String testClassName)
	{

		if(((getSuperType() != null) && (getSuperType()
			.isExtending(testClassName)))
			|| ((getExtendsFull() != null) && (getExtendsFull()
				.equals(testClassName))))
		{
			return true;
		}
		return false;
	}

	/**
	 * Is the given interface already implemented.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.200515:02:10</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param anInterfaceName
	 *            name of the test interface.
	 * @return true if the interface is already implemented.
	 */
	protected boolean isImplementing(final String anInterfaceName)
	{

		if(((getSuperType() != null) && (getSuperType()
			.isImplementing(anInterfaceName)))
			|| (this.implementsThese.contains(anInterfaceName)))
		{
			return true;
		}
		return false;
	}

	/**
	 * Set the worker thread.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.2005 17:14:55</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param aRunner
	 *            the thread.
	 */
	protected void setRunner(final Thread aRunner)
	{

		this.runner = aRunner;
	}

	/**
	 * Set the sub threads.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.20058:53:46</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param group
	 *            new (empty) sub thread group.
	 */
	protected void setSubThreads(final ThreadGroup group)
	{

		this.subThreads = group;
	}

	/**
	 * Set the super type.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.2005 15:33:53</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param superType
	 *            The superType to set
	 */
	protected void setSuperType(TypeNode superType)
	{

		this.superType = superType;
	}

	/**
	 * Set the types name.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.2005 15:33:53</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param typeName
	 *            The typeName to set
	 */
	protected void setTypeName(String typeName)
	{

		this.typeName = typeName;
	}

	/**
	 * Get the extended classes name.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.2005 9:41:32</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @return the class name.
	 */
	private String getExtends()
	{

		return this.extendsName;
	}

	/**
	 * Get the fully qualified extended class name.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.2005 20:09:32</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @return The fully qualified extended class name.
	 */
	private String getExtendsFull()
	{

		return this.extendsFull;
	}

	/**
	 * Get the real class name.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 27.3.2005 23:06:48</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param dataType
	 * @return The fully qualified class name.
	 */
	private String getFullClassIdentifier(final int dataType)
	{

		switch(dataType)
		{
			case IDfAttr.DM_BOOLEAN:
				return "boolean"; //$NON-NLS-1$
			case IDfAttr.DM_DOUBLE:
				return "double"; //$NON-NLS-1$
			case IDfAttr.DM_ID:
				return "com.documentum.fc.common.IDfId"; //$NON-NLS-1$
			case IDfAttr.DM_INTEGER:
				return "int"; //$NON-NLS-1$
			case IDfAttr.DM_STRING:
				return "java.lang.String"; //$NON-NLS-1$
			case IDfAttr.DM_TIME:
				return "com.documentum.fc.common.IDfTime"; //$NON-NLS-1$
			default:
				return "java.lang.Object"; //$NON-NLS-1$
		}
	}

	/**
	 * Get the root generator.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.200510:41:47</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @return the root.
	 */
	private GenerateTBOs getRootGenerator()
	{

		return this.rootGenerator;
	}

	/**
	 * Does this node represent a custom type.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 27.3.2005 13:39:58</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @return true if the types is a custom type.
	 */
	private boolean isCustomType()
	{

		if(getTypeName().startsWith(IPersistentObject.TYPE_PREFIX)
			|| getTypeName().startsWith(IPersistentObject.TYPE_PREFIX_CONTENT)
			|| getTypeName().startsWith(IPersistentObject.TYPE_PREFIX_INTERNAL))
		{
			return false;
		}
		return true;
	}

	/**
	 * Set the class name.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.200511:52:14</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param aClassName
	 *            The class name.
	 */
	private void setClassName(final String aClassName)
	{

		this.className = aClassName;
	}

	/**
	 * Set the extends name
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.2005 9:43:39</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param aClassName
	 *            the name of the extended class.
	 */
	private void setExtends(final String aClassName)
	{

		this.extendsName = aClassName;
	}

	/**
	 * Set the whole extended package and class name.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.2005 20:09:39</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param aClassName
	 *            the fully qualified class name.
	 */
	private void setExtendsFull(final String aClassName)
	{

		this.extendsFull = aClassName;
	}

	/**
	 * Set the interface name.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.200511:52:10</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param anInterfaceName
	 *            the interface name.
	 */
	private void setInterfaceName(final String anInterfaceName)
	{

		this.interfaceName = anInterfaceName;
	}

	/**
	 * Set this types target package.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.200511:39:34</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param aPackage
	 *            the target package.
	 */
	private void setPackage(final String aPackage)
	{

		this.filePackage = aPackage;
	}

	/**
	 * Set the root generator.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.200510:41:43</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param aRootGenerator
	 *            the root.
	 */
	private void setRootGenerator(final GenerateTBOs aRootGenerator)
	{

		this.rootGenerator = aRootGenerator;
	}

	/**
	 * Set the target folder.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.200511:42:28</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param aTargetFolder
	 *            the new target folder.
	 */
	private void setTargetFolder(final File aTargetFolder)
	{

		this.targetFolder = aTargetFolder;
	}

	/**
	 * Write the easy access methods for the custom attributes.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 29.3.2005 0:45:01</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param writer
	 *            the output.
	 * @param attributeName
	 *            name of the attribute.
	 * @param attributeInfo
	 *            information about the attribute.
	 * @param isImplementation
	 *            true for a class, false for interface.
	 */
	private void writeAttributeMethods(final PrintWriter writer,
										final String attributeName,
										final String[] attributeInfo,
										final boolean isImplementation)
	{

		final String partialMethodName = GenerateTBOs.makeTitleCase(
			attributeName, false);
		final int attributeDataType = Integer.parseInt(attributeInfo[1]);
		final String fullClassIdentifier = getFullClassIdentifier(attributeDataType);
		final String upperAttributeName = attributeName.toUpperCase();
		// attributeInfo[0] = "Undefined";
		// attributeInfo[1] = String.valueOf(type);
		// attributeInfo[2] = results.getString(IType.ATTR_LENGTH);
		// attributeInfo[3] =
		// String.valueOf(results.getBoolean(IType.ATTR_REPEATING));

		if(Boolean.getBoolean(attributeInfo[3]))
		{
			/* Repeating value. */
			writeMethodCommentStart(writer,
				"Appender for " + attributeName + '.'); //$NON-NLS-1$
			writer.println("\t * @param value"); //$NON-NLS-1$
			writer.print("\t * \tThe new value to append into '"); //$NON-NLS-1$
			writer.print(attributeName);
			writer.print("'."); //$NON-NLS-1$
			writer.println();
			writer.println("\t * @throws DfException"); //$NON-NLS-1$
			writer.print("\t * \tIf the '"); //$NON-NLS-1$
			writer.print(attributeName);
			writer.print("' value can't be appended."); //$NON-NLS-1$
			writer.println();
			if(isImplementation)
			{
				writer.print("\t * @see "); //$NON-NLS-1$
				writer.print(getPackage());
				writer.print('.');
				writer.print(getInterfaceName());
				writer.print("#append"); //$NON-NLS-1$
				writer.print(partialMethodName);
				writer.print('(');
				writer.print(fullClassIdentifier);
				writer.print(')');
				writer.println();
			}
			writer.println("\t */"); //$NON-NLS-1$
			writer.print("\tpublic "); //$NON-NLS-1$
			if(!isImplementation)
			{
				writer.print("abstract "); //$NON-NLS-1$
			}
			writer.print("void append"); //$NON-NLS-1$
			writer.print(partialMethodName);
			writer.print("(final "); //$NON-NLS-1$
			writer.print(attributeInfo[0]);
			writer.print(" value) throws DfException"); //$NON-NLS-1$
			if(isImplementation)
			{
				writeSetterMethodBody(writer,
					"append", upperAttributeName, attributeDataType, null); //$NON-NLS-1$
			} else
			{
				writer.print(';');
				writer.println();
			}

			writeMethodCommentStart(writer,
				"Value finder for " + attributeName + '.'); //$NON-NLS-1$
			writer.println("\t * @param value"); //$NON-NLS-1$
			writer.print("\t * \tThe value to find in '"); //$NON-NLS-1$
			writer.print(attributeName);
			writer.print("'."); //$NON-NLS-1$
			writer.println();
			writer.print("\t * @return The index position of the given '"); //$NON-NLS-1$
			writer.print(attributeName);
			writer.print("' value. -1 is returned if the value isn't found."); //$NON-NLS-1$
			writer.println();
			writer.println("\t * @throws DfException"); //$NON-NLS-1$
			writer.print("\t * \tIf the '"); //$NON-NLS-1$
			writer.print(attributeName);
			writer.print("' value can't be searched."); //$NON-NLS-1$
			writer.println();
			if(isImplementation)
			{
				writer.print("\t * @see "); //$NON-NLS-1$
				writer.print(getPackage());
				writer.print('.');
				writer.print(getInterfaceName());
				writer.print("#find"); //$NON-NLS-1$
				writer.print(partialMethodName);
				writer.print('(');
				writer.print(fullClassIdentifier);
				writer.print(')');
				writer.println();
			}
			writer.println("\t */"); //$NON-NLS-1$
			writer.print("\tpublic "); //$NON-NLS-1$
			if(!isImplementation)
			{
				writer.print("abstract "); //$NON-NLS-1$
			}
			writer.print("int find"); //$NON-NLS-1$
			writer.print(partialMethodName);
			writer.print("(final "); //$NON-NLS-1$
			writer.print(attributeInfo[0]);
			writer.print(" value) throws DfException"); //$NON-NLS-1$
			if(isImplementation)
			{
				writer.println();
				writer.println("\t{"); //$NON-NLS-1$
				writer.print("\t\treturn find"); //$NON-NLS-1$
				if(attributeDataType == IDfAttr.DM_BOOLEAN)
				{
					writer.print("Boolean"); //$NON-NLS-1$
				} else if(attributeDataType == IDfAttr.DM_DOUBLE)
				{
					writer.print("Double"); //$NON-NLS-1$
				} else if(attributeDataType == IDfAttr.DM_ID)
				{
					writer.print("Id"); //$NON-NLS-1$
				} else if(attributeDataType == IDfAttr.DM_INTEGER)
				{
					writer.print("Int"); //$NON-NLS-1$
				} else if(attributeDataType == IDfAttr.DM_TIME)
				{
					writer.print("Time"); //$NON-NLS-1$
				} else
				{
					writer.print("String"); //$NON-NLS-1$
				}
				writer.print('(');
				writer.print(getInterfaceName());
				writer.print('.');
				writer.print(upperAttributeName);
				writer.print(", value);"); //$NON-NLS-1$
				writer.println();
				writer.println("\t}"); //$NON-NLS-1$
			} else
			{
				writer.print(';');
				writer.println();
			}
			if(attributeDataType == IDfAttr.DM_STRING)
			{
				writeMethodCommentStart(writer,
					"Get all values from " + attributeName + '.'); //$NON-NLS-1$
				writer.println("\t * @param separator"); //$NON-NLS-1$
				writer.println("\t * \tThe value separator String."); //$NON-NLS-1$
				writer.print("\t * @return All values found in '"); //$NON-NLS-1$
				writer.print(attributeName);
				writer.print("' separated by 'separator' values."); //$NON-NLS-1$
				writer.println();
				writer.println("\t * @throws DfException"); //$NON-NLS-1$
				writer.print("\t * \tIf the '"); //$NON-NLS-1$
				writer.print(attributeName);
				writer.print("' values can't be returned."); //$NON-NLS-1$
				writer.println();
				if(isImplementation)
				{
					writer.print("\t * @see "); //$NON-NLS-1$
					writer.print(getPackage());
					writer.print('.');
					writer.print(getInterfaceName());
					writer.print("#getAll"); //$NON-NLS-1$
					writer.print(partialMethodName);
					writer.print("Values("); //$NON-NLS-1$
					writer.print(fullClassIdentifier);
					writer.print(')');
					writer.println();
				}
				writer.println("\t */"); //$NON-NLS-1$
				writer.print("\tpublic "); //$NON-NLS-1$
				if(!isImplementation)
				{
					writer.print("abstract "); //$NON-NLS-1$
				}
				writer.print("String getAll"); //$NON-NLS-1$
				writer.print(partialMethodName);
				writer
					.print("Values(final String separator) throws DfException"); //$NON-NLS-1$
				if(isImplementation)
				{
					writer.println();
					writer.println("\t{"); //$NON-NLS-1$
					writer.print("\t\treturn getAllRepeatingStrings("); //$NON-NLS-1$
					writer.print(getInterfaceName());
					writer.print('.');
					writer.print(upperAttributeName);
					writer.print(", separator);"); //$NON-NLS-1$
					writer.println();
					writer.println("\t}"); //$NON-NLS-1$
				} else
				{
					writer.print(';');
					writer.println();
				}
			}
			writeMethodCommentStart(writer,
				"Value getter for " + attributeName + '.'); //$NON-NLS-1$
			writer.println("\t * @param valueIndex"); //$NON-NLS-1$
			writer.print("\t * \tThe index of the '"); //$NON-NLS-1$
			writer.print(attributeName);
			writer.print("' to return."); //$NON-NLS-1$
			writer.println();
			writer.print("\t * @return The value found in the '"); //$NON-NLS-1$
			writer.print(attributeName);
			writer.print("'s' given index."); //$NON-NLS-1$
			writer.println();
			writer.println("\t * @throws DfException"); //$NON-NLS-1$
			writer.print("\t * \tIf the '"); //$NON-NLS-1$
			writer.print(attributeName);
			writer.print("' value can't be returned from the given index."); //$NON-NLS-1$
			writer.println();
			if(isImplementation)
			{
				writer.print("\t * @see "); //$NON-NLS-1$
				writer.print(getPackage());
				writer.print('.');
				writer.print(getInterfaceName());
				writer.print("#get"); //$NON-NLS-1$
				writer.print(partialMethodName);
				writer.print("(int)"); //$NON-NLS-1$
				writer.println();
			}
			writer.println("\t */"); //$NON-NLS-1$
			writer.print("\tpublic "); //$NON-NLS-1$
			if(!isImplementation)
			{
				writer.print("abstract "); //$NON-NLS-1$
			}
			writer.print(attributeInfo[0]);
			writer.print(" get"); //$NON-NLS-1$
			writer.print(partialMethodName);
			writer.print("(final int valueIndex) throws DfException"); //$NON-NLS-1$
			if(isImplementation)
			{
				writer.println();
				writer.println("\t{"); //$NON-NLS-1$
				writer.print("\t\treturn getRepeating"); //$NON-NLS-1$
				if(attributeDataType == IDfAttr.DM_BOOLEAN)
				{
					writer.print("Boolean"); //$NON-NLS-1$
				} else if(attributeDataType == IDfAttr.DM_DOUBLE)
				{
					writer.print("Double"); //$NON-NLS-1$
				} else if(attributeDataType == IDfAttr.DM_ID)
				{
					writer.print("Id"); //$NON-NLS-1$
				} else if(attributeDataType == IDfAttr.DM_INTEGER)
				{
					writer.print("Int"); //$NON-NLS-1$
				} else if(attributeDataType == IDfAttr.DM_TIME)
				{
					writer.print("Time"); //$NON-NLS-1$
				} else
				{
					writer.print("String"); //$NON-NLS-1$
				}
				writer.print('(');
				writer.print(getInterfaceName());
				writer.print('.');
				writer.print(upperAttributeName);
				writer.print(", valueIndex);"); //$NON-NLS-1$
				writer.println();
				writer.println("\t}"); //$NON-NLS-1$
			} else
			{
				writer.print(';');
				writer.println();
			}
			writeMethodCommentStart(writer,
				"Get the amount of '" + attributeName + "' values."); //$NON-NLS-1$ //$NON-NLS-2$
			writer.print("\t * @return The amount of '"); //$NON-NLS-1$
			writer.print(attributeName);
			writer.print("' values."); //$NON-NLS-1$
			writer.println();
			writer.println("\t * @throws DfException"); //$NON-NLS-1$
			writer.print("\t * \tIf the '"); //$NON-NLS-1$
			writer.print(attributeName);
			writer.print("' values can't be countent."); //$NON-NLS-1$
			writer.println();
			if(isImplementation)
			{
				writer.print("\t * @see "); //$NON-NLS-1$
				writer.print(getPackage());
				writer.print('.');
				writer.print(getInterfaceName());
				writer.print("#count"); //$NON-NLS-1$
				writer.print(partialMethodName);
				writer.print("()"); //$NON-NLS-1$
				writer.println();
			}
			writer.println("\t */"); //$NON-NLS-1$
			writer.print("\tpublic "); //$NON-NLS-1$
			if(!isImplementation)
			{
				writer.print("abstract "); //$NON-NLS-1$
			}
			writer.print("int count"); //$NON-NLS-1$
			writer.print(partialMethodName);
			writer.print("() throws DfException"); //$NON-NLS-1$
			if(isImplementation)
			{
				writer.println();
				writer.println("\t{"); //$NON-NLS-1$
				writer.print("\t\treturn getValueCount("); //$NON-NLS-1$
				writer.print(getInterfaceName());
				writer.print('.');
				writer.print(upperAttributeName);
				writer.print(");"); //$NON-NLS-1$
				writer.println();
				writer.println("\t}"); //$NON-NLS-1$
			} else
			{
				writer.print(';');
				writer.println();
			}
			writeMethodCommentStart(writer,
				"Inserter value for " + attributeName + '.'); //$NON-NLS-1$
			writer.println("\t * @param valueIndex"); //$NON-NLS-1$
			writer.print("\t * \tIndex position of the new '"); //$NON-NLS-1$
			writer.print(attributeName);
			writer.print("' value."); //$NON-NLS-1$
			writer.println();
			writer.println("\t * @param value"); //$NON-NLS-1$
			writer.print("\t * \t the new '"); //$NON-NLS-1$
			writer.print(attributeName);
			writer.print("' value."); //$NON-NLS-1$
			writer.println();
			writer.println("\t * @throws DfException"); //$NON-NLS-1$
			writer.print("\t * \tIf the '"); //$NON-NLS-1$
			writer.print(attributeName);
			writer.print("' value can't be set."); //$NON-NLS-1$
			writer.println();
			if(isImplementation)
			{
				writer.print("\t * @see "); //$NON-NLS-1$
				writer.print(getPackage());
				writer.print('.');
				writer.print(getInterfaceName());
				writer.print("#insert"); //$NON-NLS-1$
				writer.print(partialMethodName);
				writer.print("(int, "); //$NON-NLS-1$
				writer.print(fullClassIdentifier);
				writer.print(')');
				writer.println();
			}
			writer.println("\t */"); //$NON-NLS-1$
			writer.print("\tpublic "); //$NON-NLS-1$
			if(!isImplementation)
			{
				writer.print("abstract "); //$NON-NLS-1$
			}
			writer.print("void insert"); //$NON-NLS-1$
			writer.print(partialMethodName);
			writer.print("(final int valueIndex, final "); //$NON-NLS-1$
			writer.print(attributeInfo[0]);
			writer.print(" value) throws DfException"); //$NON-NLS-1$
			if(isImplementation)
			{
				writeSetterMethodBody(
					writer,
					"insert", upperAttributeName, attributeDataType, "valueIndex"); //$NON-NLS-1$ //$NON-NLS-2$
			} else
			{
				writer.print(';');
				writer.println();
			}
			writeMethodCommentStart(writer,
				"Remove a values from " + attributeName + '.'); //$NON-NLS-1$
			writer.println("\t * @param valueIndex"); //$NON-NLS-1$
			writer.print("\t * \tIndex position of the '"); //$NON-NLS-1$
			writer.print(attributeName);
			writer.print("' value to remove."); //$NON-NLS-1$
			writer.println();
			writer.println("\t * @throws DfException"); //$NON-NLS-1$
			writer.print("\t * \tIf the '"); //$NON-NLS-1$
			writer.print(attributeName);
			writer.print("' value can't be removed."); //$NON-NLS-1$
			writer.println();
			if(isImplementation)
			{
				writer.print("\t * @see "); //$NON-NLS-1$
				writer.print(getPackage());
				writer.print('.');
				writer.print(getInterfaceName());
				writer.print("#remove"); //$NON-NLS-1$
				writer.print(partialMethodName);
				writer.print("(int)"); //$NON-NLS-1$
				writer.println();
			}
			writer.println("\t */"); //$NON-NLS-1$
			writer.print("\tpublic "); //$NON-NLS-1$
			if(!isImplementation)
			{
				writer.print("abstract "); //$NON-NLS-1$
			}
			writer.print("void remove"); //$NON-NLS-1$
			writer.print(partialMethodName);
			writer.print("(final int valueIndex) throws DfException"); //$NON-NLS-1$
			if(isImplementation)
			{
				writer.println();
				writer.println("\t{"); //$NON-NLS-1$
				writer.print("\t\t remove("); //$NON-NLS-1$
				writer.print(getInterfaceName());
				writer.print('.');
				writer.print(upperAttributeName);
				writer.print(", valueIndex);"); //$NON-NLS-1$
				writer.println();
				writer.println("\t}"); //$NON-NLS-1$
			} else
			{
				writer.print(';');
				writer.println();
			}
			writeMethodCommentStart(writer,
				"Remove all values from " + attributeName + '.'); //$NON-NLS-1$
			writer.println("\t * @throws DfException"); //$NON-NLS-1$
			writer.print("\t * \tIf the '"); //$NON-NLS-1$
			writer.print(attributeName);
			writer.print("' values can't be removed."); //$NON-NLS-1$
			writer.println();
			if(isImplementation)
			{
				writer.print("\t * @see "); //$NON-NLS-1$
				writer.print(getPackage());
				writer.print('.');
				writer.print(getInterfaceName());
				writer.print("#removeAll"); //$NON-NLS-1$
				writer.print(partialMethodName);
				writer.print("()"); //$NON-NLS-1$
				writer.println();
			}
			writer.println("\t */"); //$NON-NLS-1$
			writer.print("\tpublic "); //$NON-NLS-1$
			if(!isImplementation)
			{
				writer.print("abstract "); //$NON-NLS-1$
			}
			writer.print("void removeAll"); //$NON-NLS-1$
			writer.print(partialMethodName);
			writer.print("() throws DfException"); //$NON-NLS-1$
			if(isImplementation)
			{
				writer.println();
				writer.println("\t{"); //$NON-NLS-1$
				writer.print("\t\t removeAll("); //$NON-NLS-1$
				writer.print(getInterfaceName());
				writer.print('.');
				writer.print(upperAttributeName);
				writer.print(");"); //$NON-NLS-1$
				writer.println();
				writer.println("\t}"); //$NON-NLS-1$
			} else
			{
				writer.print(';');
				writer.println();
			}

			writeMethodCommentStart(writer,
				"Set a value for " + attributeName + '.'); //$NON-NLS-1$
			writer.println("\t * @param valueIndex"); //$NON-NLS-1$
			writer.print("\t * \tIndex position of the new '"); //$NON-NLS-1$
			writer.print(attributeName);
			writer.print("' value."); //$NON-NLS-1$
			writer.println();
			writer.println("\t * @param value"); //$NON-NLS-1$
			writer.print("\t * \t the new '"); //$NON-NLS-1$
			writer.print(attributeName);
			writer.print("' value."); //$NON-NLS-1$
			writer.println();
			writer.println("\t * @throws DfException"); //$NON-NLS-1$
			writer.print("\t * \tIf the '"); //$NON-NLS-1$
			writer.print(attributeName);
			writer.print("' value can't be set."); //$NON-NLS-1$
			writer.println();
			if(isImplementation)
			{
				writer.print("\t * @see "); //$NON-NLS-1$
				writer.print(getPackage());
				writer.print('.');
				writer.print(getInterfaceName());
				writer.print("#setRepeating"); //$NON-NLS-1$
				writer.print(partialMethodName);
				writer.print("(int, "); //$NON-NLS-1$
				writer.print(fullClassIdentifier);
				writer.print(')');
				writer.println();
			}
			writer.println("\t */"); //$NON-NLS-1$
			writer.print("\tpublic "); //$NON-NLS-1$
			if(!isImplementation)
			{
				writer.print("abstract "); //$NON-NLS-1$
			}
			writer.print("void setRepeating"); //$NON-NLS-1$
			writer.print(partialMethodName);
			writer.print("(final int valueIndex, final "); //$NON-NLS-1$
			writer.print(attributeInfo[0]);
			writer.print(" value) throws DfException"); //$NON-NLS-1$
			if(isImplementation)
			{
				writeSetterMethodBody(
					writer,
					"setRepeating", upperAttributeName, attributeDataType, "valueIndex"); //$NON-NLS-1$ //$NON-NLS-2$
			} else
			{
				writer.print(';');
				writer.println();
			}

			// ########################################################################################################
			writeMethodCommentStart(writer,
				"Truncate values from " + attributeName + '.'); //$NON-NLS-1$
			writer.println("\t * @param valueIndex"); //$NON-NLS-1$
			writer.print("\t * \tThe index position where the truncation of '"); //$NON-NLS-1$
			writer.print(attributeName);
			writer
				.print("' values begins. All values stored at this index and above will be removed '"); //$NON-NLS-1$
			writer.println();
			writer.println("\t * @throws DfException"); //$NON-NLS-1$
			writer.print("\t * \tIf the '"); //$NON-NLS-1$
			writer.print(attributeName);
			writer.print("' values couldn't be truncated."); //$NON-NLS-1$
			writer.println();
			if(isImplementation)
			{
				writer.print("\t * @see "); //$NON-NLS-1$
				writer.print(getPackage());
				writer.print('.');
				writer.print(getInterfaceName());
				writer.print("#truncate"); //$NON-NLS-1$
				writer.print(partialMethodName);
				writer.print("(int)"); //$NON-NLS-1$
				writer.println();
			}
			writer.println("\t */"); //$NON-NLS-1$
			writer.print("\tpublic "); //$NON-NLS-1$
			if(!isImplementation)
			{
				writer.print("abstract "); //$NON-NLS-1$
			}
			writer.print("void truncate"); //$NON-NLS-1$
			writer.print(partialMethodName);
			writer.print("(final int valueIndex) throws DfException"); //$NON-NLS-1$
			if(isImplementation)
			{
				writer.println();
				writer.println("\t{"); //$NON-NLS-1$
				writer.print("\t\t truncate("); //$NON-NLS-1$
				writer.print(getInterfaceName());
				writer.print('.');
				writer.print(upperAttributeName);
				writer.print(", valueIndex);"); //$NON-NLS-1$
				writer.println();
				writer.println("\t}"); //$NON-NLS-1$
			} else
			{
				writer.print(';');
				writer.println();
			}
		} else
		{
			/* Single value. */
			writeMethodCommentStart(writer, "Getter for " + attributeName + '.'); //$NON-NLS-1$
			writer.print("\t * @return The value of "); //$NON-NLS-1$
			writer.print(attributeName);
			writer.print('.');
			writer.println();
			writer.println("\t * @throws DfException"); //$NON-NLS-1$
			writer.print("\t * \tIf the '"); //$NON-NLS-1$
			writer.print(attributeName);
			writer.print("' value can't be returned."); //$NON-NLS-1$
			writer.println();
			if(isImplementation)
			{
				writer.print("\t * @see "); //$NON-NLS-1$
				writer.print(getPackage());
				writer.print('.');
				writer.print(getInterfaceName());
				writer.print("#get"); //$NON-NLS-1$
				writer.print(partialMethodName);
				writer.print("()"); //$NON-NLS-1$
				writer.println();
			}
			writer.println("\t */"); //$NON-NLS-1$
			writer.print("\tpublic "); //$NON-NLS-1$
			if(!isImplementation)
			{
				writer.print("abstract "); //$NON-NLS-1$
			}
			writer.print(attributeInfo[0]);
			writer.print(" get"); //$NON-NLS-1$
			writer.print(partialMethodName);
			writer.print("() throws DfException"); //$NON-NLS-1$
			if(isImplementation)
			{
				writer.println();
				writer.println("\t{"); //$NON-NLS-1$
				writer.print("\t\treturn get"); //$NON-NLS-1$
				if(attributeDataType == IDfAttr.DM_BOOLEAN)
				{
					writer.print("Boolean"); //$NON-NLS-1$
				} else if(attributeDataType == IDfAttr.DM_DOUBLE)
				{
					writer.print("Double"); //$NON-NLS-1$
				} else if(attributeDataType == IDfAttr.DM_ID)
				{
					writer.print("Id"); //$NON-NLS-1$
				} else if(attributeDataType == IDfAttr.DM_INTEGER)
				{
					writer.print("Int"); //$NON-NLS-1$
				} else if(attributeDataType == IDfAttr.DM_TIME)
				{
					writer.print("Time"); //$NON-NLS-1$
				} else
				{
					writer.print("String"); //$NON-NLS-1$
				}
				writer.print('(');
				writer.print(getInterfaceName());
				writer.print('.');
				writer.print(upperAttributeName);
				writer.print(");"); //$NON-NLS-1$
				writer.println();
				writer.println("\t}"); //$NON-NLS-1$
			} else
			{
				writer.print(';');
				writer.println();
			}
			writeMethodCommentStart(writer, "Setter for " + attributeName + '.'); //$NON-NLS-1$
			writer.println("\t * @param value"); //$NON-NLS-1$
			writer.print("\t * \tThe new value for '"); //$NON-NLS-1$
			writer.print(attributeName);
			writer.print("'."); //$NON-NLS-1$
			writer.println();
			writer.println("\t * @throws DfException"); //$NON-NLS-1$
			writer.print("\t * \tIf the '"); //$NON-NLS-1$
			writer.print(attributeName);
			writer.print("' value can't be set."); //$NON-NLS-1$
			writer.println();
			if(isImplementation)
			{
				writer.print("\t * @see "); //$NON-NLS-1$
				writer.print(getPackage());
				writer.print('.');
				writer.print(getInterfaceName());
				writer.print("#set"); //$NON-NLS-1$
				writer.print(partialMethodName);
				writer.print('(');
				writer.print(fullClassIdentifier);
				writer.print(')');
				writer.println();
			}
			writer.println("\t */"); //$NON-NLS-1$
			writer.print("\tpublic "); //$NON-NLS-1$
			if(!isImplementation)
			{
				writer.print("abstract "); //$NON-NLS-1$
			}
			writer.print("void set"); //$NON-NLS-1$
			writer.print(partialMethodName);
			writer.print("(final "); //$NON-NLS-1$
			writer.print(attributeInfo[0]);
			writer.print(" value) throws DfException"); //$NON-NLS-1$
			if(isImplementation)
			{
				writeSetterMethodBody(writer,
					"set", upperAttributeName, attributeDataType, null); //$NON-NLS-1$
			} else
			{
				writer.print(';');
				writer.println();
			}
		}
	}

	/**
	 * All the imports that this file has.
	 * 
	 * @since 2.0.0
	 */
	private Vector<String>	imports	= new Vector<String>();

	/**
	 * Get the Vector of imports made for this file.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 29.3.2005 9:42:33</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @return the imports in this file.
	 */
	private Vector<String> getImports()
	{

		return this.imports;
	}

	/**
	 * Set a new imports container.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 29.3.2005 9:44:02</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param anImports
	 *            the new impors container.
	 */
	private void setImports(final Vector<String> anImports)
	{

		this.imports = anImports;
	}

	/**
	 * Write the BOF / TBO implementation class.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 25.3.2005 11:33:55</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @throws IOException
	 * @since 2.0.0
	 */
	@SuppressWarnings("unchecked")
	private void writeClass() throws IOException
	{

		PrintWriter writer = null;
		boolean destroyFile = true;
		setImports(new Vector<String>());
		final File target = new File(getTargetFolder(), getClassName()
			+ ".java"); //$NON-NLS-1$
		try
		{
			boolean writeTBORequiredMethods = false;
			if(getRootGenerator().isDebug())
			{
				System.out.println(Thread.currentThread().getName() + ' '
					+ getClass().getName() + ".writeClass() start"); //$NON-NLS-1$
			}
			if(getSuperType() != null)
			{
				writer = new PrintWriter(new BufferedWriter(new FileWriter(
					target)));
				writeLogAndPackage(writer);
				Constructor[] classConstructors = null;
				synchronized(getRootGenerator())
				{

					IDfSession session = null;
					IDfPersistentObject object = null;
					boolean destroyObject = true;
					try
					{
						session = getRootGenerator().getSession();
						try
						{
							object = session.newObject(getTypeName());
						} catch(final DfException dex)
						{
							System.out.println(Thread.currentThread().getName()
								+ " Failure creating object. " //$NON-NLS-1$
								+ dex.getMessage().trim());
						}
						if(object == null)
						{
							destroyObject = false;
							object = session
								.getObjectByQualification(getTypeName());
						}
						if((object != null)
							&& (!Modifier.isFinal(object.getClass()
								.getModifiers())))
						{

							String implementedClass = object.getClass()
								.getName();
							if(getSuperType().isExtending(implementedClass))
							{
								if(getRootGenerator().isDeepPackages())
								{
									implementedClass = getSuperType()
										.getPackage()
										+ '.' + getSuperType().getClassName();
									writer.print("import "); //$NON-NLS-1$
									writer.print(implementedClass);
									getImports().addElement(implementedClass);
									writer.print(';');
									writer.println();
									setExtends(getSuperType().getClassName());
									setExtendsFull(implementedClass);
								}
							} else
							{
								writeTBORequiredMethods = true;
								writer.print("import "); //$NON-NLS-1$
								writer.print(getRootGenerator()
									.getPersistentInterfaceName());
								getImports().addElement(
									getRootGenerator()
										.getPersistentInterfaceName());
								writer.print(';');
								writer.println();
								writer.print("import "); //$NON-NLS-1$
								writer.print(implementedClass);
								getImports().addElement(implementedClass);
								writer.print(';');
								writer.println();
								setExtends(implementedClass
									.substring(implementedClass
										.lastIndexOf('.') + 1));
								setExtendsFull(implementedClass);
							}
							if(getRootGenerator().isCustomAttributeMethods()
								&& isCustomType())
							{
								boolean importException = false;
								for(final String attributeName: getAttributes()
									.keySet())
								{
									importException = true;
									final int dataType = Integer
										.parseInt(getAttributes().get(
											attributeName)[1]);
									if((dataType == IDfAttr.DM_ID)
										&& (!getImports().contains(
											"com.documentum.fc.common.IDfId"))) //$NON-NLS-1$
									{
										writer
											.println("import com.documentum.fc.common.IDfId;"); //$NON-NLS-1$
										getImports().addElement(
											"com.documentum.fc.common.IDfId"); //$NON-NLS-1$
									} else if((dataType == IDfAttr.DM_TIME)
										&& (!getImports().contains(
											"com.documentum.fc.common.IDfTime"))) //$NON-NLS-1$
									{
										writer
											.println("import com.documentum.fc.common.IDfTime;"); //$NON-NLS-1$
										getImports().addElement(
											"com.documentum.fc.common.IDfTime"); //$NON-NLS-1$
									}
								}
								if(importException)
								{
									writer
										.println("import com.documentum.fc.common.DfException;"); //$NON-NLS-1$
									getImports().addElement(
										"com.documentum.fc.common.DfException"); //$NON-NLS-1$
								}
							}

							classConstructors = object.getClass()
								.getConstructors();
							for(final Constructor costructor: classConstructors)
							{
								for(int parameterIndex = 0; parameterIndex < costructor
									.getGenericParameterTypes().length; parameterIndex++)
								{
									final String parameterClassName = costructor
										.getGenericParameterTypes()[parameterIndex]
										.getClass().getName();
									if(!getImports().contains(
										parameterClassName))
									{
										writer.print("import "); //$NON-NLS-1$
										writer.print(parameterClassName);
										writer.print(';');
										writer.println();
										getImports().addElement(
											parameterClassName);
									}
								}
								for(int exceptionIndex = 0; exceptionIndex < costructor
									.getExceptionTypes().length; exceptionIndex++)
								{
									final String exceptionsClassName = costructor
										.getExceptionTypes()[exceptionIndex]
										.getName();
									if(!getImports().contains(
										exceptionsClassName))
									{
										writer.print("import "); //$NON-NLS-1$
										writer.print(exceptionsClassName);
										writer.print(';');
										writer.println();
										getImports().addElement(
											exceptionsClassName);
									}
								}
							}
							destroyFile = false;
						}
					} catch(final InterruptedException iex)
					{
						System.out.println(Thread.currentThread().getName()
							+ " Failure getting session. " //$NON-NLS-1$
							+ iex.getMessage().trim());
					} catch(final DfException dex)
					{
						System.out.println(Thread.currentThread().getName()
							+ " Failure getting object. " //$NON-NLS-1$
							+ dex.getMessage().trim());
					} finally
					{
						if(destroyObject && (object != null))
						{
							try
							{
								object.revert();
								object.destroy();
							} catch(final DfException swallow)
							{
								System.out.println(Thread.currentThread()
									.getName()
									+ " Failure destroying object. " //$NON-NLS-1$
									+ swallow.getMessage().trim());
							}
						}
					}
					getRootGenerator().setSession(session);
					getRootGenerator().notifyAll();
				}
				if(!destroyFile)
				{
					System.out
						.println(Thread.currentThread().getName()
							+ ' '
							+ getClass().getName()
							+ ".writeClass() Writing class " + target.getAbsolutePath() + "."); //$NON-NLS-1$ //$NON-NLS-2$
					writer.println();
					writeClassComment(writer, "BOF / TBO implementation for"); //$NON-NLS-1$
					writer.print("public class "); //$NON-NLS-1$
					writer.print(getClassName());
					writer.println();
					writer.print("\textends"); //$NON-NLS-1$
					writer.println();
					writer.print("\t\t"); //$NON-NLS-1$
					writer.print(getExtends());
					writer.println();
					writer.print("\t\t\timplements"); //$NON-NLS-1$
					writer.println();
					writer.print("\t\t\t\t"); //$NON-NLS-1$
					writer.print(getInterfaceName());
					writer.println();
					writer.println('{');
					boolean emptyClassBody = true;

					for(final Constructor costructor: classConstructors)
					{
						final StringBuffer constructorBodyBuffer = new StringBuffer();
						writeMethodCommentStart(
							writer,
							"Required constructor for '" + getTypeName() //$NON-NLS-1$
								+ "' implementation since the super class has declared a similar constructor."); //$NON-NLS-1$

						constructorBodyBuffer
							.append("\tpublic ").append(getClassName()).append('('); //$NON-NLS-1$
						final String[] parameterNames = new String[costructor
							.getGenericParameterTypes().length];
						for(int parameterIndex = 0; parameterIndex < costructor
							.getGenericParameterTypes().length; parameterIndex++)
						{
							final Type parameterType = costructor
								.getGenericParameterTypes()[parameterIndex];
							if(parameterIndex > 0)
							{
								constructorBodyBuffer.append(", "); //$NON-NLS-1$
							}
							final String parameterClassName = parameterType
								.getClass().getName();
							constructorBodyBuffer.append("final "); //$NON-NLS-1$
							if(parameterClassName.lastIndexOf('.') > -1)
							{
								parameterNames[parameterIndex] = parameterClassName
									.substring(parameterClassName
										.lastIndexOf('.') + 1);
							} else
							{
								parameterNames[parameterIndex] = parameterClassName;
							}
							constructorBodyBuffer.append(
								parameterNames[parameterIndex]).append(' ');
							parameterNames[parameterIndex] = parameterNames[parameterIndex]
								.toLowerCase()
								+ parameterIndex;
							constructorBodyBuffer
								.append(parameterNames[parameterIndex]);
							writer.print("\t * @param "); //$NON-NLS-1$
							writer.print(parameterNames[parameterIndex]);
							writer.println();
							writer.print("\t * \t"); //$NON-NLS-1$
							writer.print(parameterClassName);
							writer.print(" initialization argument."); //$NON-NLS-1$
							writer.println();
						}
						constructorBodyBuffer.append(')');
						for(int exceptionIndex = 0; exceptionIndex < costructor
							.getExceptionTypes().length; exceptionIndex++)
						{
							if(exceptionIndex > 0)
							{
								constructorBodyBuffer.append(',').append(
									GenerateTBOs.NEWLINE).append("\t\t"); //$NON-NLS-1$
							} else
							{
								constructorBodyBuffer.append(" throws "); //$NON-NLS-1$
							}
							String exceptionClassName = costructor
								.getExceptionTypes()[exceptionIndex].getName();
							exceptionClassName = exceptionClassName
								.substring(exceptionClassName.lastIndexOf('.') + 1);
							constructorBodyBuffer.append(exceptionClassName);
							writer.print("\t * @throws "); //$NON-NLS-1$
							writer.print(exceptionClassName);
							writer.println();
							writer
								.println("\t * \tIf the class can't be initialized."); //$NON-NLS-1$
						}
						constructorBodyBuffer.append(GenerateTBOs.NEWLINE)
							.append("\t{").append(GenerateTBOs.NEWLINE) //$NON-NLS-1$
							.append("\t\tsuper("); //$NON-NLS-1$
						for(int parameterIndex = 0; parameterIndex < parameterNames.length; parameterIndex++)
						{
							if(parameterIndex > 0)
							{
								constructorBodyBuffer.append(", "); //$NON-NLS-1$
							}
							constructorBodyBuffer
								.append(parameterNames[parameterIndex]);
						}
						constructorBodyBuffer
							.append(");").append(GenerateTBOs.NEWLINE).append("\t}").append( //$NON-NLS-1$ //$NON-NLS-2$
								GenerateTBOs.NEWLINE);
						writer.println("\t */"); //$NON-NLS-1$
						writer.print(constructorBodyBuffer.toString());
						emptyClassBody = false;
					}
					if(writeTBORequiredMethods)
					{
						final String persistentInterfaceName = getRootGenerator()
							.getPersistentInterfaceName().substring(
								getRootGenerator().getPersistentInterfaceName()
									.lastIndexOf('.') + 1);
						writeMethodCommentStart(
							writer,
							"Returns the copyright statement stored in the implementation class. If a business object is provided by Documentum the vendor string would be \"Copyright (c) Documentum, Inc. 2005. All Rights reserved\"."); //$NON-NLS-1$
						writer.println("\t * @return The vendor String."); //$NON-NLS-1$
						writer
							.println("\t * @see com.documentum.fc.client.IDfBusinessObject#getVendorString()"); //$NON-NLS-1$
						writer.println("\t */"); //$NON-NLS-1$
						writer.println("\tpublic String getVendorString()"); //$NON-NLS-1$
						writer.println("\t{"); //$NON-NLS-1$
						writer
							.println("\t\t// TODO Check the automatically generated method getVendorString()."); //$NON-NLS-1$
						writer.print("\t\treturn "); //$NON-NLS-1$
						writer.print(persistentInterfaceName);
						writer.print(".VENDOR;"); //$NON-NLS-1$
						writer.println();
						writer.println("\t}"); //$NON-NLS-1$
						writeMethodCommentStart(
							writer,
							"Returns the current version of the business object implementation as a string. The string has the following format: [major version].[minor version]. Example: \"1.0\"."); //$NON-NLS-1$
						writer
							.println("\t * @return The TBO's version number."); //$NON-NLS-1$
						writer
							.println("\t * @see com.documentum.fc.client.IDfBusinessObject#getVersion()"); //$NON-NLS-1$
						writer.println("\t */"); //$NON-NLS-1$
						writer.println("\tpublic String getVersion()"); //$NON-NLS-1$
						writer.println("\t{"); //$NON-NLS-1$
						writer
							.println("\t\t// TODO Check the automatically generated method getVersion()."); //$NON-NLS-1$
						writer.print("\t\treturn "); //$NON-NLS-1$
						writer.print(persistentInterfaceName);
						writer.print(".VERSION;"); //$NON-NLS-1$
						writer.println();
						writer.println();
						writer.println("\t}"); //$NON-NLS-1$
						writeMethodCommentStart(
							writer,
							"Checks if this object is compatible with the specified version, often used in conjunction with the supportsFeature method."); //$NON-NLS-1$
						writer.println("\t * @param aVersion"); //$NON-NLS-1$
						writer
							.println("\t * \tversion string of format: [major version].[minor version]. Example: \"1.0\""); //$NON-NLS-1$
						writer
							.println("\t * @return true if business object type is compatible with the version (false if incompatible)."); //$NON-NLS-1$
						writer
							.println("\t * @see com.documentum.fc.client.IDfBusinessObject#isCompatible(java.lang.String)"); //$NON-NLS-1$
						writer.println("\t */"); //$NON-NLS-1$
						writer
							.println("\tpublic boolean isCompatible(final String aVersion)"); //$NON-NLS-1$
						writer.println("\t{"); //$NON-NLS-1$
						writer
							.println("\t\t// TODO Check the automatically generated method isCompatible(final String aVersion)."); //$NON-NLS-1$
						writer
							.println("\t\treturn getVersion().equals(aVersion);"); //$NON-NLS-1$
						writer.println("\t}"); //$NON-NLS-1$

						writeMethodCommentStart(
							writer,
							"Checks if the specified feature matches an entry in the implementations list of supported features. The implementation is up to the author of the TBO class. For example, the class could look up a table in the Docbase, store features in a private Hashtable, array, IDfList, etc. Based on this information, specific UI features might then be switched on or off."); //$NON-NLS-1$
						writer.println("\t * @param aFeatureName"); //$NON-NLS-1$
						writer.println("\t * \tFeature string."); //$NON-NLS-1$
						writer
							.println("\t * @return true if feature is supported (false if the feature is not supported)."); //$NON-NLS-1$
						writer
							.println("\t * @see com.documentum.fc.client.IDfBusinessObject#supportsFeature(java.lang.String)"); //$NON-NLS-1$
						writer.println("\t */"); //$NON-NLS-1$
						writer
							.println("\tpublic boolean supportsFeature(final String aFeatureName)"); //$NON-NLS-1$
						writer.println("\t{"); //$NON-NLS-1$
						writer
							.println("\t\t// TODO Create your implementation of method supportsFeature(final String aFeatureName)."); //$NON-NLS-1$
						writer.println("\t\treturn true;"); //$NON-NLS-1$
						writer.println("\t}"); //$NON-NLS-1$
						emptyClassBody = false;
					}
					if(getRootGenerator().isCustomAttributeMethods()
						&& isCustomType())
					{
						for(final String attributeName: getAttributes()
							.keySet())
						{
							writeAttributeMethods(writer, attributeName,
								getAttributes().get(attributeName), true);
						}
						emptyClassBody = false;
					}
					if(emptyClassBody)
					{
						writer.println("\t//TODO Write TBO implementation."); //$NON-NLS-1$
					}
					writer.println('}');
				}
			}
		} finally
		{
			if(writer != null)
			{
				writer.flush();
				writer.close();
			}
			if(destroyFile)
			{
				target.delete();
			} else
			{
				getRootGenerator().addRegistration(
					new String[] {
						getPackage() + '.' + getInterfaceName(),
						getInterfaceName() + '.' + getTypeName().toUpperCase()
							+ "_TYPE", //$NON-NLS-1$
						getPackage() + '.' + getClassName()});
			}
			if(getRootGenerator().isDebug())
			{
				System.out.println(Thread.currentThread().getName() + ' '
					+ getClass().getName() + ".writeClass() end"); //$NON-NLS-1$
			}
		}
	}

	/**
	 * Write the class comment with the copyright information.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 25.3.2005 12:54:32</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param writer
	 *            the output.
	 * @param comment
	 *            information about the class or interface.
	 */
	private void writeClassComment(final PrintWriter writer,
									final String comment)
	{

		final Calendar creationTime = Calendar.getInstance();
		writer.println("/**"); //$NON-NLS-1$
		writer.print(" * <H4>"); //$NON-NLS-1$
		writer.print(comment);
		writer.print(" '"); //$NON-NLS-1$
		writer.print(getTypeName());
		writer.print("'.</H4>"); //$NON-NLS-1$
		writer.println();
		writer.print(" * <DL><DT><B>Copyright:</B> <DD>(c) "); //$NON-NLS-1$
		writer.print(creationTime.get(Calendar.YEAR));
		writer.print(' ');
		writer.print(getRootGenerator().getCopyright());
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
		writer.print(getRootGenerator().getVersion());
		writer.println();
		writer.print(" * @since "); //$NON-NLS-1$
		writer.print(getRootGenerator().getSince());
		writer.println();
		writer.println(" */"); //$NON-NLS-1$
	}

	/**
	 * Write the interface.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.200512:15:20</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @throws IOException
	 *             if the file cant be writen.
	 */
	@SuppressWarnings("unchecked")
	private void writeInterface() throws IOException
	{

		if(getRootGenerator().isDebug())
		{
			System.out.println(Thread.currentThread().getName() + ' '
				+ getClass().getName() + ".writeInterface() start"); //$NON-NLS-1$
		}
		String typeIDPrefix = null;
		final Vector<String> implementThese = new Vector<String>();
		final File target = new File(getTargetFolder(), getInterfaceName()
			+ ".java"); //$NON-NLS-1$
		System.out
			.println(Thread.currentThread().getName()
				+ ' '
				+ getClass().getName()
				+ ".writeInterface() Writing interface " + target.getAbsolutePath() + "."); //$NON-NLS-1$ //$NON-NLS-2$
		final PrintWriter writer = new PrintWriter(new BufferedWriter(
			new FileWriter(target)));
		try
		{
			writeLogAndPackage(writer);
			if(getRootGenerator().isCustomAttributeMethods() && isCustomType())
			{
				boolean importException = false;
				boolean isIDNotImported = true;
				boolean isTimeNotImported = true;
				for(final String attributeName: getAttributes().keySet())
				{
					importException = true;
					final int dataType = Integer.parseInt(getAttributes().get(
						attributeName)[1]);
					if((dataType == IDfAttr.DM_ID) && isIDNotImported)
					{
						writer
							.println("import com.documentum.fc.common.IDfId;"); //$NON-NLS-1$
						isIDNotImported = false;
					} else if((dataType == IDfAttr.DM_TIME)
						&& isTimeNotImported)
					{
						writer
							.println("import com.documentum.fc.common.IDfTime;"); //$NON-NLS-1$
						isTimeNotImported = false;
					}
				}
				if(importException)
				{
					writer
						.println("import com.documentum.fc.common.DfException;"); //$NON-NLS-1$
				}
			}
			if(getSuperType() == null)
			{
				writer
					.println("import com.documentum.fc.client.IDfBusinessObject;"); //$NON-NLS-1$
				writer
					.println("import com.documentum.fc.client.IDfPersistentObject;"); //$NON-NLS-1$
				writer
					.println("import com.documentum.fc.common.IDfDynamicInheritance;"); //$NON-NLS-1$
				writer.println();
				writer.println("import java.util.Arrays;"); //$NON-NLS-1$
				writer.println("import java.util.Vector;"); //$NON-NLS-1$
				addIsImplementing("com.documentum.fc.client.IDfBusinessObject"); //$NON-NLS-1$
				addIsImplementing("com.documentum.fc.client.IDfPersistentObject"); //$NON-NLS-1$
				addIsImplementing("com.documentum.fc.common.IDfDynamicInheritance"); //$NON-NLS-1$
				addIsImplementing("com.documentum.fc.client.IDfPersistentObjectInternal"); //$NON-NLS-1$
				getRootGenerator().setPersistentInterfaceName(
					getPackage() + '.' + getInterfaceName());
			} else
			{
				if(getRootGenerator().isDeepPackages())
				{
					writer.print("import "); //$NON-NLS-1$
					writer.print(getSuperType().getPackage());
					writer.print('.');
					writer.print(getSuperType().getInterfaceName());
					writer.print(';');
					writer.println();
				}
				implementThese.addElement(getSuperType().getInterfaceName());
				addIsImplementing(getSuperType().getPackage() + '.'
					+ getInterfaceName());
				synchronized(getRootGenerator())
				{
					IDfSession session = null;
					IDfPersistentObject object = null;
					boolean destroyObject = true;
					try
					{
						session = getRootGenerator().getSession();
						try
						{
							object = session.newObject(getTypeName());
						} catch(final DfException dex)
						{
							System.out.println(Thread.currentThread().getName()
								+ " Failure creating object. " //$NON-NLS-1$
								+ dex.getMessage().trim());
						}
						if(object == null)
						{
							destroyObject = false;
							object = session
								.getObjectByQualification(getTypeName());
						}
						if(object != null)
						{
							typeIDPrefix = object.getObjectId().getId()
								.substring(0, 2);
							if(object.getClass().isInterface())
							{
								final String implementedClassName = object
									.getClass().getName();
								if(!isImplementing(implementedClassName))
								{
									writer.print("import "); //$NON-NLS-1$
									writer.print(implementedClassName);
									writer.print(';');
									writer.println();
									implementThese
										.addElement(implementedClassName
											.substring(implementedClassName
												.lastIndexOf('.') + 1));
									addIsImplementing(implementedClassName);
								}
							} else
							{
								for(final Class implemented: object.getClass()
									.getInterfaces())
								{
									final String implementedClassName = implemented
										.getName();
									if(!isImplementing(implementedClassName))
									{
										writer.print("import "); //$NON-NLS-1$
										writer.print(implementedClassName);
										writer.print(';');
										writer.println();
										implementThese
											.addElement(implementedClassName
												.substring(implementedClassName
													.lastIndexOf('.') + 1));
										addIsImplementing(implementedClassName);
									}
								}
							}
						}
					} catch(final DfException dex)
					{
						System.out.println(Thread.currentThread().getName()
							+ " Failure getting object. " //$NON-NLS-1$
							+ dex.getMessage().trim());
					} catch(InterruptedException iex)
					{
						System.out.println(Thread.currentThread().getName()
							+ " Failure getting session. " //$NON-NLS-1$
							+ iex.getMessage().trim());
					} finally
					{
						if(destroyObject && (object != null))
						{
							try
							{
								object.revert();
								object.destroy();
							} catch(final DfException swallow)
							{
								// Do nothing.
							}
						}
						getRootGenerator().setSession(session);
						getRootGenerator().notifyAll();
					}
				}
			}
			writeClassComment(writer, "Type and attribute information for"); //$NON-NLS-1$
			writer.print("public abstract interface "); //$NON-NLS-1$
			writer.print(getInterfaceName());
			writer.println();
			writer.println("\textends"); //$NON-NLS-1$
			if(getSuperType() == null)
			{
				writer.println("\t\tIDfBusinessObject,"); //$NON-NLS-1$
				writer.println("\t\tIDfDynamicInheritance,"); //$NON-NLS-1$
				writer.println("\t\tIDfPersistentObject"); //$NON-NLS-1$
			} else
			{
				for(int interfaceIndex = 0; interfaceIndex < implementThese
					.size(); interfaceIndex++)
				{
					writer.print("\t\t"); //$NON-NLS-1$
					writer.print(implementThese.get(interfaceIndex));
					if(interfaceIndex != implementThese.size() - 1)
					{
						writer.print(',');
					}
					writer.println();
				}
			}
			writer.println('{');
			for(final String attributeName: getAttributes().keySet())
			{
				final String[] attributeInfo = getAttributes().get(
					attributeName);
				writer.println("\t/**"); //$NON-NLS-1$
				writer.print("\t * '"); //$NON-NLS-1$
				writer.print(attributeName);
				writer.print("' with data type "); //$NON-NLS-1$
				writer.print(attributeInfo[0]);
				writer.print(" ("); //$NON-NLS-1$
				writer.print(attributeInfo[1]);
				writer.print(')');
				if(Integer.parseInt(attributeInfo[1]) == IDfAttr.DM_STRING)
				{
					writer.print(" and length "); //$NON-NLS-1$
					writer.print(attributeInfo[2]);
				}
				writer.print(" that is "); //$NON-NLS-1$
				if(!Boolean.parseBoolean(attributeInfo[3]))
				{
					writer.print("not"); //$NON-NLS-1$
				}
				writer.print(" repeating."); //$NON-NLS-1$
				writer.println();
				writer.print("\t * @since "); //$NON-NLS-1$
				writer.print(getRootGenerator().getSince());
				writer.println();
				writer.println("\t */"); //$NON-NLS-1$
				writer.print("\tpublic static final String "); //$NON-NLS-1$
				writer.print(attributeName.toUpperCase());
				writer.print(" = \""); //$NON-NLS-1$
				writer.print(attributeName);
				writer.print("\";"); //$NON-NLS-1$
				writer.println();
				writer.println();
			}
			if(typeIDPrefix != null)
			{
				writer.println("\t/**"); //$NON-NLS-1$
				writer.print("\t * '"); //$NON-NLS-1$
				writer.print(getTypeName());
				writer.print("' object types type spesific ID prefix '"); //$NON-NLS-1$
				writer.print(typeIDPrefix);
				writer.print("'."); //$NON-NLS-1$
				writer.println();
				writer.print("\t * @since "); //$NON-NLS-1$
				writer.print(getRootGenerator().getSince());
				writer.println();
				writer.println("\t */"); //$NON-NLS-1$
				writer.print("\tpublic static final String "); //$NON-NLS-1$
				writer.print(getTypeName().toUpperCase());
				writer.print("_ID_PREFIX = \""); //$NON-NLS-1$
				writer.print(typeIDPrefix);
				writer.print("\";"); //$NON-NLS-1$
				writer.println();
				writer.println();
			}
			writer.println("\t/**"); //$NON-NLS-1$
			writer.print("\t * '"); //$NON-NLS-1$
			writer.print(getTypeName());
			writer.print("' object type name."); //$NON-NLS-1$
			writer.println();
			writer.print("\t * @since "); //$NON-NLS-1$
			writer.print(getRootGenerator().getSince());
			writer.println();
			writer.println("\t */"); //$NON-NLS-1$
			writer.print("\tpublic static final String "); //$NON-NLS-1$
			writer.print(getTypeName().toUpperCase());
			writer.print("_TYPE = \""); //$NON-NLS-1$
			writer.print(getTypeName());
			writer.print("\";"); //$NON-NLS-1$
			writer.println();
			writer.println();

			if(getSuperType() == null)
			{

				writer.println("\t/**"); //$NON-NLS-1$
				writer
					.println("\t * Attribute name prefixes that shouldn't be modified."); //$NON-NLS-1$
				writer.print("\t * @since "); //$NON-NLS-1$
				writer.print(getRootGenerator().getSince());
				writer.println();
				writer.println("\t */"); //$NON-NLS-1$
				writer
					.println("\tpublic static final Vector<String> ATTRIBUTE_NAME_PREFIXES_WE_SHOULDNT_TOUCH ="); //$NON-NLS-1$
				writer
					.println("\t\tnew Vector<String>(Arrays.asList(new String[] {"); //$NON-NLS-1$
				writer.print("\t\t\t"); //$NON-NLS-1$
				writer.print(getInterfaceName());
				writer.print(".ATTRIBUTE_APPLICATION_PREFIX,"); //$NON-NLS-1$
				writer.println();
				writer.print("\t\t\t"); //$NON-NLS-1$
				writer.print(getInterfaceName());
				writer.print(".ATTRIBUTE_INTERNAL_PREFIX,"); //$NON-NLS-1$
				writer.println();
				writer.print("\t\t\t"); //$NON-NLS-1$
				writer.print(getInterfaceName());
				writer.print(".ATTRIBUTE_READONLY_PREFIX}));"); //$NON-NLS-1$
				writer.println();

				writer.println("\t/**"); //$NON-NLS-1$
				writer
					.println("\t * Attribute prefix for application attributes."); //$NON-NLS-1$
				writer.print("\t * @since "); //$NON-NLS-1$
				writer.print(getRootGenerator().getSince());
				writer.println();
				writer.println("\t */"); //$NON-NLS-1$
				writer
					.println("\tpublic static final String\tATTRIBUTE_APPLICATION_PREFIX\t= \"a_\";"); //$NON-NLS-1$
				writer.println();

				writer.println("\t/**"); //$NON-NLS-1$
				writer
					.println("\t * Attribute prefix for internal attributes."); //$NON-NLS-1$
				writer.print("\t * @since "); //$NON-NLS-1$
				writer.print(getRootGenerator().getSince());
				writer.println();
				writer.println("\t */"); //$NON-NLS-1$
				writer
					.println("\tpublic static final String\tATTRIBUTE_INTERNAL_PREFIX\t= \"i_\";"); //$NON-NLS-1$
				writer.println();

				writer.println("\t/**"); //$NON-NLS-1$
				writer
					.println("\t * Attribute prefix for read only attributes."); //$NON-NLS-1$
				writer.print("\t * @since "); //$NON-NLS-1$
				writer.print(getRootGenerator().getSince());
				writer.println();
				writer.println("\t */"); //$NON-NLS-1$
				writer
					.println("\tpublic static final String\tATTRIBUTE_READONLY_PREFIX\t= \"r_\";"); //$NON-NLS-1$
				writer.println();

				writer.println("\t/**"); //$NON-NLS-1$
				writer.println("\t * Table suffix for single table."); //$NON-NLS-1$
				writer.print("\t * @since "); //$NON-NLS-1$
				writer.print(getRootGenerator().getSince());
				writer.println();
				writer.println("\t */"); //$NON-NLS-1$
				writer
					.println("\tpublic static final String\tTABLE_SINGLE_SUFFIX\t= \"_s\";"); //$NON-NLS-1$
				writer.println();

				writer.println("\t/**"); //$NON-NLS-1$
				writer.println("\t * Table suffix for repeating table."); //$NON-NLS-1$
				writer.print("\t * @since "); //$NON-NLS-1$
				writer.print(getRootGenerator().getSince());
				writer.println();
				writer.println("\t */"); //$NON-NLS-1$
				writer
					.println("\tpublic static final String\tTABLE_REPEATING_SUFFIX\t= \"_r\";"); //$NON-NLS-1$
				writer.println();

				writer.println("\t/**"); //$NON-NLS-1$
				writer.println("\t * Type name prefix."); //$NON-NLS-1$
				writer.print("\t * @since "); //$NON-NLS-1$
				writer.print(getRootGenerator().getSince());
				writer.println();
				writer.println("\t */"); //$NON-NLS-1$
				writer
					.println("\tpublic static final String\tTYPE_PREFIX\t= \"dm_\";"); //$NON-NLS-1$
				writer.println();

				writer.println("\t/**"); //$NON-NLS-1$
				writer.println("\t * Internal type name prefix."); //$NON-NLS-1$
				writer.print("\t * @since "); //$NON-NLS-1$
				writer.print(getRootGenerator().getSince());
				writer.println();
				writer.println("\t */"); //$NON-NLS-1$
				writer
					.println("\tpublic static final String\tTYPE_INTERNAL_PREFIX\t= \"dmi_\";"); //$NON-NLS-1$
				writer.println();

				writer.println("\t/**"); //$NON-NLS-1$
				writer.println("\t * Creator of the BOF / TBO."); //$NON-NLS-1$
				writer.print("\t * @since "); //$NON-NLS-1$
				writer.print(getRootGenerator().getSince());
				writer.println();
				writer.println("\t */"); //$NON-NLS-1$
				writer.print("\tpublic static final String\tVENDOR\t= \""); //$NON-NLS-1$
				writer.print(getClass().getName());
				writer.print("\";"); //$NON-NLS-1$
				writer.println();

				writer.println("\t/**"); //$NON-NLS-1$
				writer.println("\t * Version of the BOF / TBO."); //$NON-NLS-1$
				writer.print("\t * @since "); //$NON-NLS-1$
				writer.print(getRootGenerator().getSince());
				writer.println();
				writer.println("\t */"); //$NON-NLS-1$
				writer
					.println("\tpublic static final String\tVERSION\t= \"1.0\";"); //$NON-NLS-1$
				writer.println();

				writer.println("\t/**"); //$NON-NLS-1$
				writer
					.println("\t * Value to return all non repeating, non internal and non read only attributes."); //$NON-NLS-1$
				writer.print("\t * @since "); //$NON-NLS-1$
				writer.print(getRootGenerator().getSince());
				writer.println();
				writer.println("\t */"); //$NON-NLS-1$
				writer.println("\tpublic static final String\tSTAR\t= \"*\";"); //$NON-NLS-1$
				writer.println();
			}
			if(getRootGenerator().isCustomAttributeMethods() && isCustomType())
			{
				for(final String attributeName: getAttributes().keySet())
				{
					writeAttributeMethods(writer, attributeName,
						getAttributes().get(attributeName), false);
				}
			}

			writer.println('}');
		} finally
		{
			writer.flush();
			writer.close();
		}
		if(getRootGenerator().isDebug())
		{
			System.out.println(Thread.currentThread().getName() + ' '
				+ getClass().getName() + ".writeInterface() end"); //$NON-NLS-1$
		}
	}

	/**
	 * Write the CVS log comment and the package.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 25.3.2005 12:50:10</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param writer
	 *            the output.
	 */
	private void writeLogAndPackage(final PrintWriter writer)
	{

		writer.println("/*-"); //$NON-NLS-1$
		writer.println(" * $Log:$"); //$NON-NLS-1$
		writer.println(" */"); //$NON-NLS-1$
		writer.println();
		writer.print("package "); //$NON-NLS-1$
		writer.print(getPackage());
		writer.print(';');
		writer.println();
		writer.println();
	}

	/**
	 * Write a method comment start.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.2005 20:50:05</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param writer
	 *            the output
	 * @param aComment
	 *            the method comment.
	 */
	private void writeMethodCommentStart(final PrintWriter writer,
											String aComment)
	{

		final Calendar creationTime = Calendar.getInstance();
		writer.println("\t/**"); //$NON-NLS-1$
		while(aComment.length() > 0)
		{
			writer.print("\t * "); //$NON-NLS-1$
			if(aComment.length() > 80)
			{
				final int spaceLocation = aComment.lastIndexOf(' ', 80);
				if(spaceLocation > 0)
				{
					writer.print(aComment.substring(0, spaceLocation).trim());
					aComment = aComment.substring(spaceLocation + 1).trim();
				} else
				{
					writer.print(aComment.substring(0, 80).trim());
					aComment = aComment.substring(81).trim();
				}
			} else
			{
				writer.print(aComment.trim());
				aComment = ""; //$NON-NLS-1$
			}
			writer.println();
		}
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
		writer.print("\t * @since "); //$NON-NLS-1$
		writer.print(getRootGenerator().getSince());
		writer.println();
	}

	/**
	 * Write setter methods body.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 28.3.2005 0:55:59</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param writer
	 *            the output.
	 * @param methodStart
	 *            start portion of the metod.
	 * @param upperAttributeName
	 *            upper case attribute name.
	 * @param attributeDataType
	 *            the attributes data type.
	 * @param repeatingAttributeName
	 *            repeating attribute name. use null to avoid third argument.
	 */
	private void writeSetterMethodBody(final PrintWriter writer,
										final String methodStart,
										final String upperAttributeName,
										final int attributeDataType,
										final String repeatingAttributeName)
	{

		writer.println();
		writer.println("\t{"); //$NON-NLS-1$
		writer.print("\t\t"); //$NON-NLS-1$
		writer.print(methodStart);
		if(attributeDataType == IDfAttr.DM_TIME)
		{
			writer.print("Time("); //$NON-NLS-1$
		} else
		{
			writer.print("String("); //$NON-NLS-1$
		}
		writer.print(getInterfaceName());
		writer.print('.');
		writer.print(upperAttributeName);
		writer.print(", "); //$NON-NLS-1$
		if(repeatingAttributeName != null)
		{
			writer.print(repeatingAttributeName);
			writer.print(", "); //$NON-NLS-1$
		}
		if(attributeDataType == IDfAttr.DM_ID)
		{
			writer.print("value.getId()"); //$NON-NLS-1$
		} else if((attributeDataType == IDfAttr.DM_TIME)
			|| (attributeDataType == IDfAttr.DM_STRING))
		{
			writer.print("value"); //$NON-NLS-1$
		} else
		{
			writer.print("String.valueOf(value)"); //$NON-NLS-1$
		}
		writer.print(");"); //$NON-NLS-1$
		writer.println();
		writer.println("\t}"); //$NON-NLS-1$
	}
}
