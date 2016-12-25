# git push script

if [ $# -eq 0 ] ; then
	
	echo "1) Usage: push source files to github using this script";
	echo "2) Note:  run the script with git commit comment";
	echo "3) Example: $0 'add new class'";
	
	exit 2;

fi

echo "===========================>";

code_dir=/mydata/code/Asphalt8;
cd $code_dir;
git init;

echo "1. Current folder: $code_dir";
echo "2. Git add file to control";

git add * ;

image=$code_dir/src/main/webapp/resources/image;
video=$code_dir/src/main/webapp/resources/video;
target=$code_dir/target;

git rm -r --cached $image;
git rm -r --cached $video;
git rm -r --cached $target;

echo "3.1 Ignore folder: $image";
echo "3.2 Ignore folder: $video";
echo "3.3 Ignore folder: $target";

echo "4. Commit files to local git repository";

git commit -m $1;

echo "5. Github remote repository";

git remote -v;

echo "6. Push source files to github";

git push origin master;

echo "===========================>";
