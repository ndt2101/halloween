package com.vti.halloween.service;

import com.vti.halloween.exception.ApplicationException;

public interface GameService {
    Integer play() throws ApplicationException;
    String reset(String account) throws ApplicationException;
}
