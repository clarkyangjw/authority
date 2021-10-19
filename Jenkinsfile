def git_address = "git@github.com:clarkyangjw/authority.git"
def git_auth = "github-auth"
def branch = "master"
//common_project: pd-gateway@8760
//def common_project_name = "pd-gateway@8760"
//project_name: pd-auth-server@8764
def project_name = "pd-auth-server@8764"
//构建版本的名称
def tag = "latest"
def docker_hub_username = "clarkyang"
def docker_hub_auth = "docker-hub-auth"
def k8s_auth = "k8s-auth"
//k8s-dockerhub的连接凭证
def secret_name = "registry-auth-secret"
// def secret_name2 = "registry-auth-secret2"

//Harbor私服地址
//def harbor_url = "192.168.66.102:85"
//Harbor的项目名称
//def harbor_project_name = "tensquare"
//Harbor的凭证
//def harbor_auth = "71eff071-ec17-4219-bae1-5d0093e3d060"

podTemplate(label: 'jenkins-slave', cloud: 'kubernetes',
    containers: [
        containerTemplate(
            name: 'jnlp',
            image: "clarkyang/jenkins-slave-maven:latest",
//            alwaysPullImage: true,
//             resourceLimitCpu: '1.5',
//             resourceLimitMemory: '4Gi',
//             resourceRequestCpu: '1.5',
//             resourceRequestMemory: '4Gi',
        ),
        containerTemplate(
            name: 'docker',
            image: "docker:stable",
            ttyEnabled: true,
            command: 'cat'
        ),
    ],
    volumes: [
        hostPathVolume(mountPath: '/var/run/docker.sock', hostPath: '/var/run/docker.sock'),
        nfsVolume(mountPath: '/usr/local/apache-maven/repo', serverAddress: 'master01' , serverPath: '/opt/nfs/maven'),
    ],
)
{
    node("jenkins-slave"){
        // 第一步
        stage('Step 1: Pulling code from Github'){
            checkout([$class: 'GitSCM', branches: [[name: "${branch}"]], userRemoteConfigs: [[credentialsId: "${git_auth}", url: "${git_address}"]]])
        }
//         stage('Code checking by Sonarqube'){
//             script {
//                 //引入SonarQube Scanner工具
//                 scannerHome = tool 'sonarqube-scanner'
//             }
//             //引入SonarQube Server的服务器环境
//             withSonarQubeEnv('sonarqube-server') {
// //                 sh "${scannerHome}/bin/sonar-scanner"
//                 def selectedProjects = "${project_name}".split(',')
//                 for(int i=0;i<selectedProjects.size();i++){
//                     def currentProject = selectedProjects[i];
//                     def currentProjectName = currentProject.split('@')[0]
//                     def currentProjectPort = currentProject.split('@')[1]
//                     sh """
//                          cd ${currentProjectName}
//                          ${scannerHome}/bin/sonar-scanner
//                          cd ..
//                     """
//                 }
//             }
//         }
        // 第二步
//         stage('Step 2: Building common tools'){
//             //编译并安装公共工程
//             sh "mvn -f pd-tools clean install"
//         }
        // 第三步
        stage('Step 3: Building images and deploying project'){

            //把选择的项目信息转为数组
            def selectedProjects = "${project_name}".split(',')
            for(int i=0;i<selectedProjects.size();i++){
                //取出每个项目的名称和端口
                def currentProject = selectedProjects[i];
                //项目名称
                def currentProjectName = currentProject.split('@')[0]
                //项目启动端口
                def currentProjectPort = currentProject.split('@')[1]

                def parentProjectNames = currentProjectName.split('-')
                if(parentProjectNames.size() > 2){
                    sh """
                        mvn -f pd-apps/${parentProjectNames[0]}-${parentProjectNames[1]}/${parentProjectNames[0]}-${parentProjectNames[1]}-entity clean install
                    """
                }
//                  else{
//                     sh "cd pd-apps"
//                 }


                //定义镜像名称
                def imageName = "${currentProjectName}:${tag}"
                //编译，构建本地镜像
                sh """
                    mvn -f pd-apps/${parentProjectNames[0]}-${parentProjectNames[1]}/${currentProjectName} clean package dockerfile:build
                """
                container('docker') {
                    //给镜像打标签
                    sh "docker tag ${imageName} ${docker_hub_username}/${imageName}"
                    //登录docker_hub，并上传镜像
                    withCredentials([usernamePassword(credentialsId: "${docker_hub_auth}", passwordVariable: 'password', usernameVariable: 'username')]){
                        //登录
                        sh "docker login -u ${username} -p ${password}"
                        //上传镜像
                        sh "docker push ${docker_hub_username}/${imageName}"
                    }
                    //删除本地镜像
                    sh "docker rmi -f ${imageName}"
                    sh "docker rmi -f ${docker_hub_username}/${imageName}"
                }
                def deploy_image_name = "${docker_hub_username}/${imageName}"
                //部署到K8S
                sh """
                    sed -i 's#\$IMAGE_NAME#${deploy_image_name}#' pd-apps/pd-auth/${currentProjectName}/deploy.yml
                    sed -i 's#\$SECRET_NAME#${secret_name}#' pd-apps/pd-auth/${currentProjectName}/deploy.yml
                """
                //cat ${currentProjectName}/deploy.yml
                //ls /usr/local/apache-maven/repo
                kubernetesDeploy(kubeconfigId: "${k8s_auth}",
                                 configs: "${currentProjectName}/deploy.yml",
//                                  enableConfigSubstitution: false,
//                                  secretName: "${secret_name}",
//                                  dockerCredentials: [
//                                     [credentialsId: '341e4f73-a377-466c-b7a8-4fc92f66006b'],
//                                  ],
                )
//                 if(parentProjectNames.size() > 2){
//                     sh """
//                         cd ..
//                         cd ..
//                     """
//                 }else{
//                     sh "cd .."
//                 }
            }
        }
        // 第四步
        stage ('Step 4: Sending Email about deploying result'){
            emailext(
                subject: 'Deploying notification: ${PROJECT_NAME} - Build # ${BUILD_NUMBER} - ${BUILD_STATUS}!',
                body: '${FILE,path="email.html"}',
                to: 'senecacbbs@gmail.com'
            )
        }

    }
}