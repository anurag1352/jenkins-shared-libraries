def call(String Project, String ImageTag, String dockerHubUserParam) {

    withCredentials([
        usernamePassword(
            credentialsId: "dockerHub",
            usernameVariable: "dockerHubUser",
            passwordVariable: "dockerHubPass"
        )
    ]) {

        // Login to DockerHub using Jenkins credentials
        sh "docker login -u ${env.dockerHubUser} -p ${env.dockerHubPass}"

        // Tag local image → remote DockerHub image
        sh "docker tag ${Project}:${ImageTag} ${dockerHubUserParam}/${Project}:${ImageTag}"

        // Push image
        sh "docker push ${dockerHubUserParam}/${Project}:${ImageTag}"

        echo "Image Push Successful: ${dockerHubUserParam}/${Project}:${ImageTag}"
    }
}

