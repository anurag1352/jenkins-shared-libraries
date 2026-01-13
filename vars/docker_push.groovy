def call(String Project, String ImageTag, String dockerHubUser) {

    withCredentials([
        usernamePassword(
            credentialsId: "dockerHubCreds",
            usernameVariable: "dockerHubUser",
            passwordVariable: "dockerHubPass"
        )
    ]) {

        // Login
        sh "echo ${env.dockerHubPass} | docker login -u ${env.dockerHubUser} --password-stdin"

        // Push directly → no tagging needed
        sh "docker push ${dockerHubUser}/${Project}:${ImageTag}"

        echo "Image Push Successful: ${dockerHubUser}/${Project}:${ImageTag}"
    }
}
