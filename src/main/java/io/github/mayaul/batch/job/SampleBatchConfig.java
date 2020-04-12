package io.github.mayaul.batch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SampleBatchConfig {
    public static final String JOB_EXECUTION_CONTEXT_KEY = "jobExecutionContextKey";
    public static final String STEP_EXECUTION_CONTEXT_KEY = "stepExecutionContextKey";

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    public SampleBatchConfig(JobBuilderFactory jobBuilderFactory,
                             StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Job sampleJob() {
        return jobBuilderFactory.get("sample job")
                .incrementer(new RunIdIncrementer())
                .preventRestart()
                .listener(myJobListener())
                .start(sampleStep())
                .build();
    }

    @Bean
    @JobScope
    public JobExecutionListener myJobListener() {
        return new MyJobListener();
    }

    @Bean
    @JobScope
    public Step sampleStep() {
        return stepBuilderFactory.get("sample step")
                .listener(myStepListener())
                .tasklet((contribution, chunkContext) -> RepeatStatus.FINISHED)
                .transactionManager(new ResourcelessTransactionManager())
                .build();

    }

    @Bean
    @StepScope
    public StepExecutionListener myStepListener() {
        return new MyStepListener();
    }
}
