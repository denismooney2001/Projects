# CA1 Setup file
# Name: Denis Mooney

# Gather all vpc's value equal to 'vpc_ca1'
$Vpcs=(aws ec2 describe-vpcs --filter Name=tag:Name,Values=vpc_ca1 | ConvertFrom-Json).Vpcs

# Count VPC's objects in the $Vpcs
if ( $Vpcs.Count -ge 1 ) {
    throw "found $($Vpcs.Count) VPCs named vpc_ca1. Please run system.teardown.ps1"
}

# Create VPC
$Vpc = (aws ec2 create-vpc --cidr-block 10.0.0.0/16 | ConvertFrom-Json).Vpc
Write-Host "vpc-id $($Vpc.VpcId)"

aws ec2 create-tags --resources $Vpc.VpcId --tags Key=Name,Value=vpc_ca1

# Create Subnet
$Subnet = (aws ec2 create-subnet --vpc-id $Vpc.VpcId --cidr-block 10.0.1.0/24 | ConvertFrom-Json).Subnet
Write-Host "subnet-id $($Subnet.SubnetId)"

# Ensures Public IP is visible in the instance
aws ec2 modify-subnet-attribute --subnet-id $Subnet.SubnetId --map-public-ip-on-launch

aws ec2 create-tags --resources $Subnet.SubnetId --tags Key=Name,Value=ca1_SN

# Create and Attach internet gateway
$IGW = (aws ec2 create-internet-gateway | ConvertFrom-Json).InternetGateway
Write-Host "internet-gateway-id $($IGW.InternetGatewayId)"
aws ec2 create-tags --resources $IGW.InternetGatewayId --tags Key=Name,Value=LAB_IGW
aws ec2 attach-internet-gateway --vpc-id $Vpc.VpcId --internet-gateway-id $IGW.InternetGatewayId

# Add route to the route table
$RT = (aws ec2 describe-route-tables --filters Name=vpc-id,Values=$($Vpc.VpcId) | ConvertFrom-Json).RouteTables[0]
Write-Host "route-table-id $($RT.RouteTableId)"
aws ec2 create-tags --resources $RT.RouteTableId --tags Key=Name,Value=LAB_RTB
aws ec2 create-route --route-table-id $RT.RouteTableId --gateway-id $IGW.InternetGatewayId --destination-cidr-block 0.0.0.0/0

# create the security group
$SGId = (aws ec2 create-security-group --group-name LAB_SG --description LAB_SG --vpc-id $Vpc.VpcId | ConvertFrom-Json).GroupId
Write-Host "security-group-id $SGId"
aws ec2 authorize-security-group-ingress --group-id $SGId --protocol tcp --port 22 --cidr 0.0.0.0/0

# Create Instance and Get Instance Id
$InstanceId = (aws ec2 run-instances --instance-type t2.nano --security-group-ids $SGId --subnet-id $Subnet.SubnetId --image-id resolve:ssm:/aws/service/ami-amazon-linux-latest/amzn2-ami-hvm-x86_64-gp2 --key-name vockey --iam-instance-profile Name=LabInstanceProfile --user-data file://user_data.sh | ConvertFrom-Json).Instances.InstanceId
Write-Host "instance-id $InstanceId"

# Get public IP address
$PublicIpAddress = (aws ec2 describe-instances --instance-id $InstanceId | ConvertFrom-Json).Reservations.Instances.PublicIpAddress
Write-Host "Public IP address $PublicIpAddress"

#Create S3 Bucket named 'ca1_bucket'
aws s3 mb s3://denismooney-ca1-bucket
Write-Host "Bucket 'denismooney-ca1-bucket' created"

#Create SQS named 'denis-ca1-queue'
Write-Host "Queue 'denis-ca1-queue' created"
$QueueUrl=(aws sqs create-queue --queue-name denis-ca1-queue | ConvertFrom-Json).QueueUrl

Write-Host "Sytem Setup Complete." -ForegroundColor Green
