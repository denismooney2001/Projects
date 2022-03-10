# CA1 SQS consumer script
# Name: Denis Mooney

# Continuous Loop
$QueueUrl=(aws sqs get-queue-url --queue-name denis-ca1-queue | ConvertFrom-Json).QueueUrl
while (1) {
    # Receives the message from the Queue URL
    $msg_text = (aws sqs receive-message --queue-url $QueueUrl --wait-time-seconds 1 --max-number-of-messages 1)
    
    # Maximum Waiting Time
    $max_wait = 15

    # Checks if the next $msg_text is empty
    if ( $msg_text -eq $null) {
        Write-host "No new messages" -ForegroundColor Blue

        #Exits while loop
        exit
    }

    # Gets the message and converts from JSON
    $msg = ($msg_text | ConvertFrom-Json).Messages[0]

    Write-Host "Received: " -NoNewline -ForegroundColor Green

    # Prints Message
    Write-Host $msg.Body

    # Deletes Message
    aws sqs delete-message --queue-url $QueueUrl --receipt-handle $msg.ReceiptHandle
}