import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.zookeeper.KeeperException;

import java.util.Map;
import java.util.HashMap;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.List;

public class ConfigUpdater {
  
  public static final String PATH = "/config";
  
  private ActiveKeyValueStore store;
  private Random random = new Random();
  
  public ConfigUpdater(String hosts) throws IOException, InterruptedException {
    store = new ActiveKeyValueStore();
    store.connect(hosts);
  }
  
//  public void run() throws InterruptedException, KeeperException {
//    while (true) {
//      String value = random.nextInt(100) + "";
//      
//      Map<String, String> map = new HashMap<String, String>(); 
//      
//      map.put("title", "freakonomics");
//      map.put("authors", "Levitt & Dubner");
//      map.put("release date", "20-09-2011");
//      map.put("list price", "7 USD");
//      map.put("publisher", "William Morrow");
//      
//      store.write(PATH, "13579", map);
//      System.out.printf("Set %s to %s\n", PATH, value);
//      TimeUnit.SECONDS.sleep(random.nextInt(10));
//      
//      break;
//    }
//  }
  
  public void run(Map<String, Map<String, String>> idKVs) throws InterruptedException, 
  		KeeperException { 
    for (String id : idKVs.keySet()) { 
    	Map<String, String> map = idKVs.get(id); 
    	System.out.println("Written on " + PATH + "/" + id + " :\n" + map.toString());
    	store.write(PATH, id, map);
    }	  
  }
  
  public static void main(String[] args) throws Exception {
	if (args.length != 2) { 
		System.out.println("Usage: ConfigUpdater <zookeeper> <file>");
		System.exit(1);
	}
	
    ConfigUpdater configUpdater = new ConfigUpdater(args[0]);
    
    Map<String, Map<String, String>> idKVs = read(args[1]);
    configUpdater.run(idKVs);
    
//    map.put("title", "freakonomics");
//    map.put("authors", "Levitt & Dubner");
//    map.put("release date", "20-09-2011");
//    map.put("list price", "7 USD");
//    map.put("publisher", "William Morrow");
//  
  }
  
  private static Map<String, Map<String, String>> read(String filename) {
	  Map<String, Map<String, String>> ubermap = new HashMap<String, Map<String, String>>();
	  
	  BufferedReader br = null;
		try {
			String currentLine;
			br = new BufferedReader(new FileReader(filename));
			while ((currentLine = br.readLine()) != null) {
				System.out.println(currentLine);
				
				String[] tokens = currentLine.split(",", -1);
				
				if (tokens.length != 3) { 
					System.out.println("Dirty data found. Skipping.");
					continue;
				}
				
				String id = tokens[0];
				String key = tokens[1];
				String value = tokens[2];
				
				if (!ubermap.containsKey(id)) {
					ubermap.put(id, new HashMap<String, String>());
				}
				
				ubermap.get(id).remove(key);
				ubermap.get(id).put(key, value);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
	return ubermap;
  }
}
