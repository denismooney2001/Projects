This CA consists of using functions of AWS such as Simple Queue Service (SQS) and using an S3 Bucket. An EC2 instance is created along with a user_data.sh file allowing the configuration of the EC2 instance.

Scripts:

system_setup.ps1: Sets up verything that is needed to run the program e.g. VPC, EC2, S3, SQS.

user_data.sh: Contains commans when setting up the EC2 instance.

system_demo.ps1: Runs the functions of an existing S3 Bucket and an existing SQS and sends messages to a queue.

consumer.ps1: Retrieves messages from the queue & Prints the messages.

system_teardown.ps1: This removes all instances created.

text.txt: File sent to the S3 Bucket (displayed in system_demo.ps1)

system_design.pdf: Diagram and description of how the application runs.

Grade: 76% (1.1)