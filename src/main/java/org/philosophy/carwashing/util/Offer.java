package org.philosophy.carwashing.util;

import lombok.Getter;
import lombok.Setter;
import org.philosophy.carwashing.model.Box;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@Setter
public class Offer {

    LocalDateTime timeFrom;
    LocalDateTime timeTo;
    Duration duration;
    Box box;

}
