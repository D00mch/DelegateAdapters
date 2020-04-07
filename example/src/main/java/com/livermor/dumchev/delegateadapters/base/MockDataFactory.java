package com.livermor.dumchev.delegateadapters.base;

import com.livermor.delegateadapter.delegate.diff.DiffUtilItem;
import com.livermor.dumchev.delegateadapters.R;
import com.livermor.dumchev.delegateadapters.base.model.CheckItem;
import com.livermor.dumchev.delegateadapters.base.model.ImageItem;
import com.livermor.dumchev.delegateadapters.base.model.TextItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author dumchev on 05.11.17.
 */
class MockDataFactory {

    private static final int SIZE = 20;

    static List<DiffUtilItem> prepareData() {
        ArrayList<DiffUtilItem> objects = new ArrayList<>(SIZE);
        Random random = new Random();
        for (int i = 0; i < SIZE; i++) {
            DiffUtilItem item;
            int type = random.nextInt(3);
            if (type == 0) {
                item = new TextItem("Title " + i, "Description " + i);
            } else if (type == 1) {
                item = new ImageItem("Title " + i, R.mipmap.ic_launcher_round);
            } else {
                item = new CheckItem("You still love this lib", true);
            }
            objects.add(item);
        }
        return objects;
    }
}
