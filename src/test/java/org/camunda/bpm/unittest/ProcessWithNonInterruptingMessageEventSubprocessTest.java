package org.camunda.bpm.unittest;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.assertThat;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.managementService;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.runtimeService;
import static org.hamcrest.CoreMatchers.is;

import java.util.List;

import org.camunda.bpm.engine.runtime.Job;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;



public class ProcessWithNonInterruptingMessageEventSubprocessTest {

	@Rule
	public ProcessEngineRule rule = new ProcessEngineRule();
	
	
	@Test
	@Deployment(resources={"ProcessWithMessageEventSubprocess.bpmn"})
	public void test(){
		ProcessInstance processInstance = runtimeService().startProcessInstanceByKey("processWithMessageEventSubprocess");
		
		executeAsyncTask("task1");
		executeAsyncTask("sendStartMessageToSubprocess");
		executeAsyncTask("subprocessTask");
		executeAsyncTask("task2");
		assertThat(processInstance).isEnded();		
	}

	private void executeAsyncTask(String activityId){
		
		List<Job> jobs = managementService().createJobQuery().active().activityId(activityId).list();
		Assert.assertThat(jobs.size(), is(1));
		
		managementService().executeJob(jobs.get(0).getId());
	}
}
