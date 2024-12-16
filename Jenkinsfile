pipeline {
    agent any 
    environment {
        LIBMODULE = 'Jenkins-lib.groovy'	//'D:\\Program\\JenkinsHome\\groovy-4.0.24\\workspace\\demo-editfile\\jenkins-lib.groovy
    }    
    
    stages {
        //******************
        stage('front-end') {
            steps {
                dir('tsri-stock-ui') {
                    git branch: 'main', url: 'https://github.com/ChanchaiPa/tsri-stock-ui.git'
                    echo '==> tsri-stock-ui clone'
                    script {
                        //def env = System.getenv()   //println("...."  + env['JENKINS_HOME'])
                        def _libModule = load "${LIBMODULE}"
                        String environmentFile = env.WORKSPACE + "\\tsri-stock-ui\\src\\Environment.tsx"
                         _libModule._setModeFrontEnd(environmentFile)
                        echo '==> tsri-stock-ui change mode'
                    }
                    bat("npm install")
		            echo '==> npm install'
		            script {
		                try { 
		                    bat("npm run build") 
		                    echo '==> npm run build'
		                }
		                catch(err) { echo '==> npm run build with problem' }
                    }
                }                
            }
        }
        //*****************
        stage('back-end') {
            steps {
                dir('tsri-stock') {
                    git branch: 'main', url: 'https://github.com/ChanchaiPa/tsri-stock.git'
                    echo '==> tsri-stock clone'
                    bat("xcopy /s/e ${WORKSPACE}\\tsri-stock-ui\\build ${WORKSPACE}\\tsri-stock\\src\\main\\resources\\static")
                    echo '==> tsri-stock-ui copying'
                    script {
                        def _libModule = load "${LIBMODULE}"
                        String applicationFile = env.WORKSPACE + "\\tsri-stock\\src\\main\\resources\\application.properties"
                        _libModule._setModeBackEnd(applicationFile)
                        echo '==> tsri-stock change mode'
                    }               
                    bat("mvn clean package -Dmaven.test.skip=true") //-Dspring.profiles.active=uat
		            echo '==> mvn clean package'
                }                   
            }
        }
        //*****************
        stage('clear') {
            steps {
                bat("xcopy ${WORKSPACE}\\tsri-stock\\target\\*.war D:\\usr /y")
                bat("if exist ${WORKSPACE}\\tsri-stock-ui rmdir /s/q ${WORKSPACE}\\tsri-stock-ui")
                bat("if exist ${WORKSPACE}\\tsri-stock rmdir /s/q ${WORKSPACE}\\tsri-stock")
            }
        }
    }
    post {
        success {
            cleanWs()
        }
    }
}