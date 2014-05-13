#!/usr/bin/python
#Simple script to spin a machine in EC2

import boto.ec2
import time

connection = boto.ec2.connect_to_region('eu-west-1')
reservation = connection.run_instances(
	image_id = 'ami-896c96fe',  # Ubuntu Server 13.10 (64bit)
	instance_type = 't1.micro',
	key_name = 'AmazonUbuntuLaptopEU',
	#security_groups = ['default'], # No sg for this test
	#user_data=get_script(), # No user data for this test
)

print reservation.instances #Get list of instances

instance = reservation.instances[0] #Only one in our case
print instance.__dict__	#Print all data as a json

# Check up on its status every so often
status = instance.update()
while status == 'pending':
    time.sleep(5)
    status = instance.update()

if status == 'running':
    instance.add_tag("Name","Empty snowflake - Created with boto")
else:
    print('Instance status: ' + status)
