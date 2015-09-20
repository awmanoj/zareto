# == update using file "sample.txt" 
java -cp "/home/mawasthi/box/zookeeper-3.4.6/lib/*:/home/mawasthi/box/zookeeper-3.4.6/*:target/zareto-4.0.jar" ConfigUpdater localhost /home/mawasthi/tinker/hackathon/venturesity/final2/sample.txt
# == read the updates done for item id = 13579
java -cp "/home/mawasthi/box/zookeeper-3.4.6/lib/*:/home/mawasthi/box/zookeeper-3.4.6/*:target/zareto-4.0.jar" ConfigWatcher localhost 13579
# == read the updates done for item id = 1248
java -cp "/home/mawasthi/box/zookeeper-3.4.6/lib/*:/home/mawasthi/box/zookeeper-3.4.6/*:target/zareto-4.0.jar" ConfigWatcher localhost 1248
# == update using file "sample2.txt"
java -cp "/home/mawasthi/box/zookeeper-3.4.6/lib/*:/home/mawasthi/box/zookeeper-3.4.6/*:target/zareto-4.0.jar" ConfigUpdater localhost /home/mawasthi/tinker/hackathon/venturesity/final2/sample2.txt
# == read the updates done for item id = 13579
java -cp "/home/mawasthi/box/zookeeper-3.4.6/lib/*:/home/mawasthi/box/zookeeper-3.4.6/*:target/zareto-4.0.jar" ConfigWatcher localhost 13579

