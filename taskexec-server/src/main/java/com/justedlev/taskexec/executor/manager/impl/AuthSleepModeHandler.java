package com.justedlev.taskexec.executor.manager.impl;

import com.justedlev.auth.client.AuthFeignClient;
import com.justedlev.auth.enumeration.ModeType;
import com.justedlev.auth.model.request.UpdateAccountModeRequest;
import com.justedlev.taskexec.executor.manager.AbstractTaskExecutor;
import com.justedlev.taskexec.executor.model.TaskContext;
import com.justedlev.taskexec.executor.model.TaskResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class AuthSleepModeHandler extends AbstractTaskExecutor {
    private final AuthFeignClient authFeignClient;

    @Override
    protected TaskResultResponse assign(TaskContext context) {
        var request = UpdateAccountModeRequest.builder()
                .fromModes(Set.of(ModeType.HIDDEN, ModeType.ONLINE))
                .toMode(ModeType.SLEEP)
                .build();
        var res = authFeignClient.updateMode(request);

        return TaskResultResponse.builder()
                .taskName(this.getTaskName())
                .message(String.format("Updated %s accounts to mode %s", res.size(), ModeType.SLEEP))
                .build();
    }

    @Override
    public String getTaskName() {
        return "auth-sleep-mode";
    }
}
