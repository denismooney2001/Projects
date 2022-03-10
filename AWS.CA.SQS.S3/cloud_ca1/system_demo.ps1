# CA1 Demo file
# Name: Denis Mooney

#Tests to see if file is already created
if(!(Test-Path -Path test.txt)){
    New-Item test.txt
    Write-Host "New File created 'test.txt'"
    Set-Content test.txt 'Test file to S3 Bucket'
}else{
    Write-Host "File already exists!" 
}

# QueueUrl retrieved by Queue Name 'denis-ca1-queue'
$QueueUrl=(aws sqs get-queue-url --queue-name denis-ca1-queue | ConvertFrom-Json).QueueUrl

Write-Host "(1) In this Demonstration, we are moving a file to the S3 Bucket 'denismooney-ca1-bucket'" -ForegroundColor Yellow

# Copy test.txt to S3 Bucket 
aws s3 cp test.txt s3://denismooney-ca1-bucket
Write-Host "File moved to bucket" 


Write-Host "List of files in the 'denismooney-ca1-bucket'" 
# Lists files in 'denismooney-ca1-bucket'
aws s3 ls s3://denismooney-ca1-bucket

Write-Host "Renaming test.txt to newTest.txt"
# Rename test.txt to newTest.txt
aws s3 mv s3://denismooney-ca1-bucket/test.txt s3://denismooney-ca1-bucket/newTest.txt

Write-Host "Deleting file 'newTest.txt'" 
# Deleting file 'newTest.txt'
aws s3 rm s3://denismooney-ca1-bucket/newTest.txt

Write-Host "(2) In this Demonstration, we are sending messages to 'denis-ca1-queue'" -ForegroundColor Yellow

# Sends Messages to Queue URL
aws sqs send-message --queue-url $QueueUrl --message-body "Test Message 1"
aws sqs send-message --queue-url $QueueUrl --message-body "Test Message 2"
aws sqs send-message --queue-url $QueueUrl --message-body "Test Message 3"
Write-Host "Messages Sent to the queue!"
Write-Host "Run the consumer.ps1 file to retrieve these messages." -ForegroundColor Yellow

Write-Host "Demonstration Complete." -ForegroundColor Green