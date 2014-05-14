package com.jecnua;

import java.util.List;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.CreateTagsRequest;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.ec2.model.RunInstancesResult;
import com.amazonaws.services.ec2.model.Tag;

/***
 * Exercise: Rosetta one - https://github.com/jecnua/rosetta-one
 * 
 * @author <a href="mailto:jecnua@gmail.com">jecnua</a>
 * @version 1.0
 */
public class SimpleEC2Manager {

	public static void main(String[] args) {

		// First let's get the credential
		// The following method will search for the credential in ~/.aws
		AWSCredentials defaultAWSCredential = new ProfileCredentialsProvider()
				.getCredentials();

		// Prepare the client for the service
		AmazonEC2 anEC2ServiceClient = new AmazonEC2Client(defaultAWSCredential);
		// Choose the endpoint
		anEC2ServiceClient.setEndpoint("ec2.eu-west-1.amazonaws.com");

		/**
		 * NOTE: Only in Java is asked the minCount and the maxCount when
		 * requesting a new box...
		 */

		// Define a new instance
		RunInstancesRequest anEmptyBox = new RunInstancesRequest()
				.withInstanceType("t1.micro").withImageId("ami-896c96fe")
				/** Ubuntu Server 14.04 LTS (PV)(64-bit) **/
				.withMinCount(1).withMaxCount(1)
				.withSecurityGroupIds("sg-10ca3275")
				.withKeyName("AmazonUbuntuLaptopEU");

		RunInstancesResult aSpinnedBox = anEC2ServiceClient
				.runInstances(anEmptyBox);

		// Tag IT
		List<Instance> instances = aSpinnedBox.getReservation().getInstances();
		Instance instance = instances.get(0);
		CreateTagsRequest createTagsRequest = new CreateTagsRequest();
		createTagsRequest.withResources(instance.getInstanceId()).withTags(
				new Tag("Name", "Empty snowflake - Created with JavaSDK"));
		anEC2ServiceClient.createTags(createTagsRequest);

		System.out.println("===========================================");
		System.out.println("Empty box in EC2 + TAG + SG - DONE");
		System.out.println("===========================================\n");

	}
}
