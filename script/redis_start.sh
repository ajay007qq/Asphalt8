# start redis in backgroud mode

echo $0;

if [ $# -eq 0 ] ; then

echo "=========================>";

echo "1) Usage:   start redis using this script";
echo "2) Note:    execute the script with any argument";
echo "3) Example: $0 -r";

echo "=========================>";

exit 2;

fi

ping=`redis-cli ping`;

echo "=========================>";

if [ "$ping" = "PONG" ] ; then
	
	redis_pid=`ps -ef|grep -m1 redis|grep -v auto|awk '{print $2}'`;

        echo "Redis is running: pid($redis_pid)";
else

        echo "Redis is down...";
		
	echo "Start redis now ...";
		
	sudo redis-server /etc/redis/redis.conf 

fi
