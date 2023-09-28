package com.tenpo.challenge.api.dto;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;


@Entity
@Table(name = "log")
@Setter
@Getter
public class Log {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "response", nullable = false)
    private String response;

    @CreationTimestamp
    @Column(name = "date_created")
    Date dateCreated;

}