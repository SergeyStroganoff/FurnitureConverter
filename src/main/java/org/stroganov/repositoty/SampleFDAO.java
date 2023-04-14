package org.stroganov.repositoty;

import org.stroganov.entities.SampleF;

public interface SampleFDAO {
    SampleF getByArticle(String article);
}
