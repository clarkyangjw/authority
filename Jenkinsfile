def git_address = "git@github.com:clarkyangjw/authority.git"
def git_auth = "github-auth"
def branch = "master"
//project_name: pd-auth-server@8764,pd-gateway@8760
def project_name = "pd-auth-server@8764,pd-gateway@8760"
def dockerImagePrefix = "pinda"
def projectRootNames = "pd-apps"
//构建版本的名称
def tag = "latest"
def docker_hub_username = "clarkyang"
def docker_hub_auth = "docker-hub-auth"
def k8s_auth = "k8s-auth"
//k8s-dockerhub的连接凭证
def secret_name = "registry-auth-secret"


podTemplate(label: 'jenkins-slave', cloud: 'kubernetes',
    containers: [
        containerTemplate(
            name: 'jnlp',
            image: "clarkyang/jenkins-slave-maven:latest",
        ),
        containerTemplate(
            name: 'docker',
            image: "docker:stable",
            ttyEnabled: true,
            command: 'cat',
        ),
    ],
    volumes: [
        hostPathVolume(mountPath: '/var/run/docker.sock', hostPath: '/var/run/docker.sock'),
        nfsVolume(mountPath: '/usr/local/apache-maven/repo', serverAddress: 'master01' , serverPath: '/opt/nfs/maven'),
    ],
)
{
    node("jenkins-slave"){
        // 第1步
        stage('Step 1: Pulling code from Github'){
            checkout([$class: 'GitSCM', branches: [[name: "${branch}"]], userRemoteConfigs: [[credentialsId: "${git_auth}", url: "${git_address}"]]])
        }
        // 第2步
        stage('Step 2: Code checking by Sonarqube'){
            script {
                //引入SonarQube Scanner工具
                scannerHome = tool 'sonarqube-scanner'
            }
            //引入SonarQube Server的服务器环境
            withSonarQubeEnv('sonarqube-server') {
                def selectedProjects = "${project_name}".split(',')
                for(int i=0;i<selectedProjects.size();i++){
                    def currentProject = selectedProjects[i];
                    def currentProjectName = currentProject.split('@')[0]
                    def currentProjectPort = currentProject.split('@')[1]

                    def parentProjectNames = currentProjectName.split('-')
                    def projectServerPath = ""
                    if(parentProjectNames.size() > 2){
                        projectServerPath = "${projectRootNames}/${parentProjectNames[0]}-${parentProjectNames[1]}/${currentProjectName}"
                    }
                     else{
                        projectServerPath = "${projectRootNames}/${currentProjectName}"
                    }
                    sh """
                         cd ${projectServerPath}
                         ${scannerHome}/bin/sonar-scanner
                    """
                    if(parentProjectNames.size() > 2){
                        sh """
                            cd ..
                            cd ..
                        """
                    }
                     else{
                        sh "cd .."
                    }
                }
            }
        }
        // 第3步
        stage('Step 3: Building common tools'){
            //编译并安装公共工程
            sh "mvn -f pd-tools clean install"
        }
        // 第4步
        stage('Step 4: Building images and deploying project'){
            //编译安装所有pd-apps为服务
            sh "mvn -f pd-parent clean install"
            sh "mvn -f ${projectRootNames} clean install"
            //把选择的项目信息转为数组
            def selectedProjects = "${project_name}".split(',')
            for(int i=0;i<selectedProjects.size();i++){
                //取出每个项目的名称和端口
                def currentProject = selectedProjects[i];
                //项目名称
                def currentProjectName = currentProject.split('@')[0]
                //项目启动端口
                def currentProjectPort = currentProject.split('@')[1]
                //根据项目结构取出需要编译的微服务路径
                //编译微服务的上级项目和所需要的实体类项目
                def parentProjectNames = currentProjectName.split('-')
                def projectServerPath = ""
                if(parentProjectNames.size() > 2){
                    projectServerPath = "${projectRootNames}/${parentProjectNames[0]}-${parentProjectNames[1]}/${currentProjectName}"
                }
                 else{
                    projectServerPath = "${projectRootNames}/${currentProjectName}"
                }
                //定义镜像名称
                def imageName = "${currentProjectName}:${tag}"
                //编译，构建本地镜像
                sh """
                    mvn -f ${projectServerPath} clean package dockerfile:build
                """
                container('docker') {
                    //给镜像打标签
                    sh "docker tag ${dockerImagePrefix}/${imageName} ${docker_hub_username}/${imageName}"
                    //登录docker_hub，并上传镜像
                    withCredentials([usernamePassword(credentialsId: "${docker_hub_auth}", passwordVariable: 'password', usernameVariable: 'username')]){
                        //登录
                        sh "docker login -u ${username} -p ${password}"
                        //上传镜像
                        sh "docker push ${docker_hub_username}/${imageName}"
                    }
                    //删除本地镜像
                    sh "docker rmi -f ${dockerImagePrefix}/${imageName}"
                    sh "docker rmi -f ${docker_hub_username}/${imageName}"
                }
                 //部署到K8S
                def deploy_image_name = "${docker_hub_username}/${imageName}"
                sh """
                    sed -i 's#\$IMAGE_NAME#${deploy_image_name}#' ${projectServerPath}/deploy.yml
                    sed -i 's#\$SECRET_NAME#${secret_name}#' ${projectServerPath}/deploy.yml
                """
                //cat ${currentProjectName}/deploy.yml
                //ls /usr/local/apache-maven/repo
                kubernetesDeploy(kubeconfigId: "${k8s_auth}",
                                 configs: "${projectServerPath}/deploy.yml",
                )
            }
        }
        // 第5步
        stage ('Step 5: Sending Email about deploying result'){
            emailext(
                subject: 'Deploying notification: ${PROJECT_NAME} - Build # ${BUILD_NUMBER} - ${BUILD_STATUS}!',
                body: '${FILE,path="email.html"}',
                to: 'senecacbbs@gmail.com'
            )
        }

    }
}