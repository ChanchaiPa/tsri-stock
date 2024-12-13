pipeline {
    agent any 
    stages {
        //******************
        stage('front-end') {
            steps {
                dir('tsri-stock-ui') {
                    git branch: 'main', url: 'https://github.com/ChanchaiPa/tsri-stock-ui.git'
                    echo '==> tsri-stock-ui clone'
                    script {
                        //def env = System.getenv()   //println("...."  + env['JENKINS_HOME'])
                        //String fileLocation = env['JENKINS_HOME'] + '\\workspace\\demo-github-win\\tsri-stock-ui\\src\\Environment.tsx'
                        String fileLocation = env.WORKSPACE + "\\tsri-stock-ui\\src\\Environment.tsx"
                        String fileContents = ""
                        def lines = new File( fileLocation  ).readLines()
                        for(line in lines){
                            if (line.length()>16 && line.contains("export") && line.contains("const") && line.contains("env"))
                                line = "export const env = env_config.UAT;"   
                            fileContents += line + "\n"          
                        }
                        new File( fileLocation ).write(fileContents.trim())
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
                        //def env = System.getenv()   
                        //String fileLocation = env['JENKINS_HOME'] + '\\workspace\\demo-github-win\\tsri-stock\\src\\main\\resources\\application.properties'
                        String fileLocation = env.WORKSPACE + "\\tsri-stock\\src\\main\\resources\\application.properties"
                        String fileContents = ""
                        def lines = new File( fileLocation  ).readLines()
                        for(line in lines){
                            if (line.length()>23 && line.contains("spring.profiles.active") )
                                line = "spring.profiles.active=uat"  
                            fileContents += line + "\n"          
                        }
                        new File( fileLocation ).write(fileContents.trim())
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
}