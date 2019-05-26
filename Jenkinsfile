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
    stage('Install docker and docker-compose') {
          steps {
            sh 'sudo yum update'
            sh 'sudo yum install -y docker'
            sh 'sudo systemctl start docker'
            sh 'sudo setfacl -m user:ec2-user:rw /var/run/docker.sock'
            sh 'sudo curl -L https://github.com/docker/compose/releases/download/1.20.0/docker-compose-`uname -s`-`uname -m` -o /usr/local/bin/docker-compose'
            sh 'sudo chmod +x /usr/local/bin/docker-compose'
            sh 'sudo chkconfig docker on'
            sh 'sudo service docker start'
            sh 'sudo ln -s /usr/local/bin/docker-compose /usr/bin/docker-compose'
          }
        }
    stage('Build Project and Generate Docker Images') {
      steps {
        sh 'mvn -B -DskipTests clean package'
        sh 'echo $USER'
        sh 'echo whoami'
      }
    }
    stage('Run docker images with docker-compose') {
      steps {
              sh 'docker-compose up'
            }
    }
  }
}
