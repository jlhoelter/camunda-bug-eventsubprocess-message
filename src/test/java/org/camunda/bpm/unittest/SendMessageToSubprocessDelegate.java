package org.camunda.bpm.unittest;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class SendMessageToSubprocessDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		execution.getProcessEngineServices().getRuntimeService().createMessageCorrelation("start")
				.processInstanceId(execution.getProcessInstanceId()).correlate();
	}

}
