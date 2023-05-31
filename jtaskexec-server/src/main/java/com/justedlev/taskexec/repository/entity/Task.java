package com.justedlev.taskexec.repository.entity;

import com.justedlev.taskexec.enumeration.TaskMode;
import com.justedlev.taskexec.repository.entity.base.BaseEntity;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tasks")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Task extends BaseEntity {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "cron")
    private String cron;
    @Builder.Default
    @Column(name = "mode")
    @Enumerated(EnumType.STRING)
    private TaskMode mode = TaskMode.NONE;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Task task = (Task) o;
        return id != null && Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
