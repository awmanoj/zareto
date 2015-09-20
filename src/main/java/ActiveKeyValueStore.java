import java.nio.charset.Charset;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.Stat;

import java.util.Map;
import java.util.List;

import java.nio.charset.StandardCharsets;

public class ActiveKeyValueStore extends ConnectionWatcher {

  private static final Charset CHARSET = Charset.forName("UTF-8");

  public void write(String path, String id, Map<String, String> kvs) throws InterruptedException,
      KeeperException {
    Stat stat = zk.exists(path, false); // check for /config
    if (stat == null) {
      zk.create(path, new String().getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    } 
  
    String idNodePath = path + "/" + id;
    Stat stat2 = zk.exists(idNodePath, false); // check for /config/13579
    if (stat2 == null) {
    	zk.create(idNodePath, new String().getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }
    
	for (Map.Entry<String, String> kv : kvs.entrySet()) {
		String key = kv.getKey();
		String value = kv.getValue();

		String propertyNodePath = path + "/" + id + "/" + key.replace(" ", "_"); // check for /config/13579/title 

		Stat stat3 = zk.exists(propertyNodePath, false); // check for /config/13579
		if (stat3 == null) {
			zk.create(propertyNodePath, new String().getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		}
		
		System.out.println("setting the " + propertyNodePath + " to " + value);
		
  		zk.setData(propertyNodePath, value.getBytes(CHARSET), -1);
  		
  		// Implement notification logic at this place. 
  		// We will need a simple AND, OR, NOT logic parser for a text file
  		// which can formulate the conditions on keys and use a notification (REST?) 
  		// to interested subscribers. 
	}
  }

  public String read(String path, String id, Watcher watcher) throws InterruptedException,
      KeeperException {

	StringBuilder sb = new StringBuilder();
	  
    Stat stat = zk.exists(path, false); // check for /config
    if (stat == null) {
      sb.append(""); // empty
    } else {
      String idNodePath = path + "/" + id;
	  Stat stat2 = zk.exists(idNodePath, false); // check for /config/13579
	  if (stat2 == null) {
		  sb.append(""); // empty
	  } else {
		List<String> children = zk.getChildren(idNodePath, null);  
		for (String child : children) {
			String propertyNodePath = path + "/" + id + "/" + child; // check for /config/13579/title 
			sb.append(child + "=");
			sb.append(new String(zk.getData(propertyNodePath, null, stat2), StandardCharsets.UTF_8));
			sb.append("\n");
		}
	  }
    }
    
    return sb.toString();
  }  
}
