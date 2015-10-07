package dataAccess;

import java.util.ListIterator;

import com.db4o.ObjectSet;
import com.db4o.cs.Db4oClientServer;
import com.db4o.cs.config.ClientConfiguration;

import domain.Owner;

public class DataAccessRemote extends DataAccessCommon {
	private static ClientConfiguration configurationCS;

	public DataAccessRemote(){
			super();
			configurationCS = Db4oClientServer.newClientConfiguration();
			configurationCS.common().activationDepth(c.getActivationDepth());
			configurationCS.common().updateDepth(c.getUpdateDepth());
			configurationCS.common().objectClass(Owner.class).cascadeOnDelete(true);
			db = Db4oClientServer.openClient(configurationCS,c.getDatabaseNode(), 
				 c.getDatabasePort(),c.getUser(),c.getPassword());
			
			if (c.getDataBaseOpenMode().equals("initialize"))
			{	initializeDB();
				System.out.println("DataBase initialized");
			}
			else // c.getDataBaseOpenMode().equals("open")
			
				{	
				  ObjectSet<DB4oManagerAux> res =db.queryByExample(DB4oManagerAux.class);
				  ListIterator<DB4oManagerAux> listIter = res.listIterator();
				  if (listIter.hasNext()) theDB4oManagerAux =  res.next();           	
	            }
	}
}

