pipeline {
  agent any
  tools { 
        maven 'Maven'
        jdk 'JAVA_HOME'
  }
  stages {
    stage('Clone repository') {
        /* Let's make sure we have the repository cloned to our workspace... */
      steps {
        checkout scm
      }
    }
    stage('Build Project and Generate Docker Images') {
      steps {
        sh 'mvn clean install -DskipTests'
        sh 'echo $USER'
        sh 'echo whoami'
      }
    }
     stage('Push images to aws ecr'){
          steps {
            withDockerRegistry(credentialsId: 'ecr:us-east-2:aws-credentials', url: 'http://092390458462.dkr.ecr.us-east-2.amazonaws.com/account-service') {
             sh 'docker tag bank-service:latest 092390458462.dkr.ecr.us-east-2.amazonaws.com/bank-service'
             sh 'docker push 092390458462.dkr.ecr.us-east-2.amazonaws.com/bank-service'

             sh 'docker tag branch-service:latest 092390458462.dkr.ecr.us-east-2.amazonaws.com/branch-service'
             sh 'docker push 092390458462.dkr.ecr.us-east-2.amazonaws.com/branch-service'

             sh 'docker tag customer-service:latest 092390458462.dkr.ecr.us-east-2.amazonaws.com/customer-service'
             sh 'docker push 092390458462.dkr.ecr.us-east-2.amazonaws.com/customer-service'

             sh 'docker tag account-service:latest 092390458462.dkr.ecr.us-east-2.amazonaws.com/account-service'
             sh 'docker push 092390458462.dkr.ecr.us-east-2.amazonaws.com/account-service'

             sh 'docker tag transaction-service:latest 092390458462.dkr.ecr.us-east-2.amazonaws.com/transaction-service'
             sh 'docker push 092390458462.dkr.ecr.us-east-2.amazonaws.com/transaction-service'
            }
          }
    }
    stage('Run docker images with docker-compose') {
      steps {
          checkout scm
         sh 'docker-compose up'
      
      }
    }
  }
}
