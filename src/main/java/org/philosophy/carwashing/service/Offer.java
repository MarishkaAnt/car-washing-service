package org.philosophy.carwashing.service;

import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@Setter
public class Offer {

    LocalDateTime timeFrom;
    LocalDateTime timeTo;
    Duration duration;

}