# CA1 user data file
# Name: Denis Mooney

yum -y update
yum -y install httpd git elinks
systemctl enable httpd
systemctl start httpd
