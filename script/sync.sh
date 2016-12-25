#sync the folders
IMG=/usr/tomcat/apache-tomcat-7.0.70/webapps/ROOT/resources/image;
VIDEO=/usr/tomcat/apache-tomcat-7.0.70/webapps/ROOT/resources/video; 
DEST=/mydata/resource_backup; 

echo "<<=======================>>" 
date; 

echo "Image folder => $IMG"; 
echo "Video folder => $VIDEO"; 
echo "Backup folder => $DEST"; 

rsync -azvr $IMG $DEST; 
rsync -azvr $VIDEO $DEST; 

