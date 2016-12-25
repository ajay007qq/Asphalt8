# start tomcat

echo $0

if [ $# -eq 0 ]; then

echo "=========================>"

echo "1) Usage:   start tomcat using this script"
echo "2) Note:    execute the script with any argument"
echo "3) Example: $0 -r"

echo "=========================>"

exit 2

fi

echo "================>"
echo "start tomcat ..."
echo "================>"
sudo /etc/init.d/tomcat7 start
echo "================="

pid=`ps -ef|grep java|grep -m1 tomcat|grep -v auto|awk '{print $2}'`

if [ "$pid" != "" ]; then

	echo "tomcat pid => $pid"

else 

	echo "tomcat is not start yet!"	
	
fi

echo "================="
