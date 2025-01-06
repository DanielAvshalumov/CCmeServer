package com.CCMe.Emails;
import java.util.List;
import org.jobrunr.jobs.lambdas.JobRequest;
import org.jobrunr.jobs.lambdas.JobRequestHandler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SendJobAfterCreationEmail implements JobRequest{
    
    private List<String> emails;

    @SuppressWarnings("rawtypes")
    @Override
    public Class<? extends JobRequestHandler> getJobRequestHandler() {
        return SendJobAfterCreationEmailHandler.class;
    }
}
