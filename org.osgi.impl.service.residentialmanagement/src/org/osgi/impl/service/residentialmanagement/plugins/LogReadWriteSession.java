package org.osgi.impl.service.residentialmanagement.plugins;

import java.util.Date;
import java.util.Vector;

import org.osgi.framework.BundleContext;
import org.osgi.service.dmt.DmtData;
import org.osgi.service.dmt.DmtException;
import org.osgi.service.dmt.spi.TransactionalDataSession;
import org.osgi.service.dmt.spi.ReadWriteDataSession;
import org.osgi.service.log.LogEntry;

public class LogReadWriteSession extends LogReadOnlySession implements ReadWriteDataSession, TransactionalDataSession{

	protected Vector logEntriesExclusive = null;
	
	LogReadWriteSession(LogPlugin plugin, BundleContext context) {
		super(plugin, context);
	}
	
	public void setLogEntry(){
		logEntriesExclusive = (Vector)logEntries.clone();
	}

//------
	public String[] getChildNodeNames(String[] nodePath) throws DmtException {
		String[] path = shapedPath(nodePath);

		if (path.length == 1) {
			String[] children = new String[1];
			children[0] = LOGENTRIES;
			return children;
		}

		if (path.length == 2) {
			String[] children = new String[logEntriesExclusive.size()];
			for(int i=0;i<logEntriesExclusive.size();i++){
				children[i] = Integer.toString(i);
			}
			return children;
		}

		if (path.length == 3){
			try{
				LogEntry le = (LogEntry)logEntriesExclusive.get(Integer.parseInt(path[2]));
				if(le.getException()!=null){
					String[] children = new String[5];
					children[0] = MESSAGE;
					children[1] = BUNDLE;
					children[2] = TIME;
					children[3] = LEVEL;
					children[4] = EXCEPTION;
					return children;
				}else if(le.getException()==null){
					String[] children = new String[4];
					children[0] = MESSAGE;
					children[1] = BUNDLE;
					children[2] = TIME;
					children[3] = LEVEL;
					return children;
				}
			}catch(ArrayIndexOutOfBoundsException ae){
				String[] children = new String[0];
				return children;
			}
		}
		throw new DmtException(nodePath, DmtException.NODE_NOT_FOUND,
				"No such node defined in the packageState tree.");
	}

	public boolean isNodeUri(String[] nodePath) {
		String[] path = shapedPath(nodePath);

		if (path.length == 1)
			return true;

		if (path.length == 2)
			if (path[1].equals(LOGENTRIES))
				return true;

		if (path.length == 3) {
			try{
				logEntriesExclusive.get(Integer.parseInt(path[2]));
				return true;
				}catch(ArrayIndexOutOfBoundsException ae){
					return false;
			}
		}
		
		if (path.length == 4) {
			try{
				LogEntry le = (LogEntry)logEntriesExclusive.get(Integer.parseInt(path[2]));
				if(le.getException()!=null){
					if(path[3].equals(BUNDLE)
							|| path[3].equals(TIME)
							|| path[3].equals(LEVEL)
							|| path[3].equals(MESSAGE)
							|| path[3].equals(EXCEPTION))
						return true;
				} else if (le.getException()==null){
					if(path[3].equals(BUNDLE)
							|| path[3].equals(TIME)
							|| path[3].equals(LEVEL)
							|| path[3].equals(MESSAGE))
						return true;
				}
			}catch(ArrayIndexOutOfBoundsException ae){
				return false;
			}
		}
		return false;
	}

	public DmtData getNodeValue(String[] nodePath) throws DmtException {
		String[] path = shapedPath(nodePath);
		if (path.length <= 3)
			throw new DmtException(nodePath,
					DmtException.FEATURE_NOT_SUPPORTED,
					"The given path indicates an interior node.");

		if (path.length == 4) {
			try{
				LogEntry le = (LogEntry)logEntriesExclusive.get(Integer.parseInt(path[2]));
				if(path[3].equals(BUNDLE)){
					return new DmtData(le.getBundle().getLocation());
				}
				if(path[3].equals(TIME)){
					return new DmtData(new Date(le.getTime()));
				}
				if(path[3].equals(LEVEL)){
					return new DmtData(le.getLevel());
				}
				if(path[3].equals(MESSAGE)){
					return new DmtData(le.getMessage());
				}
				if(path[3].equals(EXCEPTION)){
					if(le.getException()!=null)
						return new DmtData(le.getException().getMessage());
				}
				}catch(ArrayIndexOutOfBoundsException ae){
					throw new DmtException(nodePath, DmtException.NODE_NOT_FOUND,
					"The specified leaf node does not exist in the log object.");					
			}
		}
		throw new DmtException(nodePath, DmtException.NODE_NOT_FOUND,
				"The specified key does not exist in the packageState object.");
	}
	
	public void close() throws DmtException {
		logEntriesExclusive = null;
	}
//------	
	
	
	public void commit() throws DmtException {
	}

	public void rollback() throws DmtException {
	}

	public void copy(String[] nodePath, String[] newNodePath, boolean recursive)
			throws DmtException {
	}

	public void createInteriorNode(String[] nodePath, String type)
			throws DmtException {
	}

	public void createLeafNode(String[] nodePath, DmtData value, String mimeType)
			throws DmtException {
	}

	public void deleteNode(String[] nodePath) throws DmtException {
	}

	public void renameNode(String[] nodePath, String newName)
			throws DmtException {
	}

	public void setNodeTitle(String[] nodePath, String title)
			throws DmtException {
	}

	public void setNodeType(String[] nodePath, String type) throws DmtException {
	}

	public void setNodeValue(String[] nodePath, DmtData data)
			throws DmtException {
	}
}