package org.stroganov.actions;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.stroganov.utils.ExelFileReader;

@Component
@Getter
@Setter
@NoArgsConstructor
public class CatalogItemSaveActionImpl implements CatalogItemSaveAction {


    @Override
    public void saveAlineItemsToDB(String sourceFileName, String sheetSourceName) {

    }
}
