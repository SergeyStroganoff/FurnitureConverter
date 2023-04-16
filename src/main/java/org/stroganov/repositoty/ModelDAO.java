package org.stroganov.repositoty;

import org.stroganov.entities.Model;

public interface ModelDAO {
    Model getByArticle(String article);
}
