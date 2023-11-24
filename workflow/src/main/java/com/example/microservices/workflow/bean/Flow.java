package com.example.microservices.workflow.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Flow extends AbstractEntity{
    @Id
    @Column(columnDefinition = "VARCHAR(255)")
    private String id;
    private String state;

    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'hh:mm:ss'Z'")
    private LocalDateTime dateTime;


    @PrePersist
    public void ensureId(){
        id = UUID.randomUUID().toString();
    }

    public String getId() {
        return UUID.fromString(id).toString();
    }

    public void setId(String uuid) {
        this.id = uuid.toString();
    }

    @Transient
    String event;

    @Transient
    String dataType;
}
