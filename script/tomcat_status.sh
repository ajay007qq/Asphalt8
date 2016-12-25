# check tomcat status

echo "======================>";
echo "check tomcat status...";
echo "======================>";

pid=`ps -ef|grep java|grep -m1 tomcat|grep -v auto|awk '{print $2}'`;

if [ "$pid" = "" ] ; then
	echo "tomcat is not running";
else
	echo "tomcat is runing: pid($pid)"
fi	

echo "======================>";
