package no.ueland.onionCrawler.enums.search;

import no.ueland.onionCrawler.objects.exception.OnionCrawlerException;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexableField;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TorHenning on 19.08.2015.
 */
public enum SearchField {
    URL(Field.Store.YES, SearchFieldType.String),
    Hostname(Field.Store.YES, SearchFieldType.String),
    PageTitle(Field.Store.YES, SearchFieldType.Text),
    PageContent(Field.Store.NO, SearchFieldType.Text),;
    private final Field.Store storeField;
    private final SearchFieldType searchFieldType;

    SearchField(Field.Store storeField, SearchFieldType sft) {
        this.storeField = storeField;
        this.searchFieldType = sft;
    }

    public static List<SearchField> getSearchFieldsByType(SearchFieldType type) {
        List<SearchField> res = new ArrayList<>();
        for (SearchField sf : values()) {
            if (sf.getSearchFieldType() == type) {
                res.add(sf);
            }
        }
        return res;
    }

    public Field.Store getStoreField() {
        return storeField;
    }

    public SearchFieldType getSearchFieldType() {
        return searchFieldType;
    }

    public IndexableField getField(Object fieldValue) throws OnionCrawlerException {
        if (getSearchFieldType() == SearchFieldType.String) {
            return new StringField(name(), ((String) fieldValue).toLowerCase(), getStoreField());
        } else if (getSearchFieldType() == SearchFieldType.Int) {
            return new IntField(name(), (Integer) fieldValue, getStoreField());
        } else if (getSearchFieldType() == SearchFieldType.Text) {
            return new TextField(name(), ((String) fieldValue).toLowerCase(), getStoreField());
        } else {
            throw new OnionCrawlerException("SearchFieldType not implemented in getField");
        }
    }
}