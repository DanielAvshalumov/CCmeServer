package com.CCMe.Emails;
import java.util.List;
import org.jobrunr.jobs.lambdas.JobRequest;
import org.jobrunr.jobs.lambdas.JobRequestHandler;

import com.CCMe.Model.Job;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SendJobAfterCreationEmail implements JobRequest{
    
    private List<String> emails;
    private Long jobId;
    private String map;
    private String company;
    private String description;

    @SuppressWarnings("rawtypes")
    @Override
    public Class<? extends JobRequestHandler> getJobRequestHandler() {
        return SendJobAfterCreationEmailHandler.class;
    }
}
