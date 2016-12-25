# deploy script

if [ $# -eq 0 ] ; then

echo "=========================>";

echo "1) Usage:   start deployment using this script";
echo "2) Note:    execute the script with any argument";
echo "3) Example: $0 -r";

echo "=========================>";

exit 2;

fi

echo "=========================>";
echo "Asphalt8 Deployment start...";

host_ip=118.89.33.205;
project=Asphalt8;
code_dir=/mydata/code;

mkdir -p $code_dir;

cd $code_dir;

echo "1. Current path: $code_dir";

echo "2. SVN checkout source code...";

svn co svn://$host_ip/$project $code_dir;

ret_code=$?

if [ $ret_code -ne 0 ]; then

echo "## Error: Failed to checkout source code from svn! ##"
exit 1

fi

resource_dir=$code_dir/Asphalt8/src/main/webapp/resources;
image_dir=/mydata/resource_backup/image;
video_dir=/mydata/resource_backup/video;

mkdir -p $resource_dir;

echo "==========================";
echo "resource_dir = $resource_dir";
echo "image_dir = $image_dir";
echo "video_dir = $video_dir";

echo "3. Copy image and video to $resource_dir";

cp -r $image_dir $resource_dir;
cp -r $video_dir $resource_dir;

echo "ls -l $resource_dir";

ls -l $resource_dir;

echo "==========================";
echo "4. Maven build...";

cd $code_dir/Asphalt8;

mvn install package -Dmaven.test.skip=true;

ret_code=$?

if [ $ret_code -ne 0 ]; then

	echo "## Error: Maven failed to build the project! ##"
	exit 1
fi

war=$code_dir/Asphalt8/target/ROOT.war
echo "ls -l $war";
ls -l $war;

echo "4. Deploy to tomcat...";

script_dir=/home/ubuntu/script;

cd $script_dir;

echo "Current dir: $script_dir";
echo "4.1 Stop tomcat...";

./stop_tomcat.sh;

ret_code=$?

if [ $ret_code -ne 0 ]; then

        echo "## Error: failed to stop tomcat! ##"
        exit 1
fi

webapp_dir=/var/lib/tomcat7/webapps;

echo "4.2 Delete existing ROOT.war";

sudo rm -f $webapp_dir/ROOT.war;
sudo rm -rf $webapp_dir/ROOT; 

echo "4.2.1 Delete catalina folder";

catalina=/var/lib/tomcat7/work/Catalina;

sudo rm -rf $catalina;

echo "4.3 Copy war to $webapp_dir";

cp $war $webapp_dir;

ret_code=$?

if [ $ret_code -ne 0 ]; then

        echo "## Error: failed to copy the WAR file to webapp ##"
        exit 1
fi


echo "ls -l $webapp_dir/ROOT.war";
ls -l $webapp_dir/ROOT.war;

echo "4.3 Start redis...";
./redis_start.sh -run;

echo "4.4 Tomcat start ...";

./start_tomcat.sh -run;

ret_code=$?

if [ $ret_code -ne 0 ]; then

        echo "## Error: Tomcat failed to start! ##"
        exit 1
fi

check=tomcat_status.sh;
echo "5. $check ...";
./$check;

echo "6. End";

echo "=========================>";
