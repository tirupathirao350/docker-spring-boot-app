properties([pipelineTriggers([githubPush()])])

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
      }
    }
    stage('Run docker images with docker-compose') {
      steps {
         sh 'docker-compose up'
      }
    }
  }
}
