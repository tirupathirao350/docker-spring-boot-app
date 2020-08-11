pipeline{
  agent any
  tools { 
        maven 'MAVEN_HOME'
        jdk 'JAVA_HOME'
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
            withDockerRegistry(credentialsId: 'ecr:us-east-1:aws-cred', url: 'http://349229500297.dkr.ecr.us-east-1.amazonaws.com/account-service') {
             sh 'docker tag bank-service:latest 349229500297.dkr.ecr.us-east-1.amazonaws.com/bank-service'
             sh 'docker push 349229500297.dkr.ecr.us-east-1.amazonaws.com/bank-service'

             sh 'docker tag branch-service:latest 349229500297.dkr.ecr.us-east-1.amazonaws.com/branch-service'
             sh 'docker push 349229500297.dkr.ecr.us-east-1.amazonaws.com/branch-service'

             sh 'docker tag customer-service:latest 349229500297.dkr.ecr.us-east-1.amazonaws.com/customer-service'
             sh 'docker push 349229500297.dkr.ecr.us-east-1.amazonaws.com/customer-service'

             sh 'docker tag account-service:latest 349229500297.dkr.ecr.us-east-1.amazonaws.com/account-service'
             sh 'docker push 349229500297.dkr.ecr.us-east-1.amazonaws.com/account-service'

             sh 'docker tag transaction-service:latest 349229500297.dkr.ecr.us-east-1.amazonaws.com/transaction-service'
             sh 'docker push 349229500297.dkr.ecr.us-east-1.amazonaws.com/transaction-service'
            }
          }
    }
        stage('Run docker images on kubernetes cluster') {
          steps {
            node('eks-master-node'){    
              checkout scm
             sh 'git checkout feature-1.1'
             sh 'export KUBECONFIG=~/.kube/config'
             sh 'aws eks --region us-east-1 update-kubeconfig --name terraform-eks-demo'
             sh 'kubectl apply -f deployment.yaml'
             sh 'kubectl apply -f service.yaml'
             sh 'kubectl apply -f ingress.yaml'
            
            }
          }
        }
  }
}
