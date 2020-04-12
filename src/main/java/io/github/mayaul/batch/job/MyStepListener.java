package io.github.mayaul.batch.job;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

import java.util.ArrayList;

public class MyStepListener implements StepExecutionListener {
    @Override
    public void beforeStep(StepExecution stepExecution) {
        ArrayList<TestEnum> values = new ArrayList<>();
        values.add(TestEnum.A);
        values.add(TestEnum.B);
        values.add(TestEnum.C);

        stepExecution.getExecutionContext().put(SampleBatchConfig.STEP_EXECUTION_CONTEXT_KEY, values);
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }
}
