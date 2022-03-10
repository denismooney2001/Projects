# CA1 Teardown file
# Name: Denis Mooney

# VPC Name
$VpcName = 'vpc_ca1'

# Get VPC ID by VPC Name
$VpcId=(aws ec2 describe-vpcs --filter Name=tag:Name,Values=$VpcName | ConvertFrom-Json).Vpcs[0].VpcId

# Checks if the VPC 'vpc_ca1' exists
if ( $VpcId -eq $null ) {
	Write-Host "no $VpcName found";
	Exit;
}

# Instances in VPC
$Instances = (aws ec2 describe-instances --filter Name=vpc-id,Values=$($VpcId)|ConvertFrom-Json).Reservations.Instances
	
# Terminate instances in VPC
foreach ( $Instance in $Instances) {
	$InstanceId=$Instance.InstanceId
	Write-Host "terminating instance $InstanceId"
	aws ec2 terminate-instances --instance-id $InstanceId
}

# Wait for instances to terminate
foreach ( $Instance in $Instances ) {
	Write-Host "waiting for instance $InstanceId to terminate..."
	aws ec2 wait instance-terminated --instance-ids $InstanceId
}

# Delete subnets
$Subnets=(aws ec2 describe-subnets --filter Name=vpc-id,Values=$VpcId | ConvertFrom-Json).Subnets
foreach ( $Subnet in $Subnets ) {
	# Delete subnet
	$SubnetId=$Subnet.SubnetId
	Write-Host "deleting subnet $SubnetId"
	aws ec2 delete-subnet --subnet-id $SubnetId
}

# Detach internet gateway
$IGWs=(aws ec2 describe-internet-gateways --filters Name=attachment.vpc-id,Values=$VpcId | ConvertFrom-Json).InternetGateways
foreach ( $IGW in $IGWs) {
	$IGWId = $IGW.InternetGatewayId
	Write-Host "detaching internet gateway $IGWId"
	aws ec2 detach-internet-gateway --vpc-id $VpcId --internet-gateway-id $IGWId
	Write-Host "deleting internet gateway $IGWId"
	aws ec2 delete-internet-gateway --internet-gateway-id $IGWId
}

# Delete security groups
Write-Host 'deleting security groups'
$SGs=(aws ec2 describe-security-groups --filters Name=vpc-id,Values=$VpcId | ConvertFrom-Json).SecurityGroups
foreach ( $SG in $SGs ) {
	if ( $SG.GroupName -eq 'default') {
		Write-Host 'skipping default security group'
		continue;
	}
	$SGId = $SG.GroupId
	Write-Host "deleting security group $SGId"
	aws ec2 delete-security-group --group-id $SGId
}

# Delete the VPC
Write-Host "deleting VPC"
aws ec2 delete-vpc --vpc-id $VpcId

# Delete S3 Bucket 
Write-Host "deleting Bucket"
aws s3 rb s3://denismooney-ca1-bucket --force

# Delete Queue
Write-Host "deleting Queue"
$QueueUrl=(aws sqs get-queue-url --queue-name denis-ca1-queue | ConvertFrom-Json).QueueUrl
aws sqs delete-queue --queue-url $QueueUrl

Write-Host "Sytem Teardown Complete." -ForegroundColor Green
