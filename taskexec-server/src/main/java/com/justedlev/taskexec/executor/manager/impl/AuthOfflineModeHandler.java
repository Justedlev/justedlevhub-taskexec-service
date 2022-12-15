package com.justedlev.taskexec.executor.manager.impl;

import com.justedlev.auth.client.AuthFeignClient;
import com.justedlev.taskexec.executor.manager.AbstractTaskExecutor;
import com.justedlev.taskexec.executor.model.TaskContext;
import com.justedlev.taskexec.executor.model.TaskResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthOfflineModeHandler extends AbstractTaskExecutor {
    private final AuthFeignClient authFeignClient;

    @Override
    protected TaskResultResponse assign(TaskContext context) {
        authFeignClient.offline();

        return TaskResultResponse.builder()
                .taskName(this.getTaskName())
                .build();
    }

    @Override
    public String getTaskName() {
        return "auth-offline-mode";
    }
}
