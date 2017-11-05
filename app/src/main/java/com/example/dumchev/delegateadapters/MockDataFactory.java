package com.example.dumchev.delegateadapters;

import com.example.dumchev.delegateadapters.model.CheckViewModel;
import com.example.dumchev.delegateadapters.model.IViewModel;
import com.example.dumchev.delegateadapters.model.ImageViewModel;
import com.example.dumchev.delegateadapters.model.TextViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author dumchev on 05.11.17.
 */

public class MockDataFactory {
    public static List<IViewModel> prepareData() {
        ArrayList<IViewModel> objects = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            IViewModel item;
            int type = random.nextInt(3);
            if (type == 0) {
                item = new TextViewModel("Title " + i, "Description " + i);
            } else if (type == 1) {
                item = new ImageViewModel("Title " + i, R.mipmap.ic_launcher_round);
            } else {
                item = new CheckViewModel("You still love this app", true);
            }
            objects.add(item);
        }
        return objects;
    }
}
