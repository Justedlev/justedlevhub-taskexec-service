package com.justedlev.taskexec.component.impl;

import com.justedlev.taskexec.component.TaskComponent;
import com.justedlev.taskexec.model.response.TaskResponse;
import com.justedlev.taskexec.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Slf4j
@Component
@RequiredArgsConstructor
public class TaskComponentImpl implements TaskComponent {
    private final TaskRepository taskRepository;
    private final ModelMapper defaultMapper;

    @Override
    public Optional<TaskResponse> getByName(String taskName) {
        return Optional.ofNullable(taskName)
                .filter(StringUtils::isNotEmpty)
                .map(List::of)
                .map(taskRepository::findByTaskNameIn)
                .map(Collection::stream)
                .flatMap(Stream::findFirst)
                .map(current -> defaultMapper.map(current, TaskResponse.class));
    }

    @Override
    public List<TaskResponse> getAll() {
        var tasks = taskRepository.findAll();

        return List.of(defaultMapper.map(tasks, TaskResponse[].class));
    }
}
