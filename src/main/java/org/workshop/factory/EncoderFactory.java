package org.workshop.factory;

import org.workshop.starter.objects.Algorithm;
import org.workshop.starter.objects.Message;
import org.workshop.service.BCryptEncoder;
import org.workshop.service.IServiceEncoder;
import org.workshop.service.MD5Encoder;

import java.util.Optional;

public class EncoderFactory {

    public static Optional<IServiceEncoder> getInstance(Message message) {
        if (Algorithm.BCRYPT.equals(message.getAlgorithm()))
            return Optional.of(new BCryptEncoder());

        if (Algorithm.MD5.equals(message.getAlgorithm()))
            return Optional.of(new MD5Encoder());

        return Optional.empty();
    }

}