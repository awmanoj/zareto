# zareto

`zareto$ CLASSPATH="/home/mawasthi/box/zookeeper-3.4.6/lib/*:/home/mawasthi/box/zookeeper-3.4.6/*:target/zareto-4.0.jar"`

ConfigUpdater - A writer implementation for the config using a file that contains all configs: 
==============================================================================================
 
zareto$ java ConfigUpdater
Usage: ConfigUpdater <zookeeper> <file>

ConfigWatcher - A reader implementation for the config using item ID:
======================================================================

zareto$ java ConfigWatcher
Usage: ConfigWatcher <zookeeper> <item_id>
