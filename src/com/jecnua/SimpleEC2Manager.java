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

public class SimpleEC2Manager {

	public static void main(String[] args){
		
		//First let's get the credential
		//The following method will search for the credential in ~/.aws
		//It can throw an exception but the 
		AWSCredentials defaultAWSCredential = new ProfileCredentialsProvider().getCredentials();
		
		//Prepare the client for the service
		AmazonEC2 anEC2ServiceClient = new AmazonEC2Client(defaultAWSCredential);
		//Where
		anEC2ServiceClient.setEndpoint("ec2.eu-west-1.amazonaws.com");
		
		//Define a new instance
		RunInstancesRequest anEmptyBox = new RunInstancesRequest()
	    .withInstanceType("t1.micro")
	    .withImageId("ami-896c96fe") //Ubuntu Server 14.04 LTS (PV) (64-bit)
	    .withMinCount(1)
	    .withMaxCount(1)
//	    .withSecurityGroupIds("tomcat")
	    .withKeyName("AmazonUbuntuLaptopEU")
//	    .withUserData(Base64.encodeBase64String(myUserData.getBytes()))
	    ;
		
		RunInstancesResult aSpinnedBox = anEC2ServiceClient.runInstances(anEmptyBox);
		
		//Tag IT
		List<Instance> instances = aSpinnedBox.getReservation().getInstances();
		Instance instance = instances.get(0);
		  CreateTagsRequest createTagsRequest = new CreateTagsRequest();
		  createTagsRequest.withResources(instance.getInstanceId()) //
		      .withTags(new Tag("Name", "test"));
		  anEC2ServiceClient.createTags(createTagsRequest);
	
	    System.out.println("===========================================");
	    System.out.println("Empty box in EC2 + TAG - DONE");
	    System.out.println("===========================================\n");
	    
	}
}
