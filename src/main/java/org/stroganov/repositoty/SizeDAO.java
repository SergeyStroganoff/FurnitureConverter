package org.stroganov.repositoty;

import org.stroganov.entities.Size;

public interface SizeDAO {
    Size getSizeBy(String with, String height, String depth);
}
