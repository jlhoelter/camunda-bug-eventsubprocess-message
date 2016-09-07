# Bug: non interruption message event subprocess

This test is based on the template provided on https://github.com/camunda/camunda-engine-unittest

## Process
![process](https://github.com/jlhoelter/camunda-bug-eventsubprocess-message/blob/master/src/test/resources/ProcessWithMessageEventSubprocess.png)

## Error Description
This test reffers to the Bugticket [CAM-3802](https://app.camunda.com/jira/browse/CAM-3802).

When running the test a `NullPointerException` occurs during the message correlation from the intermediat message throw event *Start Subprocess* to the non-interrupting start event *Start Subprocess* in the subprocess.
