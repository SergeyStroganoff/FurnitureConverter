package org.stroganov.repositoty;

import org.stroganov.entities.Size;

public interface SizeDAO {
    Size findSizeByDimensions(String with, String height, String depth);
}
