pipeline{
  agent any
  tools {
        maven 'MAVEN_HOME'
  }
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
            withDockerRegistry(credentialsId: 'ecr:us-west-2:aws-cred', url: 'http://154965021809.dkr.ecr.us-west-2.amazonaws.com/account-service') {
             sh 'docker tag bank-service:latest 154965021809.dkr.ecr.us-west-2.amazonaws.com/bank-service'
             sh 'docker push 154965021809.dkr.ecr.us-west-2.amazonaws.com/bank-service'

             sh 'docker tag branch-service:latest 154965021809.dkr.ecr.us-west-2.amazonaws.com/branch-service'
             sh 'docker push 154965021809.dkr.ecr.us-west-2.amazonaws.com/branch-service'

             sh 'docker tag customer-service:latest 154965021809.dkr.ecr.us-west-2.amazonaws.com/customer-service'
             sh 'docker push 154965021809.dkr.ecr.us-west-2.amazonaws.com/customer-service'

             sh 'docker tag account-service:latest 154965021809.dkr.ecr.us-west-2.amazonaws.com/account-service'
             sh 'docker push 154965021809.dkr.ecr.us-west-2.amazonaws.com/account-service'

             sh 'docker tag transaction-service:latest 154965021809.dkr.ecr.us-west-2.amazonaws.com/transaction-service'
             sh 'docker push 154965021809.dkr.ecr.us-west-2.amazonaws.com/transaction-service'
            }
          }
    }
        stage('Run docker images on kubernetes ') {
          steps {
            node('eks-master-node'){    
              checkout scm
             sh 'git checkout feature-1.1'
             sh 'export KUBECONFIG=~/.kube/config'
             sh 'aws eks --region us-east-1 update-kubeconfig --name terraform-eks-demo'
             sh 'kubectl apply -f deployment.yaml'
             sh 'kubectl apply -f service.yaml'
            }
          }
        }
    
  }
}
