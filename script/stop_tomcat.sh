# stop tomcat

echo "================>"
echo "stop tomcat ..."
echo "================>"
sudo /etc/init.d/tomcat7 stop
echo "================="

pid=`ps -ef|grep java|grep -m1 tomcat|grep -v auto|awk '{print $2}'`

if [ "$pid" != "" ]; then

	echo "tomcat pid => $pid"
	echo "kill => $pid"
	sudo kill -9 $pid

else

        echo "tomcat is stop"

fi

echo "================="
