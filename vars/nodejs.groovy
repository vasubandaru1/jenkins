def call(String COMPONENT) {

    pipeline {
        agent any

        stages {

            stage("COMPILE") {
                steps {
                    sh "echo COMPILE"
                }
            }

            stage("CODE QUALITY") {
                steps {
                    sh "echo CODE QUALITY"
                    sh "echo COMPONENT = ${COMPONENT}"
                }
            }

            stage("TEST CASES") {
                steps {
                    sh "echo TEST CASES"
                }
            }


        }
    }
}