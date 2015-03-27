/*-
 * $Log: ConnectionSaveParticipant.java,v $
 * Revision 1.11  2005/12/04 22:14:40  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 * Revision 1.10  2005/12/04 20:29:12  madcook
 * Version 3.0.0 work started.
 *
 * Revision 1.9  2005/11/21 13:04:31  madcook
 * Plugin version 3.0.0 work started and made code to comply with all java 5 
 * settings.
 *
 * Revision 1.8  2005/03/25 09:21:38  harpechr
 * Version 2.0.0 code that has implemented the new java 1.5 features.
 *
 * Revision 1.7  2005/02/10 11:33:38  harpechr
 * Safety commit!
 *
 * Revision 1.6  2005/02/09 14:01:31  harpechr
 * Version 1.0.5 work started.
 *
 * Revision 1.5  2005/01/24 12:34:54  harpechr
 * Version 1.0.4 work started.
 *
 * Revision 1.4  2005/01/11 14:02:01  harpechr
 * Changed version number from 1.0.2 to 1.0.3.
 *
 * Revision 1.3  2005/01/11 13:47:23  harpechr
 * Changed hard coded attribute names and type names to references from the bof 
 * structure (org.cah.dctm.bof).
 *
 * Revision 1.2  2005/01/07 14:17:29  harpechr
 * Version 1.0.2 code. First CVS commit!
 * 
 */

package org.cah.eclipse.plugins.dctm.dql.views.connection;

import java.io.File;

import org.cah.eclipse.plugins.dctm.dql.DCTMPlugin;
import org.eclipse.core.resources.ISaveContext;
import org.eclipse.core.resources.ISaveParticipant;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;


/**
 * <H4>A participant in the saving of the workspace.</H4>
 * <DL>
 * <DT><B>Description : </B>
 * <DD>Saves the DCTM DQL / API plugins connectino information.</DD>
 * </DT>
 * <DT><B>Copyright (c): </B>
 * <DD>Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>Nov 25, 2004 1:44:32 PM.</DD>
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
public class ConnectionSaveParticipant
										implements
											ISaveParticipant
{

	/**
	 * Partial settings file name.
	 * 
	 * @since 1.0
	 */
	public static final String	SAVE_FILE	= "save";	//$NON-NLS-1$

	/**
	 * <p>
	 * <b>This implementation does nothing. </b>
	 * </p>
	 * <p>
	 * Tells this participant that the workspace save operation is now complete
	 * and it is free to go about its normal business. Exceptions are not
	 * expected to be thrown at this point, so they should be handled
	 * internally.
	 * </p>
	 * <p>
	 * Note: This method is called by the platform; it is not intended to be
	 * called directly by clients.
	 * </p>
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 10:23:19 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param context
	 *            the save context object.
	 * @see org.eclipse.core.resources.ISaveParticipant#doneSaving(org.eclipse.core.resources.ISaveContext)
	 */
	public void doneSaving(final ISaveContext context)
	{

		/* Nothing here. */
	}

	/**
	 * <p>
	 * <b>This implementation does nothing. </b>
	 * </p>
	 * <p>
	 * Tells this participant that the workspace is about to be saved. In
	 * preparation, the participant is expected to suspend its normal operation
	 * until further notice. <code>saving</code> will be next, followed by
	 * either <code>doneSaving</code> or <code>rollback</code> depending on
	 * whether the workspace save was successful.
	 * </p>
	 * <p>
	 * Note: This method is called by the platform; it is not intended to be
	 * called directly by clients.
	 * </p>
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 10:23:31 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param context
	 *            the save context object
	 * @exception CoreException
	 *                if this method fails to snapshot the state of this
	 *                workspace
	 * @see org.eclipse.core.resources.ISaveParticipant#prepareToSave(org.eclipse.core.resources.ISaveContext)
	 */
	public void prepareToSave(final ISaveContext context) throws CoreException
	{

		/* Nothing here. */
	}

	/**
	 * <p>
	 * <b>This implementation does nothing. </b>
	 * </p>
	 * <p>
	 * Tells this participant to rollback its important state. The context's
	 * previous state number indicates what it was prior to the failed save.
	 * Exceptions are not expected to be thrown at this point, so they should be
	 * handled internally.
	 * </p>
	 * <p>
	 * Note: This method is called by the platform; it is not intended to be
	 * called directly by clients.
	 * </p>
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 10:23:44 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param context
	 *            the save context object
	 * @see org.eclipse.core.resources.ISaveParticipant#rollback(org.eclipse.core.resources.ISaveContext)
	 */
	public void rollback(final ISaveContext context)
	{

		/* Nothing here. */
	}

	/**
	 * <p>
	 * <b>Saves the connection information into a xml file. </b>
	 * </p>
	 * <p>
	 * Tells this participant to save its important state because the workspace
	 * is being saved, as described in the supplied save context.
	 * </p>
	 * <p>
	 * Note: This method is called by the platform; it is not intended to be
	 * called directly by clients.
	 * </p>
	 * <p>
	 * The basic contract for this method is the same for full saves, snapshots
	 * and project saves: the participant must absolutely guarantee that any
	 * important user data it has gathered will not be irrecoverably lost in the
	 * event of a crash. The only difference is in the space-time tradeoffs that
	 * the participant should make.
	 * <ul>
	 * <li>Full saves: the participant is encouraged to save additional
	 * non-essential information that will aid it in retaining user state and
	 * configuration information and quickly getting back in sync with the state
	 * of the platform at a later point.</li>
	 * <li>Snapshots: the participant is discouraged from saving non-essential
	 * information that could be recomputed in the unlikely event of a crash.
	 * This lifecycle event will happen often and participant actions should
	 * take an absolute minimum of time.</li>
	 * <li>Project saves: the participant should only save project related
	 * data. It is discouraged from saving non-essential information that could
	 * be recomputed in the unlikely event of a crash.</li>
	 * </ul>
	 * For instance, the Java IDE gathers various user preferences and would
	 * want to make sure that the current settings are safe and sound after a
	 * <code>save</code> (if not saved immediately). The Java IDE would likely
	 * save computed image builder state on full saves, because this would allow
	 * the Java IDE to be restarted later and not have to recompile the world at
	 * that time. On the other hand, the Java IDE would not save the image
	 * builder state on a snapshot because that information is non-essential; in
	 * the unlikely event of a crash, the image should be rebuilt either from
	 * scratch or from the last saved state.
	 * </p>
	 * <p>
	 * The following snippet shows how a plug-in participant would write its
	 * important state to a file whose name is based on the save number for this
	 * save operation.
	 * 
	 * <pre>
	 *  
	 *   
	 *    Plugin plugin = ...; // known
	 *    int saveNumber = context.getSaveNumber();
	 *    String saveFileName = &quot;save-&quot; + Integer.toString(saveNumber);
	 *    File f = plugin.getStateLocation().append(saveFileName).toFile();
	 *    plugin.writeImportantState(f);
	 *    context.map(new Path(&quot;save&quot;), new Path(saveFileName));
	 *    context.needSaveNumber();
	 *    context.needDelta(); // optional
	 *    
	 *   
	 * </pre>
	 * 
	 * When the plug-in is reactivated in a subsequent workspace session, it
	 * needs to re-register to participate in workspace saves. When it does so,
	 * it is handed back key information about what state it had last saved. If
	 * it's interested, it can also ask for a resource delta describing all
	 * resource changes that have happened since then, if this information is
	 * still available. The following snippet shows what a participant plug-in
	 * would need to do if and when it is reactivated:
	 * 
	 * <pre>
	 *  
	 *   
	 *   	IWorkspace ws = ...; // known
	 *   	Plugin plugin = ...; // known
	 *   	ISaveParticipant saver = ...; // known
	 *   	ISavedState ss = ws.addSaveParticipant(plugin, saver);
	 *   	if (ss == null) {
	 *   		// activate for very first time
	 *   		plugin.buildState();
	 *   	} else {
	 *   		String saveFileName = ss.lookup(new Path(&quot;save&quot;));
	 *   		File f = plugin.getStateLocation().append(saveFileName).toFile();
	 *   		plugin.readImportantState(f);
	 *   		IResourceChangeListener listener = new IResourceChangeListener() {
	 *   			public void resourceChanged(IResourceChangeEvent event) {
	 *   				IResourceDelta delta = event.getDelta();
	 *   				if (delta != null) {
	 *   					// fast reactivation using delta
	 *   					plugin.updateState(delta);
	 *   				} else {
	 *   					// Slower reactivation without benefit of delta.
	 *   					plugin.rebuildState();
	 *   				}
	 *   			};
	 *   		ss.processResourceChangeEvents(listener);
	 *   	}
	 *    
	 *   
	 * </pre>
	 * 
	 * </p>
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 10, 2004 4:26:29 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param context
	 *            the save context object
	 * @exception CoreException
	 *                if this method fails
	 * @see org.eclipse.core.resources.ISaveParticipant#saving(org.eclipse.core.resources.ISaveContext)
	 */
	public void saving(final ISaveContext context) throws CoreException
	{

		switch(context.getKind())
		{
			case ISaveContext.FULL_SAVE:
				if(ConnectionContentProvider.getInstance().hasChanged())
				{
					final DCTMPlugin plugin = DCTMPlugin.getInstance();
					final int saveNumber = context.getSaveNumber();
					final String saveFileName = ConnectionSaveParticipant.SAVE_FILE
						+ Integer.toString(saveNumber);
					File file = plugin.getStateLocation().append(saveFileName)
						.toFile();
					ConnectionContentProvider.getInstance().save(file);
					/* Map saved file name with common name. */
					context.map(new Path(ConnectionSaveParticipant.SAVE_FILE),
						new Path(saveFileName));
					context.needSaveNumber();
				}
				break;
		}
	}

}
