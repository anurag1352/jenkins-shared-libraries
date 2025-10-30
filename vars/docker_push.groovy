def call(String Project, String ImageTag, String dockerHubUser) {
  withCredentials ([usernamePassword(credentialsId:"dockerHub",passwordVariable:"dockerHubPass", usernameVariable:"dockerHubUser"]) {
      sh "docker login -u ${env.dockerHubUser} -p ${env.dockerHubPass}"
  }
  sh "docker push ${env.dockerHubUser}/${Project}:${ImageTag}"
}
