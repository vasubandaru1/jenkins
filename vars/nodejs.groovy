def call(Map params = [:]) {

    def args= [
            COMPONENT      : '',
            LABEL          : 'work'

    ]

    args << params


    pipeline {
        agent {
            label params.LABEL
        }

        stages {

            stage('Labeling Build') {
                steps {
                    script {
                        str = GIT_BRANCH.split('/').last()
                        addShortText background: 'yellow', color: 'black', borderColor: 'yellow', text: "COMPONENT = ${params.COMPONENT}"
                        addShortText background: 'yellow', color: 'black', borderColor: 'yellow', text: "BRANCH = ${str}"
                    }
                }
            }


            stage('compile') {
                steps {
                    sh "echo COMPONENT =  ${params.COMPONENT}"

                }
            }

            stage('code quality') {
                steps {
                    sh 'echo  code quality'

                }
            }

            stage('Test cases') {
                steps {
                    sh 'echo  test cases'
                    sh 'echo env'

                }
            }

            stage('Upload Artifacts') {
                when {
                    expression {GIT_BRANCH == "*/tags/*" }
                }
                steps {
                    sh 'echo  test cases'


                }
            }


    }

            post {
                always {
                    cleanWs()
                }
            }
        }
}

//sh([returnStdout: true, script: 'echo ${GIT_BRANCH} | grep tags || true' ])



