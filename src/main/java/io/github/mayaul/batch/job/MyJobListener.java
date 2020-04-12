package io.github.mayaul.batch.job;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

import java.util.ArrayList;

public class MyJobListener implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        ArrayList<TestEnum> hellos = new ArrayList<>();
        hellos.add(TestEnum.A);
        hellos.add(TestEnum.B);
        hellos.add(TestEnum.C);

        jobExecution.getExecutionContext().put(SampleBatchConfig.JOB_EXECUTION_CONTEXT_KEY, hellos);
    }

    @Override
    public void afterJob(JobExecution jobExecution) {

    }
}
