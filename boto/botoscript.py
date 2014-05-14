#!/usr/bin/python
#Exercise: Rosetta one - https://github.com/jecnua/rosetta-one
#@author <a href="mailto:jecnua@gmail.com">jecnua</a>
#@version 1.0

import boto.ec2
import time

connection = boto.ec2.connect_to_region('eu-west-1')
reservation = connection.run_instances(
	image_id = 'ami-896c96fe',  # Ubuntu Server 13.10 (64bit)
	instance_type = 't1.micro',
	key_name = 'AmazonUbuntuLaptopEU',
	security_groups = ['rosetta-one'], #sg-10ca3275 - The Java SDK requires the id, while boto accepts the name
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

print "Empty box in EC2 + TAG + SG - DONE"
