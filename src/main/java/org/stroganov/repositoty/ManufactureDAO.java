package org.stroganov.repositoty;

import org.stroganov.entities.Manufacture;

public interface ManufactureDAO {
    Manufacture getByName(String name);
}
