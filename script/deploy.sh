# deploy script

echo $0
echo "=========================>"

if [ $# -eq 0 ]; then

echo "1) Usage:   start deploy using this script"
echo "2) Note:    execute the script with any argument"
echo "3) Example: $0 -r"

echo "=========================>"

exit 2

fi

log_dir=/home/ubuntu/script/log

mkdir -p $log_dir

./deployment.sh -run > $log_dir/deployment.log&

echo "==> deplyment log: $log_dir/deployment.log"

tail -f $log_dir/deployment.log

