package com.otters.admissionsbackend.mapper;

import com.otters.admissionsbackend.dto.*;
import com.otters.admissionsbackend.model.*;
import com.otters.admissionsbackend.model.paper.Paper;
import com.otters.admissionsbackend.model.paper.PaperContainers;
import org.mapstruct.*;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface DTOtoEntityMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateBenchMarkFromDto(BenchMarkDTO dto, @MappingTarget BenchMark entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateClassFromDto(ClassDTO dto, @MappingTarget MajorClass entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateExamFromDto(ExamDTO dto, @MappingTarget Exam entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateExamRoomFromDto(ExamRoomDTO dto, @MappingTarget ExamRoom entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateExamRoomDetailsFromDto(ExamRoomDetailDTO dto, @MappingTarget ExamRoomDetails entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateNewsFromDto(NewsDTO dto, @MappingTarget News entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateNotificationFromDto(NotificationDTO dto, @MappingTarget Notification entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePaperContainersFromDto(PaperContainerDTO dto, @MappingTarget PaperContainers entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePaperFromDto(PaperDTO dto, @MappingTarget Paper entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePostFromDto(PostDTO dto, @MappingTarget Post entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProfileFromDto(ProfileDTO dto, @MappingTarget Profile entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateRegistrationsFromDto(RegistrationsDTO dto, @MappingTarget Registrations entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateRoomFromDto(RoomDTO dto, @MappingTarget Room room);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateStRegistrationDetailsFromDto(StRegistrationDetailsDTO dto, @MappingTarget StRegistrationDetails entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateStudentFromDto(StudentDTO dto, @MappingTarget Student entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateSubjectFromDto(SubjectDTO dto, @MappingTarget Subject entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateSubjectSetFromDto(SubjectSetDTO dto, @MappingTarget SubjectSet entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateTimeToChangeFromDto(TimeToChangeDTO dto, @MappingTarget TimeToChange entity);
}
