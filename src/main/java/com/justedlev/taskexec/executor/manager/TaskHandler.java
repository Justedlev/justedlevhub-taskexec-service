package com.justedlev.taskexec.executor.manager;

import com.justedlevhub.api.model.response.TaskResultResponse;

public interface TaskHandler<P> extends Runnable {
    TaskResultResponse execute();

    String taskName();

    P payload();
}
