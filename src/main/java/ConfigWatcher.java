import java.io.IOException;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;

public class ConfigWatcher implements Watcher {
  
  private ActiveKeyValueStore store;
  
  public ConfigWatcher(String hosts) throws IOException, InterruptedException {
    store = new ActiveKeyValueStore();
    store.connect(hosts);
  }
  
  public void displayConfig(String id) throws InterruptedException, KeeperException {
    String value = store.read(ConfigUpdater.PATH, id, this);
    System.out.printf("%s", value);
  }

  @Override
  public void process(WatchedEvent event) {
    if (event.getType() == EventType.NodeDataChanged) {
      try {
        displayConfig(event.getPath());
      } catch (InterruptedException e) {
        System.err.println("Interrupted. Exiting.");        
        Thread.currentThread().interrupt();
      } catch (KeeperException e) {
        System.err.printf("KeeperException: %s. Exiting.\n", e);        
      }
    }
  }
  
  public static void main(String[] args) throws Exception {
	if (args.length != 2) { 
		System.out.println("Usage: ConfigWatcher <zookeeper> <item_id>");
		System.exit(1);
	}
	  
    ConfigWatcher configWatcher = new ConfigWatcher(args[0]);
    configWatcher.displayConfig(args[1]);
  }
}
