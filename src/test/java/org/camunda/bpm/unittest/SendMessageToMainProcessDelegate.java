package org.camunda.bpm.unittest;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class SendMessageToMainProcessDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		execution.getProcessEngineServices().getRuntimeService().createMessageCorrelation("finished")
				.processInstanceId(execution.getProcessInstanceId()).correlate();
	}

}
