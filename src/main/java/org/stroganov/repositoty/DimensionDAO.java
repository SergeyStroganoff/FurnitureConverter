package org.stroganov.repositoty;

import org.stroganov.entities.Dimension;

public interface DimensionDAO {
    Dimension findDimensionByDimensions(String with, String height, String depth);
}
