# check redis running status

echo "======================>";
echo "check redis status...";
echo "======================>";

ping=`redis-cli ping`;

if [ "$ping" = "PONG" ] ; then

	echo "Redis is running...";
else

	echo "Redis is down ...";

fi

