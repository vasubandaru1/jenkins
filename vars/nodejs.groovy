def call(Map params = [:]) {
    //Start Default Arguments
    def args = [
            COMPONENT: '',
            LABEL    : 'work'

    ]
    args << params

    pipeline {
        agent {
            label params.LABEL
        }

        stages {

            stage("Labiling Build") {
                steps {
                        addShortText background: 'yellow', color: 'black', bordercolor: 'yellow', text: "COMPONENT = ${params.COMPONENT}"
//                        addShortText background: 'yellow', color: 'black', bordercolor: 'yellow', text: "BRANCH = ${str}"
//                        addShortText background: 'yellow', color: 'black', bordercolor: 'yellow', text: "${ENV}"
//
//                    }
//                }
//            }

                        stage("COMPILE") {
                            steps {
                                sh "echo COMPILE"
                            }
                        }

                        stage("CODE QUALITY") {
                            steps {
                                sh "echo CODE QUALITY"
                                sh "echo COMPONENT = ${params.COMPONENT}"
                            }
                        }

                        stage("TEST CASES") {
                            steps {
                                sh "echo TEST CASES"
                            }
                        }


                        stage("ARTIFACTORY") {
                            when {
                                expression { sh([returnStdout: true, script: 'echo ${GIT_BRANCH} | grep tags || true']) }
                            }
                            steps {
                                sh "echo TEST CASES"
                                sh "env"
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
        }
    }
}