#backup svn

echo "<<=========================>>";

date;

backup_file=/home/ubuntu/mydata/resource_backup/svn/svn.code.$(date +%Y%m%d_%H%M%S).bk.gz;

svnadmin dump /home/ubuntu/mydata/svn/code | gzip > $backup_file;

echo "SVN backup file: $backup_file";
