def call(String Project, String ImageTag, String dockerHubUser) {

    withCredentials([usernamePassword(
        credentialsId: "dockerHub",
        passwordVariable: "dockerHubPass",
        usernameVariable: "dockerHubUser"
    )]) {

        // Login to DockerHub
        sh "docker login -u ${dockerHubUser} -p ${dockerHubPass}"

        // Tag local image → remote DockerHub image name
        sh "docker tag ${Project}:${ImageTag} ${dockerHubUser}/${Project}:${ImageTag}"

        // Push image
        sh "docker push ${dockerHubUser}/${Project}:${ImageTag}"

        echo "Image Push Successful: ${dockerHubUser}/${Project}:${ImageTag}"
    }
}

