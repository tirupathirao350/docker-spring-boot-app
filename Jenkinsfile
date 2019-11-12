pipeline{
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
    stage('Build and Generate Docker Images') {
      steps {
        sh 'mvn -B -DskipTests clean package'
        sh 'echo $USER'
        sh 'echo whoami'
      }
    }
    stage('Push images to aws ecr'){
          steps {
            withDockerRegistry(credentialsId: 'ecr:us-east-2:aws-credentials', url: 'http://067421168594.dkr.ecr.us-east-2.amazonaws.com/account-service') {
             sh 'docker tag bank-service:latest 067421168594.dkr.ecr.us-east-2.amazonaws.com/bank-service'
             sh 'docker push 067421168594.dkr.ecr.us-east-2.amazonaws.com/bank-service'

             sh 'docker tag branch-service:latest 067421168594.dkr.ecr.us-east-2.amazonaws.com/branch-service'
             sh 'docker push 067421168594.dkr.ecr.us-east-2.amazonaws.com/branch-service'

             sh 'docker tag customer-service:latest 067421168594.dkr.ecr.us-east-2.amazonaws.com/customer-service'
             sh 'docker push 067421168594.dkr.ecr.us-east-2.amazonaws.com/customer-service'

             sh 'docker tag account-service:latest 067421168594.dkr.ecr.us-east-2.amazonaws.com/account-service'
             sh 'docker push 067421168594.dkr.ecr.us-east-2.amazonaws.com/account-service'

             sh 'docker tag transaction-service:latest 067421168594.dkr.ecr.us-east-2.amazonaws.com/transaction-service'
             sh 'docker push 067421168594.dkr.ecr.us-east-2.amazonaws.com/transaction-service'
            }
          }
    }
        stage('Run docker images on kubernetes cluster') {
          steps {
            node('EKS-master'){    
              checkout scm
             sh 'export KUBECONFIG=~/.kube/config'
             sh 'kubectl apply -f deployment.yaml'
             sh 'kubectl apply -f service.yaml'
            // sh 'kubectl apply -f ingress.yaml'
            
            }
          }
        }
  }
}
