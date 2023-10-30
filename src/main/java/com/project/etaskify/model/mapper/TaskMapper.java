package com.project.etaskify.model.mapper;

import com.project.etaskify.model.dto.request.CreateTaskRequest;
import com.project.etaskify.model.dto.request.UpdateTaskRequest;
import com.project.etaskify.model.dto.response.TaskResponse;
import com.project.etaskify.model.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    @Mapping(target = "status", source = "statusAsEnum")
    TaskResponse toTaskResponse(Task task);

    List<TaskResponse> toTaskResponses(List<Task> tasks);

    Task toTask(CreateTaskRequest request);
    Task toTask(UpdateTaskRequest request);

}
