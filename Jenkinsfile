pipeline{
  agent any
  stages {
    stage('Clone repository') {
        /* Let's make sure we have the repository cloned to our workspace... */
      steps {
        checkout scm
      }
    }
    stage('Build and Generate Docker Images') {
      steps {
        sh 'mvn -B -DskipTests clean package'
        sh 'echo $USER'
        sh 'echo whoami'
      }
    }
    stage('Push images to aws ecr'){
          steps {
             withDockerRegistry(credentialsId: 'ecr:us-east-1:aws-credentials', url: 'http://092390458462.dkr.ecr.us-east-1.amazonaws.com/bank-service') {
             sh 'docker tag anil9848/bank-service:latest 092390458462.dkr.ecr.us-east-1.amazonaws.com/bank-service'
             sh 'docker push 092390458462.dkr.ecr.us-east-1.amazonaws.com/bank-service'
            }
             withDockerRegistry(credentialsId: 'ecr:us-east-1:aws-credentials', url: 'http://092390458462.dkr.ecr.us-east-1.amazonaws.com/branch-service') {
             sh 'docker tag anil9848/branch-service:latest 092390458462.dkr.ecr.us-east-1.amazonaws.com/branch-service'
             sh 'docker push 092390458462.dkr.ecr.us-east-1.amazonaws.com/branch-service'
            }
             withDockerRegistry(credentialsId: 'ecr:us-east-1:aws-credentials', url: 'http://092390458462.dkr.ecr.us-east-1.amazonaws.com/customer-service') {
             sh 'docker tag anil9848/customer-service:latest 092390458462.dkr.ecr.us-east-1.amazonaws.com/customer-service'
             sh 'docker push 092390458462.dkr.ecr.us-east-1.amazonaws.com/customer-service'
            }
             withDockerRegistry(credentialsId: 'ecr:us-east-1:aws-credentials', url: 'http://092390458462.dkr.ecr.us-east-1.amazonaws.com/account-service') {
             sh 'docker tag anil9848/account-service:latest 092390458462.dkr.ecr.us-east-1.amazonaws.com/account-service'
             sh 'docker push 092390458462.dkr.ecr.us-east-1.amazonaws.com/account-service'
            }
             withDockerRegistry(credentialsId: 'ecr:us-east-1:aws-credentials', url: 'http://092390458462.dkr.ecr.us-east-1.amazonaws.com/transaction-service') {
             sh 'docker tag anil9848/transaction-service:latest 092390458462.dkr.ecr.us-east-1.amazonaws.com/transaction-service'
             sh 'docker push 092390458462.dkr.ecr.us-east-1.amazonaws.com/transaction-service'
            }
          }
    }
        stage('Run docker images on kubernetes cluster') {
          steps {
            node('EKS-master'){
             sh 'kubectl apply -f deployment.yaml'
             sh 'kubectl apply -f service.yaml'
             sh 'kubectl apply -f ingress.yaml'
            }
          }
        }
  }
}
