# zareto

`zareto$ CLASSPATH="/home/mawasthi/box/zookeeper-3.4.6/lib/*:/home/mawasthi/box/zookeeper-3.4.6/*:target/lsns-4.0.jar"`

ConfigUpdater - A writer implementation for the config using a file that contains all configs: 
==============================================================================================
 
zareto$ java ConfigUpdater
Usage: ConfigUpdater <zookeeper> <file>

ConfigWatcher - A reader implementation for the config using item ID:
======================================================================

zareto$ java ConfigWatcher
Usage: ConfigWatcher <zookeeper> <item_id>

Following are contents of two text files which are used to test stuff: 
==========================================================

`zareto$ cat /home/mawasthi/tinker/hackathon/venturesity/final2/sample.txt 
13579,title,freakonomics
13579,authors,Levitt & Dubner
13579,release date,20-09-2011
13579,list price,7 USD
13579,publisher,William Morrow
1248,title,Effective Java
1248,authors,Joshua Bloch
1248,list price,10 USD
1248,publisher,Addison-Wesley`

`zareto$ cat /home/mawasthi/tinker/hackathon/venturesity/final2/sample2.txt 
13579,list price,11 USD`
