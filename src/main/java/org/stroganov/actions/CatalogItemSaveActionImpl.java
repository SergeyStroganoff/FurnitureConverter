package org.stroganov.actions;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
public class CatalogItemSaveActionImpl implements CatalogItemSaveAction {

    @Override
    public int saveAlineItemsToDB(String sourceFileName, String sheetSourceName) {
        int savedEntitiesCount = 0;
        return savedEntitiesCount;
    }
}
