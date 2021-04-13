package com.example.vote.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class option implements Serializable {
    Integer Id;
    String description;
    Integer poll;
    Integer aId;
}
