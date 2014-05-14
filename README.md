rosetta-one
===========

*Exercise*: Spin an empty machine in EC2 (VPC).
Simple test done in JavaSDK for AWS / boto.
The same steps will be implemented in both.

Prerequisite
------------
Both boto and the sdk must have the credential in place.
Boto: https://code.google.com/p/boto/wiki/BotoConfig
aws-cli: $aws configure

TODO
----
+ Add the instance to a security group in java / boto
+ Put name key name on configuration file and remove it from code (after this create a new key ;D)
+ Use user data in java / boto

DONE
----
+ Spin a new instance in java/boto
+ Tag the new instance in java/boto
