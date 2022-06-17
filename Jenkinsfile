pipeline { 
agent any 
    stages { 
        
        stage ('Build') { 
            steps{
                echo "Building"

            }
        }
        
         stage ('Deploy in Dev') { 
            steps{
                echo "Build is deployed in Dev"

            }
        }
        
          stage ('Deploy in QA') { 
            steps{
                echo "Build is deployed in QA"

            }
        }
        
          stage ('Smoke Test') { 
            steps{
                echo "Smoke Test carried"

            }
        }
  
        stage('Regression Test') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    bat "mvn clean install"
                }
            }
        }
                
        stage('Publish Allure Reports') {
           steps {
                script {
                    allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: '/allure-results']]
                    ])
                }
            }
        }
        
        
        stage('Publish Extent Report'){
            steps{
                     publishHTML([allowMissing: false,
                                  alwaysLinkToLastBuild: false, 
                                  keepAll: false, 
                                  reportDir: 'build', 
                                  reportFiles: 'TestExecutionReport.html', 
                                  reportName: 'HTML Extent Report', 
                                  reportTitles: ''])
            }
        }
        
        
        
    }

 }