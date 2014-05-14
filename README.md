rosetta-one
===========

*Exercise*: Spin an empty machine in EC2 (VPC), set an user group and tag it.
The test is implemented in both JavaSDK for AWS and boto.

Prerequisite
------------
Both boto and the SDK must have the AWS credentials (tokens) in place.
Boto: https://code.google.com/p/boto/wiki/BotoConfig
aws-cli: Run $aws configure

DONE
----
+ Spin a new instance in java/boto
+ Tag the new instance in java/boto
+ Add the instance to a security group in java/boto
