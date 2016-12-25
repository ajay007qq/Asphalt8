#backup mysql
echo "<<=========================>>";
date;
backup_file=/mydata/mysql/Asphalt8_bk_$(date +%Y%m%d_%H%M%S).sql.gz;

echo "Mysql backup file: $backup_file";

mysqldump -uroot -proot asphalt8 | gzip > $backup_file;

