package org.stroganov.repositoty;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public abstract class ItemDAO {
    public static final String INPUT_PARAMETERS_CANNOT_BE_NULL = "Input parameters cannot be null.";
    public static final Logger LOGGER = LogManager.getLogger(ItemDAO.class);
    public static final String ERROR_MESSAGE_FOR_QUERY = "Error while executing query";
}
